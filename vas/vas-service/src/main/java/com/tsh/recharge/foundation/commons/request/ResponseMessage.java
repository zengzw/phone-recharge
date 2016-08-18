/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.commons.request;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tsh.recharge.foundation.commons.exections.SystemCodes;

/**
 * Http 请求消息封装类
 * 
 * @author zengzw
 * @date 2016年7月26日
 */
public class ResponseMessage {

    /**
     * 状态码. 此代码位接口中出错的代码。如：参数校验，缺少参数等等、系统异常、网络异常等等。
     *  业务执行具体的代码在value字段中获取判断
     */ 
    private String code;


    /**
     * 消息
     */
    private String message;


    /**
     * Json 对象
     */
    private String value;
    
    
    /**
     * 输入参数
     */
    private String inParametes;


    public String getInParametes() {
        return inParametes;
    }


    public void setInParametes(String inParametes) {
        this.inParametes = inParametes;
    }


    /**
     * 
     */
    public ResponseMessage() {
    }


    public ResponseMessage(String code,String message){
        this.code = code;
        this.message = message;
    }

    public ResponseMessage(String code){
        this.code = code;
    }

    public ResponseMessage(String code,String message,String value){
        this.code = code;
        this.message = message;
        this.value = value;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JSONField(serialize=false)
    public String toJsonString(){
        return JSON.toJSONString(this,SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue);
    }

    @JSONField(serialize=false)
    public String getValueToJsonString(){
        return JSON.toJSONString(this.value,SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue);
    }

    @JSONField(serialize=false)
    public String getJsonValue(){
        if(this.value != null){
            return JSON.toJSONString(this.value,SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteMapNullValue);
        }
        return null;
    }

    @JSONField(serialize=false)
    public boolean hasError(){
        return (this.code == null 
                || !this.code.equals(SystemCodes.SUCCESS_CODE));
    }

    @JSONField(serialize=false)
    public <T> T parseValueToObject(Class<T> clas){
        if(this.value != null){
            return (T) JSON.parseObject(this.value,clas);   
        }
        return null;
    }

}
