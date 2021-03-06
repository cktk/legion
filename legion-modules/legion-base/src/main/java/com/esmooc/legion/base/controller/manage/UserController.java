package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.base.async.AddMessage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.utils.StopWordsUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.mapper.DeleteMapper;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.service.DepartmentHeaderService;
import com.esmooc.legion.core.service.DepartmentService;
import com.esmooc.legion.core.service.RoleService;
import com.esmooc.legion.core.service.UserRoleService;
import com.esmooc.legion.core.service.UserService;
import com.esmooc.legion.core.service.IUserRoleService;
import com.esmooc.legion.core.entity.vo.RoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "????????????")
@RequestMapping("/legion/user")
@CacheConfig(cacheNames = "user")
@Transactional
public class UserController {

    public static final String USER = "user::";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentHeaderService departmentHeaderService;

    @Autowired
    private IUserRoleService iUserRoleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AddMessage addMessage;

    @Autowired
    private DeleteMapper deleteMapper;

    @Autowired
    private RedisTemplateHelper redisTemplate;




    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "??????????????????????????????")
    public Result<User> getUserInfo() {

        User u =  SecurityUtil.getUser();
        u.setPassword(null);
        return ResultUtil.data(u);
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.POST)
    @ApiOperation(value = "??????????????????")
    public Result<Object> unLock(@RequestParam String password) {

        User u =  SecurityUtil.getUser();
        if (!new BCryptPasswordEncoder().matches(password, u.getPassword())) {
            return ResultUtil.error("???????????????");
        }
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "????????????????????????", notes = "?????????????????????????????? ??????username????????????")
    @CacheEvict(key = "#u.username")
    public Result<Object> editOwn(User u) {

        // ?????????
        StopWordsUtil.matchWord(u.getNickname());

        User old =  SecurityUtil.getUser();
        // ?????????????????????
        u.setUsername(old.getUsername()).setPassword(old.getPassword()).setType(old.getType()).setStatus(old.getStatus());
        if (StrUtil.isBlank(u.getDepartmentId())) {
            u.setDepartmentId(null);
        }
        userService.updateById(u);
        return ResultUtil.success("????????????");
    }

    /**
     * ??????
     *
     * @param password
     * @param newPass
     * @return
     */
    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    @ApiOperation(value = "????????????")
    public Result<Object> modifyPass(@ApiParam("?????????") @RequestParam String password,
                                     @ApiParam("?????????") @RequestParam String newPass,
                                     @ApiParam("????????????") @RequestParam String passStrength) {

        User user =  SecurityUtil.getUser();


        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return ResultUtil.error("??????????????????");
        }

        String newEncryptPass = new BCryptPasswordEncoder().encode(newPass);
        user.setPassword(newEncryptPass);
        user.setPassStrength(passStrength);
        userService.updateById(user);

        // ??????????????????
        redisTemplate.delete(USER + user.getUsername());

        return ResultUtil.success("??????????????????");
    }

    @RequestMapping(value = "/changeMobile", method = RequestMethod.POST)
    @ApiOperation(value = "??????????????????")
    public Result<Object> changeMobile(@RequestParam String mobile) {

        User u =  SecurityUtil.getUser();
        u.setMobile(mobile);
        userService.updateById(u);
        // ????????????
        redisTemplate.delete(USER + u.getUsername());
        return ResultUtil.success("?????????????????????");
    }

    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    @ApiOperation(value = "????????????")
    public Result<Object> resetPass(@RequestParam String[] ids) {

        for (String id : ids) {
            User u = userService.getById(id);
            u.setPassword(new BCryptPasswordEncoder().encode("123456"));
            userService.updateById(u);
            redisTemplate.delete(USER + u.getUsername());
        }
        return ResultUtil.success("????????????");
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "?????????????????????????????????")
    public Result<Object> getByCondition(User user,
                                         SearchVo searchVo,
                                         PageVo pageVo) {

        IPage<User> page = userService.findByCondition(user, searchVo,pageVo);
        for (User u : page.getRecords()) {
            // ????????????
            List<Role> list = iUserRoleService.findByUserId(u.getId());
            List<RoleDTO> roleDTOList = list.stream().map(e -> {
                return new RoleDTO().setId(e.getId()).setName(e.getName()).setDescription(e.getDescription());
            }).collect(Collectors.toList());
            u.setRoles(roleDTOList);
            u.setPassword(null);
        }
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/getByDepartmentId/{departmentId}", method = RequestMethod.GET)
    @ApiOperation(value = "?????????????????????????????????")
    public Result<List<User>> getByCondition(@PathVariable String departmentId) {

        List<User> list = userService.findByDepartmentId(departmentId);
        list.forEach(u -> {
            u.setPassword(null);
        });
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/searchByName/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "???????????????????????????")
    public Result<List<User>> searchByName(@PathVariable String username) throws UnsupportedEncodingException {

        List<User> list = userService.findByUsernameLikeAndStatus(URLDecoder.decode(username, "utf-8"), CommonConstant.STATUS_NORMAL);
        list.forEach(u -> {
            u.setPassword(null);
        });
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "????????????????????????")
    public Result<List<User>> getAll() {

        List<User> list = userService.list();
        // ??????????????????????????? ?????????????????????????????????
        list.forEach(u -> {
            u.setPassword(null);
        });
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    @ApiOperation(value = "????????????")
    public Result<Object> add(@Valid User u,
                              @RequestParam(required = false) String[] roleIds) {

        // ?????????????????????
        NameUtil.checkUserInfo(u.getUsername(), u.getMobile(), u.getEmail());

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
        User user = u;
        if (roleIds != null) {
            // ????????????
            List<UserRole> userRoles = Arrays.asList(roleIds).stream().map(e -> {
                return new UserRole().setUserId(u.getId()).setRoleId(e);
            }).collect(Collectors.toList());
            userRoleService.saveOrUpdateBatch(userRoles);
        }
        // ????????????????????????
        addMessage.addSendMessage(user.getId());

        return ResultUtil.success("????????????");
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    @ApiOperation(value = "?????????????????????", notes = "????????????id????????????????????? ??????username????????????")
    public Result<Object> edit(User u,
                               @RequestParam(required = false) String[] roleIds) {

        User old = userService.getById(u.getId());

        u.setUsername(old.getUsername());
        // ?????????????????????????????????????????????
        if (!old.getMobile().equals(u.getMobile()) && userService.findByMobile(u.getMobile()) != null) {
            return ResultUtil.error("?????????????????????????????????");
        }
        if (!old.getEmail().equals(u.getEmail()) && userService.findByEmail(u.getEmail()) != null) {
            return ResultUtil.error("??????????????????????????????");
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
        // ?????????????????????
        userRoleService.deleteByUserId(u.getId());
        if (roleIds != null) {
            // ?????????
            List<UserRole> userRoles = Arrays.asList(roleIds).stream().map(e -> {
                return new UserRole().setRoleId(e).setUserId(u.getId());
            }).collect(Collectors.toList());
            userRoleService.saveOrUpdateBatch(userRoles);
        }
        // ??????????????????
        return ResultUtil.success("????????????");
    }

    @RequestMapping(value = "/admin/disable/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "??????????????????")
    public Result<Object> disable(@ApiParam("????????????id??????") @PathVariable String userId) {

        User user = userService.getById(userId);
        user.setStatus(CommonConstant.USER_STATUS_LOCK);
        userService.updateById(user);
        // ??????????????????
        redisTemplate.delete(USER + user.getUsername());
        return ResultUtil.success("????????????");
    }

    @RequestMapping(value = "/admin/enable/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "??????????????????")
    public Result<Object> enable(@ApiParam("????????????id??????") @PathVariable String userId) {

        User user = userService.getById(userId);
        user.setStatus(CommonConstant.USER_STATUS_NORMAL);
        userService.updateById(user);
        // ??????????????????
        redisTemplate.delete(USER + user.getUsername());
        return ResultUtil.success("????????????");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "????????????ids??????")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            User u = userService.getById(id);
            // ??????????????????
            redisTemplate.delete(USER + u.getUsername());
            redisTemplate.delete("userRole::" + u.getId());
            redisTemplate.delete("userRole::depIds:" + u.getId());
            redisTemplate.delete("permission::userMenuList:" + u.getId());
            redisTemplate.deleteByPattern("department::*");

            userService.removeById(id);

            // ??????????????????
            userRoleService.deleteByUserId(id);
            // ???????????????????????????
            departmentHeaderService.deleteByUserId(id);

            // ???????????????????????????????????????
            try {
                deleteMapper.deleteActNode(u.getId());
                deleteMapper.deleteActStarter(u.getId());
                deleteMapper.deleteSocial(u.getUsername());
            } catch (Exception e) {
                log.warn(e.toString());
            }
        }
        return ResultUtil.success("????????????id??????????????????");
    }

    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ApiOperation(value = "??????????????????")
    public Result<Object> importData(@RequestBody List<User> users) {

        List<Integer> errors = new ArrayList<>();
        List<String> reasons = new ArrayList<>();
        int count = 0;
        for (User u : users) {
            count++;
            // ???????????????????????????????????????????????????
            if (StrUtil.isBlank(u.getUsername()) || StrUtil.isBlank(u.getPassword()) || StrUtil.isBlank(u.getMobile())
                    || StrUtil.isBlank(u.getEmail())) {
                errors.add(count);
                reasons.add("????????????????????????????????????????????????");
                continue;
            }
            // ???????????????????????????????????????
            if (userService.findByUsername(u.getUsername()) != null || userService.findByMobile(u.getMobile()) != null
                    || userService.findByEmail(u.getEmail()) != null) {
                errors.add(count);
                reasons.add("????????????????????????????????????");
                continue;
            }
            // ????????????
            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
            // ????????????id?????????
            if (StrUtil.isNotBlank(u.getDepartmentId())) {
                Department department = departmentService.getById(u.getDepartmentId());
                if (department == null) {
                    errors.add(count);
                    reasons.add("??????id?????????");
                    continue;
                }
                u.setDepartmentTitle(department.getTitle());
            }
            if (u.getStatus() == null) {
                u.setStatus(CommonConstant.USER_STATUS_NORMAL);
            }
            userService.save(u);
            // ??????????????????
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
        // ??????????????????
        int successCount = users.size() - errors.size();
        String successMessage = "??????????????????????????? " + successCount + " ?????????";
        String failMessage = "???????????? " + successCount + " ???????????? " + errors.size() + " ????????????<br>" +
                "??? " + errors + " ????????????????????????????????????????????????<br>" + reasons;
        String message;
        if (errors.isEmpty()) {
            message = successMessage;
        } else {
            message = failMessage;
        }
        return ResultUtil.success(message);
    }

}
