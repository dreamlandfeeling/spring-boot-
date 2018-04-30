package com.xin.manager.dto;

public class UploadResult {
    private Integer error;//0为成功其他是失败
    private String message;//错误信息
    private String url;//成功后图片访问的url

    public UploadResult() {
    }

    public UploadResult(Integer error, String url) {
        this.error = error;
        this.url = url;
    }

    public UploadResult(Integer error, String message, String url) {
        this.error = error;
        this.message = message;
        this.url = url;
    }


    public static UploadResult ok(String url){
        return new UploadResult(0,url);
    }

    public static UploadResult error(String message,String url){
        return new UploadResult(-1,message,url);
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
