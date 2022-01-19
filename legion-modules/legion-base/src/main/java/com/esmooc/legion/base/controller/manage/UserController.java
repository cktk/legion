package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.base.async.AddMessage;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.utils.StopWordsUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.config.security.SecurityUserDetails;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.mapper.DeleteMapper;
import com.esmooc.legion.core.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "用户接口")
@RequestMapping("/legion/user")
@CacheConfig(cacheNames = "user")
@Transactional
@AllArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserController {

    public static final String USER = "user::";
    UserService userService;
    RoleService roleService;
    DepartmentService departmentService;
    DepartmentHeaderService departmentHeaderService;
    UserRoleService userRoleService;
    AddMessage addMessage;
    DeleteMapper deleteMapper;
    RedisTemplateHelper redisTemplate;
    SecurityUtil securityUtil;


    @RequestMapping(value = "/smsLogin", method = RequestMethod.POST)
    @SystemLog(description = "短信登录", type = LogType.LOGIN)
    @ApiOperation(value = "短信登录接口")
    public Result<Object> smsLogin(@RequestParam String mobile,
                                   @RequestParam(required = false) Boolean saveLogin) {

        User u = userService.findByMobile(mobile);
        if (u == null) {
            throw new LegionException("手机号不存在");
        }
        String accessToken = securityUtil.getToken(u.getUsername(), saveLogin);
        // 记录日志使用
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new SecurityUserDetails(u), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResultUtil.data(accessToken);
    }

    @RequestMapping(value = "/resetByMobile", method = RequestMethod.POST)
    @ApiOperation(value = "通过短信重置密码")
    public Result<Object> resetByMobile(@RequestParam String mobile,
                                        @RequestParam String password,
                                        @RequestParam String passStrength) {

        User u = userService.findByMobile(mobile);
        String encryptPass = new BCryptPasswordEncoder().encode(password);
        u.setPassword(encryptPass).setPassStrength(passStrength);
        userService.updateById(u);
        // 删除缓存
        redisTemplate.delete(USER + u.getUsername());
        return ResultUtil.success("重置密码成功");
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value = "注册用户")
    public Result<Object> regist(@Valid User u) {

        // 校验是否已存在
        checkUserInfo(u.getUsername(), u.getMobile(), u.getEmail());

        String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
        u.setPassword(encryptPass).setType(CommonConstant.USER_TYPE_NORMAL);
         userService.save(u);

        // 默认角色
        List<Role> roleList = roleService.findByDefaultRole(true);
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                UserRole ur = new UserRole().setUserId(u.getId()).setRoleId(role.getId());
                userRoleService.save(ur);
            }
        }
        // 异步发送创建账号消息
        addMessage.addSendMessage(u.getId());

        return ResultUtil.data(u);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登录用户接口")
    public Result<User> getUserInfo() {

        User u = securityUtil.getCurrUser();
        u.setPassword(null);
        return new ResultUtil<User>().setData(u);
    }

    @RequestMapping(value = "/changeMobile", method = RequestMethod.POST)
    @ApiOperation(value = "修改绑定手机")
    public Result<Object> changeMobile(@RequestParam String mobile) {

        User u = securityUtil.getCurrUser();
        u.setMobile(mobile);
        userService.updateById(u);
        // 删除缓存
        redisTemplate.delete(USER + u.getUsername());
        return ResultUtil.success("修改手机号成功");
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.POST)
    @ApiOperation(value = "解锁验证密码")
    public Result<Object> unLock(@RequestParam String password) {

        User u = securityUtil.getCurrUser();
        if (!new BCryptPasswordEncoder().matches(password, u.getPassword())) {
            return ResultUtil.error("密码不正确");
        }
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    @ApiOperation(value = "重置密码")
    public Result<Object> resetPass(@RequestParam String[] ids) {

        for (String id : ids) {
            User u = userService.getById(id);
            u.setPassword(new BCryptPasswordEncoder().encode("123456"));
            userService.updateById(u);
            redisTemplate.delete(USER + u.getUsername());
        }
        return ResultUtil.success("操作成功");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改用户自己资料", notes = "用户名密码等不会修改 需要username更新缓存")
    @CacheEvict(key = "#u.username")
    public Result<Object> editOwn(User u) {

        User old = securityUtil.getCurrUser();
        // 不能修改的字段
        u.setUsername(old.getUsername()).setPassword(old.getPassword()).setType(old.getType()).setStatus(old.getStatus());
        if (StrUtil.isBlank(u.getDepartmentId())) {
            u.setDepartmentId(null);
        }
        userService.updateById(u);
        return ResultUtil.success("修改成功");
    }

    /**
     * @param password
     * @param newPass
     * @return
     */
    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码")
    public Result<Object> modifyPass(@ApiParam("旧密码") @RequestParam String password,
                                     @ApiParam("新密码") @RequestParam String newPass,
                                     @ApiParam("密码强度") @RequestParam String passStrength) {

        User user = securityUtil.getCurrUser();

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return ResultUtil.error("旧密码不正确");
        }

        String newEncryptPass = new BCryptPasswordEncoder().encode(newPass);
        user.setPassword(newEncryptPass);
        user.setPassStrength(passStrength);
        userService.updateById(user);

        // 手动更新缓存
        redisTemplate.delete(USER + user.getUsername());

        return ResultUtil.success("修改密码成功");
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<User>> getByCondition(User user,
                                             SearchVo searchVo,
                                             PageVo pageVo) {
        return new ResultUtil<Page<User>>().setData(userService.page(PageUtil.initPage(pageVo)));
    }

    @RequestMapping(value = "/getByDepartmentId/{departmentId}", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<List<User>> getByCondition(@PathVariable String departmentId) {

        List<User> list = userService.findByDepartmentId(departmentId);
        list.forEach(u -> {
            u.setPassword(null);
        });
        return new ResultUtil<List<User>>().setData(list);
    }

    @RequestMapping(value = "/searchByName/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户名搜索用户")
    public Result<List<User>> searchByName(@PathVariable String username) throws UnsupportedEncodingException {

        List<User> list = userService.findByUsernameLikeAndStatus(URLDecoder.decode(username, "utf-8"), CommonConstant.STATUS_NORMAL);
        list.forEach(u -> {
            u.setPassword(null);
        });
        return new ResultUtil<List<User>>().setData(list);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部用户数据")
    public Result<List<User>> getAll() {

        List<User> list = userService.list();
        // 清除持久上下文环境 避免后面语句导致持久化
        for (User u : list) {
            u.setPassword(null);
        }
        return new ResultUtil<List<User>>().setData(list);
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户")
    public Result<Object> add(@Valid User u,
                              @RequestParam(required = false) String[] roleIds) {

        // 校验是否已存在
        checkUserInfo(u.getUsername(), u.getMobile(), u.getEmail());

        String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
        u.setPassword(encryptPass);
        if (StrUtil.isNotBlank(u.getDepartmentId())) {
            Department d = departmentService.getById(u.getDepartmentId());
            if (d != null) {
                u.setDepartmentTitle(d.getTitle());
            }
        } else {
            u.setDepartmentId(null);
            u.setDepartmentTitle("");
        }

         userService.save(u);
        User user =u;
        if (roleIds != null) {
            // 添加角色
            List<UserRole> userRoles = Arrays.asList(roleIds).stream().map(e -> {
                return new UserRole().setUserId(u.getId()).setRoleId(e);
            }).collect(Collectors.toList());
            userRoleService.saveOrUpdateBatch(userRoles);
        }
        // 发送创建账号消息
        addMessage.addSendMessage(user.getId());

        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    @ApiOperation(value = "管理员修改资料", notes = "需要通过id获取原用户信息 需要username更新缓存")
    @CacheEvict(key = "#u.username")
    public Result<Object> edit(User u,
                               @RequestParam(required = false) String[] roleIds) {

        User old = userService.getById(u.getId());

        u.setUsername(old.getUsername());
        // 若修改了手机和邮箱判断是否唯一
        if (!old.getMobile().equals(u.getMobile()) && userService.findByMobile(u.getMobile()) != null) {
            return ResultUtil.error("该手机号已绑定其他账户");
        }
        if (!old.getEmail().equals(u.getEmail()) && userService.findByEmail(u.getEmail()) != null) {
            return ResultUtil.error("该邮箱已绑定其他账户");
        }

        if (StrUtil.isNotBlank(u.getDepartmentId())) {
            Department d = departmentService.getById(u.getDepartmentId());
            if (d != null) {
                u.setDepartmentTitle(d.getTitle());
            }
        } else {
            u.setDepartmentId(null);
            u.setDepartmentTitle("");
        }

        u.setPassword(old.getPassword());
        userService.updateById(u);
        // 删除该用户角色
        userRoleService.deleteByUserId(u.getId());
        if (roleIds != null) {
            // 新角色
            List<UserRole> userRoles = Arrays.asList(roleIds).stream().map(e -> {
                return new UserRole().setRoleId(e).setUserId(u.getId());
            }).collect(Collectors.toList());
            userRoleService.saveOrUpdateBatch(userRoles);
        }
        // 手动删除缓存
        redisTemplate.delete("userRole::" + u.getId());
        redisTemplate.delete("userRole::depIds:" + u.getId());
        redisTemplate.delete("permission::userMenuList:" + u.getId());
        return ResultUtil.success("修改成功");
    }

    @RequestMapping(value = "/admin/disable/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "后台禁用用户")
    public Result<Object> disable(@ApiParam("用户唯一id标识") @PathVariable String userId) {

        User user = userService.getById(userId);
        user.setStatus(CommonConstant.USER_STATUS_LOCK);
        userService.updateById(user);
        // 手动更新缓存
        redisTemplate.delete(USER + user.getUsername());
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/admin/enable/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "后台启用用户")
    public Result<Object> enable(@ApiParam("用户唯一id标识") @PathVariable String userId) {

        User user = userService.getById(userId);
        user.setStatus(CommonConstant.USER_STATUS_NORMAL);
        userService.updateById(user);
        // 手动更新缓存
        redisTemplate.delete(USER + user.getUsername());
        return ResultUtil.success("操作成功");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过ids删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            User u = userService.getById(id);
            // 删除相关缓存
            redisTemplate.delete(USER + u.getUsername());
            redisTemplate.delete("userRole::" + u.getId());
            redisTemplate.delete("userRole::depIds:" + u.getId());
            redisTemplate.delete("permission::userMenuList:" + u.getId());
            redisTemplate.deleteByPattern("department::*");

            userService.removeById(id);

            // 删除关联角色
            userRoleService.deleteByUserId(id);
            // 删除关联部门负责人
            departmentHeaderService.deleteByUserId(id);

            // 删除关联流程、社交账号数据
            try {
                deleteMapper.deleteSocial(u.getUsername());
            } catch (Exception e) {
                log.warn(e.toString());
            }
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }

    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ApiOperation(value = "导入用户数据")
    public Result<Object> importData(@RequestBody List<User> users) {

        List<Integer> errors = new ArrayList<>();
        List<String> reasons = new ArrayList<>();
        int count = 0;
        for (User u : users) {
            count++;
            // 验证用户名、密码、手机、邮箱不为空
            if (StrUtil.isBlank(u.getUsername()) || StrUtil.isBlank(u.getPassword()) || StrUtil.isBlank(u.getMobile())
                    || StrUtil.isBlank(u.getEmail())) {
                errors.add(count);
                reasons.add("用户名、密码、手机、邮箱不能为空");
                continue;
            }
            // 验证用户名、手机、邮箱唯一
            if (userService.findByUsername(u.getUsername()) != null || userService.findByMobile(u.getMobile()) != null
                    || userService.findByEmail(u.getEmail()) != null) {
                errors.add(count);
                reasons.add("用户名、手机、邮箱已存在");
                continue;
            }
            // 加密密码
            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
            // 验证部门id正确性
            if (StrUtil.isNotBlank(u.getDepartmentId())) {
                Department department = departmentService.getById(u.getDepartmentId());
                if (department == null) {
                    errors.add(count);
                    reasons.add("部门id不存在");
                    continue;
                }
                u.setDepartmentTitle(department.getTitle());
            }
            if (u.getStatus() == null) {
                u.setStatus(CommonConstant.USER_STATUS_NORMAL);
            }
            userService.save(u);
            // 分配默认角色
            if (u.getDefaultRole() != null && u.getDefaultRole() == 1) {
                List<Role> roleList = roleService.findByDefaultRole(true);
                if (roleList != null && roleList.size() > 0) {
                    for (Role role : roleList) {
                        UserRole ur = new UserRole().setUserId(u.getId()).setRoleId(role.getId());
                        userRoleService.save(ur);
                    }
                }
            }
        }
        // 批量保存数据
        int successCount = users.size() - errors.size();
        String successMessage = "全部导入成功，共计 " + successCount + " 条数据";
        String failMessage = "导入成功 " + successCount + " 条，失败 " + errors.size() + " 条数据。<br>" +
                "第 " + errors.toString() + " 行数据导入出错，错误原因分别为：<br>" + reasons.toString();
        String message = "";
        if (errors.isEmpty()) {
            message = successMessage;
        } else {
            message = failMessage;
        }
        return ResultUtil.success(message);
    }

    /**
     * 校验
     * @param username 用户名 不校验传空字符或null 下同
     * @param mobile   手机号
     * @param email    邮箱
     */
    public void checkUserInfo(String username, String mobile, String email) {

        // 禁用词
        StopWordsUtil.matchWord(username);

        if (StrUtil.isNotBlank(username) && userService.findByUsername(username) != null) {
            throw new LegionException("该登录账号已被注册");
        }
        if (StrUtil.isNotBlank(email) && userService.findByEmail(email) != null) {
            throw new LegionException("该邮箱已被注册");
        }
        if (StrUtil.isNotBlank(mobile) && userService.findByMobile(mobile) != null) {
            throw new LegionException("该手机号已被注册");
        }
    }
}
