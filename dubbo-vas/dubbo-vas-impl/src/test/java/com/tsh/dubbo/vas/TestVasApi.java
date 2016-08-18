package com.tsh.dubbo.vas;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.vas.api.PrepaidRechargeApi;
import com.tsh.dubbo.vas.util.VasApiConstant;
import com.tsh.dubbo.vas.vo.DepositVo;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TestVasApi {

	@Autowired 
	PrepaidRechargeApi prepaidRechargeApi;
	
	private  Result result = new Result();
	
	@Test
	public void testAddUser() {
		try {
			DepositVo depositPo = new DepositVo();
			depositPo.setDepositCode("12");
			depositPo.setMobile("13545487447");
			depositPo.setNumberType(2l);
			depositPo.setPayAmount(100.00);
			depositPo.setPostAmount(99.00);
			depositPo.setPostTime(new Date());
			depositPo.setRemarks("test");
			depositPo.setSource(1l);
			depositPo.setSourceCode("32323232");
			depositPo.setState(VasApiConstant.BillStatus.rechargeIng);
			depositPo.setSupplierCode("32323232");
			depositPo.setSupplierId(1l);
			depositPo.setSupplierName("ewewewew");
			depositPo.setTradingCode("32323232");
			depositPo.setTradingId(2l);
			depositPo.setType(2l);
			depositPo.setWeight(63.35);
			prepaidRechargeApi.addDeposit(result, depositPo);
			System.out.println(result.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
