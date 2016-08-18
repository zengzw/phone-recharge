/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.request;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dtds.platform.data.redis.RedisSlave;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.gaoypay.phone.xmlbean.Product;

/**
 *  高阳  充值产品缓存
 *  
 *  项目启动加载初始化数据。初始化刷新线程。
 *  
 * @author zengzw
 * @date 2016年7月25日
 */
@Deprecated
public class CacheProductManager {

    private CacheProductManager(){}

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheProductManager.class);

    private static final String  FIXED_PHONE = "固定电话";

    private static final String  MOBILE_PHONE = "移动电话";

    private static final String  PHS = "小灵通";

    private static CacheProductManager cacheProduct = null;

    public static CacheProductManager getInstance() {
        if (cacheProduct == null) {
            synchronized (CacheProductManager.class) {
                if (cacheProduct == null) {
                    cacheProduct = new CacheProductManager();
                }
            }
        }
        return cacheProduct;
    }


    /**
     * 获取固定电话的产品
     * 
     * @param key 城市 + 服务商类型 + 面值 +  服务类型
     * @return 产品
     */
    public synchronized Product getProductOfFixedPhone(String key){
        if(key == null || key.equals("")){
            throw new IllegalArgumentException("key 参数为空");
        }
        key += Configurations.REDIS_KEY_SYMBOL + FIXED_PHONE;

        return getContent(key);
        
    }


    /**
     * 获取小灵通的产品
     * 
     * @param key 城市 + 服务商类型 + 面值 +  服务类型
     * @return 产品
     */
    public synchronized Product getProductOfPHS(String key){
        if(key == null || key.equals("")){
            throw new IllegalArgumentException("key 参数为空");
        }
        key += Configurations.REDIS_KEY_SYMBOL  + PHS;

        return getContent(key);
    }



    /**
     * 获取移动电话的产品
     * 
     * @param key 城市 + 服务商类型 + 面值 +  服务类型
     * @return 产品
     */
    public synchronized Product getProductOfMobilePhone(String key){
        if(key == null || key.equals("")){
            throw new IllegalArgumentException("key 参数为空");
        }
        key += Configurations.REDIS_KEY_SYMBOL  + MOBILE_PHONE;

        return getContent(key);
    }


    /**
     * 更新缓存数据
     * 
     * @param products
     */
    public void update(List<Product> products){
        setStore(products);
    }


    /**
     * 存储缓存数据
     * 
     * @param products
     */
    public void set(List<Product> products){
        setStore(products);
    }




    /**
     * 初始化数据
     */
    public void init(){
        LOGGER.info("初始化 高阳 充值服务产品数据........");

     //   List<Product> lstProducts =  request.queryProduct();

//       / set(lstProducts);

        LOGGER.info("初始化 高阳 充值服务产品数据结束........");
    }




    /**
     * @param products
     */
    private void setStore(List<Product> products) {
        if(products == null || products.size() ==0){
            return;
        }

        RedisSlave slave = RedisSlave.getInstance();
        for(Product p: products){
            String key = p.getProdProvinceid() + Configurations.REDIS_KEY_SYMBOL  +
                    p.getProdIsptype() + Configurations.REDIS_KEY_SYMBOL  +
                    p.getProdContent() + Configurations.REDIS_KEY_SYMBOL  +
                    p.getProdType();

            String value = JSON.toJSONString(p);

            //redis set
            slave.setObject(key, value.getBytes(), 0);
        }
    }
    

    /**
     * @param key
     */
    private Product getContent(String key) {
        RedisSlave redis =  RedisSlave.getInstance();
        //判断key不否存在，拉取最新的数据
        if(!redis.exists(key)){
            LOGGER.info("key:{} 不存在，拉取最新数据。",key);
            
            init();
        }

        //从redis中取值
        String productJson = redis.getString(key);
        if(productJson != null){
            return JSON.parseObject(productJson,Product.class);
        }
        
        return null;
    }



}
