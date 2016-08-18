package com.tsh.dubbo.vas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.vas.po.IntInvokingLogPo;

@Repository
@SuppressWarnings("all")
public class IntInvokingLogDao extends HibernateDao<IntInvokingLogPo, Long> {
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result addIntInvokingLog(Result result,IntInvokingLogPo intInvokingLog)throws Exception{
		this.save(intInvokingLog);
		return result;
	}
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchSaveIntInvokingLog(Result result, List<IntInvokingLogPo> intInvokingLog_list) throws Exception {
		this.batchSave(intInvokingLog_list);
		result.setData(null);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deleteIntInvokingLog(Result result, Long id) throws Exception {
		int count = this.updateHql("delete IntInvokingLogPo where id=?",id);
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchDelIntInvokingLog(Result result, List<IntInvokingLogPo> intInvokingLog_list)throws Exception{
		this.batchDelete(intInvokingLog_list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ById
	 * @param result
	 * @param intInvokingLog
	 * @return
	 */
	public Result batchDelIntInvokingLogByIds(Result result,Long[] ids)throws Exception{
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
	public Result clearIntInvokingLog(Result result) {
		String sql = " truncate table int_invoking_log ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}
	

	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updateIntInvokingLog(Result result,IntInvokingLogPo intInvokingLogPo) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update IntInvokingLogPo set ");
		
													if(intInvokingLogPo.getOrderId()!=null){
				hql.append("orderId = ").append(intInvokingLogPo.getOrderId());
			}
											if(intInvokingLogPo.getOrderCode()!=null){
				hql.append("orderCode = ").append(intInvokingLogPo.getOrderCode());
			}
											if(intInvokingLogPo.getOpAction()!=null){
				hql.append("opAction = ").append(intInvokingLogPo.getOpAction());
			}
											if(intInvokingLogPo.getOpParam()!=null){
				hql.append("opParam = ").append(intInvokingLogPo.getOpParam());
			}
											if(intInvokingLogPo.getOpMessage()!=null){
				hql.append("opMessage = ").append(intInvokingLogPo.getOpMessage());
			}
											if(intInvokingLogPo.getState()!=null){
				hql.append("state = ").append(intInvokingLogPo.getState());
			}
											if(intInvokingLogPo.getSource()!=null){
				hql.append("source = ").append(intInvokingLogPo.getSource());
			}
											if(intInvokingLogPo.getSourceCode()!=null){
				hql.append("sourceCode = ").append(intInvokingLogPo.getSourceCode());
			}
											if(intInvokingLogPo.getType()!=null){
				hql.append("type = ").append(intInvokingLogPo.getType());
			}
											if(intInvokingLogPo.getPostTime()!=null){
				hql.append("postTime = ").append(intInvokingLogPo.getPostTime());
			}
											if(intInvokingLogPo.getRemarks()!=null){
				hql.append("remarks = ").append(intInvokingLogPo.getRemarks());
			}
							
		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(),intInvokingLogPo.getId());
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdateIntInvokingLog(Result result,List<IntInvokingLogPo> intInvokingLog_list) throws Exception {
		this.batchUpdate(intInvokingLog_list);
		result.setData(null);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getIntInvokingLogById(Result result,Long id) throws Exception{
		IntInvokingLogPo intInvokingLogPo = this.get(id);
		result.setData(intInvokingLogPo);
		return result;
	}
	
	
	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryIntInvokingLogList(Result result,Page page,IntInvokingLogPo intInvokingLogPo){
		Criteria criteria = this.getSession().createCriteria(IntInvokingLogPo.class);
		if(null != intInvokingLogPo){
																		if(intInvokingLogPo.getOrderId()!=null){
					criteria.add(Restrictions.eq("orderId", intInvokingLogPo.getOrderId()));
				}
															if(intInvokingLogPo.getOrderCode()!=null){
					criteria.add(Restrictions.eq("orderCode", intInvokingLogPo.getOrderCode()));
				}
															if(intInvokingLogPo.getOpAction()!=null){
					criteria.add(Restrictions.eq("opAction", intInvokingLogPo.getOpAction()));
				}
															if(intInvokingLogPo.getOpParam()!=null){
					criteria.add(Restrictions.eq("opParam", intInvokingLogPo.getOpParam()));
				}
															if(intInvokingLogPo.getOpMessage()!=null){
					criteria.add(Restrictions.eq("opMessage", intInvokingLogPo.getOpMessage()));
				}
															if(intInvokingLogPo.getState()!=null){
					criteria.add(Restrictions.eq("state", intInvokingLogPo.getState()));
				}
															if(intInvokingLogPo.getSource()!=null){
					criteria.add(Restrictions.eq("source", intInvokingLogPo.getSource()));
				}
															if(intInvokingLogPo.getSourceCode()!=null){
					criteria.add(Restrictions.eq("sourceCode", intInvokingLogPo.getSourceCode()));
				}
															if(intInvokingLogPo.getType()!=null){
					criteria.add(Restrictions.eq("type", intInvokingLogPo.getType()));
				}
															if(intInvokingLogPo.getPostTime()!=null){
					criteria.add(Restrictions.eq("postTime", intInvokingLogPo.getPostTime()));
				}
															if(intInvokingLogPo.getRemarks()!=null){
					criteria.add(Restrictions.eq("remarks", intInvokingLogPo.getRemarks()));
				}
									}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}
	
}
