package com.tsh.vas.service.bill;

import java.util.List;
import java.util.ArrayList;

import com.dtds.platform.util.bean.Result;

import org.springframework.stereotype.Service;

import com.dtds.platform.util.security.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Page;
import com.tsh.vas.po.bill.IntInvokingLogPo;
import com.tsh.vas.vo.bill.IntInvokingLogVo;
import com.tsh.vas.dao.bill.IntInvokingLogDao;


@Service
@SuppressWarnings("all")
public class IntInvokingLogService {
	@Autowired
	private IntInvokingLogDao intInvokingLogDao;
	
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result addIntInvokingLog(Result result,IntInvokingLogVo intInvokingLogVo)throws Exception{
		IntInvokingLogPo intInvokingLogPo = new IntInvokingLogPo();
		
		if (intInvokingLogVo != null) {
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
									}
		
		result = intInvokingLogDao.addIntInvokingLog(result,intInvokingLogPo);
		return result;
	}
	
	
	
	/**
	 * 保存 充值接口对象 带User对象
	 * @param result
	 * @return
	 */
	public Result saveIntInvokingLog(Result result,IntInvokingLogVo intInvokingLogVo,UserInfo user) throws Exception {
		if(intInvokingLogVo == null){
			result.setData("参数为空，保存失败");
			return result;
		}
		
		Long id = intInvokingLogVo.getId();
		result = intInvokingLogDao.getIntInvokingLogById(result,id);
		IntInvokingLogPo intInvokingLogPo  = (IntInvokingLogPo)result.getData();
		
		if (intInvokingLogPo != null) {
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
									}else{
			intInvokingLogPo = new IntInvokingLogPo();
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
										result = intInvokingLogDao.addIntInvokingLog(result,intInvokingLogPo);
		}
		return result;
	}
	
	
	
	/**
	 * 保存 充值接口对象
	 * @param result
	 * @return
	 */
	public Result saveIntInvokingLog(Result result,IntInvokingLogVo intInvokingLogVo) throws Exception {
		if(intInvokingLogVo == null){
			result.setData("参数为空，保存失败");
			return result;
		}
		
		Long id = intInvokingLogVo.getId();
		result = intInvokingLogDao.getIntInvokingLogById(result,id);
		IntInvokingLogPo intInvokingLogPo  = (IntInvokingLogPo)result.getData();
		
		if (intInvokingLogPo != null) {
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
									}else{
			intInvokingLogPo = new IntInvokingLogPo();
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
										result = intInvokingLogDao.addIntInvokingLog(result,intInvokingLogPo);
		}
		return result;
	}
	
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchSaveIntInvokingLog(Result result, List<IntInvokingLogVo> intInvokingLog_list) throws Exception {
		List<IntInvokingLogPo> list = new ArrayList<IntInvokingLogPo>();
		result = intInvokingLogDao.batchSaveIntInvokingLog(result,list);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deleteIntInvokingLog(Result result, Long id) throws Exception {
		result = intInvokingLogDao.deleteIntInvokingLog(result,id);
		return result;
	}
	/**
	 * 预留30天的内部系统日志
	 * @param result
	 * @return
	 */
	public Result ReservedIntInvokingLogThirty(Result result,int reservedDate) {
		int count = intInvokingLogDao.ReservedIntInvokingLogThirty(result,reservedDate).getData();
		result.setData(count);
		return result;
	}
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchDelIntInvokingLog(Result result, List<IntInvokingLogVo> intInvokingLog_list)throws Exception{
		List<IntInvokingLogPo> list = new ArrayList<IntInvokingLogPo>(); 
		intInvokingLogDao.batchDelete(list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ByIds
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchDelIntInvokingLogByIds(Result result,Long[] ids)throws Exception{
		intInvokingLogDao.batchDelIntInvokingLogByIds(result,ids);
		return result;
	}
	

	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryIntInvokingLogList(Result result,Page page,IntInvokingLogVo intInvokingLogVo){
		IntInvokingLogPo intInvokingLogPo = new IntInvokingLogPo();
		result = intInvokingLogDao.queryIntInvokingLogList(result,page,intInvokingLogPo);
		return result;
	}
	

	/**
	 * 根据条件获取 充值接口对象列表 带User
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryIntInvokingLogList(Result result,Page page,IntInvokingLogVo intInvokingLogVo,UserInfo user){
		IntInvokingLogPo intInvokingLogPo = new IntInvokingLogPo();
		/**
		*自行匹配需要查询的字段及值
		**/
		result = intInvokingLogDao.queryIntInvokingLogList(result,page,intInvokingLogPo);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象 带User对象
	 * @param result
	 * @return
	 */
	public Result getIntInvokingLogById(Result result,Long id,UserInfo user) throws Exception{
		result = intInvokingLogDao.getIntInvokingLogById(result,id);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getIntInvokingLogById(Result result,Long id) throws Exception{
		result = intInvokingLogDao.getIntInvokingLogById(result,id);
		return result;
	}
	
	
	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updateIntInvokingLog(Result result,IntInvokingLogVo intInvokingLogVo) throws Exception {
		Long id = intInvokingLogVo.getId();
		result = intInvokingLogDao.getIntInvokingLogById(result,id);
		IntInvokingLogPo intInvokingLogPo  = (IntInvokingLogPo)result.getData();
		if (intInvokingLogPo != null) {
																			if(intInvokingLogVo.getOrderId()!=null){
						intInvokingLogPo.setOrderId(intInvokingLogVo.getOrderId());
					}
																if(intInvokingLogVo.getOrderCode()!=null){
						intInvokingLogPo.setOrderCode(intInvokingLogVo.getOrderCode());
					}
																if(intInvokingLogVo.getOpAction()!=null){
						intInvokingLogPo.setOpAction(intInvokingLogVo.getOpAction());
					}
																if(intInvokingLogVo.getOpParam()!=null){
						intInvokingLogPo.setOpParam(intInvokingLogVo.getOpParam());
					}
																if(intInvokingLogVo.getOpMessage()!=null){
						intInvokingLogPo.setOpMessage(intInvokingLogVo.getOpMessage());
					}
																if(intInvokingLogVo.getState()!=null){
						intInvokingLogPo.setState(intInvokingLogVo.getState());
					}
																if(intInvokingLogVo.getSource()!=null){
						intInvokingLogPo.setSource(intInvokingLogVo.getSource());
					}
																if(intInvokingLogVo.getSourceCode()!=null){
						intInvokingLogPo.setSourceCode(intInvokingLogVo.getSourceCode());
					}
																if(intInvokingLogVo.getType()!=null){
						intInvokingLogPo.setType(intInvokingLogVo.getType());
					}
																if(intInvokingLogVo.getPostTime()!=null){
						intInvokingLogPo.setPostTime(intInvokingLogVo.getPostTime());
					}
																if(intInvokingLogVo.getRemarks()!=null){
						intInvokingLogPo.setRemarks(intInvokingLogVo.getRemarks());
					}
									}
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdateIntInvokingLog(Result result,List<IntInvokingLogVo> intInvokingLog_list) throws Exception {
		List<IntInvokingLogPo> list = new ArrayList<IntInvokingLogPo>(); 
		intInvokingLogDao.batchUpdateIntInvokingLog(result,list);
		return result;
	}
	
}
