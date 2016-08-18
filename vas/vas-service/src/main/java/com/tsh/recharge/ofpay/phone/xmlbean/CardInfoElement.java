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
 *  手机号码信息
 *      
 * @author zengzw
 * @date 2016年7月26日
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="cardinfo")
public class CardInfoElement {
    
    @XmlElement(name="retcode")
    private String retCode;
    
    @XmlElement(name="err_msg")
    private String errorMsg;
    
    @XmlElement(name="cardid")
    private String cardId;
    
    @XmlElement(name="inprice")
    private Integer price;
    
    @XmlElement(name="cardname")
    private String cardName;
    
    @XmlElement(name="game_area")
    private String gameArea;
    
    
    
    public String getRetCode() {
        return retCode;
    }
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getGameArea() {
        return gameArea;
    }
    public void setGameArea(String gameArea) {
        this.gameArea = gameArea;
    }
   
    
}
