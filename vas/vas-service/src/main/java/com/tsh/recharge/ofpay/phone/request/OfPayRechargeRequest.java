/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.ofpay.phone.request;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.SystemCodes;
import com.tsh.recharge.foundation.commons.request.BaseRechargeRquest;
import com.tsh.recharge.foundation.commons.request.ResponseMessage;
import com.tsh.recharge.foundation.utils.DateUtils;
import com.tsh.recharge.foundation.utils.HttpUtils;
import com.tsh.recharge.foundation.utils.JaxbUtil;
import com.tsh.recharge.foundation.utils.MD5Util;
import com.tsh.recharge.foundation.utils.PropertiesUtility;
import com.tsh.recharge.ofpay.phone.xmlbean.CardInfoElement;
import com.tsh.recharge.ofpay.phone.xmlbean.OrderInfoElement;

/**
 *  欧飞，接口请求
 *  
 * @author zengzw
 * @date 2016年7月22日
 */
public class OfPayRechargeRequest extends BaseRechargeRquest{

    /**
     * 根据手机号码 查询是否充值可用
     */
    public ResponseMessage queryPhoneInfo(String mobileNum,int price){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("userid", Configurations.OfPay.USERID);
        params.put("userpws", Configurations.OfPay.PASSWORD);
        params.put("phoneno",mobileNum);
        params.put("pervalue", price);
        params.put("version", Configurations.OfPay.VERSION);

        String inParamters = JSON.toJSONString(params);
        String result = HttpUtils.doGet(Configurations.OfPay.TELQUERYURL, params, HttpUtils.charset_gbk);
        System.out.println("调用结果:" + result);
        if(result == null){
            return getErrorMessage(SystemCodes.SYSTEM_ERROR,inParamters);

        }

        CardInfoElement cardInfo = JaxbUtil.converyToJavaBean(result, CardInfoElement.class);
        if(cardInfo.getRetCode().equals("1")){
            return getSuccessMessage(JSON.toJSONString(cardInfo),inParamters);
        }

        return getErrorMessage(cardInfo.getRetCode(),JSON.toJSONString(cardInfo),Configurations.SP_TYPE_OF,inParamters);
    }



    /**
     * 根据手机号码 + 充值爱面额查询充值是否可用
     * @deprecated
     */
    public  String telQuery(String mobileNum,int price){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("userid", Configurations.OfPay.USERID);
        params.put("userpws", Configurations.OfPay.PASSWORD);
        params.put("phoneno",mobileNum);
        params.put("pervalue", price);
        params.put("version", Configurations.OfPay.VERSION);

        String result = HttpUtils.doGet(Configurations.OfPay.TELQUERYURL, params, HttpUtils.charset_gbk);
        System.out.println("调用结果:" + result);

        return result;
    }
    
    
    
    
    /**
     * 根据手机号码 查询运营商类型
     *
     */
    public  String telQueryType(String mobileNum){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("mobilenum",mobileNum);
        
        String result = HttpUtils.doGet(Configurations.OfPay.QUERY_TYPE_URL, params, HttpUtils.charset_gbk);
        System.out.println("调用结果:" + result);
        
        return result;
    }

    public static void main(String[] args) {
        new OfPayRechargeRequest().queryByOrderNo("IP201608151821474141");
    }

    /**
     *根据订单号 查询当前充值的
     * 返回实际金额
     * 
     * 1充值成功，0充值中，9充值失败，-1找不到此订单。如果返回-1
     * 
     * 
     */
    public ResponseMessage queryByOrderNo(String orderNo){
        Map<String, Object> params = new LinkedHashMap();
        params.put("userid", Configurations.OfPay.USERID);
        params.put("userpws", Configurations.OfPay.PASSWORD);
        params.put("sporder_id", orderNo);
        
        StringBuffer buffer = new StringBuffer();
        for (String key : params.keySet()) {
            buffer.append(params.get(key));
        }
        buffer.append("OFCARD");
        String md5Str = MD5Util.encode(buffer.toString());
        params.put("md5_str",md5Str.toUpperCase());
        params.put("version",Configurations.OfPay.VERSION);
        
        String inParameter = JSON.toJSONString(params);
        String result = HttpUtils.doPost(Configurations.OfPay.QUERY_ORDERINFO_URL, params, HttpUtils.charset_gbk);
        System.out.println("调用结果:" + result);
        
        if(result == null){
            return getErrorMessage(SystemCodes.SYSTEM_ERROR,inParameter);
        }
        if(!JaxbUtil.validate(result, OrderInfoElement.class)){
            return  getErrorMessage(result,result,Configurations.SP_TYPE_OF,inParameter);
        }

        OrderInfoElement orderInfo = JaxbUtil.converyToJavaBean(result, OrderInfoElement.class);
        if(orderInfo.getRetCode().equals("1")){
            return getSuccessMessage(JSON.toJSONString(orderInfo),inParameter);
        }

        return getErrorMessage(orderInfo.getRetCode()+"", JSON.toJSONString(orderInfo), Configurations.SP_TYPE_OF,inParameter);
    }

    


    /**
     * 在线充值接口
     */
    public ResponseMessage recharge(String orderNo,String phoneNo,int price){
        Map<String, Object> params = new LinkedHashMap<String,Object>();
        params.put("userid", Configurations.OfPay.USERID);
        params.put("userpws", Configurations.OfPay.PASSWORD);
        params.put("cardid", "140101"); //快充 140101; 慢充：170101 
        params.put("cardnum",price); //面额
        params.put("sporder_id",orderNo); //订单号 注意：不能重复，保证唯一性
        params.put("sporder_time", DateUtils.dateToString(new Date(),"yyyyMMddHHmmss"));
        params.put("game_userid",phoneNo);

        StringBuffer buffer = new StringBuffer();
        for (String key : params.keySet()) {
            buffer.append(params.get(key));
        }
        buffer.append("OFCARD");
        String md5Str = MD5Util.encode(buffer.toString());
        params.put("md5_str",md5Str.toUpperCase());
        params.put("ret_url",Configurations.OfPay.BACK_URL);
        params.put("version",Configurations.OfPay.VERSION);

        String inParameter = JSON.toJSONString(params);
        String result =  HttpUtils.doGet(Configurations.OfPay.ONLINEORDERURL, params, HttpUtils.charset_gbk);
        System.out.println("result:" +result);

        if(result == null){
            return getErrorMessage(SystemCodes.SYSTEM_ERROR,inParameter);
        }

        if(!JaxbUtil.validate(result, OrderInfoElement.class)){
            return getErrorMessage(result,result,Configurations.SP_TYPE_OF,inParameter);
        }


        OrderInfoElement orderInfo = JaxbUtil.converyToJavaBean(result, OrderInfoElement.class);
        if(orderInfo.getRetCode()==1
                && (orderInfo.getGameState() != null 
                || orderInfo.getGameState().equals("1")
                || orderInfo.getGameState().equals("0"))){

            return  getSuccessMessage(JSON.toJSONString(orderInfo),inParameter);
        }

        return  getErrorMessage(orderInfo.getRetCode()+"", JSON.toJSONString(orderInfo),Configurations.SP_TYPE_OF,inParameter);
    }


    public  void test(){
      try {
        Properties properties =  PropertiesUtility.load("log4j.properties");
        System.out.println("-------"+properties.getProperty("log4j.rootLogger"));
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
