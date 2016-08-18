/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.service;

import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.model.OrderInfoVo;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.recharge.foundation.model.RechargeVo;

/**
 *
 * 话费充值接口
 * 
 * @author zengzw
 * @date 2016年7月22日
 */
public interface IPhoneRechargeService {

    
    /**
     * 根据订单Id 查询订单状态
     * 
     * @param orderId 
     * @return
     */
   public OrderInfoVo queryOrderById(String orderId) throws BusinessException;
    
    
    /**
     * 根据手机号码 查询具体运营商 
     * 
     * @param mobileNum
     * @return
     */
   public PhoneLocationVo queryPhoneType(String mobileNum) throws BusinessException;
    
    
    /**
     * 具体充值接口
     * 
     * @param mobileNum
     * @param price
     * @return
     */
    public RechargeVo recharge(String mobileNum,int price,Long despsitId,IRechargeCallback callback) throws BusinessException;

    
}
