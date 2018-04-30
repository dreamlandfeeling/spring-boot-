package com.xin.manager.dto;

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
    public Result(Integer status, String message){
        this.status = status;
        this.message = message;
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
}
