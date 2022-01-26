package com.esmooc.legion.edu.common.constant;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";


    /**
     * 全部      0
     * 中心学员  1
     * 社会学员  2
     */
    public static final Integer FACUITYSTUDENTS = 0;
    public static final Integer INTERNALSTUDENTS = 1;
    public static final Integer EXTERNALSTUDENTS = 2;

    /**
     * 课件 1
     * 视频 2
     */
    public static final Integer PDF = 1;
    public static final Integer VIDEO = 2;

    /**
     * 视频课 1
     * 社会课 2
     */
    public static final Integer VIDEOCOURSE = 1;
    public static final Integer SOCIETYCOURSE = 2;

    /**
     * 是否删除
     * 0 未删除
     * 1 删除
     */
    public static final Integer ISDELETE = 0;
    public static final Integer ISNOTDELETE = 1;

    /**
     * 未审核  unrevised
     */
    public static final Integer UNREVISED = 2;
    /**
     * 已审核
     */
    public static final Integer UNREVISED_OK = 0;
    /**
     * 审核未通过
     */
    public static final Integer UNREVISED_NO = 1;

    /**
     * 题目类型
     */
    // 单选题
    public static final Integer RADIO = 0;
    // 多选题
    public static final Integer CHECKBOX = 1;
    // 判断题
    public static final Integer JUDGMENT = 2;

    /**
     * 考试类型
     */
    // 练习
    public static final Integer EXERCISE = 0;
    // 考试
    public static final Integer EXAMINATION = 1;
    // 创建考试
    public static final Integer EXAM = 2;

    /**
     * 学习状态
     * 未学习     0
     * 学习中     1
     * 学习结束   2
     */
    public static final Integer NOSTUDY = 0;
    public static final Integer STUDY = 1;
    public static final Integer ENDSTUDY = 2;


    public static final String USER="1";
    public static final String DEPT="2";
    public static final String ROLE="3";
    /**
     * 角色编号
     * 超级管理员     1
     * 普通角色       2
     * 内部学员       3
     * 外部学员       4
     */
    public static final Long ADMIN = (long) 1;
    public static final Long COMMON = (long) 2;
    public static final Long INTERNAL = (long) 3;
    public static final Long EXTERNAL = (long) 4;
    public static final Long CITY = (long) 5;

    public static final String ADMIN_S = "ROLE_ADMIN";
    public static final String COMMON_S = "ROLE_COMMON";
    public static final String INTERNAL_S = "ROLE_INTERNAL";
    public static final String EXTERNAL_S = "ROLE_EXTERNAL";
    public static final String CITY_S = "ROLE_CITY";


    public static final Integer CERTIFICATELENGTH = 4;

    /**
     * 行政区划级别
     */
    public static final Integer PROVINCE = 1;
    public static final Integer AU = 2;
    public static final Integer FLAGCOUNTY = 3;

    /**
     * 考试限制
     */
    public static final int TESTLIMIT = 3;

}
