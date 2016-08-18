/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.ofpay.phone.parse;

import com.tsh.recharge.foundation.utils.JaxbUtil;
import com.tsh.recharge.ofpay.phone.xmlbean.OrderInfoElement;

/**
 *
 * @author zengzw
 * @date 2016年7月22日
 */
public class OfPayXmlToBean {

    /**
     * 格式化充值返回内容
     * 
     * @param result
     * @return
     */
    public static OrderInfoElement parseOrderInfo(String result){
        OrderInfoElement orderInfo = JaxbUtil.converyToJavaBean(result, OrderInfoElement.class);
        return orderInfo;
    }

}
