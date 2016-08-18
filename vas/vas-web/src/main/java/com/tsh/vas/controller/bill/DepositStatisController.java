package com.tsh.vas.controller.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.tsh.vas.service.bill.DepositStatisService;
import com.tsh.vas.vo.bill.DepositStatisVo;
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
@RequestMapping("/depositStatis")
public class DepositStatisController extends BaseController{
	@Autowired
	private DepositStatisService depositStatisService;
	
	/**
	* 保存充值接口对象
	* @param result
	* @param depositStatis
	* @return
	*/
	@RequestMapping(value = "/saveDepositStatis.do")
	@ResponseBody
	public Result saveDepositStatis(@ModelAttribute DepositStatisVo depositStatisVo)  {
		Result result = this.getResult();
		try {
			UserInfo user = result.getUserInfo();
			result = depositStatisService.saveDepositStatis(result, depositStatisVo,user);
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
	@RequestMapping(value = "/getDepositStatisById.do")
	@ResponseBody
	public Result getDepositStatisById(Long id){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			result = depositStatisService.getDepositStatisById(result,id,user);
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
	@RequestMapping(value = "/delDepositStatisById.do")
	@ResponseBody
	public Result delDepositStatisById(Long id){
		Result result = this.getResult();
		try {
			result = depositStatisService.deleteDepositStatis(result,id);
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
	@RequestMapping(value = "/queryDepositStatisPage.do")
	@ResponseBody
	public Pagination queryDepositStatisPage(int page,int rows,@ModelAttribute DepositStatisVo depositStatisVo){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		Pagination pagination = null;
		try {
			Page page_num = new Page(page,rows);
			result =  depositStatisService.queryDepositStatisList(result,page_num,depositStatisVo,user);
			pagination = (Pagination)result.getData();
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		
		return pagination;
	}
	
	
	

}
