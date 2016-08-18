package com.tsh.vas.controller.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.tsh.vas.service.bill.ExtInvokingLogService;
import com.tsh.vas.vo.bill.ExtInvokingLogVo;
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
@RequestMapping("/extInvokingLog")
public class ExtInvokingLogController extends BaseController{
	@Autowired
	private ExtInvokingLogService extInvokingLogService;
	
	/**
	* 保存充值接口对象
	* @param result
	* @param extInvokingLog
	* @return
	*/
	@RequestMapping(value = "/saveExtInvokingLog.do")
	@ResponseBody
	public Result saveExtInvokingLog(@ModelAttribute ExtInvokingLogVo extInvokingLogVo)  {
		Result result = this.getResult();
		try {
			UserInfo user = result.getUserInfo();
			result = extInvokingLogService.saveExtInvokingLog(result, extInvokingLogVo,user);
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
	@RequestMapping(value = "/getExtInvokingLogById.do")
	@ResponseBody
	public Result getExtInvokingLogById(Long id){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			result = extInvokingLogService.getExtInvokingLogById(result,id,user);
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
	@RequestMapping(value = "/delExtInvokingLogById.do")
	@ResponseBody
	public Result delExtInvokingLogById(Long id){
		Result result = this.getResult();
		try {
			result = extInvokingLogService.deleteExtInvokingLog(result,id);
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
	@RequestMapping(value = "/queryExtInvokingLogPage.do")
	@ResponseBody
	public Pagination queryExtInvokingLogPage(int page,int rows,@ModelAttribute ExtInvokingLogVo extInvokingLogVo){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		Pagination pagination = null;
		try {
			Page page_num = new Page(page,rows);
			result =  extInvokingLogService.queryExtInvokingLogList(result,page_num,extInvokingLogVo,user);
			pagination = (Pagination)result.getData();
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		
		return pagination;
	}
	
	
	

}
