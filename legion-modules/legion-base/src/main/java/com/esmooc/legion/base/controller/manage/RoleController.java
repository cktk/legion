package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.RoleDepartment;
import com.esmooc.legion.core.entity.RolePermission;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.service.RoleDepartmentService;
import com.esmooc.legion.core.service.RolePermissionService;
import com.esmooc.legion.core.service.RoleService;
import com.esmooc.legion.core.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "角色管理接口")
@RequestMapping("/legion/role")
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;
    UserRoleService userRoleService;
    RolePermissionService rolePermissionService;
    RoleDepartmentService roleDepartmentService;
    RedisTemplateHelper redisTemplate;

    @GetMapping("/getAllList")
    @ApiOperation(value = "获取全部角色")
    public Result<Object> roleGetAll() {

        List<Role> list = roleService.list();
        return ResultUtil.data(list);
    }

    @GetMapping("/getAllByPage")
    @ApiOperation(value = "分页获取角色")
    public Result<Page<Role>> getRoleByPage(String key, PageVo page) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Role::getName, key).or().like(Role::getDescription, key);
        Page<Role> list = roleService.page(PageUtil.initPage(page), queryWrapper);
        for (Role role : list.getRecords()) {
            // 角色拥有权限
            List<RolePermission> permissions =   rolePermissionService.findByRoleId(role.getId());
            role.setPermissions(permissions);
            // 角色拥有数据权限
            List<RoleDepartment> departments = roleDepartmentService.findByRoleId(role.getId());
            role.setDepartments(departments);
        }

        log.info("所有权限{}", list);

        return new ResultUtil<Page<Role>>().setData(list);
    }


    @PostMapping("/setDefault")
    @ApiOperation(value = "设置或取消默认角色")
    public Result<Object> setDefault(@RequestParam String id,
                                     @RequestParam Boolean isDefault) {

        Role role = roleService.getById(id);
        if (role == null) {
            return ResultUtil.error("角色不存在");
        }
        role.setDefaultRole(isDefault);
        roleService.updateById(role);
        return ResultUtil.success("设置成功");
    }

    @PostMapping("/editRolePerm")
    @ApiOperation(value = "编辑角色分配菜单权限")
    @Transactional
    public Result<Object> editRolePerm(@RequestParam String roleId,
                                       @RequestParam(required = false) ArrayList<String> permIds) {

        long time = new Date().getTime();
        // 删除其关联权限
        rolePermissionService.deleteByRoleId(roleId);
        // 批量分配新权限
        if (permIds != null && permIds.size() > 0) {
            List<RolePermission> list = permIds.stream().map(e -> new RolePermission().setRoleId(roleId).setPermissionId(e)).collect(Collectors.toList());
            rolePermissionService.saveBatch(list);
        }
        // 手动批量删除缓存
        redisTemplate.deleteByPattern("user:*");
        redisTemplate.deleteByPattern("userRole:*");
        redisTemplate.deleteByPattern("permission::userMenuList:*");
        log.info("执行时间{}", new Date().getTime() - time);
        return ResultUtil.data(null);
    }

    @Transactional
    @PostMapping("/editRoleDep")
    @ApiOperation(value = "编辑角色分配数据权限")
    public Result<Object> editRoleDep(@RequestParam String roleId,
                                      @RequestParam Integer dataType,
                                      @RequestParam(required = false) String[] depIds) {

        Role r = roleService.getById(roleId);
        r.setDataType(dataType);
        roleService.updateById(r);
        if (CommonConstant.DATA_TYPE_CUSTOM.equals(dataType)) {
            // 删除其关联数据权限
            roleDepartmentService.deleteByRoleId(roleId);
            // 批量分配新数据权限
            if (depIds != null) {
                List<RoleDepartment> list = Arrays.asList(depIds).stream().map(e -> {
                    return new RoleDepartment().setRoleId(roleId).setDepartmentId(e);
                }).collect(Collectors.toList());
                roleDepartmentService.saveOrUpdateBatch(list);
            }
        }
        // 手动删除相关缓存
        redisTemplate.deleteByPattern("department:*");
        redisTemplate.deleteByPattern("userRole:*");
        return ResultUtil.data(null);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存数据")
    public Result<Role> save(Role role) {
        roleService.save(role);
        return new ResultUtil<Role>().setData(role);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "更新数据")
    public Result<Role> edit(Role entity) {

        roleService.updateById(entity);
        redisTemplate.deleteByPattern("user:*");
        redisTemplate.deleteByPattern("userRole:*");
        return new ResultUtil<Role>().setData(entity);
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过ids删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            List<UserRole> list = userRoleService.findByRoleId(id);
            if (list != null && list.size() > 0) {
                return ResultUtil.error("删除失败，包含正被用户使用关联的角色");
            }
        }
        for (String id : ids) {
            roleService.removeById(id);
            // 删除关联菜单权限
            rolePermissionService.deleteByRoleId(id);
            // 删除关联数据权限
            roleDepartmentService.deleteByRoleId(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
