package com.esmooc.legion.file.controller;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.CommonUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.entity.FileCategory;
import com.esmooc.legion.file.service.FileCategoryService;
import com.esmooc.legion.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "文件分类管理接口")
@RequestMapping("/legion/fileCategory")
@Transactional
public class FileCategoryController {

    @Autowired
    private FileCategoryService fileCategoryService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<List<FileCategory>> getByParentId(@PathVariable String parentId) {

        User user = securityUtil.getCurrUserSimple();
        List<FileCategory> list = fileCategoryService.findByParentIdAndCreateBy(parentId, user.getUsername());
        setInfo(list);
        return  ResultUtil.data(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result<Object> add(FileCategory fileCategory) {

        fileCategoryService.save(fileCategory);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if (!CommonConstant.PARENT_ID.equals(fileCategory.getParentId())) {
            FileCategory parent = fileCategoryService.get(fileCategory.getParentId());
            if (parent.getIsParent() == null || !parent.getIsParent()) {
                parent.setIsParent(true);
                fileCategoryService.update(parent);
            }
        }
        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Object> edit(FileCategory fileCategory) {

        if (fileCategory.getId().equals(fileCategory.getParentId())) {
            return ResultUtil.error("上级节点不能为自己");
        }
        User user = securityUtil.getCurrUserSimple();
        FileCategory old = fileCategoryService.get(fileCategory.getId());
        if (!user.getUsername().equals(old.getCreateBy())) {
            return ResultUtil.error("你无权编辑非本人文件");
        }
        String oldParentId = old.getParentId();
        fileCategoryService.update(fileCategory);
        // 如果该节点不是一级节点 且修改了级别 判断上级还有无子节点
        if (!CommonConstant.PARENT_ID.equals(oldParentId) && !oldParentId.equals(fileCategory.getParentId())) {
            FileCategory parent = fileCategoryService.get(oldParentId);
            List<FileCategory> children = fileCategoryService.findByParentIdAndCreateBy(parent.getId(), user.getUsername());
            if (parent != null && (children == null || children.isEmpty())) {
                parent.setIsParent(false);
                fileCategoryService.update(parent);
            }
        }
        return ResultUtil.success("编辑成功");
    }

    @RequestMapping(value = "/moveByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量修改分类（移动）")
    public Result<Object> moveByIds(@RequestParam String[] ids,
                                    @RequestParam String categoryId) {

        User user = securityUtil.getCurrUserSimple();
        if(!CommonConstant.PARENT_ID.equals(categoryId)) {
            FileCategory fileCategory = fileCategoryService.get(categoryId);
            if (!user.getUsername().equals(fileCategory.getCreateBy())) {
                return ResultUtil.error("你无权移动至该文件夹");
            }
        }
        for (String id : ids) {
            File file = fileService.get(id);
            if (categoryId.equals(file.getCategoryId())) {
                // 分类没变化 无需移动
                continue;
            }
            if (!user.getUsername().equals(file.getCreateBy())) {
                return ResultUtil.error("你无权编辑非本人文件");
            }
            file.setCategoryId(categoryId);
            fileService.save(file);
            redisTemplate.delete("file::" + id);
        }
        return ResultUtil.success("批量移动文件成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        User user = securityUtil.getCurrUserSimple();
        for (String id : ids) {
            deleteRecursion(id, ids, user.getUsername());
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }

    public void deleteRecursion(String id, String[] ids, String username) {

        // 获得其父节点
        FileCategory o = fileCategoryService.get(id);
        if (username.equals(o.getCreateBy())) {
            throw new LegionException("你无权删除非本人文件");
        }
        FileCategory parent = null;
        if (StrUtil.isNotBlank(o.getParentId())) {
            parent = fileCategoryService.findById(o.getParentId());
        }
        fileCategoryService.delete(id);
        fileService.deleteByCategoryId(id);
        // 判断父节点是否还有子节点
        if (parent != null) {
            List<FileCategory> children = fileCategoryService.findByParentIdAndCreateBy(parent.getId(), username);
            if (children == null || children.isEmpty()) {
                parent.setIsParent(false);
                fileCategoryService.update(parent);
            }
        }
        // 递归删除
        List<FileCategory> objs = fileCategoryService.findByParentIdAndCreateBy(id, username);
        for (FileCategory obj : objs) {
            if (!CommonUtil.judgeIds(obj.getId(), ids)) {
                deleteRecursion(obj.getId(), ids, username);
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "名称模糊搜索")
    public Result<List<FileCategory>> searchByTitle(@RequestParam String title) {

        User user = securityUtil.getCurrUserSimple();
        List<FileCategory> list = fileCategoryService.findByTitleLikeAndCreateBy("%" + title + "%", user.getUsername());
        setInfo(list);
        return ResultUtil.data(list);
    }

    public void setInfo(List<FileCategory> list) {

        // lambda表达式
        list.forEach(item -> {
            if (!CommonConstant.PARENT_ID.equals(item.getParentId())) {
                FileCategory parent = fileCategoryService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            } else {
                item.setParentTitle("一级目录");
            }
        });
    }
}
