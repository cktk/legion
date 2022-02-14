package com.esmooc.legion.core.common.constant;

import cn.hutool.core.util.IdUtil;

/**
 * @author DaiMao
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = IdUtil.simpleUUID();

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * appToken参数头
     */
    String APP_HEADER = "appToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * github保存state前缀key
     */
    String GITHUB_STATE = "LEGION_GITHUB:";

    /**
     * qq保存state前缀key
     */
    String QQ_STATE = "LEGION_QQ:";

    /**
     * 微信保存state前缀key
     */
    String WECHAT_STATE = "LEGION_WECHAT:";

    /**
     * 微博保存state前缀key
     */
    String WEIBO_STATE = "LEGION_WEIBO:";

    /**
     * 企业微信保存state前缀key
     */
    String WORKWECHAT_STATE = "LEGION_WORKWECHAT:";

    /**
     * 钉钉保存state前缀key
     */
    String DINGDING_STATE = "LEGION_DINGDING:";

    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "LEGION_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "LEGION_USER_TOKEN:";

    /**
     * 会员交互token前缀key
     */
    String TOKEN_MEMBER_PRE = "LEGION_TOKEN_MEMBER_PRE:";

    /**
     * 会员token前缀key
     */
    String MEMBER_TOKEN = "LEGION_MEMBER_TOKEN:";
}

