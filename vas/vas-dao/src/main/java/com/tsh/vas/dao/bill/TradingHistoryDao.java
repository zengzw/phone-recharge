package com.tsh.vas.dao.bill;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;
import com.tsh.vas.po.bill.TradingHistoryPo;
import com.tsh.vas.po.bill.TradingPo;

@Repository
@SuppressWarnings("all")
public class TradingHistoryDao extends HibernateDao<TradingHistoryPo, Long> {
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param tradingHistory
	 * @return
	 */
	public Result addTradingHistory(Result result,TradingHistoryPo tradingHistory)throws Exception{
		this.save(tradingHistory);
		return result;
	}
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param tradingHistory
	 * @return
	 */
	public Result batchSaveTradingHistory(Result result, List<TradingHistoryPo> tradingHistory_list) throws Exception {
		this.batchSave(tradingHistory_list);
		result.setData(null);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deleteTradingHistory(Result result, Long id) throws Exception {
		int count = this.updateHql("delete TradingHistoryPo where id=?",id);
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param tradingHistory
	 * @return
	 */
	public Result batchDelTradingHistory(Result result, List<TradingHistoryPo> tradingHistory_list)throws Exception{
		this.batchDelete(tradingHistory_list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ById
	 * @param result
	 * @param tradingHistory
	 * @return
	 */
	public Result batchDelTradingHistoryByIds(Result result,Long[] ids)throws Exception{
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
	public Result clearTradingHistory(Result result) {
		String sql = " truncate table tradingHistory ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}
	

	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updateTradingHistory(Result result,TradingHistoryPo tradingHistoryPo) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update TradingHistoryPo set ");
		
				
		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(),tradingHistoryPo.getId());
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdateTradingHistory(Result result,List<TradingHistoryPo> tradingHistory_list) throws Exception {
		this.batchUpdate(tradingHistory_list);
		result.setData(null);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getTradingHistoryById(Result result,Long id) throws Exception{
		TradingHistoryPo tradingHistoryPo = this.get(id);
		result.setData(tradingHistoryPo);
		return result;
	}
	
	
	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryTradingHistoryList(Result result,Page page,TradingHistoryPo tradingHistoryPo){
		Criteria criteria = this.getSession().createCriteria(TradingHistoryPo.class);
		if(null != tradingHistoryPo){
					}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}
	
	

    
    /**
     * 根据外部订单号，查询订单对象
     * @param result
     * @return
     */
    public Result queryTradingHistoryByTradingCode(Result result,String tradingCode){
        String hql = "from TradingHistoryPo where tradingCode = ?";
        TradingPo tradingPo = this.findUnique(hql,tradingCode);
        result.setData(tradingPo);
        
        return result;
    }

    /**
     * @param result
     * @return
     */
    public Result deleteTradingHistoryByTradingCode(Result result,String tradingCode) {
        int count = this.updateHql("delete from TradingHistoryPo where tradingCode=?",tradingCode);
        result.setData(count);
        return result;
    }
    
    
	
}
