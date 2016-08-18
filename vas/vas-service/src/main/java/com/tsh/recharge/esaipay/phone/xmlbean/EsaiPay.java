/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.esaipay.phone.xmlbean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tsh.recharge.foundation.utils.JaxbUtil;

/**
 *
 * @author zengzw
 * @date 2016年7月20日
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name="esaipay")
public class EsaiPay {

    @XmlElement
    private String result ;
    
    @XmlElement
    private String inOrderNumber;
    
    @XmlElement
    private String outOrderNumber;
    
    @XmlElement
    private Integer remark;
    
    @XmlElement
    private String recordKey;

   
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInOrderNumber() {
        return inOrderNumber;
    }

    public void setInOrderNumber(String inOrderNumber) {
        this.inOrderNumber = inOrderNumber;
    }

    public String getOutOrderNumber() {
        return outOrderNumber;
    }

    public void setOutOrderNumber(String outOrderNumber) {
        this.outOrderNumber = outOrderNumber;
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    @Override
    public String toString() {
       return result +" : "+inOrderNumber +" : " + outOrderNumber 
               +" :" + remark +" : "+ recordKey;
    }
    
    public static void main(String[] args) {
        String xml = "<?xml version='1.0' encoding='GB2312'?><esaipay><result>success</result><inOrderNumber>IP1500009201607201047287870</inOrderNumber><outOrderNumber>None</outOrderNumber><remark>600</remark><recordKey>310BD469DE463D50</recordKey></esaipay>";
        EsaiPay pay  = JaxbUtil.converyToJavaBean(xml, EsaiPay.class);
        System.out.println(pay.toString());
    }
}
