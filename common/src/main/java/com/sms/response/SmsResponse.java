package com.sms.response;

public class SmsResponse<T>{
    private Integer code;
    private String name;
    private Boolean status;
    private T response;

    public SmsResponse() {
    }

    public SmsResponse(Integer code, String name, Boolean status, T response) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.response = response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
