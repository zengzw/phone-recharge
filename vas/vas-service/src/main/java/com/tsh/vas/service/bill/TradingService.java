package com.tsh.vas.service.bill;

import java.util.List;
import java.util.ArrayList;

import com.dtds.platform.util.bean.Result;

import org.springframework.stereotype.Service;

import com.dtds.platform.util.security.UserInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Page;
import com.tsh.vas.po.bill.TradingHistoryPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.vo.bill.TradingVo;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.vas.dao.bill.TradingDao;
import com.tsh.vas.dao.bill.TradingHistoryDao;


@Service
@SuppressWarnings("all")
public class TradingService {
    @Autowired
    private TradingDao tradingDao;

    @Autowired
    private TradingHistoryDao tradingHistoryDao;

    /**
     * 新增充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result addTrading(Result result,TradingVo tradingVo)throws Exception{
        TradingPo tradingPo = new TradingPo();

        if (tradingVo != null) {
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
        }

        result = tradingDao.addTrading(result,tradingPo);
        return result;
    }



    /**
     * 保存 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result saveTrading(Result result,TradingVo tradingVo,UserInfo user) throws Exception {
        if(tradingVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = tradingVo.getId();
        result = tradingDao.getTradingById(result,id);
        TradingPo tradingPo  = (TradingPo)result.getData();

        if (tradingPo != null) {
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
            if(tradingVo.getPushStatus() != null){
                tradingPo.setPushStatus(tradingVo.getPushStatus());
            }
        }else{
            tradingPo = new TradingPo();
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
            if(tradingVo.getPushStatus() != null){
                tradingPo.setPushStatus(tradingVo.getPushStatus());
            }
            result = tradingDao.addTrading(result,tradingPo);
        }
        return result;
    }



    /**
     * 保存 充值接口对象
     * @param result
     * @return
     */
    public Result saveTrading(Result result,TradingVo tradingVo) throws Exception {
        if(tradingVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = tradingVo.getId();
        result = tradingDao.getTradingById(result,id);
        TradingPo tradingPo  = (TradingPo)result.getData();

        if (tradingPo != null) {
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
            if(tradingVo.getPushStatus() != null){
                tradingPo.setPushStatus(tradingVo.getPushStatus());
            }
        }else{
            tradingPo = new TradingPo();
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
            if(tradingVo.getPushStatus() != null){
                tradingPo.setPushStatus(tradingVo.getPushStatus());
            }
            result = tradingDao.addTrading(result,tradingPo);
        }
        return result;
    }


    /**
     * 批量新增充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result batchSaveTrading(Result result, List<TradingVo> trading_list) throws Exception {
        List<TradingPo> list = new ArrayList<TradingPo>();
        result = tradingDao.batchSaveTrading(result,list);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deleteTrading(Result result, Long id) throws Exception {
        result = tradingDao.deleteTrading(result,id);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param trading
     * @return
     */
    public Result batchDelTrading(Result result, List<TradingVo> trading_list)throws Exception{
        List<TradingPo> list = new ArrayList<TradingPo>(); 
        tradingDao.batchDelete(list);
        return result;
    }


    /**
     * 批量删除充值接口对象ByIds
     * @param result
     * @param trading
     * @return
     */
    public Result batchDelTradingByIds(Result result,Long[] ids)throws Exception{
        tradingDao.batchDelTradingByIds(result,ids);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryTradingList(Result result,Page page,TradingVo tradingVo){
        TradingPo tradingPo = new TradingPo();
        result = tradingDao.queryTradingList(result,page,tradingPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表 带User
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryTradingList(Result result,Page page,TradingVo tradingVo,UserInfo user){
        TradingPo tradingPo = new TradingPo();
        /**
         *自行匹配需要查询的字段及值
         **/
        result = tradingDao.queryTradingList(result,page,tradingPo);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result getTradingById(Result result,Long id,UserInfo user) throws Exception{
        result = tradingDao.getTradingById(result,id);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getTradingById(Result result,Long id) throws Exception{
        result = tradingDao.getTradingById(result,id);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updateTrading(Result result,TradingVo tradingVo) throws Exception {
        Long id = tradingVo.getId();
        result = tradingDao.getTradingById(result,id);
        TradingPo tradingPo  = (TradingPo)result.getData();
        if (tradingPo != null) {
            if(tradingVo.getTradingCode()!=null){
                tradingPo.setTradingCode(tradingVo.getTradingCode());
            }
            if(tradingVo.getShopId()!=null){
                tradingPo.setShopId(tradingVo.getShopId());
            }
            if(tradingVo.getShopName()!=null){
                tradingPo.setShopName(tradingVo.getShopName());
            }
            if(tradingVo.getAreaId()!=null){
                tradingPo.setAreaId(tradingVo.getAreaId());
            }
            if(tradingVo.getAreaName()!=null){
                tradingPo.setAreaName(tradingVo.getAreaName());
            }
            if(tradingVo.getOrderId()!=null){
                tradingPo.setOrderId(tradingVo.getOrderId());
            }
            if(tradingVo.getOrderCode()!=null){
                tradingPo.setOrderCode(tradingVo.getOrderCode());
            }
            if(tradingVo.getSupplierId()!=null){
                tradingPo.setSupplierId(tradingVo.getSupplierId());
            }
            if(tradingVo.getSupplierCode()!=null){
                tradingPo.setSupplierCode(tradingVo.getSupplierCode());
            }
            if(tradingVo.getSupplierName()!=null){
                tradingPo.setSupplierName(tradingVo.getSupplierName());
            }
            if(tradingVo.getMobile()!=null){
                tradingPo.setMobile(tradingVo.getMobile());
            }
            if(tradingVo.getNumberType()!=null){
                tradingPo.setNumberType(tradingVo.getNumberType());
            }
            if(tradingVo.getPostAmount()!=null){
                tradingPo.setPostAmount(tradingVo.getPostAmount());
            }
            if(tradingVo.getPayAmount()!=null){
                tradingPo.setPayAmount(tradingVo.getPayAmount());
            }
            if(tradingVo.getPostTime()!=null){
                tradingPo.setPostTime(tradingVo.getPostTime());
            }
            if(tradingVo.getState()!=null){
                tradingPo.setState(tradingVo.getState());
            }
            if(tradingVo.getSource()!=null){
                tradingPo.setSource(tradingVo.getSource());
            }
            if(tradingVo.getSourceCode()!=null){
                tradingPo.setSourceCode(tradingVo.getSourceCode());
            }
            if(tradingVo.getType()!=null){
                tradingPo.setType(tradingVo.getType());
            }
            if(tradingVo.getRemarks()!=null){
                tradingPo.setRemarks(tradingVo.getRemarks());
            }
            if(tradingVo.getPushStatus() != null){
                tradingPo.setPushStatus(tradingVo.getPushStatus());
            }
            if(tradingVo.getGoodsId()!=null){
                tradingPo.setGoodsId(tradingVo.getGoodsId());
            }
            if(tradingVo.getSkuId() != null){
                tradingPo.setSkuId(tradingVo.getSkuId());
            }
            if(tradingVo.getDepositNo() != null){
                tradingPo.setDepositNo(tradingVo.getDepositNo());
            }
        }
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdateTrading(Result result,List<TradingVo> trading_list) throws Exception {
        List<TradingPo> list = new ArrayList<TradingPo>(); 
        tradingDao.batchUpdateTrading(result,list);
        return result;
    }


    /**
     * 查询正在充值中的订单，4分钟没有收到回调url的。
     * @param result
     * @return
     */
    public Result queryAllRechargeingRecodes(Result result){
        int time = 4;
        String status = Configurations.OrderStatus.RECHARGEING;

        return tradingDao.queryAllRechargeingTrading(result,time,Integer.parseInt(status));
    }


    /**
     * 查询初始化状态的订单，超过2分钟
     * @param result
     * @return
     */
    public Result queryAllRechargeInitialRecodes(Result result){
        int time = 2;
        String status = Configurations.OrderStatus.INITIAL;

        return tradingDao.queryAllRechargeingTrading(result,time,Integer.parseInt(status));
    }



    public Result getTradingByTradingCode(Result result,String tradingCode){
        return tradingDao.queryTradingByTradingCode(result, tradingCode);
    }

    /**
     *  将外部订单表 添加 到 history中 
     *  
     * @param result
     * @param tradingCode
     * @return
     * @throws Exception
     */
    public Result removeToHistory(Result result,String tradingCode) throws Exception{

        getTradingByTradingCode(result, tradingCode);
        TradingPo tradingPo = result.getData();

        if(tradingPo != null){
            TradingHistoryPo tradingHistoryPo = new TradingHistoryPo();
            BeanUtils.copyProperties(tradingPo, tradingHistoryPo);
            tradingHistoryPo.setId(null);
            tradingHistoryDao.addTradingHistory(result, tradingHistoryPo);

            tradingDao.deleteTrading(result, tradingPo.getId());
        }

        return result;
    }

    
    /**
     * 获取充值成功，未通知订单接口数据列表
     * 
     * 
     * @param result
     * @return
     */
    public Result queryRechargedLstTrading(Result result){
        
        return tradingDao.queryRechargedTrading(result);
    }


}
