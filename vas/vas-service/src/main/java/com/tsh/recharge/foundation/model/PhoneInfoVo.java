/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.model;

/**
 *
 * @author zengzw
 * @date 2016年7月26日
 */
public class PhoneInfoVo {

    /**
     *  供应商 类型
     */
    private String spType;
    
    /**
     * 手机类型。移动电话、固定电话、小灵通 
     */
    private String phoneType;
    
    /**
     * 省市名称
     */
    private String provinceName;
    
    /**
     * 城市编码
     */
    private String cityCode;
    
    /**
     * 产品ID
     */
    private String productId;
    
    /**
     * 价格
     */
    private Integer price;
    
    /**
     * 销售价格
     */
    private Integer sellerPrice;
    
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(Integer sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    
}
