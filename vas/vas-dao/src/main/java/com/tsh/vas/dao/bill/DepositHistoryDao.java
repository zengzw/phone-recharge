package com.tsh.vas.dao.bill;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Pagination;
import com.dtds.platform.util.bean.Result;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.tsh.vas.po.bill.DepositHistoryPo;
import com.tsh.vas.po.bill.DepositHistoryPo;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;

@Repository
@SuppressWarnings("all")
public class DepositHistoryDao extends HibernateDao<DepositHistoryPo, Long> {
    /**
     * 新增充值接口对象
     * 
     * @param result
     * @param deposit
     * @return
     */
    public Result addDepositHistory(Result result, DepositHistoryPo deposit) throws Exception {
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
    public Result batchSaveDepositHistory(Result result, List<DepositHistoryPo> deposit_list)
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
    public Result deleteDepositHistory(Result result, Long id) throws Exception {
        int count = this.updateHql("delete DepositHistoryPo where id=?", id);
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
    public Result batchDelDepositHistory(Result result, List<DepositHistoryPo> deposit_list)
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
    public Result batchDelDepositHistoryByIds(Result result, Long[] ids)
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
    public Result clearDepositHistory(Result result) {
        String sql = " truncate table deposit ";
        int count = this.getSession().createSQLQuery(sql).executeUpdate();
        result.setData(count);
        return result;
    }

   
    
    public Result updateDepositHistory(Result result, DepositHistoryPo DepositHistoryPo)
            throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update DepositHistoryPo set ");

        if (DepositHistoryPo.getDepositCode() != null) {
            hql.append("depositCode = ").append(DepositHistoryPo.getDepositCode());
        }
        if (DepositHistoryPo.getTradingId() != null) {
            hql.append("tradingId = ").append(DepositHistoryPo.getTradingId());
        }
        if (DepositHistoryPo.getTradingCode() != null) {
            hql.append("tradingCode = ").append(DepositHistoryPo.getTradingCode());
        }
        if (DepositHistoryPo.getWeight() != null) {
            hql.append("weight = ").append(DepositHistoryPo.getWeight());
        }
        if (DepositHistoryPo.getSupplierId() != null) {
            hql.append("supplierId = ").append(DepositHistoryPo.getSupplierId());
        }
        if (DepositHistoryPo.getSupplierCode() != null) {
            hql.append("supplierCode = ").append(DepositHistoryPo.getSupplierCode());
        }
        if (DepositHistoryPo.getSupplierName() != null) {
            hql.append("supplierName = ").append(DepositHistoryPo.getSupplierName());
        }
        if (DepositHistoryPo.getMobile() != null) {
            hql.append("mobile = ").append(DepositHistoryPo.getMobile());
        }
        if (DepositHistoryPo.getNumberType() != null) {
            hql.append("numberType = ").append(DepositHistoryPo.getNumberType());
        }
        if (DepositHistoryPo.getPostAmount() != null) {
            hql.append("postAmount = ").append(DepositHistoryPo.getPostAmount());
        }
        if (DepositHistoryPo.getPayAmount() != null) {
            hql.append("payAmount = ").append(DepositHistoryPo.getPayAmount());
        }
        if (DepositHistoryPo.getPostTime() != null) {
            hql.append("postTime = ").append(DepositHistoryPo.getPostTime());
        }
        if (DepositHistoryPo.getState() != null) {
            hql.append("state = ").append(DepositHistoryPo.getState());
        }
        if (DepositHistoryPo.getSource() != null) {
            hql.append("source = ").append(DepositHistoryPo.getSource());
        }
        if (DepositHistoryPo.getSourceCode() != null) {
            hql.append("sourceCode = ").append(DepositHistoryPo.getSourceCode());
        }
        if (DepositHistoryPo.getType() != null) {
            hql.append("type = ").append(DepositHistoryPo.getType());
        }
        if (DepositHistoryPo.getRemarks() != null) {
            hql.append("remarks = ").append(DepositHistoryPo.getRemarks());
        }

        hql.append("where id = ?");
        int count = this.updateHql(hql.toString(), DepositHistoryPo.getId());
        result.setData(count);
        return result;
    }

    /**
     * 批量更新 充值接口对象
     * 
     * @param result
     * @return
     */
    public Result batchUpdateDepositHistory(Result result, List<DepositHistoryPo> deposit_list)
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
    public Result getDepositHistoryById(Result result, Long id) throws Exception {
        DepositHistoryPo DepositHistoryPo = this.get(id);
        result.setData(DepositHistoryPo);
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
    public Result queryDepositHistoryList(Result result, Page page, DepositHistoryPo depositHistoryPo) 
    {
        Criteria criteria = this.getSession().createCriteria(DepositHistoryPo.class);
        if (null != depositHistoryPo) {
            if (depositHistoryPo.getDepositCode() != null) {
                criteria.add(Restrictions.eq("depositCode",
                        depositHistoryPo.getDepositCode()));
            }
            if (depositHistoryPo.getTradingId() != null) {
                criteria.add(Restrictions.eq("tradingId",
                        depositHistoryPo.getTradingId()));
            }
            if (depositHistoryPo.getTradingCode() != null) {
                criteria.add(Restrictions.eq("tradingCode",
                        depositHistoryPo.getTradingCode()));
            }
            if (depositHistoryPo.getWeight() != null) {
                criteria.add(Restrictions.eq("weight", depositHistoryPo.getWeight()));
            }
            if (depositHistoryPo.getSupplierId() != null) {
                criteria.add(Restrictions.eq("supplierId",
                        depositHistoryPo.getSupplierId()));
            }
            if (depositHistoryPo.getSupplierCode() != null) {
                criteria.add(Restrictions.eq("supplierCode",
                        depositHistoryPo.getSupplierCode()));
            }
            if (depositHistoryPo.getSupplierName() != null) {
                criteria.add(Restrictions.eq("supplierName",
                        depositHistoryPo.getSupplierName()));
            }
            if (depositHistoryPo.getMobile() != null) {
                criteria.add(Restrictions.eq("mobile", depositHistoryPo.getMobile()));
            }
            if (depositHistoryPo.getNumberType() != null) {
                criteria.add(Restrictions.eq("numberType",
                        depositHistoryPo.getNumberType()));
            }
            if (depositHistoryPo.getPostAmount() != null) {
                criteria.add(Restrictions.eq("postAmount",
                        depositHistoryPo.getPostAmount()));
            }
            if (depositHistoryPo.getPayAmount() != null) {
                criteria.add(Restrictions.eq("payAmount",
                        depositHistoryPo.getPayAmount()));
            }
            if (depositHistoryPo.getPostTime() != null) {
                criteria.add(Restrictions.eq("postTime",
                        depositHistoryPo.getPostTime()));
            }
            if (depositHistoryPo.getState() != null) {
                criteria.add(Restrictions.eq("state", depositHistoryPo.getState()));
            }
            if (depositHistoryPo.getSource() != null) {
                criteria.add(Restrictions.eq("source", depositHistoryPo.getSource()));
            }
            if (depositHistoryPo.getSourceCode() != null) {
                criteria.add(Restrictions.eq("sourceCode",
                        depositHistoryPo.getSourceCode()));
            }
            if (depositHistoryPo.getType() != null) {
                criteria.add(Restrictions.eq("type", depositHistoryPo.getType()));
            }
            if (depositHistoryPo.getRemarks() != null) {
                criteria.add(Restrictions.eq("remarks", depositHistoryPo.getRemarks()));
            }
        }
        
        Pagination pagination = this.findPagination(page, criteria);
        result.setData(pagination);
        return result;
    }

    

}
