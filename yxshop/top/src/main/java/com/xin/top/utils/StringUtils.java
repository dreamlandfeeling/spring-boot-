package com.xin.top.utils;

public class StringUtils {

    public static boolean isNotBlank(String str){
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
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
