/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsh.vas.service.recharge.phone.PhoneProductService;

/**
 *
 * 产品更新定时器
 * 
 * @author zengzw
 * @date 2016年8月1日
 */
public class RechargeProductTimer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeProductTimer.class);

    @Autowired
    private PhoneProductService service;


    public void start(){

        LOGGER.info(">> 开始刷新话费充值产品信息........");

        service.initProductDate();


        LOGGER.info(">> 刷新话费充值产品信息介绍.........");
    }

}
