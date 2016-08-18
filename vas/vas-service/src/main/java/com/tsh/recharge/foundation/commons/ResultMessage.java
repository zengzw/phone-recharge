/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.commons;

/**
 * 业务逻辑消息类
 * 
 * @author zengzw
 * @date 2016年7月26日
 */
public class ResultMessage {
    
    /**
     * 接口返回状态码.
     */ 
    private String code;


    /**
     * 消息
     */
    private String message;


    /**
     * Json 对象
     */
    private Object value;


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
    }

    public ResultMessage(String code,String message){
        this.code = code;
        this.message = message;
    }
    public ResultMessage(){
    }

    public ResultMessage(String code){
        this.code = code;
    }

    public ResultMessage(String code,String message,String value){
        this.code = code;
        this.message = message;
        this.value = value;
    }

}
