package com.esmooc.legion.core.entity.vo;

import com.esmooc.legion.core.common.constant.SystemConstant;
import lombok.Data;

/**
 * @Author 呆猫
 * @Date: 2022/02/18/ 00:02
 * @Description:
 */
@Data
public class SendSmsMsg {

    //运营商返回的状态码
    private String code;
    //运营商返回的消息
    private String msg;
    //运营商返回的原始数据
    private String res;
    //系统判断是否成功的状态
    private String status = SystemConstant.FLAG_N;
    //错误代码
    private String errCode;
    //错误信息
    private String errMsg;
    //错误类型 网络超时 什么的错误类型
    private String errType;


}
