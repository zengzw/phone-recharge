package com.tsh.dubbo.vas.api;

import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.vas.vo.DepositVo;

/**
 * 话费充值Api
 * @version 1.0.0 2016年8月2日<br>
 * @see 
 * @since JDK 1.7.0
 */
public interface PrepaidRechargeApi {
	/**
	 * 话费充值提交订单
	 * @param result
	 * @param depositVo
	 * @return
	 * @throws Exception
	 */
	public Result addDeposit(Result result,DepositVo depositVo)throws Exception;
}