package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.config.datascope.DataScopeTypeEnum;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.RoleDepartment;
import com.esmooc.legion.core.entity.RolePermission;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.mapper.DeleteMapper;
import com.esmooc.legion.core.service.RoleDepartmentService;
import com.esmooc.legion.core.service.RolePermissionService;
import com.esmooc.legion.core.service.RoleService;
import com.esmooc.legion.core.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "角色管理接口")
@RequestMapping("/legion/role")
@Transactional
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleDepartmentService roleDepartmentService;

    @Autowired
    private DeleteMapper deleteMapper;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @RequestMapping(value = "/getAllList", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部角色")
    public Result<Object> roleGetAll() {

        List<Role> list = roleService.list();
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取角色")
    public Result<IPage<Role>> getRoleByPage(String key, PageVo page) {

        IPage<Role> list = roleService.findByCondition(key, page);
        for (Role role : list.getRecords()) {
            // 角色拥有权限
            List<RolePermission> permissions = rolePermissionService.findByRoleId(role.getId());
            role.setPermissions(permissions);
            // 角色拥有数据权限
            List<RoleDepartment> departments = roleDepartmentService.findByRoleId(role.getId());
            role.setDepartments(departments);
        }
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
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

    @RequestMapping(value = "/editRolePerm", method = RequestMethod.POST)
    @ApiOperation(value = "编辑角色分配菜单权限")
    public Result<Object> editRolePerm(@RequestParam String roleId,
                                       @RequestParam(required = false) String[] permIds) {

        // 删除其关联权限
        rolePermissionService.deleteByRoleId(roleId);
        // 批量分配新权限
        if (permIds != null) {
            List<RolePermission> list = Arrays.asList(permIds).stream().map(e -> {
                return new RolePermission().setRoleId(roleId).setPermissionId(e);
            }).collect(Collectors.toList());
            rolePermissionService.saveOrUpdateBatch(list);
        }
        // 手动批量删除缓存
        redisTemplate.deleteByPattern("user:*");
        redisTemplate.deleteByPattern("userRole:*");
        redisTemplate.deleteByPattern("permission::userMenuList:*");
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/editRoleDep", method = RequestMethod.POST)
    @ApiOperation(value = "编辑角色分配数据权限")
    public Result<Object> editRoleDep(@RequestParam String roleId,
                                      @RequestParam Integer dataType,
                                      @RequestParam(required = false) String[] depIds) {

        Role r = roleService.getById(roleId);
        r.setDataType(dataType);
        roleService.updateById(r);
        if (DataScopeTypeEnum.CUSTOM.getType()==dataType) {
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存数据")
    public Result<Role> save(Role role) {

        return ResultUtil.ok(roleService.save(role));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "更新数据")
    public Result<Role> edit(Role entity) {

        roleService.updateById(entity);
        // 手动批量删除缓存
        redisTemplate.deleteByPattern("user:*");
        redisTemplate.deleteByPattern("userRole:*");
        return ResultUtil.data(entity);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
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
            // 删除流程关联节点
            deleteMapper.deleteActNode(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
