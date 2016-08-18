package com.tsh.vas.dao.bill;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dtds.platform.util.bean.Pagination;

import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.DepositStatisPo;

@Repository
@SuppressWarnings("all")
public class DepositStatisDao extends HibernateDao<DepositStatisPo, Long> {
	/**
	 * 新增充值接口对象
	 * @param result
	 * @param depositStatis
	 * @return
	 */
	public Result addDepositStatis(Result result,DepositStatisPo depositStatis)throws Exception{
		this.save(depositStatis);
		return result;
	}
	
	/**
	 * 批量新增充值接口对象
	 * @param result
	 * @param depositStatis
	 * @return
	 */
	public Result batchSaveDepositStatis(Result result, List<DepositStatisPo> depositStatis_list) throws Exception {
		this.batchSave(depositStatis_list);
		result.setData(null);
		return result;
	}
	
	/**
	 * 删除充值接口对象
	 * @param id 充值接口对象标识
	 * @return
	 */
	public Result deleteDepositStatis(Result result, Long id) throws Exception {
		int count = this.updateHql("delete DepositStatisPo where id=?",id);
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象
	 * @param result
	 * @param depositStatis
	 * @return
	 */
	public Result batchDelDepositStatis(Result result, List<DepositStatisPo> depositStatis_list)throws Exception{
		this.batchDelete(depositStatis_list);
		return result;
	}
	
	
	/**
	 * 批量删除充值接口对象ById
	 * @param result
	 * @param depositStatis
	 * @return
	 */
	public Result batchDelDepositStatisByIds(Result result,Long[] ids)throws Exception{
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
	public Result clearDepositStatis(Result result) {
		String sql = " truncate table deposit_statis ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}
	

	/**
	 * 更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result updateDepositStatis(Result result,DepositStatisPo depositStatisPo) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update DepositStatisPo set ");
		
													if(depositStatisPo.getSupplierId()!=null){
				hql.append("supplierId = ").append(depositStatisPo.getSupplierId());
			}
											if(depositStatisPo.getSupplierCode()!=null){
				hql.append("supplierCode = ").append(depositStatisPo.getSupplierCode());
			}
											if(depositStatisPo.getSupplierName()!=null){
				hql.append("supplierName = ").append(depositStatisPo.getSupplierName());
			}
											if(depositStatisPo.getSuccCount()!=null){
				hql.append("succCount = ").append(depositStatisPo.getSuccCount());
			}
											if(depositStatisPo.getFailCount()!=null){
				hql.append("failCount = ").append(depositStatisPo.getFailCount());
			}
											if(depositStatisPo.getSource()!=null){
				hql.append("source = ").append(depositStatisPo.getSource());
			}
											if(depositStatisPo.getSourceCode()!=null){
				hql.append("sourceCode = ").append(depositStatisPo.getSourceCode());
			}
							
		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(),depositStatisPo.getId());
		result.setData(count);
		return result;
	}
	
	
	/**
	 * 批量更新 充值接口对象
	 * @param result
	 * @return
	 */
	public Result batchUpdateDepositStatis(Result result,List<DepositStatisPo> depositStatis_list) throws Exception {
		this.batchUpdate(depositStatis_list);
		result.setData(null);
		return result;
	}
	
	
	/**
	 * 根据ID获取 充值接口对象
	 * @param result
	 * @return
	 */
	public Result getDepositStatisById(Result result,Long id) throws Exception{
		DepositStatisPo depositStatisPo = this.get(id);
		result.setData(depositStatisPo);
		return result;
	}
	
	
	/**
	 * 根据条件获取 充值接口对象列表
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryDepositStatisList(Result result,Page page,DepositStatisPo depositStatisPo){
		Criteria criteria = this.getSession().createCriteria(DepositStatisPo.class);
		if(null != depositStatisPo){
																		if(depositStatisPo.getSupplierId()!=null){
					criteria.add(Restrictions.eq("supplierId", depositStatisPo.getSupplierId()));
				}
															if(depositStatisPo.getSupplierCode()!=null){
					criteria.add(Restrictions.eq("supplierCode", depositStatisPo.getSupplierCode()));
				}
															if(depositStatisPo.getSupplierName()!=null){
					criteria.add(Restrictions.eq("supplierName", depositStatisPo.getSupplierName()));
				}
															if(depositStatisPo.getSuccCount()!=null){
					criteria.add(Restrictions.eq("succCount", depositStatisPo.getSuccCount()));
				}
															if(depositStatisPo.getFailCount()!=null){
					criteria.add(Restrictions.eq("failCount", depositStatisPo.getFailCount()));
				}
															if(depositStatisPo.getSource()!=null){
					criteria.add(Restrictions.eq("source", depositStatisPo.getSource()));
				}
															if(depositStatisPo.getSourceCode()!=null){
					criteria.add(Restrictions.eq("sourceCode", depositStatisPo.getSourceCode()));
				}
									}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}
	
	/**
     * 根据供应商ID 获取供应商对象
     * 
     * 
     * @return
     */
    public Result getDepositStatisBySpId(Result result, Long spId) throws Exception {
        String hql = "from DepositStatisPo where supplierId = ?";
        DepositStatisPo statisPo = this.findUnique(hql,spId);
        result.setData(statisPo);
        
        return result;
    }
    
    
    /**
     * 根据供应商ID 获取供应商对象
     * 
     * 
     * @return
     */
    public Result getDepositStatisList(Result result) throws Exception {
        String hql = "from DepositStatisPo";
        List<DepositStatisPo> lstStatisPo = this.find(hql);
        result.setData(lstStatisPo);
        return result;
    }
    
    /**
	 * 根据供应商ID 获取供应商对象
	 * @param result
	 * @param supplierId
	 * @return
	 * @throws Exception
	 */
	public Result getDepositStatisListBySupplierId(Result result,Long supplierId)throws Exception{
		String hql = "from DepositStatisPo where supplierId = ?";
		DepositStatisPo depositStatisPo = this.findUnique(hql, supplierId);
		result.setData(depositStatisPo);
		return result;
	}
}
