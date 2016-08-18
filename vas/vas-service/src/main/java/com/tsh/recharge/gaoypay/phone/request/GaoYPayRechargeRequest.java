/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.request;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.SystemCodes;
import com.tsh.recharge.foundation.commons.request.BaseRechargeRquest;
import com.tsh.recharge.foundation.commons.request.ResponseMessage;
import com.tsh.recharge.foundation.utils.HttpUtils;
import com.tsh.recharge.foundation.utils.JaxbUtil;
import com.tsh.recharge.foundation.utils.MD5Util;
import com.tsh.recharge.foundation.utils.StringUtils;
import com.tsh.recharge.gaoypay.phone.parse.GaoYXmlToBean;
import com.tsh.recharge.gaoypay.phone.xmlbean.Accsegment;
import com.tsh.recharge.gaoypay.phone.xmlbean.AllProducts;
import com.tsh.recharge.gaoypay.phone.xmlbean.Mobile;
import com.tsh.recharge.gaoypay.phone.xmlbean.PostResult;
import com.tsh.recharge.gaoypay.phone.xmlbean.PostResultElement;
import com.tsh.recharge.gaoypay.phone.xmlbean.Product;

/**
 *  高阳 接口http 请求
 *  
 *  由于供应商接口中，如果错误的话，直接返回字符串错误码。因此这种情况下我们要封装成自己的对象返回。
 *  
 * @author zengzw
 * @date 2016年7月22日
 */
public class GaoYPayRechargeRequest extends BaseRechargeRquest{

    private static final Logger LOGGER = LoggerFactory.getLogger(GaoYPayRechargeRequest.class);

    /**
     * 产品查询接口
     * 
     *@return  product list
     * 
     */
    public ResponseMessage queryProduct(){
        Map<String, Object> params = new LinkedHashMap<String,Object>();
        params.put("agentid", Configurations.GaoYan.AGENTID);
        params.put("source", "esales"); //来源。固定值
        String verifyString = generateRquestVerify(params);
        params.put("verifystring",verifyString);
        System.out.println("--> 加密结果："+verifyString);

        String inParamter = JSON.toJSONString(params);
        String result =  HttpUtils.doGet(Configurations.GaoYan.PRODUCT_QUERY_URL, params, HttpUtils.charset_utf8);
        System.out.println("result:" +result);


        if(!JaxbUtil.validate(result,AllProducts.class)){
            LOGGER.info("--> numberSegment 返回结果xml解析出错!。result:{}",result);
            return getErrorMessage(SystemCodes.SYSTEM_ERROR,result,Configurations.SP_TYPE_GY,inParamter);
        }

        try {
            List<Product> lstProduct = GaoYXmlToBean.parseProduct(result);
            return getSuccessMessage(JSON.toJSONString(lstProduct));

        } catch (UnsupportedEncodingException e) {
            System.out.println("-->result:"+result);
            e.printStackTrace();
        }

        return getErrorMessage(SystemCodes.SYSTEM_ERROR);

    }


    /**
     * * 订单查询
     * 单查询接口有频率限制，1秒最多只能查1笔订单
     * 
     * @param orderId 订单好Id
     * @return String 
     * 
     */
    public ResponseMessage queryOrderByOrderNo(String orderId){
        Map<String, Object> params = new LinkedHashMap<String,Object>();
        params.put("agentid", Configurations.GaoYan.AGENTID);
        params.put("backurl", Configurations.GaoYan.BACK_URL);
        params.put("returntype", 2); //返回类型。2：xml,1:调用callback url。 这里写2
        params.put("orderid", orderId); //订单号 注意：不能重复，保证唯一性
        params.put("source", "esales"); //来源。固定值

        String verifyString = generateRquestVerify(params);
        params.put("verifystring",verifyString);
        System.out.println("--> 加密结果："+verifyString);


        String result =  HttpUtils.doGet(Configurations.GaoYan.ORDER_QUERY_URL, params, HttpUtils.charset_utf8);
        System.out.println("result:" +result);

        if(!JaxbUtil.validate(result,PostResultElement.class)){
            LOGGER.info("解析xml出错.code:{}",result);
            return getErrorMessage(result);
        }

        PostResult postResult =  GaoYXmlToBean.parseOrder(result);
        if(postResult.getResultNo().equals("2")
                || postResult.getResultNo().equals("1")
                || postResult.getResultNo().equals("3")){
            return getSuccessMessage(JSON.toJSONString(postResult));
        }

        return getErrorMessage(postResult.getResultNo(), JSON.toJSONString(postResult), Configurations.SP_TYPE_GY);

    }

    /**
     * 号码段 接口.
     * 
     * @param mobileNum 手机号码
     * @return  Mobile
     * 
     */
    public  ResponseMessage numberSegment(String mobileNum){
        Map<String, Object> params = new LinkedHashMap<String,Object>();
        params.put("agentid", Configurations.GaoYan.AGENTID);
        params.put("source", "esales"); //来源。固定值
        params.put("mobilenum",mobileNum); //手机号码
        String verifyString = generateRquestVerify(params);
        params.put("verifystring",verifyString);
        System.out.println("--> 加密结果："+verifyString);

        String inParams = JSON.toJSONString(params);
        String result =  HttpUtils.doGet(Configurations.GaoYan.NUMBER_SEGMENT_QUERY_URL, params, HttpUtils.charset_utf8);
        System.out.println("result:" +result);

        if(StringUtils.isEmpty(result)){
            return getErrorMessage(SystemCodes.SYSTEM_ERROR, "",Configurations.SP_TYPE_GY,inParams);
        }

        if(!JaxbUtil.validate(result,Accsegment.class)){
            LOGGER.info("--> numberSegment 返回结果xml解析出错!");
            return getErrorMessage(result, "",Configurations.SP_TYPE_GY,inParams);
        }

        try {
            Mobile mobile = GaoYXmlToBean.parseSegment(result);
            return getSuccessMessage(JSON.toJSONString(mobile),inParams);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解析出错.",e);
            return getErrorMessage(SystemCodes.SYSTEM_ERROR,"",inParams);
        }

    }

    public static void main(String[] args) {
        System.err.println(new GaoYPayRechargeRequest().numberSegment("15917169858").toJsonString());
    }

    /**
     * 充值接口
     * 
     * 下单返回1006 1011 无响应、超时时是否会调订单查询接口判断状态，而不是直接置为失败
     * 
     * @param orderNo 订单号，自己生成
     * @param prodId  产品Id，通过producQuery() 产品查询接口得到。
     * @param mobileNum  充值号码
     * @return  Json String  {@link PostResult}
     *           
     */

    public ResponseMessage recharge(String orderNo,String prodId,String mobileNum){
        Map<String, Object> params = new LinkedHashMap<String,Object>();
        params.put("prodid", prodId); //产品id
        params.put("agentid", Configurations.GaoYan.AGENTID);
        params.put("backurl", "");
        params.put("returntype", 2); //返回类型 1:backUrl,2:xml.2的时候 backurl 可以为空。
        params.put("orderid", orderNo); //订单号 注意：不能重复，保证唯一性
        params.put("mobilenum", mobileNum);
        params.put("source", "esales"); //来源。固定值
        params.put("mark","test"); //预留字段，原值返回

        String verifyString = generateRquestVerify(params);
        params.put("verifystring",verifyString);
        System.out.println("--> 加密结果："+verifyString);

        String inParameters = JSON.toJSONString(params);

        String result =  HttpUtils.doGet(Configurations.GaoYan.RECHARGE_URL, params, HttpUtils.charset_utf8);
        if(!JaxbUtil.validate(result,PostResultElement.class)){
            ResponseMessage resultMessage =  getErrorMessage(result);
            resultMessage.setInParametes(inParameters);
            return resultMessage;
        }

        PostResult postResult =  GaoYXmlToBean.parseOrder(result);
        if(postResult.getResultNo().equals("0000")){
            ResponseMessage responseMessage =  getSuccessMessage(JSON.toJSONString(postResult));
            responseMessage.setInParametes(inParameters);
            return responseMessage;
        }

        return getErrorMessage(postResult.getResultNo(), JSON.toJSONString(postResult), Configurations.SP_TYPE_GY,inParameters);
    }



    private String generateRquestVerify(Map<String, Object> params){
        StringBuffer buffer = new StringBuffer();
        for (String key : params.keySet()) {
            buffer.append(key+"="+params.get(key)).append("&");
        }
        buffer.append("merchantKey="+Configurations.GaoYan.merchantKey);

        return MD5Util.encode(buffer.toString());
    }
}


