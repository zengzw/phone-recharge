package com.tsh.vas.dao.bill;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;

import org.hibernate.criterion.Restrictions;

import com.dtds.platform.util.bean.Pagination;

import org.springframework.stereotype.Repository;

import com.dtds.platform.data.hibernate.HibernateDao;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.po.bill.TradingPo;

@Repository
@SuppressWarnings("all")
public class TradingDao extends HibernateDao<TradingPo, Long> {
    /**
     * 新增充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result addTrading(Result result,TradingPo trading)throws Exception{
        this.save(trading);
        result.setData(trading);
        return result;
    }

    /**
     * 批量新增充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result batchSaveTrading(Result result, List<TradingPo> trading_list) throws Exception {
        this.batchSave(trading_list);
        result.setData(null);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deleteTrading(Result result, Long id) throws Exception {
        int count = this.updateHql("delete TradingPo where id=?",id);
        result.setData(count);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result batchDelTrading(Result result, List<TradingPo> trading_list)throws Exception{
        this.batchDelete(trading_list);
        return result;
    }


    /**
     * 批量删除充值接口对象ById
     * @param result
     * @param trading
     * @return
     */
    public Result batchDelTradingByIds(Result result,Long[] ids)throws Exception{
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
    public Result clearTrading(Result result) {
        String sql = " truncate table trading ";
        int count = this.getSession().createSQLQuery(sql).executeUpdate();
        result.setData(count);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updateTrading(Result result,TradingPo tradingPo) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update TradingPo set ");

        if(tradingPo.getTradingCode()!=null){
            hql.append("tradingCode = ").append(tradingPo.getTradingCode());
        }
        if(tradingPo.getShopId()!=null){
            hql.append("shopId = ").append(tradingPo.getShopId());
        }
        if(tradingPo.getShopName()!=null){
            hql.append("shopName = ").append(tradingPo.getShopName());
        }
        if(tradingPo.getAreaId()!=null){
            hql.append("areaId = ").append(tradingPo.getAreaId());
        }
        if(tradingPo.getAreaName()!=null){
            hql.append("areaName = ").append(tradingPo.getAreaName());
        }
        if(tradingPo.getOrderId()!=null){
            hql.append("orderId = ").append(tradingPo.getOrderId());
        }
        if(tradingPo.getOrderCode()!=null){
            hql.append("orderCode = ").append(tradingPo.getOrderCode());
        }
        if(tradingPo.getSupplierId()!=null){
            hql.append("supplierId = ").append(tradingPo.getSupplierId());
        }
        if(tradingPo.getSupplierCode()!=null){
            hql.append("supplierCode = ").append(tradingPo.getSupplierCode());
        }
        if(tradingPo.getSupplierName()!=null){
            hql.append("supplierName = ").append(tradingPo.getSupplierName());
        }
        if(tradingPo.getMobile()!=null){
            hql.append("mobile = ").append(tradingPo.getMobile());
        }
        if(tradingPo.getNumberType()!=null){
            hql.append("numberType = ").append(tradingPo.getNumberType());
        }
        if(tradingPo.getPostAmount()!=null){
            hql.append("postAmount = ").append(tradingPo.getPostAmount());
        }
        if(tradingPo.getPayAmount()!=null){
            hql.append("payAmount = ").append(tradingPo.getPayAmount());
        }
        if(tradingPo.getPostTime()!=null){
            hql.append("postTime = ").append(tradingPo.getPostTime());
        }
        if(tradingPo.getState()!=null){
            hql.append("state = ").append(tradingPo.getState());
        }
        if(tradingPo.getSource()!=null){
            hql.append("source = ").append(tradingPo.getSource());
        }
        if(tradingPo.getSourceCode()!=null){
            hql.append("sourceCode = ").append(tradingPo.getSourceCode());
        }
        if(tradingPo.getType()!=null){
            hql.append("type = ").append(tradingPo.getType());
        }
        if(tradingPo.getRemarks()!=null){
            hql.append("remarks = ").append(tradingPo.getRemarks());
        }

        hql.append("where id = ?");
        int count = this.updateHql(hql.toString(),tradingPo.getId());
        result.setData(count);
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdateTrading(Result result,List<TradingPo> trading_list) throws Exception {
        this.batchUpdate(trading_list);
        result.setData(null);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getTradingById(Result result,Long id) throws Exception{
        TradingPo tradingPo = this.get(id);
        result.setData(tradingPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryTradingList(Result result,Page page,TradingPo tradingPo){
        Criteria criteria = this.getSession().createCriteria(TradingPo.class);
        if(null != tradingPo){
            if(tradingPo.getTradingCode()!=null){
                criteria.add(Restrictions.eq("tradingCode", tradingPo.getTradingCode()));
            }
            if(tradingPo.getShopId()!=null){
                criteria.add(Restrictions.eq("shopId", tradingPo.getShopId()));
            }
            if(tradingPo.getShopName()!=null){
                criteria.add(Restrictions.eq("shopName", tradingPo.getShopName()));
            }
            if(tradingPo.getAreaId()!=null){
                criteria.add(Restrictions.eq("areaId", tradingPo.getAreaId()));
            }
            if(tradingPo.getAreaName()!=null){
                criteria.add(Restrictions.eq("areaName", tradingPo.getAreaName()));
            }
            if(tradingPo.getOrderId()!=null){
                criteria.add(Restrictions.eq("orderId", tradingPo.getOrderId()));
            }
            if(tradingPo.getOrderCode()!=null){
                criteria.add(Restrictions.eq("orderCode", tradingPo.getOrderCode()));
            }
            if(tradingPo.getSupplierId()!=null){
                criteria.add(Restrictions.eq("supplierId", tradingPo.getSupplierId()));
            }
            if(tradingPo.getSupplierCode()!=null){
                criteria.add(Restrictions.eq("supplierCode", tradingPo.getSupplierCode()));
            }
            if(tradingPo.getSupplierName()!=null){
                criteria.add(Restrictions.eq("supplierName", tradingPo.getSupplierName()));
            }
            if(tradingPo.getMobile()!=null){
                criteria.add(Restrictions.eq("mobile", tradingPo.getMobile()));
            }
            if(tradingPo.getNumberType()!=null){
                criteria.add(Restrictions.eq("numberType", tradingPo.getNumberType()));
            }
            if(tradingPo.getPostAmount()!=null){
                criteria.add(Restrictions.eq("postAmount", tradingPo.getPostAmount()));
            }
            if(tradingPo.getPayAmount()!=null){
                criteria.add(Restrictions.eq("payAmount", tradingPo.getPayAmount()));
            }
            if(tradingPo.getPostTime()!=null){
                criteria.add(Restrictions.eq("postTime", tradingPo.getPostTime()));
            }
            if(tradingPo.getState()!=null){
                criteria.add(Restrictions.eq("state", tradingPo.getState()));
            }
            if(tradingPo.getSource()!=null){
                criteria.add(Restrictions.eq("source", tradingPo.getSource()));
            }
            if(tradingPo.getSourceCode()!=null){
                criteria.add(Restrictions.eq("sourceCode", tradingPo.getSourceCode()));
            }
            if(tradingPo.getType()!=null){
                criteria.add(Restrictions.eq("type", tradingPo.getType()));
            }
            if(tradingPo.getRemarks()!=null){
                criteria.add(Restrictions.eq("remarks", tradingPo.getRemarks()));
            }
        }
        Pagination pagination = this.findPagination(page, criteria);
        result.setData(pagination);
        return result;
    }


    /**
     * 根据时间、状态 查询位订单
     * 
     * @param result
     * @return
     */
    public Result queryAllRechargeingTrading(Result result,int time,int status){
        String sql = "SELECT * FROM trading d where NOW() >= date_add(post_time,INTERVAL "+time 
                +" MINUTE) and d.state="+status+"  GROUP BY d.post_time asc";

        Session session = this.getSession();
        SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(TradingPo.class);
        List<TradingPo> lstDeposit = sqlQuery.list();

        result.setData(lstDeposit);
        return result;
    }

    /**
     * 根据外部订单号，查询订单对象
     * @param result
     * @return
     */
    public Result queryTradingByTradingCode(Result result,String tradingCode){
        String hql = "from TradingPo where tradingCode = ?";
        TradingPo tradingPo = this.findUnique(hql,tradingCode);
        result.setData(tradingPo);

        return result;
    }
    
    /**
     * 查找 充值完成，未通知订单表接口的数据
     * 
     * @param result
     * @return
     */
    public Result queryRechargedTrading(Result result){
        String sql = "SELECT * FROM trading d where (state=1 or state=2 or state=3) "
                + "and (push_status = 0 or push_status=2)  GROUP BY d.post_time desc";

        Session session = this.getSession();
        SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(TradingPo.class);
        List<TradingPo> lstDeposit = sqlQuery.list();

        result.setData(lstDeposit);
        return result;
    }


}
