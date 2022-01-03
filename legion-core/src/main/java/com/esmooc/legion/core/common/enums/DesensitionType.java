package com.esmooc.legion.core.common.enums;

public enum DesensitionType {
    PHONE("phone", "11位手机号", "^(\\d{3})\\d{4}(\\d{4})$", "$1****$2"),
    //注意后四位的表达式，因为有的身份证最后一位是X
    ID_CARD("idCard", "16或者18身份证号", "^(\\d{4})\\d{8,10}(\\w{4})$", "$1****$2"),
    BANK_CARD("bankCardNo", "银行卡号", "^(\\d{4})\\d*(\\d{4})$", "$1****$2"),
    REAL_NAME("realName", "真实姓名", "(?<=.{1}).*(?=.{1})", "*"),
    EMAIL("email", "电子邮箱", "(\\w+)\\w{5}@(\\w+)", "$1***@$2"),
    CUSTOM("custom", "自定义正则处理", ""),
    TRUNCATE("truncate", "字符串截取处理", ""),
    ;

    String type;

    String describe;

    String[] regular;

    DesensitionType(String type, String describe, String... regular) {
        this.type = type;
        this.describe = describe;
        this.regular = regular;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }

    public String[] getRegular() {
        return regular;
    }
}
