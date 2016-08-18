/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.commons.factory;


import java.util.HashMap;
import java.util.Map;

import com.tsh.recharge.foundation.service.IPhoneRechargeService;
import com.vas.util.VasConstant;

/**
 * 根据供应商Id  创建对应的供应商api实例
 * 
 * @author zengzw
 * @date 2016年8月1日
 */
public class CreateSupplierFactory {

    private static Map<String, String> className = new HashMap<>();
    
    static{
        //TODO  跟商品那边配置对应的映射关系到数据库表，不应该在程序中写死
        className.put(VasConstant.SupplierInterface.GYPAY, "com.tsh.recharge.gaoypay.phone.serviceimpl.GaoYPhoneRechargeServiveImpl");
        className.put(VasConstant.SupplierInterface.OFPAY, "com.tsh.recharge.ofpay.phone.serviceimpl.OfPayPhoneRechargeServiveImpl");
    }

    
    /**
     * 动态创建服务商API 实例
     * 
     * @param spId 服务商ID
     * @return
     */
    public static IPhoneRechargeService create(String spId){
        String claz = className.get(spId);
        try {
            IPhoneRechargeService service = (IPhoneRechargeService)Class.forName(claz).newInstance();
            return service;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    
    /**
     * 获取所有供应商列表
     * 
     * @return
     */
    public static Map<String, String> getAllSupplier(){
        return className;
    }
}
