/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.vas.service.recharge.phone;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtds.platform.util.bean.Result;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.enums.EnumLogAction;
import com.tsh.recharge.foundation.commons.exections.BusinessException;
import com.tsh.recharge.foundation.commons.exections.BusinessRuntimeException;
import com.tsh.recharge.foundation.service.IRechargeCallback;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.service.bill.DepositService;
import com.tsh.vas.vo.bill.DepositVo;


/**
 * 
 * @author zengzw
 * @date 2016年8月3日
 */
@Transactional(rollbackFor={BusinessException.class,BusinessRuntimeException.class})
@Service
public class RechargeCallbackService implements IRechargeCallback{

    @Autowired
    private  DepositService depositService;

    @Autowired
    private PhoneRechargeFacade rechargeFacde;
    
    


    public void before(Long depositId,String orderNo){
        Result result = new Result();
        try {
            result = depositService.getDepositById(result,depositId);
            DepositPo depositPo = result.getData();
            //回写外部订单号
            DepositVo depositVo = new DepositVo();
            BeanUtils.copyProperties(depositPo, depositVo);
            depositVo.setDepositCode(orderNo);
            
            depositService.updateDeposit(result, depositVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void after(String orderNo,Double price,String status,String inParam,String outParam){
        try {
            Result result = new Result();
            result = depositService.getDepositByDepositCode(result,orderNo);
            DepositPo dp = result.getData();
            if(dp == null){
                return;
            }
            
            //修改供应商的销售价格
            dp.setPayAmount(price);
            rechargeFacde.updateDeposit(dp, status);
            
            //添加日志
            rechargeFacde.insertExtLog(dp, status, EnumLogAction.RECHARGE.getValue(), inParam, outParam);

            //修改成功数
            rechargeFacde.updateStatis(dp, status);
            
        }catch(Exception exception){
            exception.printStackTrace();
        }

    }


    @Override
    public void availablePhoneLog(Long depositId,String status, String inParams, String outParams) {
        try {
            Result result = new Result();
            result = depositService.getDepositById(result,depositId);
            DepositPo dp = result.getData();
            
            //添加日志
            rechargeFacde.insertExtLog(dp, status,EnumLogAction.QUERY.getValue(), inParams, outParams);

            //修改成功数
            rechargeFacde.updateStatis(dp, status);
            
        }catch(Exception exception){
            exception.printStackTrace();
        }

    }

}
