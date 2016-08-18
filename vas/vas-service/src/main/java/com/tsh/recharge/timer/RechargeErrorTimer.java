/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.timer;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.dtds.platform.util.bean.Result;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.service.bill.TradingService;

/**
 * 针对于订单位初始状态的，超过1分钟未处理的订单。进行补发机制。
 * 
 * 场景：当机器重启，或者遇到一些断电等动作。这个时候过来了，有可能没有被触发消费到。所以得用一个定时器去轮休处理。
 * 
 * @author zengzw
 * @date 2016年8月4日
 */
public class RechargeErrorTimer {

    @Autowired
    private TradingService tradingService;

    @Autowired
    private PhoneRechargeFacade rechargeFacade;


    private static org.slf4j.Logger logger = LoggerFactory.getLogger(RechargeErrorTimer.class);

    @Autowired

    public void start(){
        Result result = new Result();
        tradingService.queryAllRechargeInitialRecodes(result);
        List<TradingPo> lstTradingPo = result.getData();

        logger.info(">>  执行RechargeError查询定时任务开始.....");

        try{
            if(CollectionUtils.isNotEmpty(lstTradingPo)){
                logger.info(">> 执行RechargeError 查询到数据：{}条",lstTradingPo.size());

                for(TradingPo tp:lstTradingPo){
                    if(!StringUtils.isEmpty(tp.getTradingCode())){
                        //进行充值调用
                        rechargeFacade.callRetryRecharge(tp.getTradingCode());
                    }
                }
            }

            logger.info(">> 执行RechargeError 查询 定时任务完成.....");

        }catch(Exception e){
            logger.error(">> 执行回调url查询定时任务出错了",e.getMessage());
        }

    }
}
