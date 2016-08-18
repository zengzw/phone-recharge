package com.tsh.vas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dtds.platform.commons.utility.ListUtil;
import com.dtds.platform.web.userinfo.exclude.LoginHandler;

@Component
public class LoginHandlerImpl extends LoginHandler {

	private static List<String> EXCLUDES_LIST = new ArrayList<String>();

	@Override
	public List<String> getExcludeUris() {
		if (ListUtil.isEmpty(EXCLUDES_LIST)) {
			EXCLUDES_LIST.add("/index.do");
			EXCLUDES_LIST.add("/getUserName.do");
			EXCLUDES_LIST.add("/test/");
			EXCLUDES_LIST.add("/views/");
			EXCLUDES_LIST.add("/deposit/saveDeposit.do");
			EXCLUDES_LIST.add("/deposit/getDepositById.do");
			EXCLUDES_LIST.add("/deposit/delDepositById.do");
			EXCLUDES_LIST.add("/deposit/queryDepositPage.do");
			EXCLUDES_LIST.add("/recharge/ofpay_callback");
			EXCLUDES_LIST.add("/recharge/gaoy_recharge_callback");
			EXCLUDES_LIST.add("/recharge/test");
			
			
			/*EXCLUDES_LIST.add("/prepaidRecharge/toPrepaidRechargePage.do");
			EXCLUDES_LIST.add("/prepaidRecharge/getRechargePageInfo.do");*/
			
			//屏端请求
			EXCLUDES_LIST.add("/prepaidRecharge/getDenominationByToken.do");
			EXCLUDES_LIST.add("/prepaidRecharge/getOperatorsByMobile.do");
			
		}
		return EXCLUDES_LIST;
	}
}
