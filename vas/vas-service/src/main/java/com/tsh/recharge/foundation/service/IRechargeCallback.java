/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.service;

/**
 *  回调接口
 *
 * @author zengzw
 * @date 2016年7月22日
 */
public  interface IRechargeCallback{

    /**
     * 手机号码段不能用日志记录
     * @param depositId
     * @param status
     * @param inParams
     * @param outParams
     */
    public void availablePhoneLog(Long depositId,String status,String inParams,String outParams);
    
    /**
     * 充值前的一些操作
     * @param depositId
     * @param orderNo
     */
    public void before(Long depositId,String orderNo);
    
    
    /**
     * 充值后的一些操作
     * 
     * @param orderNo
     * @param price
     * @param status
     * @param inParam
     * @param outParam
     */
    
    public void after(String orderNo,Double price,String status,String inParam,String outParam);
}
