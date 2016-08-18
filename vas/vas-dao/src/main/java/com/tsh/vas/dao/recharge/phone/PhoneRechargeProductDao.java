/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.dao.recharge.phone;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;

/**
 * 话费充值产品类
 * 
 * @author zengzw
 * @date 2016年7月29日
 */

@Repository
public class PhoneRechargeProductDao extends HibernateDao<PhoneRechargeProduct, String> {

    /**
     * 根据条件匹配相关产品
     * 
     * @param provinceId
     * @param prodIspType
     * @param prodContent
     * @param prodType
     * @return
     */
    public PhoneRechargeProduct queryProduct(String provinceId,String prodIspType,Integer prodContent,String prodType){
        String hql = "from PhoneRechargeProduct where prodProvinceid = ?"
                + "and prodIsptype=?  and prodContent=? and prodType=? and status='ON'";
        PhoneRechargeProduct phoneRechargeProduct = this.findUnique(hql,provinceId,prodIspType,prodContent,prodType);
 
        return phoneRechargeProduct;
    }

    
    /**
     * 逻辑删除所有的商品数据
     * 
     * @return
     */
    public int removeAllProducts(){
        String sql = " update phone_recharge_product  set status='OF' ";
        int count = this.getSession().createSQLQuery(sql).executeUpdate();
        
        return count;
    }

    
    /**
     * 批量新增或者修改
     * 
     * @param lstParams
     */
    public void batchSaves(List<PhoneRechargeProduct> lstParams){
       super.batchSaveOrUpdate(lstParams);
    }


}
