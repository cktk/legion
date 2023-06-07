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
import com.esmooc.legion.file.entity.FileCategory;
import com.esmooc.legion.file.entity.LegionFile;
import com.esmooc.legion.file.service.FileCategoryService;
import com.esmooc.legion.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private RedisTemplateHelper redisTemplate;

    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<List<FileCategory>> getByParentId(@PathVariable String parentId) {

        User user = SecurityUtil.getUser();
        List<FileCategory> list = fileCategoryService.findByParentIdAndCreateBy(parentId, user.getId());
        setInfo(list);
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result<Object> add(FileCategory fileCategory) {

        fileCategoryService.save(fileCategory);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if (!CommonConstant.PARENT_ID.equals(fileCategory.getParentId())) {
            FileCategory parent = fileCategoryService.getById(fileCategory.getParentId());
            if (parent.getIsParent() || !parent.getIsParent()) {
                parent.setIsParent(true);
                fileCategoryService.updateById(parent);
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
        User user = SecurityUtil.getUser();
        FileCategory old = fileCategoryService.getById(fileCategory.getId());
        if (!user.getUsername().equals(old.getCreateBy())) {
            return ResultUtil.error("你无权编辑非本人文件");
        }
        String oldParentId = old.getParentId();
        fileCategoryService.updateById(fileCategory);
        // 如果该节点不是一级节点 且修改了级别 判断上级还有无子节点
        if (!CommonConstant.PARENT_ID.equals(oldParentId) && !oldParentId.equals(fileCategory.getParentId())) {
            FileCategory parent = fileCategoryService.getById(oldParentId);
            List<FileCategory> children = fileCategoryService.findByParentIdAndCreateBy(parent.getId(), user.getId());
            if (parent != null && (children == null || children.isEmpty())) {
                parent.setIsParent(false);
                fileCategoryService.updateById(parent);
            }
        }
        return ResultUtil.success("编辑成功");
    }

    @RequestMapping(value = "/moveByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量修改分类（移动）")
    public Result<Object> moveByIds(@RequestParam String[] ids,
                                    @RequestParam String categoryId) {

        User user = SecurityUtil.getUser();
        if (!CommonConstant.PARENT_ID.equals(categoryId)) {
            FileCategory fileCategory = fileCategoryService.getById(categoryId);
            if (!user.getUsername().equals(fileCategory.getCreateBy())) {
                return ResultUtil.error("你无权移动至该文件夹");
            }
        }
        for (String id : ids) {
            LegionFile legionFile = fileService.getById(id);
            if (categoryId.equals(legionFile.getCategoryId())) {
                // 分类没变化 无需移动
                continue;
            }
            if (!user.getUsername().equals(legionFile.getCreateBy())) {
                return ResultUtil.error("你无权编辑非本人文件");
            }
            legionFile.setCategoryId(categoryId);
            fileService.save(legionFile);
            redisTemplate.delete("LegionFile::" + id);
        }
        return ResultUtil.success("批量移动文件成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        User user = SecurityUtil.getUser();
        for (String id : ids) {
            deleteRecursion(id, ids, user.getId());
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }

    public void deleteRecursion(String id, String[] ids, String userId) {

        // 获得其父节点
        FileCategory o = fileCategoryService.getById(id);
        if (userId.equals(o.getCreateId())) {
            throw new LegionException("你无权删除非本人文件");
        }
        FileCategory parent = null;
        if (StrUtil.isNotBlank(o.getParentId())) {
            parent = fileCategoryService.getById(o.getParentId());
        }
        fileCategoryService.removeById(id);
        fileService.deleteByCategoryId(id);
        // 判断父节点是否还有子节点
        if (parent != null) {
            List<FileCategory> children = fileCategoryService.findByParentIdAndCreateBy(parent.getId(), userId);
            if (children == null || children.isEmpty()) {
                parent.setIsParent(false);
                fileCategoryService.updateById(parent);
            }
        }
        // 递归删除
        List<FileCategory> objs = fileCategoryService.findByParentIdAndCreateBy(id, userId);
        for (FileCategory obj : objs) {
            if (!CommonUtil.judgeIds(obj.getId(), ids)) {
                deleteRecursion(obj.getId(), ids, userId);
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "名称模糊搜索")
    public Result<List<FileCategory>> searchByTitle(@RequestParam String title) {

        User user = SecurityUtil.getUser();
        List<FileCategory> list = fileCategoryService.findByTitleLikeAndCreateBy( title, user.getId());
        setInfo(list);
        return ResultUtil.data(list);
    }

    public void setInfo(List<FileCategory> list) {
        // lambda表达式
        list.forEach(item -> {
            if (!CommonConstant.PARENT_ID.equals(item.getParentId())) {
                FileCategory parent = fileCategoryService.getById(item.getParentId());
                item.setParentTitle(parent.getTitle());
            } else {
                item.setParentTitle("一级目录");
            }
        });
    }
}
