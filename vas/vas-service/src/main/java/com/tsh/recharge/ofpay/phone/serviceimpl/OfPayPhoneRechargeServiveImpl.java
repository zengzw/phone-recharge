/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.ofpay.phone.serviceimpl;




import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.commons.exections.BusinessRuntimeException;
import com.tsh.recharge.foundation.commons.request.ResponseMessage;
import com.tsh.recharge.foundation.model.OrderInfoVo;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.recharge.foundation.model.RechargeVo;
import com.tsh.recharge.foundation.service.IPhoneRechargeService;
import com.tsh.recharge.foundation.service.IRechargeCallback;
import com.tsh.recharge.foundation.utils.StringUtils;
import com.tsh.recharge.ofpay.phone.parse.OfPayBeanParse;
import com.tsh.recharge.ofpay.phone.request.OfPayRechargeRequest;
import com.tsh.recharge.ofpay.phone.xmlbean.CardInfoElement;
import com.tsh.recharge.ofpay.phone.xmlbean.OrderInfoElement;

/**
 *  欧飞 充值实现类
 * @author zengzw
 * @date 2016年7月22日
 */
public class OfPayPhoneRechargeServiveImpl implements IPhoneRechargeService{


    private OfPayRechargeRequest request = new OfPayRechargeRequest();

    private static final Logger LOG= LoggerFactory.getLogger(OfPayPhoneRechargeServiveImpl.class);

    //针对于三个字的省份
    private static List<String> lstProvince = new ArrayList<>();
    static{
        lstProvince.add("内蒙古");
        lstProvince.add("黑龙江");
    }


    public OrderInfoVo queryOrderById(String orderId) throws BusinessException {
        ResponseMessage respMesage = request.queryByOrderNo(orderId);
        if(respMesage.hasError()){
            LOG.info("-->获取订单出错了:code:{},orderId:{}",respMesage.getCode(),orderId);
            throw new BusinessException(respMesage.getCode(), respMesage.getMessage());
        }

        OrderInfoElement orderInfo = respMesage.parseValueToObject(OrderInfoElement.class);

        return OfPayBeanParse.parseOrder(orderInfo);


    }


    private CardInfoElement checkPhoneAvailable(String mobileNum, int price,Long depositId,IRechargeCallback rechargeCallback)  throws BusinessException{
        ResponseMessage respMesage = request.queryPhoneInfo(mobileNum, price);

        //日志操作
        String status = respMesage.hasError() ? Configurations.OrderStatus.FAILED : Configurations.OrderStatus.RECHARGEING;
        rechargeCallback.availablePhoneLog(depositId,status, respMesage.getInParametes(),respMesage.getJsonValue());

        if(respMesage.hasError()){
            LOG.info("-->获取手机归属地信息出错:code:"+respMesage.getCode());
            throw new BusinessException(respMesage.getCode(), respMesage.getMessage());
        }

        return respMesage.parseValueToObject(CardInfoElement.class);
    }



    @Override
    public RechargeVo recharge(String mobileNum, int price,Long depositId,IRechargeCallback rechargeCallback) throws BusinessException {
        
        LOG.info("--> OFpay Phone recharge call。mobile:{},price:{}......",mobileNum,price);
        //检查手机状态是否可以充值
        try {
            /*CardInfoElement cardInfo = */checkPhoneAvailable(mobileNum, price,depositId,rechargeCallback);
        } catch (BusinessException e) {
            throw new BusinessRuntimeException(e.getErrCode(), e.getErrMsg());
        }

        String orderNo = StringUtils.getOrderNo(Configurations.OrderNoType.OF);
        rechargeCallback.before(depositId, orderNo);

        //进行接口调用
        ResponseMessage respMessage = request.recharge(orderNo, mobileNum, price);
        if(respMessage.hasError()){
            rechargeCallback.after(orderNo, 0D, Configurations.OrderStatus.FAILED, respMessage.getInParametes(), respMessage.getJsonValue());
            throw new BusinessRuntimeException(respMessage.getCode(), respMessage.getMessage());
        }

        
        //转换成公共的对象返回
        OrderInfoElement orderInfo = respMessage.parseValueToObject(OrderInfoElement.class);
        RechargeVo rechargeVo = OfPayBeanParse.parseRechargeInfo(orderInfo);



        //如果下单成功，显示充值；否则，充值失败。下单成功，不代表充值成功。
        if(rechargeVo.getOrderStauts().equals(Configurations.RechargeStatus.SUCCESS)){
            rechargeCallback.after(orderNo, orderInfo.getOrderCash(), Configurations.OrderStatus.RECHARGEING, respMessage.getInParametes(), respMessage.getJsonValue());
        }else{
            rechargeCallback.after(orderNo, orderInfo.getOrderCash(), Configurations.OrderStatus.FAILED, respMessage.getInParametes(), respMessage.getJsonValue());
        }

        LOG.info("--> OFpay Phone recharge call end。mobile:{},price:{}......",mobileNum,price);
        
        return rechargeVo;
    }



    @Override
    public PhoneLocationVo queryPhoneType(String mobileNum) {
        String result = request.telQueryType(mobileNum);
        if(result != null){
            String[] splitResult = result.split("\\|");

            PhoneLocationVo phoneLocationVo = new PhoneLocationVo();
            phoneLocationVo.setProvinceName(getSpecialProvince(splitResult[1]));
            phoneLocationVo.setType(splitResult[2]);
            return phoneLocationVo;
        }

        return null;
    }


    /**
     * 截取到省份
     * 
     * @param content
     * @return
     */
    private  String getSpecialProvince(String content){
        String result = "未知";
        for(String s: lstProvince){
            if(content.startsWith(s)){
                result = content.substring(0,3);
                return result;
            }
        }

        result = content.substring(0,2);
        return result;
    }
    

}
