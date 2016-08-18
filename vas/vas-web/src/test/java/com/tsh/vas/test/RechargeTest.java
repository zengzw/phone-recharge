/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tsh.diamond.TshDiamondClient;
import com.tsh.recharge.ofpay.phone.request.OfPayRechargeRequest;

/**
 *
 * @author zengzw
 * @date 2016年7月29日
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class RechargeTest {

    OfPayRechargeRequest recompile = new OfPayRechargeRequest();
    
    @Autowired
    TshDiamondClient client;



    @Autowired
    public void testDiamond(){
       String conent =  client.getConfig("gy-callbackUrl");
       System.out.println(conent);
    }
    
    @Test
    public void gaoyRecharge(){
        recompile.test();
    }
    
   
}
