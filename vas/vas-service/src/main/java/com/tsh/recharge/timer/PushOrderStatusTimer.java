/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.timer;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Result;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.tsh.vas.service.bill.PushOrderHistoryService;
import com.tsh.vas.service.bill.TradingService;

/**
 * 推送订单状态
 * 
 * 场景：
 *    当第三方服务不可用时，我们定时的去更新推送这单的状态。 为了避免两边订单不一致的状况
 *    
 * @author zengzw
 * @date 2016年8月10日
 */
public class PushOrderStatusTimer {

    private static Logger logger = LoggerFactory.getLogger(PushOrderStatusTimer.class);


    @Autowired
    TradingService tradingService;

    @Autowired
    PushOrderHistoryService pushOrderHistoryService;

    @Autowired
    PrepaidRechargeService prepaidRechargeService;

    @Autowired
    PhoneRechargeFacade phoneRechargeFacade;

    public void start(){
        logger.info("-->PushOrderStatusTimer 推送订单状态定时器开始 ...");

        Result result = new Result();
        tradingService.queryRechargedLstTrading(result);
        List<TradingPo> lstTradingPo = result.getData();

        if(CollectionUtils.isNotEmpty(lstTradingPo)){
           
            logger.info("--> PushOrderStatusTimer 查询到 {} 条，需要通知的订单统计数据.",lstTradingPo.size());
            
            for(TradingPo tp: lstTradingPo){
                Long status = tp.getState();//状态

                phoneRechargeFacade.notifyOrderService(tp.getDepositNo(), tp.getId(), String.valueOf(status));

            }
        }

        logger.info("-->PushOrderStatusTimer 推送订单状态定时器执行结束.....");
    }

}
