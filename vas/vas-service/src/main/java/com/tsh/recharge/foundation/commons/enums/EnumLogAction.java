/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.commons.enums;

/**
 * 日志记录类型 
 * 
 * @author zengzw
 * @date 2016年8月4日
 */
public enum EnumLogAction {

    /**
     * 查询
     */
    QUERY("1"),

    /**
     * 充值
     */
    RECHARGE("2"),

    /**
     * 回调
     */
    Callback("3");
    
    private String value;
    
    private EnumLogAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
  

}
