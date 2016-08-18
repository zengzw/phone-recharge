package com.tsh.vas.controller.bill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.security.UserInfo;
import com.tsh.dubbo.bis.vo.CompanyServiceDetailVO;
import com.tsh.dubbo.gms.vo.VirtualGoodsPropertyValueVo;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.share.vo.AreaVo;
import com.tsh.vas.controller.BaseController;
import com.tsh.vas.metaq.vo.TradingVo;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.tsh.vas.vo.bill.DenVo;
import com.tsh.vas.vo.bill.OperatorsVo;
import com.tsh.vas.vo.bill.ReturnVo;
import com.vas.util.VasConstant;
import com.vas.util.VasStringUtil;

@Controller
@RequestMapping("/prepaidRecharge")
@SuppressWarnings("all")
public class PrepaidRechargeController extends BaseController{
	private static final Logger log = Logger.getLogger(PrepaidRechargeController.class);
	
	@Autowired
	private PrepaidRechargeService prepaidRechargeService;
	@Autowired
	private PhoneRechargeFacade phoneRechargeFacade;
	/**
	 * 根据imei返回需要的充值数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRechargeInfo.do")
	@ResponseBody
	public Result getRechargeInfo(@RequestParam(value="imei") String imei){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			//result = depositService.getDepositById(result,id,user);
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		return result;
	}
	
	/**
	 * 根据供应商id获取虚拟供应商的信息
	 * @return
	 */
	public Result getDymmySupplierList(){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			prepaidRechargeService.getDymmySupplierList(result,null);
		} catch (Exception e) {
			result = this.error(result, e);
		} finally{
			this.send(result);
		}
		return result;
	}
	/**
	 * 回写调配率
	 * @return
	 */
	public Result updateCompany(){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			List<CompanyServiceDetailVO> companyServiceDetailVOs = new ArrayList<CompanyServiceDetailVO>();
			CompanyServiceDetailVO cs1 = new CompanyServiceDetailVO();
			//cs1.setId(26l); // 服务id
			cs1.setSuccessRate(52.23); // 成功率
			cs1.setSupplierId(137l); // 供应商id
			cs1.setServiceTypeId(VasConstant.SupplierType.RECHARGE);
			companyServiceDetailVOs.add(cs1);
			prepaidRechargeService.updateCompany(result,companyServiceDetailVOs);
		} catch (Exception e) {
			result = this.error(result, e);
		} finally{
			this.send(result);
		}
		return result;
	}
	
	/**
	 * 进入话费充值页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toPrepaidRechargePage.do")
	public Object toPrepaidRechargePage(){
		Result result = this.getResult();
		ModelAndView mav = new ModelAndView();
		try {
			//prepaidRechargeService.get("13545678765");
			String mobile = "13545678765";
			//prepaidRechargeService.getMobileByTenpay(mobile);
			//prepaidRechargeService.getMobileByTaobao(mobile);
			
			//prepaidRechargeService.getAllSypplierByPropertyAndAreaId(result,100l,1l);
			
			prepaidRechargeService.getSupplierByProperty(result,1);
			
			
			prepaidRechargeService.getVitrualGoodsPropertyByGoodsId(result,1l);
			//prepaidRechargeService.getAllSupplierByProperty(result);
			
			int type = prepaidRechargeService.getOperators(mobile);
			System.out.println(type);
			mav.setViewName("index");
		} catch (Exception e) {
			this.error(result, e);
		} finally{
			this.send(result);
		}
		return mav;
	}
	/**
	 * ajax获取该网点可以使用的话费充值服务
	 * @Date 2016年8月4日<br>
	 */
	@RequestMapping(value="/getRechargePageInfo.do")
	@ResponseBody
	public void getRechargePageInfo(){
		Result result = this.getResult();
		try {
			//调用商品获取数据
			//prepaidRechargeService.getProductVoBytype(result,1l,1l);
		} catch (Exception e) {
			this.error(result, e);
		} finally{
			this.send(result);
		}
	}
	
	/**
	 * 该网点(县域)是否有话费充值服务(有则界面显示该功能，没有则隐藏)
	 * @author Administrator <br>
	 * @Date 2016年8月8日<br>
	 */
	@RequestMapping(value="/existPrepaidRecharge.do")
	@ResponseBody
	public void existPrepaidRecharge(@RequestParam(value="imei") String imei){
		Result result = this.getResult();
		try {
			
		} catch (Exception e) {
			
		} finally{
			
		}
	}
	/**
	 * 根据手机号码判断运营商类型
	 * @param mobile
	 * @return 1-移动 2-联通 3-电信 4-未知运营商
	 */
	@RequestMapping(value="/getOperatorsByMobiles.do")
	@ResponseBody
	public Object getOperatorsByMobiles(@RequestParam(value="mobile") String mobile){
		Result result = this.getResult();
		ReturnVo returnVo = new ReturnVo();
		try {
			AreaVo areaVo = prepaidRechargeService.getAreaByTelAreaName(result, "广东");
			TradingVo tradingVo = new TradingVo();
			tradingVo.setOrderCode("refed323232");
			tradingVo.setState(1l);
			tradingVo.setGoodsId(3434343l);
			tradingVo.setSkuId(12l);
			//发送消息
			//SendResult sendResult = prepaidRechargeService.sendMsg("vas_recharge_topic", by);
			boolean ii = prepaidRechargeService.sendMsgObject(result,"vas_recharge_topic", tradingVo, null);
			System.out.println(ii);
			//System.out.println(sendResult);
			int type = prepaidRechargeService.getOperators(mobile);
			returnVo.setStatus(200);
			returnVo.setData(type);
		} catch (Exception e) {
			this.error(result, e);
			returnVo.setStatus(500);
			returnVo.setData("");
		} finally{
			this.send(result);
		}
		return returnVo;
	}
	
	/**
	 * 根据手机号码查询运营商和面额
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/getOperatorsByMobile.do")
	@ResponseBody
	public Object getOperatorsByMobile(@RequestParam(value="mobile") String mobile){
		Result result = this.getResult();
		ReturnVo returnVo = new ReturnVo();
		OperatorsVo ov = new OperatorsVo();
		List<String> dens = new ArrayList<String>();
		try {
			List<VirtualGoodsPropertyValueVo> virtualGoodsInfoVos = prepaidRechargeService.getPropertyByAreaId(result, mobile).getData();
			for(VirtualGoodsPropertyValueVo vv : virtualGoodsInfoVos){
				String m = vv.getValueName();
				//dens.add(m.substring(0, m.indexOf("元")));
				m = m.replace("元", "").toString();
				dens.add(m);
			}
			dens = VasStringUtil.removeDuplicate(dens);
			Collections.sort(dens);
			ov.setDen(dens);
			PhoneLocationVo phoneLocationVo = phoneRechargeFacade.queryPhoneSegment(mobile);
			if(null != phoneLocationVo){
				ov.setOperatorsType(phoneLocationVo.getProvinceName() + phoneLocationVo.getType());
			}else{
				log.error("根据手机号码" +mobile+ "属无法获取归地");  
			}
			returnVo.setStatus(200);
			returnVo.setData(ov);
			returnVo.setMsg("查询成功");
		} catch (Exception e) {
			this.error(result, e);
			returnVo.setStatus(500);
			returnVo.setData("");
			returnVo.setMsg("查询失败" + e.getMessage());
		} finally{
			this.send(result);
		}
		return returnVo;
	}
	
	/**
	 * 获取所有面额
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/getDenominationByToken.do")
	@ResponseBody
	public Object getDenominationByToken(@RequestParam(value="token") String token){
		Result result = this.getResult();
		ReturnVo returnVo = new ReturnVo();
		DenVo denVo = new DenVo();
		List<String> dens = new ArrayList<String>();
		try {
			List<VirtualGoodsPropertyValueVo> virtualGoodsInfoVos = prepaidRechargeService.getPropertyByAreaId(result, null).getData();
			for(VirtualGoodsPropertyValueVo vv : virtualGoodsInfoVos){
				String m = vv.getValueName();
				m = m.replace("元", "").toString();
				dens.add(m);
				//dens.add(m.substring(0, m.indexOf("元")));
			}
			dens = VasStringUtil.removeDuplicate(dens);
			Collections.sort(dens);
			denVo.setDen(dens);
			returnVo.setStatus(200);
			returnVo.setData(denVo);
			returnVo.setMsg("查询成功");
		} catch (Exception e) {
			this.error(result, e);
			returnVo.setStatus(500);
			returnVo.setData("");
			returnVo.setMsg("查询失败");
		} finally{
			this.send(result);
		}
		return returnVo;
	}
	
	/**
	 * 屏端根据token获取供应商商品信息
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/getRechargeInfoByToken.do")
	@ResponseBody
	public Object getRechargeInfoByToken(@RequestParam("token") String token){
		ReturnVo returnVo = new ReturnVo();
		Result result = this.getResult();
		try {
			UserInfo userInfo = result.getUserInfo();
			//网点角色
			if(null != userInfo){
				if(4 == userInfo.getRoleType()){
					//网点角色登录
					int shopId = userInfo.getBizId().intValue();
					//prepaidRechargeService.getAllSypplierByPropertyAndAreaId(result, 1, shopId);
				}else{
					log.error(">> 获取到的token为：" + token + " userInfo是: " + userInfo.toString() +"roleType是：" + userInfo.getRoleType());
				}
			}else{
				log.error(">> 获取到的token为：" + token + " userInfo是null：" + userInfo);
			}
		} catch (Exception e) {
			this.error(result, e);
		} finally{
			this.send(result);
		}
		return returnVo;
	}
}

