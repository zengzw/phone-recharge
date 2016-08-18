/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.facade;


import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.gms.vo.VirtualGoodsLadderPriceVo;
import com.tsh.dubbo.share.api.AddressApi;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.enums.EnumLogAction;
import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.commons.exections.BusinessRuntimeException;
import com.tsh.recharge.foundation.commons.factory.CreateSupplierFactory;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.recharge.foundation.model.RechargeVo;
import com.tsh.recharge.foundation.service.IPhoneRechargeService;
import com.tsh.recharge.foundation.service.IRechargeCallback;
import com.tsh.share.vo.AreaVo;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.service.bill.DepositService;
import com.tsh.vas.service.bill.DepositStatisService;
import com.tsh.vas.service.bill.ExtInvokingLogService;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.tsh.vas.service.bill.PrepaidRechargeService.Operators;
import com.tsh.vas.service.bill.TradingService;
import com.tsh.vas.vo.bill.DepositStatisVo;
import com.tsh.vas.vo.bill.DepositVo;
import com.tsh.vas.vo.bill.ExtInvokingLogVo;
import com.tsh.vas.vo.bill.TradingVo;
import com.vas.util.VasConstant;

/**
 * 手机充值 门面入口
 * 
 * @author zengzw
 * @date 2016年7月29日
 */
@Service
public class PhoneRechargeFacade {
    private static Logger logger = LoggerFactory.getLogger(PhoneRechargeFacade.class);

    @Autowired
    private DepositService depositService;

    @Autowired
    private TradingService tradingService;

    @Autowired
    private DepositStatisService statisService;

    @Autowired
    private ExtInvokingLogService extInvokingLogService;

    @Autowired
    private IRechargeCallback rechargeCallback;
    @Autowired
    private AddressApi addressApi;

    /**
     * dubble服务，第三方接口
     */
    @Autowired
    private PrepaidRechargeService prepaidRechargeService;

    /**
     * 根据订单号   实现轮休充值调用。
     * 
     * @param tradingId 
     * @throws Exception 
     */
    public  void callRetryRecharge(String tradingCode) throws Exception{
        Result result = new Result();

        //检查外部订单是否存在
        tradingService.getTradingByTradingCode(result, tradingCode);
        TradingPo tradingPo = result.getData();
        if(tradingPo == null){
            throw new BusinessRuntimeException("","订单不存在.tradingCode:"+tradingCode);
        }

        //检查状态
        if(tradingPo.getState() != Long.parseLong(Configurations.OrderStatus.INITIAL)
                && tradingPo.getState() != Long.parseLong(Configurations.OrderStatus.FAILED)){
            throw new BusinessRuntimeException("", "订单状态不是初始化或者失败状态，不能充值！status:"+tradingPo.getState()); 

        }

        //根据权重值，调用状态 查出可用供应商接口.根据订单号查询，根据权重排序取第一条。
        depositService.getDepositInitialByTradingCode(result, tradingCode);
        DepositPo depositPo = result.getData();
        if(depositPo == null){
            logger.info(">> 没有可用的供应商可用......");
            throw new BusinessRuntimeException("","没有可用的供应商");
        }

        //得到供应商ID，获取具体的充值实例
        String supplierCode = depositPo.getSupplierCode();
        IPhoneRechargeService rechargeService = CreateSupplierFactory.create(supplierCode);

        try{
            if(rechargeService != null){
                //修改充值前状态
                requestOperator(depositPo,Configurations.OrderStatus.RECHARGEING);

                //充值
                RechargeVo rechargeVo = rechargeService.recharge(depositPo.getMobile(),depositPo.getPostAmount().intValue(),depositPo.getId(), rechargeCallback);

                //如果失败了，重试
                if(rechargeVo.getOrderStauts().equals(Configurations.OrderStatus.FAILED)){

                    retryRechargeError(depositPo);
                }

            }else{
                logger.error(">> 供应商ID:{},没有对于的相关实例.",supplierCode);
            }

        }catch(BusinessRuntimeException exception){
            logger.error(">> 充值出错啦,spId:{},code:{},message:{}",new Object[]{supplierCode,exception.getErrCode(),exception.getMessage()});

            //修改供应商执行状态
            updateDeposit(depositPo, Configurations.OrderStatus.FAILED);

            updateTrading(depositPo, Configurations.OrderStatus.FAILED);

            retryRechargeError(depositPo);
        }
    }


    /**
     * 部分成功，业务处理
     * 
     * @param tradingId 
     * @throws Exception 
     */
    public void callPartSuccessRetryRecharge(String orderNo,int orderMoney) throws Exception{
        /*  Result result = new Result();
        depositService.getDepositByDepositCode(result, orderNo);
        DepositPo depositPo = result.getData();
        //用户实际金额
        int actualMoney = Integer.parseInt(depositPo.getPostAmount()+"");

        //计算应该退换或者补充金额。  退款金额=用户支付金额-用户支付金额*（实际完成金额/产品面值）
        int retryMoeney = actualMoney - actualMoney * (orderMoney/actualMoney);
        //得到供应商ID，获取具体的充值实例
        Long spId = depositPo.getSupplierId(); 
        IPhoneRechargeService rechargeService = CreateSupplierFactory.create(String.valueOf(spId));


        //检查外部订单是否存在
        tradingService.getTradingByTradingCode(result, depositPo.getTradingCode());
        TradingPo tradingPo = result.getData();
        if(tradingPo == null){
            throw new BusinessRuntimeException("","订单不存在.tradingCode:"+depositPo.getTradingCode());
        }
        //检查状态
        if(tradingPo.getState() != Long.parseLong(Configurations.OrderStatus.INITIAL)){
            throw new BusinessRuntimeException("", "订单状态不是初始化状态，不能充值！status:"+tradingPo.getState()); 
        }*/
    }


    /**
     * 获取手机号码段
     * 
     * @param phoneNum 手机号码
     * @return
     * @throws BusinessException 
     */
    public PhoneLocationVo queryPhoneSegment(String phoneNum) throws BusinessException{
        if(StringUtils.isEmpty(phoneNum)){
            throw new BusinessRuntimeException("", "phone number must not be null.");
        }

        Map<String, String> lstSupplier = CreateSupplierFactory.getAllSupplier();
        try {
            Iterator<Map.Entry<String, String>> it = lstSupplier.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();

                String tartgetClass = entry.getValue();
                IPhoneRechargeService rechargeService = (IPhoneRechargeService)Class.forName(tartgetClass).newInstance();
                PhoneLocationVo phoneLocationVo = rechargeService.queryPhoneType(phoneNum);
                if(phoneLocationVo != null){
                    return phoneLocationVo;
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

            logger.error("queryPhoneSegment error",e);
        }

        return null;
    }


    /**
     *  请求入口开始前，进行状态修改操作
     * 
     * @param depositPo
     * @param status
     */
    private void requestOperator(DepositPo depositPo,String status){
        try{
            updateDeposit(depositPo, status);

            updateTrading(depositPo, status);

        }catch(Exception e){
            logger.error("requestOperator error",e);
        }
    }




    /**
     * 修改供应商充值状态
     * 
     * @param depositPo
     * @throws Exception
     */
    public void updateDeposit(DepositPo depositPo,String status) throws Exception{
        DepositVo depositVo = new DepositVo();
        BeanUtils.copyProperties(depositPo, depositVo);
        depositVo.setState(Long.parseLong(status));

        Result result = new Result();
        depositService.updateDeposit(result, depositVo);
    }



    /**
     * 修改供外部订单状态
     * 
     * @param depositPo
     * @throws Exception
     */
    private void updateTrading(DepositPo depositPo,String status) throws Exception{
        TradingVo tradingVo = new TradingVo();
        tradingVo.setId(depositPo.getTradingId());
        tradingVo.setState(Long.parseLong(status));

        Result result = new Result();
        tradingService.updateTrading(result, tradingVo);
    }

    private void updateTrading(Long traidingId,String status) throws Exception{
        TradingVo tradingVo = new TradingVo();
        tradingVo.setId(traidingId);
        tradingVo.setState(Long.parseLong(status));

        Result result = new Result();
        tradingService.updateTrading(result, tradingVo);
    }


    /**
     * 将供应商相关信息 设置到 外部订单中
     * 
     * @param depositPo 第三分充值订单号（供应商平台） 
     * @param status   最终充值结果信息
     * @param pushStatus 推送状态
     * @throws Exception
     */
    private void updateTrading(DepositPo depositPo,String status,int pushStatus,Long goodsId,Long skuId) throws Exception{
        TradingVo tradingVo = new TradingVo();
        tradingVo.setId(depositPo.getTradingId());
        tradingVo.setState(Long.parseLong(status));
        tradingVo.setPushStatus(pushStatus);
        tradingVo.setSupplierCode(depositPo.getSupplierCode());
        tradingVo.setSupplierId(depositPo.getSupplierId());
        tradingVo.setSupplierName(depositPo.getSupplierName());
        tradingVo.setPayAmount(depositPo.getPayAmount());
        tradingVo.setGoodsId(goodsId);
        tradingVo.setSkuId(skuId);
        tradingVo.setDepositNo(depositPo.getDepositCode());

        Result result = new Result();
        tradingService.updateTrading(result, tradingVo);
    }


    /**
     * 修改供外部订单状态
     * 
     * @param depositPo
     * @throws Exception
     */
    public void updatePushOrderStatus(Long tradingId,String status) throws Exception{
        TradingVo tradingVo = new TradingVo();
        tradingVo.setId(tradingId);
        tradingVo.setPushStatus(Integer.parseInt(status));

        Result result = new Result();
        tradingService.updateTrading(result, tradingVo);
    }


    /**
     * 修改供应商执行成功、失败数
     * 
     * @param depositPo
     * @throws Exception
     */
    public void updateStatis(DepositPo depositPo,String status) throws Exception{
        Result result = new Result();

        statisService.getDepositStatisBySpId(result, depositPo.getSupplierId());
        DepositStatisPo depositStatisPo = result.getData();

        if(status.equals(Configurations.OrderStatus.SUCCESS)
                || status.equals(Configurations.OrderStatus.RECHARGEING)
                || status.equals(Configurations.OrderStatus.PARTSUCCESS)){
            depositStatisPo.setSuccCount(depositStatisPo.getSuccCount() + 1);
        }else{
            depositStatisPo.setFailCount(depositStatisPo.getFailCount() + 1);
        }

        DepositStatisVo vo = new DepositStatisVo();
        BeanUtils.copyProperties(depositStatisPo, vo);
        statisService.updateDepositStatis(result, vo);

    }


    /**
     * 添加执行操作日志
     * 
     * @param depositPo
     * @throws Exception
     */
    public void insertExtLog(DepositPo depositPo,String status,String action,String inParam,String outParam) throws Exception{
        ExtInvokingLogVo invokingLogVo = new ExtInvokingLogVo();
        invokingLogVo.setDepositId(depositPo.getId());
        invokingLogVo.setDepositCode(depositPo.getDepositCode());
        invokingLogVo.setOpAction(Long.parseLong(action));
        invokingLogVo.setOpParam(inParam);
        invokingLogVo.setOpMessage(outParam);
        invokingLogVo.setState(Long.parseLong(status));
        invokingLogVo.setPostTime(new Date());
        invokingLogVo.setSource(depositPo.getSource());
        invokingLogVo.setSourceCode(depositPo.getSourceCode());

        Result result = new Result();
        extInvokingLogService.addExtInvokingLog(result, invokingLogVo);

    }



    /**
     * 最后执行成功、或者失败的操作。
     * 
     * @param orderNo
     * @param outParam
     * @param status
     */
    public boolean rechargeAfterOperator(String tradingCode,Long spId,String outParam,String status){
        Result result = new Result();
        boolean isTrue = true;
        
        depositService.getDepositByCondition(result, tradingCode, spId);
        DepositPo depositPo = result.getData();
        Long supplierId = null;
        String operators = null;
        String den = null;

        try {
            supplierId = depositPo.getSupplierId();
            operators = Operators.getName(Integer.valueOf(String.valueOf(depositPo.getNumberType())));
            den = String.valueOf(depositPo.getPostAmount().intValue()) + "元";

            VirtualGoodsLadderPriceVo virtualGoodsLadderPriceVo = prepaidRechargeService.getGoodsInfo(result, supplierId,operators,den);

            if(virtualGoodsLadderPriceVo == null){
                return !isTrue;
            }

            //修改交易状态
            updateTrading(depositPo, status,VasConstant.PushStatus.NOTSEND,virtualGoodsLadderPriceVo.getGoodsId(),virtualGoodsLadderPriceVo.getSkuId());

            //修改服务商执行状态表
            updateDeposit(depositPo, status);

            //修改服务商成功失败率
            updateStatis(depositPo,status);

            //记录日志
            if(StringUtils.isNotEmpty(outParam)){
                insertExtLog(depositPo, status, EnumLogAction.Callback.getValue(), "", outParam);
            }

            //将服务商 移除到历史表，同时删除服务商表
            depositService.batctMoveToHistory(result, depositPo.getTradingCode());
            depositService.deleteDepositByTradingCode(result, depositPo.getTradingCode());

            //将推送状态修改位 “未推送”
            //updatePushOrderStatus(depositPo.getTradingId(), VasConstant.PushStatus.NOTSEND+"");

            //将Trading移除到History中
            // tradingService.removeToHistory(result, depositPo.getTradingCode());
            
        } catch (Exception e) {
            logger.error("rechargeAfterOperator error",e);
            return !isTrue;
        }

        return isTrue;
    }



    public void retryRechargeError(DepositPo depositPo){
        Result result = new Result();
        try {
            //查询是否还有可用服务商，有的话，继续轮休调用相关供应商
            depositService.getDepositInitialByTradingCode(result, depositPo.getTradingCode());
            DepositPo dp = result.getData();
            if(dp != null){
                callRetryRecharge(depositPo.getTradingCode());

            }else{//没有可用供应商。1：修改相关数据信息  2：通知订单系统
                logger.info(">> retry recharge 没有可用供应商,充值失败......");

                rechargeAfterOperator(depositPo.getTradingCode(),depositPo.getSupplierId(), "",Configurations.OrderStatus.FAILED);

                //通知订单系统
                notifyOrderService(depositPo.getDepositCode(), depositPo.getTradingId(), Configurations.OrderStatus.FAILED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通知订单
     * 
     * @param thirdOrderNo 第三方供应商订单号
     * @param status   最总推送状态
     */
    public void notifyOrderService(String thirdOrderNo,Long tradingId,String status){

        try {
            Result result = new Result();
            tradingService.getTradingById(result, tradingId);
            TradingPo tradingPo = result.getData();
            
            logger.info("tradingPo对象内容是:"+JSON.toJSONString(tradingPo));
            
            String mobile = tradingPo.getMobile();
           //根据手机号码获取归属地和运营商
			PhoneLocationVo phoneLocationVo = queryPhoneSegment(mobile);
			
			//根据省份名称获取省份id
			AreaVo areaVo = addressApi.getAreaByTelAreaName(result, phoneLocationVo.getProvinceName()).getData();
			logger.info("areaVo对象内容是:"+JSON.toJSONString(areaVo));
			
			
            if(null == areaVo){
            	logger.info("--> AreaVo is null......");
            	return ;
            }
            if(tradingPo.getPayAmount() == null){
            	tradingPo.setPayAmount(0D);
            }

            boolean isSend = prepaidRechargeService.senMsgToOrd(result,tradingPo.getOrderCode(),thirdOrderNo,status,
            		tradingPo.getSkuId(),tradingPo.getGoodsId(),tradingPo.getSupplierId(),tradingPo.getPayAmount(),areaVo.getId());

            String pushStatus = isSend?VasConstant.PushStatus.SUCESS+"" : VasConstant.PushStatus.ERROR+"";

            //修改订单推送状态  
            updatePushOrderStatus(tradingId, pushStatus);

            //如果成功，将订单移除到历史表
            if(isSend){
                tradingService.removeToHistory(result,tradingPo.getTradingCode());
            }

        } catch (Exception e) {
            logger.error("通知订单出错了:",e);
        }
    }


}
