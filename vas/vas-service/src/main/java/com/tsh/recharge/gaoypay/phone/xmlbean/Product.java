/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.xmlbean;

/**
 * 产品
 * 
 * @author zengzw
 * @date 2016年7月20日
 */
public class Product {

    /**
     * 话费充值产品
     */
    private String prodId;

    /**
     * 产品面额
     */
    private Integer prodContent;

    /**
     * 产品支付价格
     */
    private Double prodPrice;

    /**
     * 运营商类别
     */
    private String prodIsptype;

    /**
     * 充值处理时间
     */
    private String prodDelaytimes;

    /**
     * 省份名称 
     */
    private String prodProvinceid;

    /**
     * 产品类型
     */
    private String prodType;


    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getProdContent() {
        return prodContent;
    }

    public void setProdContent(Integer prodContent) {
        this.prodContent = prodContent;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdIsptype() {
        return prodIsptype;
    }

    public void setProdIsptype(String prodIsptype) {
        this.prodIsptype = prodIsptype;
    }

    public String getProdDelaytimes() {
        return prodDelaytimes;
    }

    public void setProdDelaytimes(String prodDelaytimes) {
        this.prodDelaytimes = prodDelaytimes;
    }

    public String getProdProvinceid() {
        return prodProvinceid;
    }

    public void setProdProvinceid(String prodProvinceid) {
        this.prodProvinceid = prodProvinceid;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "productId: "+ prodId;
    }
}
