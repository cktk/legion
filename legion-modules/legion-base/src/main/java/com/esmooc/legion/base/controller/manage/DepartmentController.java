package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.CommonUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.DepartmentHeader;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.DepartmentHeaderService;
import com.esmooc.legion.core.service.DepartmentService;
import com.esmooc.legion.core.service.RoleDepartmentService;
import com.esmooc.legion.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "部门管理接口")
@RequestMapping("/legion/department")
@CacheConfig(cacheNames = "department")
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {

    DepartmentService departmentService;
    UserService userService;
    RoleDepartmentService roleDepartmentService;
    DepartmentHeaderService departmentHeaderService;
    RedisTemplateHelper redisTemplate;
    SecurityUtil securityUtil;


    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过parentId获取")
    public Result<List<Department>> getByParentId(@PathVariable String parentId,
                                                  @ApiParam("是否开始数据权限过滤") @RequestParam(required = false, defaultValue = "true") Boolean openDataFilter) {

        List<Department> list;
        User u = securityUtil.getCurrUser();
        String key = "department::" + parentId + ":" + u.getId() + "_" + openDataFilter;
        String v = redisTemplate.get(key);
        if (StrUtil.isNotEmpty(v)) {
            list = JSONUtil.toList(v, Department.class);
            return new ResultUtil<List<Department>>().setData(list);
        }
        list = departmentService.findByParentIdOrderBySortOrder(parentId, openDataFilter, securityUtil.getDeparmentIds());
        setInfo(list);
        redisTemplate.set(key, JSONUtil.toJsonStr(list), 15L, TimeUnit.DAYS);
        return new ResultUtil<List<Department>>().setData(list);
    }

    @PostMapping( "/add")
    @ApiOperation(value = "添加")
    public Result<Object> add(Department department) {
        departmentService.save(department);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if (!CommonConstant.PARENT_ID.equals(department.getParentId())) {
            Department parent = departmentService.getById(department.getParentId());
            if (parent.getIsParent() == null || !parent.getIsParent()) {
                parent.setIsParent(true);
                departmentService.updateById(parent);
            }
        }
        // 更新缓存
        redisTemplate.deleteByPattern("department::*");
        return ResultUtil.success("添加成功");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑")
    @Transactional
    public Result<Object> edit(Department department,
                               @RequestParam(required = false) String[] mainHeader,
                               @RequestParam(required = false) String[] viceHeader) {

        Department old = departmentService.getById(department.getId());
        String oldParentId = old.getParentId();
        departmentService.updateById(department);
        Department d = department;
        // 先删除原数据
        departmentHeaderService.deleteByDepartmentId(department.getId());
        List<DepartmentHeader> headers = new ArrayList<>();
        if (mainHeader != null) {
            for (String id : mainHeader) {
                DepartmentHeader dh = new DepartmentHeader().setUserId(id).setDepartmentId(d.getId())
                        .setType(CommonConstant.HEADER_TYPE_MAIN);
                headers.add(dh);
            }
        }
        if (viceHeader != null) {
            for (String id : viceHeader) {
                DepartmentHeader dh = new DepartmentHeader().setUserId(id).setDepartmentId(d.getId())
                        .setType(CommonConstant.HEADER_TYPE_VICE);
                headers.add(dh);
            }
        }
        // 批量保存
        departmentHeaderService.saveOrUpdateBatch(headers);
        // 如果该节点不是一级节点 且修改了级别 判断上级还有无子节点
        if (!"0".equals(oldParentId) && !oldParentId.equals(department.getParentId())) {
            Department parent = departmentService.getById(oldParentId);
            List<Department> children = departmentService.findByParentIdOrderBySortOrder(parent.getId(), false, securityUtil.getDeparmentIds());
            if (parent != null && (children == null || children.isEmpty())) {
                parent.setIsParent(false);
                departmentService.updateById(parent);
            }
        }
        // 若修改了部门名称
        if (!old.getTitle().equals(department.getTitle())) {
            userService.updateDepartmentTitle(department.getId(), department.getTitle());
            // 删除所有用户缓存
            redisTemplate.deleteByPattern("user:*");
        }
        // 手动删除所有部门缓存
        redisTemplate.deleteByPattern("department:*");
        return ResultUtil.success("编辑成功");
    }

    @PostMapping(value = "/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            deleteRecursion(id, ids);
        }
        // 手动删除所有部门缓存
        redisTemplate.deleteByPattern("department:*");
        // 删除数据权限缓存
        redisTemplate.deleteByPattern("userRole::depIds:*");
        return ResultUtil.success("批量通过id删除数据成功");
    }

    public void deleteRecursion(String id, String[] ids) {

        List<User> list = userService.findByDepartmentId(id);
        if (list != null && list.size() > 0) {
            throw new LegionException("删除失败，包含正被用户使用关联的部门");
        }
        // 获得其父节点
        Department dep = departmentService.getById(id);
        Department parent = null;
        if (dep != null && StrUtil.isNotBlank(dep.getParentId())) {
            parent = departmentService.getById(dep.getParentId());
        }
        departmentService.removeById(id);
        // 删除关联数据权限
        roleDepartmentService.deleteByDepartmentId(id);
        // 删除关联部门负责人
        departmentHeaderService.deleteByDepartmentId(id);
        // 判断父节点是否还有子节点
        if (parent != null) {
            List<Department> childrenDeps = departmentService.findByParentIdOrderBySortOrder(parent.getId(), false, securityUtil.getDeparmentIds());
            if (childrenDeps == null || childrenDeps.isEmpty()) {
                parent.setIsParent(false);
                departmentService.updateById(parent);
            }
        }
        // 递归删除
        List<Department> departments = departmentService.findByParentIdOrderBySortOrder(id, false, securityUtil.getDeparmentIds());
        for (Department d : departments) {
            if (!CommonUtil.judgeIds(d.getId(), ids)) {
                deleteRecursion(d.getId(), ids);
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "部门名模糊搜索")
    public Result<List<Department>> searchByTitle(@RequestParam String title,
                                                  @ApiParam("是否开始数据权限过滤") @RequestParam(required = false, defaultValue = "true") Boolean openDataFilter) {

        List<Department> list = departmentService.findByTitleLikeOrderBySortOrder("%" + title + "%", openDataFilter, securityUtil.getDeparmentIds());
        setInfo(list);
        return new ResultUtil<List<Department>>().setData(list);
    }

    public void setInfo(List<Department> list) {
        list.forEach(item -> {
            if (!CommonConstant.PARENT_ID.equals(item.getParentId())) {
                Department parent = departmentService.getById(item.getParentId());
                item.setParentTitle(parent.getTitle());
            } else {
                item.setParentTitle("一级部门");
            }
            // 设置负责人
            item.setMainHeaders(departmentHeaderService.findHeaderByDepartmentId(item.getId(), CommonConstant.HEADER_TYPE_MAIN));
            item.setViceHeaders(departmentHeaderService.findHeaderByDepartmentId(item.getId(), CommonConstant.HEADER_TYPE_VICE));
        });
    }
}
