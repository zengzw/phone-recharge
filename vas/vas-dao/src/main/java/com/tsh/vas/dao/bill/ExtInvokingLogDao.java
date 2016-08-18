package com.tsh.vas.dao.bill;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;

import org.hibernate.criterion.Restrictions;

import com.dtds.platform.util.bean.Pagination;

import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.tsh.vas.po.bill.ExtInvokingLogPo;

@Repository
@SuppressWarnings("all")
public class ExtInvokingLogDao extends HibernateDao<ExtInvokingLogPo, Long> {
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param extInvokingLog
	 * @return
	 */
	public Result addExtInvokingLog(Result result,ExtInvokingLogPo extInvokingLog)throws Exception{
		this.save(extInvokingLog);
		return result;
	}
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param extInvokingLog
	 * @return
	 */
	public Result batchSaveExtInvokingLog(Result result, List<ExtInvokingLogPo> extInvokingLog_list) throws Exception {
		this.batchSave(extInvokingLog_list);
		result.setData(null);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deleteExtInvokingLog(Result result, Long id) throws Exception {
		int count = this.updateHql("delete ExtInvokingLogPo where id=?",id);
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param extInvokingLog
	 * @return
	 */
	public Result batchDelExtInvokingLog(Result result, List<ExtInvokingLogPo> extInvokingLog_list)throws Exception{
		this.batchDelete(extInvokingLog_list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ById
	 * @param result
	 * @param extInvokingLog
	 * @return
	 */
	public Result batchDelExtInvokingLogByIds(Result result,Long[] ids)throws Exception{
		int count = 0;
		for(Long id : ids){
			this.delete(id);
			count ++;
		}
		result.setData(count);
		return result;
	}
	
	
	
	/**
	 * 清空表 充值接口对象
	 * @param result
	 * @return
	 */
	public Result clearExtInvokingLog(Result result) {
		String sql = " truncate table ext_invoking_log ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}
	

	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updateExtInvokingLog(Result result,ExtInvokingLogPo extInvokingLogPo) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update ExtInvokingLogPo set ");
		
													if(extInvokingLogPo.getDepositId()!=null){
				hql.append("depositId = ").append(extInvokingLogPo.getDepositId());
			}
											if(extInvokingLogPo.getDepositCode()!=null){
				hql.append("depositCode = ").append(extInvokingLogPo.getDepositCode());
			}
											if(extInvokingLogPo.getOpAction()!=null){
				hql.append("opAction = ").append(extInvokingLogPo.getOpAction());
			}
											if(extInvokingLogPo.getOpParam()!=null){
				hql.append("opParam = ").append(extInvokingLogPo.getOpParam());
			}
											if(extInvokingLogPo.getOpMessage()!=null){
				hql.append("opMessage = ").append(extInvokingLogPo.getOpMessage());
			}
											if(extInvokingLogPo.getState()!=null){
				hql.append("state = ").append(extInvokingLogPo.getState());
			}
											if(extInvokingLogPo.getSource()!=null){
				hql.append("source = ").append(extInvokingLogPo.getSource());
			}
											if(extInvokingLogPo.getSourceCode()!=null){
				hql.append("sourceCode = ").append(extInvokingLogPo.getSourceCode());
			}
											if(extInvokingLogPo.getType()!=null){
				hql.append("type = ").append(extInvokingLogPo.getType());
			}
											if(extInvokingLogPo.getPostTime()!=null){
				hql.append("postTime = ").append(extInvokingLogPo.getPostTime());
			}
											if(extInvokingLogPo.getRemarks()!=null){
				hql.append("remarks = ").append(extInvokingLogPo.getRemarks());
			}
							
		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(),extInvokingLogPo.getId());
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdateExtInvokingLog(Result result,List<ExtInvokingLogPo> extInvokingLog_list) throws Exception {
		this.batchUpdate(extInvokingLog_list);
		result.setData(null);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getExtInvokingLogById(Result result,Long id) throws Exception{
		ExtInvokingLogPo extInvokingLogPo = this.get(id);
		result.setData(extInvokingLogPo);
		return result;
	}
	
	
	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryExtInvokingLogList(Result result,Page page,ExtInvokingLogPo extInvokingLogPo){
		Criteria criteria = this.getSession().createCriteria(ExtInvokingLogPo.class);
		if(null != extInvokingLogPo){
																		if(extInvokingLogPo.getDepositId()!=null){
					criteria.add(Restrictions.eq("depositId", extInvokingLogPo.getDepositId()));
				}
															if(extInvokingLogPo.getDepositCode()!=null){
					criteria.add(Restrictions.eq("depositCode", extInvokingLogPo.getDepositCode()));
				}
															if(extInvokingLogPo.getOpAction()!=null){
					criteria.add(Restrictions.eq("opAction", extInvokingLogPo.getOpAction()));
				}
															if(extInvokingLogPo.getOpParam()!=null){
					criteria.add(Restrictions.eq("opParam", extInvokingLogPo.getOpParam()));
				}
															if(extInvokingLogPo.getOpMessage()!=null){
					criteria.add(Restrictions.eq("opMessage", extInvokingLogPo.getOpMessage()));
				}
															if(extInvokingLogPo.getState()!=null){
					criteria.add(Restrictions.eq("state", extInvokingLogPo.getState()));
				}
															if(extInvokingLogPo.getSource()!=null){
					criteria.add(Restrictions.eq("source", extInvokingLogPo.getSource()));
				}
															if(extInvokingLogPo.getSourceCode()!=null){
					criteria.add(Restrictions.eq("sourceCode", extInvokingLogPo.getSourceCode()));
				}
															if(extInvokingLogPo.getType()!=null){
					criteria.add(Restrictions.eq("type", extInvokingLogPo.getType()));
				}
															if(extInvokingLogPo.getPostTime()!=null){
					criteria.add(Restrictions.eq("postTime", extInvokingLogPo.getPostTime()));
				}
															if(extInvokingLogPo.getRemarks()!=null){
					criteria.add(Restrictions.eq("remarks", extInvokingLogPo.getRemarks()));
				}
									}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}
	
}
