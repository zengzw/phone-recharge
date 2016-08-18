/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.controller.recharge.phone;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dtds.platform.util.bean.Result;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.utils.StringUtils;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.service.bill.DepositService;

/** 
 * 手机充值回调接口
 * 
 * @author zengzw
 * @date 2016年7月29日
 */
@Controller
@RequestMapping("/recharge")
public class RechargePhoneController {

    private static final Logger LOG = LoggerFactory.getLogger(RechargePhoneController.class);


    @Autowired
    private PhoneRechargeFacade rechargeFacade;

    @Autowired
    private DepositService depositService;


    @RequestMapping(value="/test",method ={ RequestMethod.GET,RequestMethod.POST})
    public void testHi(HttpServletRequest request,HttpServletResponse response) throws IOException{

        Resource resource = new ClassPathResource("configurations.properties");
        Properties gaoyProperties =  PropertiesLoaderUtils.loadProperties(resource);
        System.out.println("---- "+gaoyProperties.getProperty("test"));
    }
    /**
     * 易赛回调接口
     * @param request
     * @param response
     * @throws IOException 
     */
    @Deprecated
    @RequestMapping(value="/esaipay_callback",method ={ RequestMethod.GET,RequestMethod.POST})
    public void EsaiPayCallback(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String userNumber = request.getParameter("UserNumber");
        String inOrderNumber = request.getParameter("InOrderNumber");
        String outOrderNumber = request.getParameter("OutOrderNumber");
        String payResult = request.getParameter("PayResult");
        String customInfo = request.getParameter("CustomInfo");
        String recodeKey = request.getParameter("RecordKey");
        String orderType = request.getParameter("OrderType");


        System.out.println("userNumber:"+userNumber
                + "inOrderNumber:"+inOrderNumber
                + " outOrderNumber:"+outOrderNumber
                + " payResult :"+payResult
                +" customInfo : "+customInfo
                + " recodeKey :"+recodeKey
                + " orderType :"+orderType);

        if(payResult.equals("4")){
            System.out.println("--> 充值成功!");
        }else if(payResult.equals("5")){
            System.out.println("--> 充值失败！");
        }
        /*      
      String responseResult = "<?xml version=\"1.0\" encoding=\"GB2312\"?><root><result> success</result></root>";
      PrintWriter writer = response.getWriter();
      writer.println(responseResult);*/
    }


    /**
     * 欧飞 回调接口
     * @param request
     * @param response
     */
    @RequestMapping(value="/ofpay_callback",method ={RequestMethod.GET,RequestMethod.POST})
    public void OfPayCallback(HttpServletRequest request,HttpServletResponse response){
        String retCode = request.getParameter("ret_code"); //充值后状态，1代表成功，9代表撤消
        String orderNo = request.getParameter("sporder_id"); //SP订单号
        String postTime = request.getParameter("ordersuccesstime"); //处理时间
        String errMsg = request.getParameter("err_msg");  //失败原因(ret_code为1时，该值为空)

        LOG.info("-->Ofpay callback parameters# retCode:{},orderNo:{},postTime:{},errMsg:{}",
                new Object[]{retCode,orderNo,postTime,errMsg});


        if(StringUtils.isEmpty(orderNo)|| StringUtils.isEmpty(retCode)){
            LOG.info("--> Ofpay callback 参数为空");
            return;
        }


        String outParam = JSON.toJSONString(new Object[]{retCode,orderNo,postTime,errMsg});
        if(!StringUtils.isEmpty(retCode) && retCode.equals(Configurations.OrderStatus.SUCCESS)){
            LOG.info("--> ofpay 手机充值成功.......");
            rechargeSuccess(orderNo,outParam);
        }else{
            LOG.info("--> ofpay 手机充值失败");

            rechargeError(orderNo,outParam);

        }
    }



    /**
     * 高阳 充值结果回调接口
     * 
     * 代理商商城系统在接收到充值结果后将状态值（status）返回，返回其他信息均表示未收到充值结果
     *5分钟之后重新发送充值结果，直到代理商商城系统返回正确的充值结果：状态值（status）,或者重发的次数到达6次
     * @param request
     * @param response
     */
    @RequestMapping(value="/gaoy_recharge_callback",method ={RequestMethod.GET,RequestMethod.POST})
    public void gaoyRechargeCallback(HttpServletRequest request,HttpServletResponse response){
        String status = request.getParameter("status"); //订单金额  2充值成功  3部分成功4充值失败
        String orderNo = request.getParameter("orderid"); 
        String orderMoney = request.getParameter("ordermoney"); //订单金额
        String mobileBalance = request.getParameter("mobileBalance"); //手机余额
        String verifystring = request.getParameter("verifystring");


        LOG.info("-->Gaoy callback parameters# status:{},orderNo:{},"
                + "orderMoney:{},mobileBalance:{},verifystring:{}",
                new Object[]{status,orderNo,orderMoney,mobileBalance,verifystring});

        if(StringUtils.isEmpty(orderNo)|| StringUtils.isEmpty(status)){
            LOG.info("--> Gaoy callback 参数为空");
            return;
        }

        String outParam = JSON.toJSONString(new Object[]{status,orderNo,orderMoney,mobileBalance,verifystring});
        if(!StringUtils.isEmpty(status)){
            if(status.equals("2")){
                LOG.info("--> gaoypay 手机充值成功!");
                rechargeSuccess(orderNo, outParam);
            }
            else if(status.equals("3")){
                LOG.info("--> gaoypay 手机充值部分成功!,OrderNo:{},orderMoeny:{}",orderNo,orderMoney);
                //TODO 部分成功情况下。未充值上部分，如果你不想给用户退款，可以再次给他们充值未成功部分，需要重新下单
                //  如果不再冲了那就退款给用户
            }
            else{
                LOG.info("--> gaoypay 手机充值失败!");    
                rechargeError(orderNo,outParam);
            }
        }


        //不管成功失败，必须把状态返回给服务商。免得服务商多次重试调用
        try {
            response.getWriter().write(status);
        } catch (IOException e) {
            LOG.error("gaoyRechargeCallback error:"+e);
        }
    }




    private void rechargeSuccess(String orderNo,String outParam){
        Result result = new Result();
        depositService.getDepositByDepositCode(result, orderNo);
        DepositPo depositPo = result.getData();

        if(depositPo != null){
            boolean isDeal =  rechargeFacade.rechargeAfterOperator(depositPo.getTradingCode(),depositPo.getSupplierId(), outParam,Configurations.OrderStatus.SUCCESS);

            //状态通知订单部
            if(isDeal){
                rechargeFacade.notifyOrderService(depositPo.getDepositCode(),depositPo.getTradingId(),Configurations.OrderStatus.SUCCESS);
            }
            
        }else{
            LOG.info("-->callback 没有找到订单,订单号:{}",orderNo);
        }
    }


    /**
     * 部分成功的情况
     * @param orderNo
     * @param orderMoney 实际支付金额
     */
    private void partSuccess(String orderNo,int orderMoney){

        try {
            rechargeFacade.callPartSuccessRetryRecharge(orderNo, orderMoney);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void rechargeError(String orderNo,String outParam){
        Result result = new Result();
        //根据订单号获取充值的供应商
        depositService.getDepositByDepositCode(result, orderNo);
        DepositPo depositPo = result.getData();

        if(depositPo != null){
            rechargeFacade.retryRechargeError(depositPo);
        }else{
            LOG.info("--> callback 没有找到相关订单。订单号：【{}】",orderNo);
        }


    }



}
