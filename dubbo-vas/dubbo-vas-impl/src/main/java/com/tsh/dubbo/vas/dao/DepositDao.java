package com.tsh.dubbo.vas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;
import com.tsh.dubbo.vas.po.DepositPo;

@Repository
@SuppressWarnings("all")
public class DepositDao extends HibernateDao<DepositPo, Long> {
	/**
	 * 新增充值接口对象
	 * 
	 * @param result
	 * @param deposit
	 * @return
	 */
	public Result addDeposit(Result result, DepositPo deposit) throws Exception {
		this.save(deposit);
		return result;
	}

	/**
	 * 批量新增充值接口对象
	 * 
	 * @param result
	 * @param deposit
	 * @return
	 */
	public Result batchSaveDeposit(Result result, List<DepositPo> deposit_list)
			throws Exception {
		this.batchSave(deposit_list);
		result.setData(null);
		return result;
	}

	/**
	 * 删除充值接口对象
	 * 
	 * @param id
	 *            充值接口对象标识
	 * @return
	 */
	public Result deleteDeposit(Result result, Long id) throws Exception {
		int count = this.updateHql("delete DepositPo where id=?", id);
		result.setData(count);
		return result;
	}

	/**
	 * 批量删除充值接口对象
	 * 
	 * @param result
	 * @param deposit
	 * @return
	 */
	public Result batchDelDeposit(Result result, List<DepositPo> deposit_list)
			throws Exception {
		this.batchDelete(deposit_list);
		return result;
	}

	/**
	 * 批量删除充值接口对象ById
	 * 
	 * @param result
	 * @param deposit
	 * @return
	 */
	public Result batchDelDepositByIds(Result result, Long[] ids)
			throws Exception {
		int count = 0;
		for (Long id : ids) {
			this.delete(id);
			count++;
		}
		result.setData(count);
		return result;
	}

	/**
	 * 清空表 充值接口对象
	 * 
	 * @param result
	 * @return
	 */
	public Result clearDeposit(Result result) {
		String sql = " truncate table deposit ";
		int count = this.getSession().createSQLQuery(sql).executeUpdate();
		result.setData(count);
		return result;
	}

	/**
	 * 更新 充值接口对象
	 * 
	 * @param result
	 * @return
	 */
	public Result updateDeposit(Result result, DepositPo depositPo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update DepositPo set ");

		if (depositPo.getDepositCode() != null) {
			hql.append("depositCode = ").append(depositPo.getDepositCode());
		}
		if (depositPo.getTradingId() != null) {
			hql.append("tradingId = ").append(depositPo.getTradingId());
		}
		if (depositPo.getTradingCode() != null) {
			hql.append("tradingCode = ").append(depositPo.getTradingCode());
		}
		if (depositPo.getWeight() != null) {
			hql.append("weight = ").append(depositPo.getWeight());
		}
		if (depositPo.getSupplierId() != null) {
			hql.append("supplierId = ").append(depositPo.getSupplierId());
		}
		if (depositPo.getSupplierCode() != null) {
			hql.append("supplierCode = ").append(depositPo.getSupplierCode());
		}
		if (depositPo.getSupplierName() != null) {
			hql.append("supplierName = ").append(depositPo.getSupplierName());
		}
		if (depositPo.getMobile() != null) {
			hql.append("mobile = ").append(depositPo.getMobile());
		}
		if (depositPo.getNumberType() != null) {
			hql.append("numberType = ").append(depositPo.getNumberType());
		}
		if (depositPo.getPostAmount() != null) {
			hql.append("postAmount = ").append(depositPo.getPostAmount());
		}
		if (depositPo.getPayAmount() != null) {
			hql.append("payAmount = ").append(depositPo.getPayAmount());
		}
		if (depositPo.getPostTime() != null) {
			hql.append("postTime = ").append(depositPo.getPostTime());
		}
		if (depositPo.getState() != null) {
			hql.append("state = ").append(depositPo.getState());
		}
		if (depositPo.getSource() != null) {
			hql.append("source = ").append(depositPo.getSource());
		}
		if (depositPo.getSourceCode() != null) {
			hql.append("sourceCode = ").append(depositPo.getSourceCode());
		}
		if (depositPo.getType() != null) {
			hql.append("type = ").append(depositPo.getType());
		}
		if (depositPo.getRemarks() != null) {
			hql.append("remarks = ").append(depositPo.getRemarks());
		}

		hql.append("where id = ?");
		int count = this.updateHql(hql.toString(), depositPo.getId());
		result.setData(count);
		return result;
	}

	/**
	 * 批量更新 充值接口对象
	 * 
	 * @param result
	 * @return
	 */
	public Result batchUpdateDeposit(Result result, List<DepositPo> deposit_list)
			throws Exception {
		this.batchUpdate(deposit_list);
		result.setData(null);
		return result;
	}

	/**
	 * 根据ID获取 充值接口对象
	 * 
	 * @param result
	 * @return
	 */
	public Result getDepositById(Result result, Long id) throws Exception {
		DepositPo depositPo = this.get(id);
		result.setData(depositPo);
		return result;
	}

	/**
	 * 根据条件获取 充值接口对象列表
	 * 
	 * @param result
	 * @param page
	 * @param screenAdvertisementQuery
	 * @return
	 */
	public Result queryDepositList(Result result, Page page, DepositPo depositPo) {
		Criteria criteria = this.getSession().createCriteria(DepositPo.class);
		if (null != depositPo) {
			if (depositPo.getDepositCode() != null) {
				criteria.add(Restrictions.eq("depositCode",
						depositPo.getDepositCode()));
			}
			if (depositPo.getTradingId() != null) {
				criteria.add(Restrictions.eq("tradingId",
						depositPo.getTradingId()));
			}
			if (depositPo.getTradingCode() != null) {
				criteria.add(Restrictions.eq("tradingCode",
						depositPo.getTradingCode()));
			}
			if (depositPo.getWeight() != null) {
				criteria.add(Restrictions.eq("weight", depositPo.getWeight()));
			}
			if (depositPo.getSupplierId() != null) {
				criteria.add(Restrictions.eq("supplierId",
						depositPo.getSupplierId()));
			}
			if (depositPo.getSupplierCode() != null) {
				criteria.add(Restrictions.eq("supplierCode",
						depositPo.getSupplierCode()));
			}
			if (depositPo.getSupplierName() != null) {
				criteria.add(Restrictions.eq("supplierName",
						depositPo.getSupplierName()));
			}
			if (depositPo.getMobile() != null) {
				criteria.add(Restrictions.eq("mobile", depositPo.getMobile()));
			}
			if (depositPo.getNumberType() != null) {
				criteria.add(Restrictions.eq("numberType",
						depositPo.getNumberType()));
			}
			if (depositPo.getPostAmount() != null) {
				criteria.add(Restrictions.eq("postAmount",
						depositPo.getPostAmount()));
			}
			if (depositPo.getPayAmount() != null) {
				criteria.add(Restrictions.eq("payAmount",
						depositPo.getPayAmount()));
			}
			if (depositPo.getPostTime() != null) {
				criteria.add(Restrictions.eq("postTime",
						depositPo.getPostTime()));
			}
			if (depositPo.getState() != null) {
				criteria.add(Restrictions.eq("state", depositPo.getState()));
			}
			if (depositPo.getSource() != null) {
				criteria.add(Restrictions.eq("source", depositPo.getSource()));
			}
			if (depositPo.getSourceCode() != null) {
				criteria.add(Restrictions.eq("sourceCode",
						depositPo.getSourceCode()));
			}
			if (depositPo.getType() != null) {
				criteria.add(Restrictions.eq("type", depositPo.getType()));
			}
			if (depositPo.getRemarks() != null) {
				criteria.add(Restrictions.eq("remarks", depositPo.getRemarks()));
			}
		}
		Pagination pagination = this.findPagination(page, criteria);
		result.setData(pagination);
		return result;
	}

}
