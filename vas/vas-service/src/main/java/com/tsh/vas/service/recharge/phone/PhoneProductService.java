/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.service.recharge.phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.request.ResponseMessage;
import com.tsh.recharge.gaoypay.phone.request.GaoYPayRechargeRequest;
import com.tsh.recharge.gaoypay.phone.xmlbean.Product;
import com.tsh.vas.dao.recharge.phone.PhoneRechargeProductDao;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;

/**
 *
 * @author zengzw
 * @date 2016年7月29日
 */

@Service
public class PhoneProductService{

    @Autowired
    private PhoneRechargeProductDao phoneRechargeDao;

    private GaoYPayRechargeRequest req = new GaoYPayRechargeRequest();

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneProductService.class);


    /**
     *  
     * 获取固定电话的产品
     * （城市 + 服务商类型 + 面值 +  服务类型 ） 确认一个产品
     * 
     * @param provinceId 城市ID
     * @param prodIspType 产品类型
     * @param prodContent 充值金额
     * @return
     */
    public PhoneRechargeProduct getProductOfFixedPhone(String provinceId,String prodIspType,Integer prodContent){
        validateParamters(provinceId, prodIspType, prodContent);

        return phoneRechargeDao.queryProduct(provinceId, prodIspType, prodContent,  Configurations.PhoneType.FIXED_PHONE);
    }


    /**
     * 获取小灵通的产品
     * （城市 + 服务商类型 + 面值 +  服务类型 ） 确认一个产品
     * 
     * @param provinceId 城市ID
     * @param prodIspType 产品类型
     * @param prodContent 充值金额
     */
    public PhoneRechargeProduct getProductOfPHS(String provinceId,String prodIspType,Integer prodContent){
        validateParamters(provinceId, prodIspType, prodContent);

        return phoneRechargeDao.queryProduct(provinceId, prodIspType, prodContent,  Configurations.PhoneType.PHS);
    }



    /**
     * 获取移动电话的产品
     * 
     * （城市 + 服务商类型 + 面值 +  服务类型 ） 确认一个产品
     * 
     * @param provinceId 城市ID
     * @param prodIspType 产品类型
     * @param prodContent 充值金额
     */
    public PhoneRechargeProduct getProductOfMobilePhone(String provinceId,String prodIspType,Integer prodContent){
        validateParamters(provinceId, prodIspType, prodContent);

        return phoneRechargeDao.queryProduct(provinceId, prodIspType, prodContent,  Configurations.PhoneType.MOBILE_PHONE);
    }


    /**
     * 批量保存
     * 
     * @param lstProducts
     */
    public void batchSaveProduct(List<PhoneRechargeProduct> lstProducts){
        phoneRechargeDao.batchSaves(lstProducts);
    }



    public void saveProduct(PhoneRechargeProduct prp){
        phoneRechargeDao.save(prp);
    }



    /**
     * 初始化充值产品，没天定时刷新
     * 
     * @return
     */
    public List<PhoneRechargeProduct> initProductDate(){
        List<Product> lstPro = getRequestProduct();
        List<PhoneRechargeProduct> lstProducts = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(lstPro)){
            LOGGER.info("---> 加载产品数位："+lstPro.size());

            for(Product p : lstPro){
                PhoneRechargeProduct target = new PhoneRechargeProduct();
                BeanUtils.copyProperties(p, target);
                target.setStatus("ON"); //默认状态位ON

                lstProducts.add(target);
            }
        }

        //先将数据清除，再新增。只做逻辑删除，因为后面统计需要。
        phoneRechargeDao.removeAllProducts();

        //批量新增。
        batchSaveProduct(lstProducts);

        return lstProducts;

    }


    /*
     * 初始加载Product 
     */
    private List<Product> getRequestProduct(){
        ResponseMessage responseMessage = req.queryProduct();
        if(responseMessage.hasError()){
            LOGGER.info(">> 加载高阳充值产品出错",responseMessage.getMessage());
            return Collections.emptyList();
        }

        List<Product> lstPro = JSON.parseObject(responseMessage.getValue(),new TypeReference<List<Product>>(){});
        return lstPro;
    }

    
    
    /*
     * 校验参数
     */
    private void validateParamters(String provinceId,String prodIspType,Integer prodContent){
        Assert.notNull(provinceId,"provinceId is required,it must not be null");

        Assert.notNull(prodIspType,"prodIspType is required,it must not be null");

        Assert.notNull(prodContent,"prodContentis required,it must not be null");
    }

}
