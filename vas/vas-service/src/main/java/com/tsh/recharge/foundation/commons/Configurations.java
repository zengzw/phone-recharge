/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.commons;




import com.tsh.diamond.TshDiamondClient;
import com.tsh.recharge.foundation.utils.MD5Util;

/**
 *  公共配置文件
 * @author zengzw
 * @date 2016年7月22日
 */
public class Configurations {

    private static TshDiamondClient client = TshDiamondClient.getInstance();

    private Configurations(){};

    static String ENV = "test";

    /**
     * redis 缓存key链接字符串
     */
    public static final String REDIS_KEY_SYMBOL = "-";

    public static final String SP_TYPE_GY = "GY";

    public static final String SP_TYPE_OF = "OF";


    /**
     * 订单号前缀
     *
     * @author zengzw
     * @date 2016年7月29日
     */
    public interface OrderNoType{

        /** 高阳平台 订单好前缀。没要求 **/

        String GY = "GY";

        /** 欧飞平台规则要求 要以IP开头 **/
        String OF = "IP";
    }


    /**
     *  环境变量
     */
    public interface ENVInfo{
        String DEV = "dev";

        String TEST = "test";

        String UAT = "uat";

        String PROD = "product";
    }


    /**
     * 手机类型
     *
     * @author zengzw
     * @date 2016年7月29日
     */
    public interface PhoneType{
        /** 固定电话 **/
        String  FIXED_PHONE = "固定电话";

        /** 移动电话 **/
        String  MOBILE_PHONE = "移动电话";

        /** 小灵通 **/
        String  PHS = "小灵通";
    }

    /**
     * 通用 订单状态。
     * 
     *
     * @author zengzw
     * @date 2016年7月27日
     */
    public interface OrderStatus{

        /** 初始状态 **/
        String INITIAL = "-1";

        /**正在充值 **/
        String RECHARGEING = "0"; 

        /**充值成功 **/
        String SUCCESS = "1";  

        /**部分成功 **/
        String PARTSUCCESS = "2"; 

        /** 充值失败 **/
        String FAILED = "3"; 

        /** 找不到此订单 **/
        String NOT_EXISTS = "4";
    }



    /**
     * 通用的 充值状态
     *
     * @author zengzw
     * @date 2016年7月27日
     */
    public interface RechargeStatus{

        /**下单成功**/
        String SUCCESS = "1";

        /**下单失败**/
        String ERROR = "0"; 

        /**支付失败 **/
        String PAY_ERROR = "2";

        /** 未知异常 **/
        String unknow_error = "3";


    }


    /**
     * 高阳配置信息
     *
     * @author zengzw
     * @date 2016年7月22日
     */
    public interface GaoYan{

        //        String AGENTID = "test_agent_id_1";

        String AGENTID = client.getConfig("gy-agentId");


        String merchantKey = client.getConfig("gy-merchantKey");

        /**
         * 商品查询接口
         */
        String PRODUCT_QUERY_URL = getGYPayEnvironment(ENV)+"/prodquery/directProduct.do";

        /**
         * 号码段查询
         */
        String NUMBER_SEGMENT_QUERY_URL = getGYPayEnvironment(ENV)+"/accegment/accsegment.do";


        /**
         * 订单查询接口
         */
        String ORDER_QUERY_URL = getGYPayEnvironment(ENV)+"/orderquery/directSearch.do";


        /**
         * 支付接口
         */
        String RECHARGE_URL = getGYPayEnvironment(ENV)+"/directfill/directFill.do";


        /**
         * 回调地址.单返回类型位1的时候，此callback会被调用。这里不适用。
         */
        String BACK_URL= "http://58.251.48.20:8093/gaoy_queryorder_callback";
    }


    /**
     * 欧飞配置信息
     *
     * @author zengzw
     * @date 2016年7月22日
     */
    public interface OfPay{

        String VERSION = "6.0";

        String USERID = client.getConfig("of-userId");

        String PASSWORD = MD5Util.encode(client.getConfig("of-password"));

        /*
         * 通过手机号码 查询是否可以充值
         */
        String TELCHECKURL = getOfpayEnvironment(ENV) + "/telcheck.do";

        /*
         * 通过手机号码 + 面额 查询是否可以充值
         */
        String TELQUERYURL= getOfpayEnvironment(ENV) + "/telquery.do";

        /*
         * 充值接口
         */
        String ONLINEORDERURL =  getOfpayEnvironment(ENV) + "/onlineorder.do";

        /*
         * 根据订单号查询充值状态
         * 
         */
        String QUERYURL = getOfpayEnvironment(ENV) +  "/api/query.do";


        String QUERY_ORDERINFO_URL = getOfpayEnvironment(ENV) +  "/queryOrderInfo.do";


        /**
         * 归属地查询
         */
        String QUERY_TYPE_URL = getOfpayEnvironment(ENV) + "/mobinfo.do";


        /*
         * 回调地址
         */
        String BACK_URL = getOfCallBack(ENV);

    }


    public static String getOfCallBack(String env){
      return client.getConfig("of-callbackUrl");
    }


    public static String getOfpayEnvironment(String env){
       return client.getConfig("of-reqeustUrl");

    }



    public static String getGYPayEnvironment(String env){
        return client.getConfig("gy-requestUrl");
    }

}
