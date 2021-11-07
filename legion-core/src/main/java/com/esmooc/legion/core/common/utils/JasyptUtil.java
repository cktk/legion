package com.esmooc.legion.core.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;


/**
 * @author Daimao
 */
@Slf4j
public class JasyptUtil {

    /**
     * Jasypt生成加密结果
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待加密值
     * @return
     */
    public static String encyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.encrypt(value);
        return result;
    }

    /**
     * 解密
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待解密密文
     * @return
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        encryptor.decrypt(value);
        String result = encryptor.decrypt(value);
        return result;
    }

    public static SimpleStringPBEConfig cryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {

        // 加密 若修改了第一个参数加密password记得在配置文件同步修改
        String pwd="hUnr3tyARPSQnAlVufQP6VP/+0n7t0E6O91qmiVk2tn0EWjAC43iaSlGfTyKzRu629osKnyAEKJJ+UOx1J4uEw==";

        String encCode="j6JzDaUk7pcfTWUaYsvQdEVkuv";

        String encPwd = encyptPwd(encCode, pwd);
        System.out.println("加密后密码  "+encPwd);
        // 解密
//        encPwd="";

        String decPwd = decyptPwd(encCode, pwd);

        System.out.println("解密后密码  "+decPwd);

        System.out.println("解密结果  " +pwd.equals(decPwd));

    }
}
