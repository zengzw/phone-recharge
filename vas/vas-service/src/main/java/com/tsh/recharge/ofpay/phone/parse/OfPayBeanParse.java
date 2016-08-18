/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.ofpay.phone.parse;

import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.model.OrderInfoVo;
import com.tsh.recharge.foundation.model.PhoneInfoVo;
import com.tsh.recharge.foundation.model.RechargeVo;
import com.tsh.recharge.ofpay.phone.xmlbean.CardInfoElement;
import com.tsh.recharge.ofpay.phone.xmlbean.OrderInfoElement;

/**
 *
 * @author zengzw
 * @date 2016年7月27日
 */
public class OfPayBeanParse {

    /**
     * 格式化订单详情
     * 
     * @param orderInfo
     * @return
     */
    public static OrderInfoVo parseOrder(OrderInfoElement orderInfo){
        if(orderInfo == null){
            return null;
        }

        OrderInfoVo infoVo = new OrderInfoVo();
        infoVo.setOrderNo(orderInfo.getSporderId());
        infoVo.setSpOrderNo(orderInfo.getOrderId());
        infoVo.setPrice(orderInfo.getCardNum());
        infoVo.setSellPrice(orderInfo.getOrderCash());
        infoVo.setOrderStatus(getOrderStatus(orderInfo.getGameState()));

        return infoVo;
    }


    /**
     * 格式化手机信息
     * 
     * @param cardInfo
     * @return
     */
    public static PhoneInfoVo parsePhoneInfo(CardInfoElement cardInfo){
        if(cardInfo == null){
            return null;
        }

        PhoneInfoVo phoneInfoVo = new PhoneInfoVo();
        phoneInfoVo.setProvinceName(cardInfo.getGameArea());
        phoneInfoVo.setPrice(cardInfo.getPrice());

        return phoneInfoVo;

    }


    /**
     * 格式化充值 信息对象
     * @param result
     * @return
     */
    public static RechargeVo parseRechargeInfo(OrderInfoElement result){
        if(result == null){
            return null;
        }
        RechargeVo rechargeVo = new RechargeVo();
        rechargeVo.setOrderNo(result.getOrderId());
        rechargeVo.setSpOrderNo(result.getSporderId());
        if(result.getRetCode() == 1){
            rechargeVo.setOrderStauts(getRechargeStatus(result.getGameState()));
        }else{
            rechargeVo.setOrderStauts(Configurations.RechargeStatus.ERROR);
        }
        
        rechargeVo.setSellerPrice(result.getOrderCash());

        return rechargeVo;

    }


    private static String getOrderStatus(String orderStatus){
        if(orderStatus.equals("1")){
            return Configurations.OrderStatus.SUCCESS;
        }
        else if(orderStatus.equals("0")){
            return Configurations.OrderStatus.RECHARGEING;
        }
        else  if(orderStatus.equals("-1")){
            return Configurations.OrderStatus.NOT_EXISTS;
        }
        else if(orderStatus.equals("9")){
            return Configurations.OrderStatus.FAILED;
        }


        return Configurations.OrderStatus.FAILED;
    }


    private static String getRechargeStatus(String orderStatus){
        if(orderStatus.equals("1")
                || orderStatus.equals("0")){
            return Configurations.RechargeStatus.SUCCESS;
        }
        else if(orderStatus.equals("9")){
            return Configurations.RechargeStatus.ERROR;
        }

        return Configurations.RechargeStatus.ERROR;
    }
}
