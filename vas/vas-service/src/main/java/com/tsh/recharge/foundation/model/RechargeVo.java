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
public class RechargeVo {

    /**
     * 自定义订单号
     */
    private String orderNo;
    
    /**
     * 供应商订单号
     */
    private String spOrderNo;
    
    /**
     * 订单状态
     */
    private String orderStauts;
    
    /**
     * 订单处理时间
     */
    private String successTime;
    
    /**
     * 实际充值金额
     */
    private Double sellerPrice;
    
    

    public Double getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(Double sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSpOrderNo() {
        return spOrderNo;
    }

    public void setSpOrderNo(String spOrderNo) {
        this.spOrderNo = spOrderNo;
    }

    public String getOrderStauts() {
        return orderStauts;
    }

    public void setOrderStauts(String orderStauts) {
        this.orderStauts = orderStauts;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }
    
    
}
