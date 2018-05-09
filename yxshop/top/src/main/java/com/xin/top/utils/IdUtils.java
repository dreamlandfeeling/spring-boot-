package com.xin.top.utils;

public class IdUtils {

    public static String createId(){
        int r1=(int)(Math.random()*(10));
        int r2=(int)(Math.random()*(10));
        int r3=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String id =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(r3)+String.valueOf(now);// 订单ID
        return id;
    }
}
