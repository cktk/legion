package com.esmooc.legion.core.common.utils;

import java.security.MessageDigest;

public class MD5Util {
  public static String MD5(String s) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] bytes = md.digest(s.getBytes("utf-8"));
      return toHex(bytes);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String toHex(byte[] bytes) {
    char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    StringBuilder ret = new StringBuilder(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++) {
      ret.append(HEX_DIGITS[bytes[i] >> 4 & 0xF]);
      ret.append(HEX_DIGITS[bytes[i] & 0xF]);
    }
    return ret.toString().toUpperCase();
  }
}
