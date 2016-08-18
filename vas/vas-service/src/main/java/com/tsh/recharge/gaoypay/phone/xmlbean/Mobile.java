/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.xmlbean;

/**
 *
 * @author zengzw
 * @date 2016年7月21日
 */
public class Mobile {

    /**
     *  运营商名称
     */
    private String spType;
    

    /**
     * 省份名称
     */
    private String provinceName;
    
    /**
     * 地市代码
     */
    private String cityCode;
    
    
    /**
     * 描述
     */
    private String detail;
    
    

    public String getSpType() {
        return spType;
    }


    public void setSpType(String spType) {
        this.spType = spType;
    }


    public String getProvinceName() {
        return provinceName;
    }


    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getCityCode() {
        return cityCode;
    }


    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    public String getDetail() {
        return detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }

}
