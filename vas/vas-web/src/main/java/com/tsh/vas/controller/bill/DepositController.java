package com.tsh.vas.controller.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;
import com.tsh.vas.service.bill.DepositService;
import com.tsh.vas.vo.bill.DepositVo;
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
@RequestMapping("/deposit")
public class DepositController extends BaseController{
	@Autowired
	private DepositService depositService;
	
	/**
	* 保存充值接口对象
	* @param result
	* @param deposit
	* @return
	*/
	@RequestMapping(value = "/saveDeposit.do")
	@ResponseBody
	public Result saveDeposit(@ModelAttribute DepositVo depositVo)  {
		Result result = this.getResult();
		try {
			UserInfo user = result.getUserInfo();
			result = depositService.saveDeposit(result, depositVo,user);
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
	@RequestMapping(value = "/getDepositById.do")
	@ResponseBody
	public Result getDepositById(Long id){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			result = depositService.getDepositById(result,id,user);
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
	@RequestMapping(value = "/delDepositById.do")
	@ResponseBody
	public Result delDepositById(Long id){
		Result result = this.getResult();
		try {
			result = depositService.deleteDeposit(result,id);
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
	@RequestMapping(value = "/queryDepositPage.do")
	@ResponseBody
	public Pagination queryDepositPage(int page,int rows,@ModelAttribute DepositVo depositVo){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		Pagination pagination = null;
		try {
			Page page_num = new Page(page,rows);
			result =  depositService.queryDepositList(result,page_num,depositVo,user);
			pagination = (Pagination)result.getData();
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		
		return pagination;
	}
	
	
	

}
