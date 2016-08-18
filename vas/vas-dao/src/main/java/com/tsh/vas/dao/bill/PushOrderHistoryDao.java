package com.tsh.vas.dao.bill;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;
import com.tsh.vas.po.bill.PushOrderHistoryPo;

@Repository
@SuppressWarnings("all")
public class PushOrderHistoryDao extends HibernateDao<PushOrderHistoryPo, Long> {
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param pushOrderHistory
	 * @return
	 */
	public Result addPushOrderHistory(Result result,PushOrderHistoryPo pushOrderHistory)throws Exception{
		this.save(pushOrderHistory);
		return result;
	}
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param pushOrderHistory
	 * @return
	 */
	public Result batchSavePushOrderHistory(Result result, List<PushOrderHistoryPo> pushOrderHistory_list) throws Exception {
		this.batchSave(pushOrderHistory_list);
		result.setData(null);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deletePushOrderHistory(Result result, Long id) throws Exception {
		int count = this.updateHql("delete PushOrderHistoryPo where id=?",id);
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param pushOrderHistory
	 * @return
	 */
	public Result batchDelPushOrderHistory(Result result, List<PushOrderHistoryPo> pushOrderHistory_list)throws Exception{
		this.batchDelete(pushOrderHistory_list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ById
	 * @param result
	 * @param pushOrderHistory
	 * @return
	 */
	public Result batchDelPushOrderHistoryByIds(Result result,Long[] ids)throws Exception{
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
	public Result clearPushOrderHistory(Result result) {
		String sql = " truncate table push_order_history ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}
	

	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updatePushOrderHistory(Result result,PushOrderHistoryPo pushOrderHistoryPo) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update PushOrderHistoryPo set ");
		
													if(pushOrderHistoryPo.getPushStatus()!=null){
				hql.append("pushStatus = ").append(pushOrderHistoryPo.getPushStatus());
			}
											if(pushOrderHistoryPo.getPushTime()!=null){
				hql.append("pushTime = ").append(pushOrderHistoryPo.getPushTime());
			}
											if(pushOrderHistoryPo.getTradingCode()!=null){
				hql.append("tradingCode = ").append(pushOrderHistoryPo.getTradingCode());
			}
											if(pushOrderHistoryPo.getPushParams()!=null){
				hql.append("pushParams = ").append(pushOrderHistoryPo.getPushParams());
			}
							
		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(),pushOrderHistoryPo.getId());
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdatePushOrderHistory(Result result,List<PushOrderHistoryPo> pushOrderHistory_list) throws Exception {
		this.batchUpdate(pushOrderHistory_list);
		result.setData(null);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getPushOrderHistoryById(Result result,Long id) throws Exception{
		PushOrderHistoryPo pushOrderHistoryPo = this.get(id);
		result.setData(pushOrderHistoryPo);
		return result;
	}
	
	
	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryPushOrderHistoryList(Result result,Page page,PushOrderHistoryPo pushOrderHistoryPo){
		Criteria criteria = this.getSession().createCriteria(PushOrderHistoryPo.class);
		if(null != pushOrderHistoryPo){
																		if(pushOrderHistoryPo.getPushStatus()!=null){
					criteria.add(Restrictions.eq("pushStatus", pushOrderHistoryPo.getPushStatus()));
				}
															if(pushOrderHistoryPo.getPushTime()!=null){
					criteria.add(Restrictions.eq("pushTime", pushOrderHistoryPo.getPushTime()));
				}
															if(pushOrderHistoryPo.getTradingCode()!=null){
					criteria.add(Restrictions.eq("tradingCode", pushOrderHistoryPo.getTradingCode()));
				}
															if(pushOrderHistoryPo.getPushParams()!=null){
					criteria.add(Restrictions.eq("pushParams", pushOrderHistoryPo.getPushParams()));
				}
									}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}
	
}
