package com.xin.top.dto;

import java.io.Serializable;

public class Result<T> implements Serializable{
    private Integer status;
    private String message;
    private T data;

    public Result(){
        this.status = 200;
    }


    public Result(T data){
        this.status = 200;
        this.data = data;
    }
    public Result(String message){
        this.status = 400;
        this.message = message;
    }
    public Result(Integer status, String message){
        this.status = status;
        this.message = message;
    }

    public static Result ok(){
        return new Result();
    }
    public static <T>Result ok(T data){
        return new Result(data);
    }
    public static <T>Result error(String message){
        return new Result(message);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
