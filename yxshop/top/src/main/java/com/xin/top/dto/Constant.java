package com.xin.top.dto;

public interface Constant {
    /**
     * 一次性查询商品数量限制
     */
    public static int SOLR_ROW = 20;

    public static String REDIS_LOGIN = "user:";
    public static String REDIS_CART = "cart:";
    public static int REDIS_LOGIN_TIME = 3600;
    public static String COOKIE_LOGIN = "userToken";
    public static int COOKIE_LOGIN_TIME = 3600;
}
