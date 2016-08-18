package com.tsh.dubbo.vas.api.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.vas.api.PrepaidRechargeApi;
import com.tsh.dubbo.vas.dao.DepositDao;
import com.tsh.dubbo.vas.po.DepositPo;
import com.tsh.dubbo.vas.util.VasApiConstant;
import com.tsh.dubbo.vas.vo.DepositVo;
@SuppressWarnings({ "all" })
@Service
public class PrepaidRechargeApiImpl implements PrepaidRechargeApi{
	@Autowired
	private DepositDao depositDao;
	
	@Override
	public Result addDeposit(Result result, DepositVo depositVo)throws Exception {
		DepositPo depositPo = new DepositPo();
		depositPo.setDepositCode(depositVo.getDepositCode());
		depositPo.setMobile(depositVo.getMobile());
		depositPo.setNumberType(depositVo.getNumberType());
		depositPo.setPayAmount(depositVo.getPayAmount());
		depositPo.setPostAmount(depositVo.getPostAmount());
		depositPo.setPostTime(new Date());
		depositPo.setRemarks(depositVo.getRemarks());
		depositPo.setSource(depositVo.getSource());
		depositPo.setSourceCode(depositVo.getSourceCode());
		depositPo.setState(VasApiConstant.BillStatus.rechargeIng);
		depositPo.setSupplierCode(depositVo.getSourceCode());
		depositPo.setSupplierId(depositVo.getSupplierId());
		depositPo.setSupplierName(depositVo.getSupplierName());
		depositPo.setTradingCode(depositVo.getTradingCode());
		depositPo.setTradingId(depositVo.getTradingId());
		depositPo.setType(depositVo.getType());
		depositPo.setWeight(depositVo.getWeight());
		return depositDao.addDeposit(result, depositPo);
	}
	
}