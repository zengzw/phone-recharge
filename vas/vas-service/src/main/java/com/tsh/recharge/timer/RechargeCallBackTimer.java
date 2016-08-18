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
import com.alibaba.fastjson.JSON;
import com.dtds.platform.util.bean.Result;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.factory.CreateSupplierFactory;
import com.tsh.recharge.foundation.model.OrderInfoVo;
import com.tsh.recharge.foundation.service.IPhoneRechargeService;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.service.bill.DepositService;
import com.tsh.vas.service.bill.TradingService;

/**
 * 针对于第三方服务没有调用callback url的，我们每隔4分钟主动去查询一次
 * 
 * 场景：供应平台’‘， 我们的平台 都有可能存在网络异常。供应平台发送了，我们这边没收到。或者供应商平台由于内部错误，没有主动给我们发通知。
 * 这个时候，我们需要手动根据 ’订单号‘ 去过去当前’订单‘的状态。做更新、相关服务的通知。
 * 
 * @author zengzw
 * @date 2016年8月4日
 */
public class RechargeCallBackTimer {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(RechargeCallBackTimer.class);

    @Autowired
    private TradingService tradingService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private PhoneRechargeFacade rechargeFacade;



    public void start(){
        Result result = new Result();
        tradingService.queryAllRechargeingRecodes(result);
        List<TradingPo> lstTP = result.getData();

        logger.info(">>RechargeCallBackTimer  执行callback查询定时任务开始.....");

        try{
            if(CollectionUtils.isNotEmpty(lstTP)){
                logger.info(">>RechargeCallBackTimer 执行callback订单任务需要处理的数据有：{}条",lstTP.size());

                for(TradingPo tp: lstTP){

                    //查询正在”充值中“状态的数据
                    depositService.getDepositRechargeingByTradingCode(result, tp.getTradingCode());
                    DepositPo depositPo = result.getData();
                    if(depositPo == null){
                        logger.info("没有找到可用的充值服务商,tradingCode:{}",tp.getTradingCode());
                        continue;
                    }

                    IPhoneRechargeService rechargeService = CreateSupplierFactory.create(depositPo.getSupplierCode());
                    if(!StringUtils.isEmpty(depositPo.getDepositCode())){
                        OrderInfoVo orderInfo =  rechargeService.queryOrderById(depositPo.getDepositCode());

                        //充值成功、失败，修改订单表trading,修改deposit表，修改成功率
                        if(orderInfo.getOrderStatus().equals(Configurations.OrderStatus.SUCCESS)
                                || orderInfo.getOrderStatus().equals(Configurations.OrderStatus.FAILED)){

                            //修改表的状态
                            boolean isDeal = rechargeFacade.rechargeAfterOperator(depositPo.getTradingCode(), depositPo.getSupplierId(),"", orderInfo.getOrderStatus());
                            
                            //通知订单
                            if(isDeal){
                                rechargeFacade.notifyOrderService(depositPo.getDepositCode(), depositPo.getTradingId(), orderInfo.getOrderStatus());
                            }
                        }
                        else if(orderInfo.getOrderStatus().equals(Configurations.OrderStatus.PARTSUCCESS)){
                            //部分成功 TODO 部分成功未处理
                            logger.info("部分成功,data:{}",JSON.toJSONString(orderInfo));
                        }

                        logger.info("-->RechargeCallBackTimer 其他充值状态：order status:{}",orderInfo.getOrderStatus());

                    }

                    //这里休眠1.5秒，查询订单的有接口有时间限制
                    Thread.sleep(1500);
                }
            }

            logger.info(">>RechargeCallBackTimer 执行callback 查询 定时任务完成.....");

        }catch(Exception e){
            logger.error(">>RechargeCallBackTimer 执行回调url查询定时任务出错了:{}",e.getMessage());
        }

    }
}
