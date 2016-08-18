package com.tsh.vas.dao.bill;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.recharge.phone.PhoneRechargeProduct;

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
    public Result updateDepositHql(Result result, DepositPo depositPo){
        String hql = "update DepositPo set state = ?,payAmount = ? where id = ?";
        int count = this.updateHql(hql,depositPo.getState(),depositPo.getPayAmount(),depositPo.getId());
        result.setData(count);
        return result;
    }


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


    /**
     * 根据订单号，查询供应商信息
     * 
     * @return
     */
    public Result queryDepositByDepositCode(Result result,String depositCode){
        String hql = "from DepositPo where depositCode = ?";
        DepositPo depositPo = this.findUnique(hql,depositCode);
        result.setData(depositPo);

        return result;
    }

    
    
    /**
     * 根据外部订单号，状态查询可用供应商
     * 
     * @return
     */
    public Result queryDepositByTradingCode(Result result,String tradingCode,String status){
        String hql = "from DepositPo where tradingCode = ? and state="+status+" order by weight desc";
        List<DepositPo> lstDepositPos = this.find(hql, tradingCode);
        if(lstDepositPos != null && lstDepositPos.size() > 0){
            result.setData(lstDepositPos.get(0));
        }
        
        return result;
    }

/*
    *//**
     * 根据外部订单号，查询可用供应商信息
     * 
     * @return
     *//*
    public Result queryDepositByTradingCode(Result result,String tradingCode){
        String hql = "from DepositPo where tradingCode = ? and state in (-1) order by weight desc";
        List<DepositPo> lstDepositPos = this.find(hql, tradingCode);
        if(lstDepositPos != null && lstDepositPos.size() > 0){
            result.setData(lstDepositPos.get(0));
        }

        return result;
    }*/

    /**
     * 根据外部订单号，查询可用供应商信息
     * 
     * @param tradingCode 外部订单号
     * @param spId  供应商Id
     * @return
     */
    public Result queryDepositByCondition(Result result,String tradingCode,Long spId){
        String hql = "from DepositPo where tradingCode = ? and supplierId = ?";
        List<DepositPo> lstDepositPos = this.find(hql, tradingCode,spId);
        if(lstDepositPos != null && lstDepositPos.size() > 0){
            result.setData(lstDepositPos.get(0));
        }

        return result;
    }
    

    
    /**
     * 根据外部订单号，查询可用供应商信息
     * 
     * @param tradingCode 外部订单号
     * @param spId  供应商Id
     * @return
     */
    public Result queryDepositListByCondition(Result result,String tradingCode){
        String hql = "from DepositPo where tradingCode = ?";
        List<DepositPo> lstDepositPos = this.find(hql, tradingCode);
        result.setData(lstDepositPos);

        return result;
    }


    /**
     * 批量copy到另外一张表
     * 
     * @param result
     * @param tradingCode
     * @return
     */
    public Result batctMoveToHistory(Result result,String tradingCode){
        String sql = "INSERT into deposit_history(deposit_code,trading_id,trading_code,weight"+
                ",supplier_id,supplier_code,supplier_name,mobile,number_type,post_amount,pay_amount,post_time" +
                ",state,source,source_code,type,remarks) select deposit_code,trading_id,trading_code,weight" +
                ",supplier_id,supplier_code,supplier_name,mobile,number_type,post_amount,pay_amount,post_time"+
                ",state,source,source_code,type,remarks from deposit where trading_code='"+tradingCode+"'";

        int count = this.getSession().createSQLQuery(sql).executeUpdate();
        result.setData(count);

        return result;

    }


    /**
     * @param result
     * @return
     */
    public Result deleteDepositByTradingCode(Result result,String tradingCode) {
        int count = this.updateHql("delete DepositPo where tradingCode=?",tradingCode);
        result.setData(count);
        return result;
    }



    /**
     * 根据时间、状态获取可用供应商
     * 
     * @param result
     * @return
     */
    public Result queryAllRechargeingRecodes(Result result,int time,int status){
        String sql = "select * from (SELECT * FROM deposit d where NOW() >= date_add(post_time,INTERVAL"+time 
                +" MINUTE) and d.state="+status+" order by weight asc) a GROUP BY a.trading_code";

        Session session = this.getSession();
        SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(DepositPo.class);
        List<DepositPo> lstDeposit = sqlQuery.list();

        result.setData(lstDeposit);
        return result;
    }
    
   
}
