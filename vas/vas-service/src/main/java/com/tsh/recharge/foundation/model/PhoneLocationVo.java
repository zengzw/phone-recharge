/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.model;

/**
 * 手机号码归属地对象
 * 
 * 
 * @author zengzw
 * @date 2016年8月10日
 */
public class PhoneLocationVo {

    /**
     * 归属地
     */
    private String provinceName;
    
    
    /**
     * 手机类型
     * 
     */      
    private String type;


    
    
    public String getProvinceName() {
        return provinceName;
    }


    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
    
    
}
