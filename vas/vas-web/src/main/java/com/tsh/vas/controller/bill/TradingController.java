package com.tsh.vas.controller.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.tsh.vas.service.bill.TradingService;
import com.tsh.vas.vo.bill.TradingVo;
import com.tsh.vas.controller.BaseController;
import com.dtds.platform.util.security.UserInfo;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/trading")
public class TradingController extends BaseController{
	@Autowired
	private TradingService tradingService;
	
	/**
	* 保存充值接口对象
	* @param result
	* @param trading
	* @return
	*/
	@RequestMapping(value = "/saveTrading.do")
	@ResponseBody
	public Result saveTrading(@ModelAttribute TradingVo tradingVo)  {
		Result result = this.getResult();
		try {
			UserInfo user = result.getUserInfo();
			result = tradingService.saveTrading(result, tradingVo,user);
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		return result;
	}
	
	
	/**
	 * 根据ID查询充值接口对象的数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTradingById.do")
	@ResponseBody
	public Result getTradingById(Long id){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			result = tradingService.getTradingById(result,id,user);
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		return result;
	}
	
	
	
	/**
	* 根据ID删除充值接口对象
	* @param id
	* @return
	*/
	@RequestMapping(value = "/delTradingById.do")
	@ResponseBody
	public Result delTradingById(Long id){
		Result result = this.getResult();
		try {
			result = tradingService.deleteTrading(result,id);
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		return result;
	}
	
	
	
	
	/**
	 * @param pages
	 * @param rows
	 * @return 返回站内广告列表数据
	 */
	@RequestMapping(value = "/queryTradingPage.do")
	@ResponseBody
	public Pagination queryTradingPage(int page,int rows,@ModelAttribute TradingVo tradingVo){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		Pagination pagination = null;
		try {
			Page page_num = new Page(page,rows);
			result =  tradingService.queryTradingList(result,page_num,tradingVo,user);
			pagination = (Pagination)result.getData();
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		
		return pagination;
	}
	
	
	

}
