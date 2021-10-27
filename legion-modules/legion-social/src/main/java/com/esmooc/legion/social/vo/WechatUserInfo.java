package com.esmooc.legion.social.vo;

import lombok.Data;

/**
 * @author Daimao
 */
@Data
public class WechatUserInfo {

    /**
     * 唯一id
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * unionid
     */
    private String unionid;
}
