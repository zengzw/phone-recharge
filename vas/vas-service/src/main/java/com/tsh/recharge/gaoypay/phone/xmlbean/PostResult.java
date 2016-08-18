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
public class PostResult {

    /**
     * 订单ID
     */
    private String orderId;
    
    /**
     * 产品ID
     */
    private String prodId;
    
    /**
     * 代理平台订单号
     */
    private String tranid;
    
    /**
     * 结果编码
     */
    private String resultNo;
    
    /**
     * 充值成功金额
     */
    private String finishMoney;
    
    
    
    private String verifyString;
    
    /**
     * 预留字段。原样返回
     */
    private String mark;
    

    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getProdId() {
        return prodId;
    }


    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


    public String getTranid() {
        return tranid;
    }


    public void setTranid(String tranid) {
        this.tranid = tranid;
    }


    public String getResultNo() {
        return resultNo;
    }


    public void setResultNo(String resultNo) {
        this.resultNo = resultNo;
    }


    public String getFinishMoney() {
        return finishMoney;
    }


    public void setFinishMoney(String finishMoney) {
        this.finishMoney = finishMoney;
    }


    public String getVerifyString() {
        return verifyString;
    }


    public void setVerifyString(String verifyString) {
        this.verifyString = verifyString;
    }


    public String getMark() {
        return mark;
    }


    public void setMark(String mark) {
        this.mark = mark;
    }
}
