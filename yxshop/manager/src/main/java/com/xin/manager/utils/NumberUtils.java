package com.xin.manager.utils;

public class NumberUtils {


    public static boolean isNotBlank(Long num){
        return num!=null&&num!=0L;
    }

    public static boolean isNotBlank(Integer num){
        return num!=null&&num!=0;
    }
}
