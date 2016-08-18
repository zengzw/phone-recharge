/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.serviceimpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.dtds.platform.util.spring.SpringContextUtil;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.commons.exections.BusinessRuntimeException;
import com.tsh.recharge.foundation.commons.exections.SystemCodes;
import com.tsh.recharge.foundation.commons.request.ResponseMessage;
import com.tsh.recharge.foundation.model.OrderInfoVo;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.recharge.foundation.model.RechargeVo;
import com.tsh.recharge.foundation.service.IPhoneRechargeService;
import com.tsh.recharge.foundation.service.IRechargeCallback;
import com.tsh.recharge.foundation.utils.StringUtils;
import com.tsh.recharge.gaoypay.phone.parse.GaoYBeanParse;
import com.tsh.recharge.gaoypay.phone.request.GaoYPayRechargeRequest;
import com.tsh.recharge.gaoypay.phone.xmlbean.Mobile;
import com.tsh.recharge.gaoypay.phone.xmlbean.PostResult;
import com.tsh.recharge.gaoypay.phone.xmlbean.Product;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;
import com.tsh.vas.service.recharge.phone.PhoneProductService;

/**
 *  高阳 充值接口 实现类
 *  
 * @author zengzw
 * @date 2016年7月22日
 */
public class GaoYPhoneRechargeServiveImpl implements IPhoneRechargeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GaoYPhoneRechargeServiveImpl.class);

    private GaoYPayRechargeRequest request = new GaoYPayRechargeRequest();

    private PhoneProductService productService = SpringContextUtil.getBean(PhoneProductService.class);

    /**
     * 查询订单信息
     */
    public OrderInfoVo queryOrderById(String orderId) throws BusinessException {
        ResponseMessage message = request.queryOrderByOrderNo(orderId);
        if(message.hasError()){
            LOGGER.info("--> 获取订单信息出错。code:{} ",message.getCode());
            throw new BusinessException(message.getCode(), message.getMessage());
        }

        PostResult postResult = message.parseValueToObject(PostResult.class);
        return GaoYBeanParse.parseOrder(postResult);
    }


    /**
     * 检查手机号码状态是否用
     * 
     * @param mobileNum
     * @param price
     * @param depositId
     * @param rechargeCallback
     * @return
     * @throws BusinessException
     */
    private Product checkPhoneAvailable(String mobileNum, int price,Long depositId,IRechargeCallback rechargeCallback) throws BusinessException {
        ResponseMessage resultMessage = request.numberSegment(mobileNum);

        //日志操作
        String status = resultMessage.hasError() ? Configurations.OrderStatus.FAILED : Configurations.OrderStatus.RECHARGEING;
        rechargeCallback.availablePhoneLog(depositId,status, resultMessage.getInParametes(),resultMessage.getJsonValue());

        if(!resultMessage.hasError()){
            Mobile mobile = resultMessage.parseValueToObject(Mobile.class);
            //查数据库
            PhoneRechargeProduct prProduct= productService.getProductOfMobilePhone(mobile.getProvinceName(), mobile.getSpType(), price);
            if(prProduct != null){
                Product product = new Product();
                BeanUtils.copyProperties(prProduct, product);
                return product;
            }else{
                throw new BusinessException(SystemCodes.SYSTEM_ERROR, "没有找到相关产品");
            }


        }else{
            LOGGER.info("mobile:{}获取不到号码段。错误码:{}",mobileNum,resultMessage.getCode());

            throw new BusinessException(resultMessage.getCode(), resultMessage.getMessage());
        }
    }


    @Override 
    public RechargeVo recharge(String mobileNum, int price,Long depositId,IRechargeCallback rechargeCallback) throws BusinessException {
        
       LOGGER.info("--> GaoY pay phone recharge call.Mobile:{},price:{}",mobileNum,price);
       
        //检查手机状态是否可以用
        String orderNo = StringUtils.getOrderNo(Configurations.SP_TYPE_GY);
        Product product = null;
        try {
            product = checkPhoneAvailable(mobileNum, price,depositId,rechargeCallback);
        } catch (BusinessException e) {
            throw new BusinessRuntimeException(e.getErrCode(),e.getErrMsg());
        }

        //FIXME 有时间把日志记录的操作,用切面实现
        rechargeCallback.before(depositId, orderNo);

        //进行充值
        ResponseMessage reqeustResult =  request.recharge(orderNo, product.getProdId(), mobileNum);

        if(reqeustResult.hasError()){
            LOGGER.info("调用充值出错了,code:{},message:{}"+reqeustResult.getCode(),reqeustResult.getMessage());
            throw new BusinessRuntimeException(reqeustResult.getCode(), reqeustResult.getMessage());
            //记录日志
        }

        PostResult postResult =  reqeustResult.parseValueToObject(PostResult.class);
        RechargeVo rechargeVo =  GaoYBeanParse.parseRechargeInfo(postResult);
        rechargeVo.setSellerPrice(product.getProdPrice());

        //如果下单成功，显示充值中。；否则，充值失败。下单成功，不代表充值成功，成功以callback为准。
        if(rechargeVo.getOrderStauts().equals(Configurations.OrderStatus.SUCCESS)
                || rechargeVo.getOrderStauts().equals(Configurations.OrderStatus.PARTSUCCESS)){
            rechargeCallback.after(orderNo, product.getProdPrice(), Configurations.OrderStatus.RECHARGEING, reqeustResult.getInParametes(), reqeustResult.getJsonValue());
        }else{
            rechargeCallback.after(orderNo, product.getProdPrice(), Configurations.OrderStatus.FAILED, reqeustResult.getInParametes(), reqeustResult.getJsonValue());
        }
        
        LOGGER.info("--> GaoY pay phone recharge call end .Mobile:{},price:{}",mobileNum,price);
        
        return rechargeVo;
    }


    @Override
    public PhoneLocationVo queryPhoneType(String mobileNum) throws BusinessException{

        ResponseMessage responseMessage = request.numberSegment(mobileNum);
        if(responseMessage.hasError()){
            throw new BusinessException("", responseMessage.getMessage());
        }
        
        Mobile mobile = responseMessage.parseValueToObject(Mobile.class);
        return GaoYBeanParse.parsePhoneLocation(mobile);
    }

}
