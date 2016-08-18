/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.ofpay.phone.xmlbean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  订单充值返回xmlbean
 *  
 * @author zengzw
 * @date 2016年7月21日
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="orderinfo")
public class OrderInfoElement {

    /**
     * 错误消息
     */
    @XmlElement(name="err_msg")
    private String errorMessage;


    /**
     * 支付状态
     */
    @XmlElement(name="retcode")
    private Integer retCode;


    /**
     * 平台订单号
     */
    @XmlElement(name="orderid")
    private String orderId;


    /**
     * 商品编号
     */
    @XmlElement(name="cardid")
    private String cardId;


    /**
     * 商品数量
     */
    @XmlElement(name="cardnum")
    private Integer cardNum;


    /**
     * 订单金额
     */
    @XmlElement(name="ordercash")
    private Double orderCash;


    /**
     * 描述
     */
    @XmlElement(name="cardname")
    private String cardName;


    /**
     * 内部订单号
     */
    @XmlElement(name="sporder_id")
    private String sporderId;


    /**
     * 手机号码
     */
    @XmlElement(name="game_userid")
    private String gameUserid;


    /**
     * 如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
     */
    @XmlElement(name="game_state")
    private String gameState;


    public String getCardId() {
        return cardId;
    }


    public String getCardName() {
        return cardName;
    }


    public Integer getCardNum() {
        return cardNum;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public String getGameState() {
        return gameState;
    }


    public String getGameUserid() {
        return gameUserid;
    }


    public Double getOrderCash() {
        return orderCash;
    }


    public String getOrderId() {
        return orderId;
    }


    public Integer getRetCode() {
        return retCode;
    }


    public String getSporderId() {
        return sporderId;
    }


    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    
    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }
    
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
    
    public void setGameUserid(String gameUserid) {
        this.gameUserid = gameUserid;
    }
    
    public void setOrderCash(Double orderCash) {
        this.orderCash = orderCash;
    }
    
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }
    
    
    public void setSporderId(String sporderId) {
        this.sporderId = sporderId;
    }
    
    
}
