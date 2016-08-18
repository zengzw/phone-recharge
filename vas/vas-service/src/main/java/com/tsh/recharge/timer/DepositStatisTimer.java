/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.timer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.bis.vo.CompanyServiceDetailVO;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.service.bill.DepositStatisService;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.vas.util.DigitUtils;
import com.vas.util.VasConstant;

/**
 *  定时 成功率统计 线程
 *
 * @author zengzw
 * @date 2016年8月9日
 */
public class DepositStatisTimer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepositStatisTimer.class);

    @Autowired
    private DepositStatisService statisService; 
    
    @Autowired
    private PrepaidRechargeService prepaidRechargeService;

    
    public void start(){
        LOGGER.info("-->DepositStatisTimer 成功率统计接口开始........");

        Result result = new Result();
        try {
            statisService.getDepositStatisList(result);
            
            List<DepositStatisPo> lStatisPos = result.getData();
            if(CollectionUtils.isNotEmpty(lStatisPos)){
                List<CompanyServiceDetailVO> lstCompServiceDetail = new ArrayList<>();
                
                for(DepositStatisPo dp : lStatisPos){
                    CompanyServiceDetailVO companyServiceDetailVO = new CompanyServiceDetailVO();
                    Long susCount = dp.getSuccCount();
                    Long failCount = dp.getFailCount();
                    
                    double calResult = (double) susCount / (susCount + failCount);
                    double percentResul = 0;
                    if(calResult >0){
                        percentResul = DigitUtils.round(calResult,2);
                    }
                    
                    companyServiceDetailVO.setSupplierId(dp.getSupplierId());
                    companyServiceDetailVO.setSuccessRate(percentResul);
                    companyServiceDetailVO.setServiceTypeId(VasConstant.SupplierType.RECHARGE);
                    companyServiceDetailVO.setSuccessCount(susCount);
                    companyServiceDetailVO.setFailCount(failCount);
                    
                    lstCompServiceDetail.add(companyServiceDetailVO);
                }
                
                //调用第三方服务修改成功率
                prepaidRechargeService.updateCompany(result, lstCompServiceDetail);
                
            }

            LOGGER.info("--> DepositStatisTimer 成功率统计接口结束");
            
        } catch (Exception e) {
            LOGGER.error("--> DepositStatisTimer 回写供应商成功率统计接口出错啦.......",e);
        }

    }
}
