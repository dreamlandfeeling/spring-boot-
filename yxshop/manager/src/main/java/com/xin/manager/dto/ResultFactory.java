package com.xin.manager.dto;

public class ResultFactory {

    public static <T>Result getSuccessResult(){
        return new Result();
    }
    /**
     * 返回成功结果
     * @param data 需要的数据
     * @param <T>
     * @return
     */
    public static <T>Result getSuccessResult(T data){
        return new Result(data);
    }

    /**
     * 返回失败结果
     * @param status 错误类型
     * @param message 错误信息
     * @param <T>
     * @return
     */
    public static <T>Result getFailResult(Integer status,String message){
        return new Result(status,message);
    }
}
