/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.controller.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsh.vas.service.recharge.phone.PhoneProductService;

/** 
 *   初始化产品数据
 *
 * @author zengzw
 * @date 2016年8月1日
 */
public class RechargeProductInitialize implements InitializingBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeProductInitialize.class);
    
    @Autowired
    private PhoneProductService productService;
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        
        LOGGER.info("---> 加载产品开始.....");
        
        productService.initProductDate();
        
        LOGGER.info("---> 加载产品结束......");
    }



}
