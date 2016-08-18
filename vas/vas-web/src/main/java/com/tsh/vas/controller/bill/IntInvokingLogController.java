package com.tsh.vas.controller.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.tsh.vas.service.bill.IntInvokingLogService;
import com.tsh.vas.vo.bill.IntInvokingLogVo;
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
@RequestMapping("/intInvokingLog")
public class IntInvokingLogController extends BaseController{
	@Autowired
	private IntInvokingLogService intInvokingLogService;
	
	/**
	* 保存充值接口对象
	* @param result
	* @param intInvokingLog
	* @return
	*/
	@RequestMapping(value = "/saveIntInvokingLog.do")
	@ResponseBody
	public Result saveIntInvokingLog(@ModelAttribute IntInvokingLogVo intInvokingLogVo)  {
		Result result = this.getResult();
		try {
			UserInfo user = result.getUserInfo();
			result = intInvokingLogService.saveIntInvokingLog(result, intInvokingLogVo,user);
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
	@RequestMapping(value = "/getIntInvokingLogById.do")
	@ResponseBody
	public Result getIntInvokingLogById(Long id){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		try {
			result = intInvokingLogService.getIntInvokingLogById(result,id,user);
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
	@RequestMapping(value = "/delIntInvokingLogById.do")
	@ResponseBody
	public Result delIntInvokingLogById(Long id){
		Result result = this.getResult();
		try {
			result = intInvokingLogService.deleteIntInvokingLog(result,id);
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
	@RequestMapping(value = "/queryIntInvokingLogPage.do")
	@ResponseBody
	public Pagination queryIntInvokingLogPage(int page,int rows,@ModelAttribute IntInvokingLogVo intInvokingLogVo){
		Result result = this.getResult();
		UserInfo user = result.getUserInfo();
		Pagination pagination = null;
		try {
			Page page_num = new Page(page,rows);
			result =  intInvokingLogService.queryIntInvokingLogList(result,page_num,intInvokingLogVo,user);
			pagination = (Pagination)result.getData();
		} catch (Exception e) {
			result = this.error(result, e);
		}finally {
			this.send(result);
		}
		
		return pagination;
	}
	
	
	

}
