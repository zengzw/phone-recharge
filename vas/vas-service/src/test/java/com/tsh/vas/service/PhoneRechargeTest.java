/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dtds.platform.util.bean.Result;
import com.tsh.diamond.TshDiamondClient;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.recharge.foundation.service.IRechargeCallback;
import com.tsh.recharge.gaoypay.phone.request.GaoYPayRechargeRequest;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.tsh.vas.service.bill.TradingHistoryService;
import com.tsh.vas.service.bill.TradingService;
import com.tsh.vas.service.recharge.phone.PhoneProductService;

/**
 *
 * @author zengzw
 * @date 2016年7月29日
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})
public class PhoneRechargeTest {

    @Autowired
    PhoneProductService service;

    @Autowired
    PhoneRechargeFacade facade;

    @Autowired
    IRechargeCallback callService;

    @Autowired
    TradingService tradingService;


    @Autowired
    TradingHistoryService trdhistory;


    @Autowired
    private PrepaidRechargeService preService;



    @Test
    public void testDiamond(){
        System.out.println("---"+Configurations.GaoYan.AGENTID);
    }


    @Test
    public void testAreadId() throws Exception{
        Result result = new Result();
        preService.getAreaByTelAreaName(result, "广东");

        System.out.println("----> "+result.getData());
    }

    @Test
    public void testRemove() throws Exception{
        Result result = new Result();
        tradingService.removeToHistory(result, "1112");
    }

    @Test
    public void testTrand() throws Exception{
        callService.after("111", 20D, "1", "", "");
    }

    @Test
    public void testSave(){
        PhoneRechargeProduct phone = new PhoneRechargeProduct();
        phone.setProdId("eee");
        phone.setProdContent(20);
        phone.setProdDelaytimes("102222222");
        phone.setProdIsptype("移动");
        phone.setProdPrice(20.3);
        phone.setProdProvinceid("广东");
        phone.setProdType("移动电话");
        phone.setStatus("on");

        service.saveProduct(phone);
    }

    @Test
    public void testQuery(){

        PhoneRechargeProduct product =  service.getProductOfMobilePhone("北京", "移动", 50);
        System.out.println(JSON.toJSONString(product));
    }


    @Test
    public void testCharge() throws Exception{
        facade.callRetryRecharge("85938223-19b1-444e-92db-8522ff57e05d");
    }
    
    
    @Test
    public void testQueryOrderStatus() throws Exception{
        GaoYPayRechargeRequest request = new GaoYPayRechargeRequest();
        request.queryOrderByOrderNo("GY201608162206354356");
    }

    @Test
    public void testQueryRecharge(){
        Result result = new Result();
        tradingService.queryAllRechargeInitialRecodes(result);

        System.out.println(JSON.toJSON(result.getData()));
    }

    @Test
    public void testPhoneType() throws BusinessException{
        PhoneLocationVo phoneLocationVo = facade.queryPhoneSegment("15917169858");
        System.out.println(JSON.toJSONString(phoneLocationVo));
    }

}
