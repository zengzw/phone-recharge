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
public class OrderInfoVo {

    /**
     * 自定义订单ID
     */
    private String orderNo;
    
    
    /**
     * 供应商订单   ID
     */
    private String spOrderNo;
    
    
    /**
     * 订单状态
     */
    private String orderStatus;
    
    
    /**
     * 实际充值金额
     */
    private String finishMoney;
    
    
    /**
     * 实际金额
     */
    private int price;
    
    
    /**
     * 销售金额
     */
    private Double sellPrice;



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


    public String getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getFinishMoney() {
        return finishMoney;
    }


    public void setFinishMoney(String finishMoney) {
        this.finishMoney = finishMoney;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public Double getSellPrice() {
        return sellPrice;
    }


    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
