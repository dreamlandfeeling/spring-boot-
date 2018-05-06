package com.xin.manager.utils;

public class StringUtils {

    public static boolean isBlank(String str){
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }
    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }

    public static boolean isNotBlank(Byte bt){
        return bt!=null||bt!=0;
    }

    public static boolean isNotBlank(Long num){
        return num!=null&&num!=0L;
    }

    public static boolean isNotBlank(Integer num){
        return num!=null&&num!=0;
    }

    public static String resolveJson(String str){
        str = str.substring(str.indexOf("=")+1);
        return str;
    }
}
