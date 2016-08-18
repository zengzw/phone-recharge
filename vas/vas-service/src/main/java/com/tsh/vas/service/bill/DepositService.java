package com.tsh.vas.service.bill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.security.UserInfo;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.vas.dao.bill.DepositDao;
import com.tsh.vas.dao.bill.DepositHistoryDao;
import com.tsh.vas.po.bill.DepositHistoryPo;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.vo.bill.DepositVo;

@Service
@SuppressWarnings("all")
public class DepositService {
    @Autowired
    private DepositDao depositDao;
    
    @Autowired
    private DepositHistoryDao depositHistoryDao;

    /**
     * 新增充值接口对象
     * 
     * @param result
     * @param deposit
     * @return
     */
    public Result addDeposit(Result result, DepositVo depositVo)
            throws Exception {
        DepositPo depositPo = new DepositPo();

        if (depositVo != null) {
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
        }

        result = depositDao.addDeposit(result, depositPo);
        return result;
    }

    /**
     * 保存 充值接口对象 带User对象
     * 
     * @param result
     * @return
     */
    public Result saveDeposit(Result result, DepositVo depositVo, UserInfo user)
            throws Exception {
        if (depositVo == null) {
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = depositVo.getId();
        result = depositDao.getDepositById(result, id);
        DepositPo depositPo = (DepositPo) result.getData();

        if (depositPo != null) {
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
        } else {
            depositPo = new DepositPo();
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
            result = depositDao.addDeposit(result, depositPo);
        }
        return result;
    }

    /**
     * 保存 充值接口对象
     * 
     * @param result
     * @return
     */
    public Result saveDeposit(Result result, DepositVo depositVo)
            throws Exception {
        if (depositVo == null) {
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = depositVo.getId();
        result = depositDao.getDepositById(result, id);
        DepositPo depositPo = (DepositPo) result.getData();

        if (depositPo != null) {
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
        } else {
            depositPo = new DepositPo();
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
            result = depositDao.addDeposit(result, depositPo);
        }
        return result;
    }

    /**
     * 批量新增充值接口对象
     * 
     * @param result
     * @param deposit
     * @return
     */
    public Result batchSaveDeposit(Result result, List<DepositPo> depositPos) throws Exception {
        result = depositDao.batchSaveDeposit(result, depositPos);
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
        result = depositDao.deleteDeposit(result, id);
        return result;
    }

    /**
     * 批量删除充值接口对象
     * 
     * @param result
     * @param deposit
     * @return
     */
    public Result batchDelDeposit(Result result, List<DepositVo> deposit_list)
            throws Exception {
        List<DepositPo> list = new ArrayList<DepositPo>();
        depositDao.batchDelete(list);
        return result;
    }

    /**
     * 批量删除充值接口对象ByIds
     * 
     * @param result
     * @param deposit
     * @return
     */
    public Result batchDelDepositByIds(Result result, Long[] ids)
            throws Exception {
        depositDao.batchDelDepositByIds(result, ids);
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
    public Result queryDepositList(Result result, Page page, DepositVo depositVo) {
        DepositPo depositPo = new DepositPo();
        result = depositDao.queryDepositList(result, page, depositPo);
        return result;
    }

    /**
     * 根据条件获取 充值接口对象列表 带User
     * 
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryDepositList(Result result, Page page,
            DepositVo depositVo, UserInfo user) {
        DepositPo depositPo = new DepositPo();
        result = depositDao.queryDepositList(result, page, depositPo);
        return result;
    }

    /**
     * 根据ID获取 充值接口对象 带User对象
     * 
     * @param result
     * @return
     */
    public Result getDepositById(Result result, Long id, UserInfo user)
            throws Exception {
        result = depositDao.getDepositById(result, id);
        return result;
    }

    /**
     * 根据ID获取 充值接口对象
     * 
     * @param result
     * @return
     */
    public Result getDepositById(Result result, Long id) throws Exception {
        result = depositDao.getDepositById(result, id);
        return result;
    }

    /**
     * 更新 充值接口对象
     * 
     * @param result
     * @return
     */
    public Result updateDeposit(Result result, DepositVo depositVo)
            throws Exception {
        Long id = depositVo.getId();
        result = depositDao.getDepositById(result, id);
        DepositPo depositPo = (DepositPo) result.getData();
        if (depositPo != null) {
            if (depositVo.getDepositCode() != null) {
                depositPo.setDepositCode(depositVo.getDepositCode());
            }
            if (depositVo.getTradingId() != null) {
                depositPo.setTradingId(depositVo.getTradingId());
            }
            if (depositVo.getTradingCode() != null) {
                depositPo.setTradingCode(depositVo.getTradingCode());
            }
            if (depositVo.getWeight() != null) {
                depositPo.setWeight(depositVo.getWeight());
            }
            if (depositVo.getSupplierId() != null) {
                depositPo.setSupplierId(depositVo.getSupplierId());
            }
            if (depositVo.getSupplierCode() != null) {
                depositPo.setSupplierCode(depositVo.getSupplierCode());
            }
            if (depositVo.getSupplierName() != null) {
                depositPo.setSupplierName(depositVo.getSupplierName());
            }
            if (depositVo.getMobile() != null) {
                depositPo.setMobile(depositVo.getMobile());
            }
            if (depositVo.getNumberType() != null) {
                depositPo.setNumberType(depositVo.getNumberType());
            }
            if (depositVo.getPostAmount() != null) {
                depositPo.setPostAmount(depositVo.getPostAmount());
            }
            if (depositVo.getPayAmount() != null) {
                depositPo.setPayAmount(depositVo.getPayAmount());
            }
            if (depositVo.getPostTime() != null) {
                depositPo.setPostTime(depositVo.getPostTime());
            }
            if (depositVo.getState() != null) {
                depositPo.setState(depositVo.getState());
            }
            if (depositVo.getSource() != null) {
                depositPo.setSource(depositVo.getSource());
            }
            if (depositVo.getSourceCode() != null) {
                depositPo.setSourceCode(depositVo.getSourceCode());
            }
            if (depositVo.getType() != null) {
                depositPo.setType(depositVo.getType());
            }
            if (depositVo.getRemarks() != null) {
                depositPo.setRemarks(depositVo.getRemarks());
            }
        }
        
        return result;
    }

    /**
     * 批量更新 充值接口对象
     * 
     * @param result
     * @return
     */
    public Result batchUpdateDeposit(Result result, List<DepositVo> deposit_list)
            throws Exception {
        List<DepositPo> list = new ArrayList<DepositPo>();
        depositDao.batchUpdateDeposit(result, list);
        return result;
    }


    /**
     * 根据depositCode  获取记录
     * 
     * @param result
     * @param depostiCode
     * @return
     */
    public Result getDepositByDepositCode(Result result,String depostiCode){
        return depositDao.queryDepositByDepositCode(result, depostiCode);
    }


    /**
     * 根据单号查询 可用供应商，安装权重排序
     * 
     * @param result
     * @param tradingCode
     * @return
     */
    public Result getDepositInitialByTradingCode(Result result,String tradingCode){
        return depositDao.queryDepositByTradingCode(result, tradingCode,Configurations.OrderStatus.INITIAL);
    }
    
    /**
     * 根据外部订单号 + 供应商ID 获取供应商
     * 
     * @param result
     * @param tradingCode
     * @return
     */
    public Result getDepositByCondition(Result result,String tradingCode,Long spId){
        return depositDao.queryDepositByCondition(result, tradingCode, spId);
    }


 /*   *//**
     * 批量将可用供应商 移除  到 历史记录
     * 
     * @param result
     * @param tradingCode
     * @return
     *//*
    public Result batctMoveToHistory(Result result,String tradingCode){

        return depositDao.batctMoveToHistory(result, tradingCode);
    }*/
    
    
    /**
     * 批量将可用供应商 移除  到 历史记录
     * 
     * @param result
     * @param tradingCode
     * @return
     * @throws Exception 
     */
    public Result batctMoveToHistory(Result result,String tradingCode) throws Exception{
        depositDao.queryDepositListByCondition(result, tradingCode);
        List<DepositPo> lstDepositPo = result.getData();
        
        if(lstDepositPo != null){
            for(DepositPo dp: lstDepositPo){
                DepositHistoryPo historyPo = new DepositHistoryPo();
                BeanUtils.copyProperties(dp, historyPo);
                historyPo.setId(null);
                
                depositHistoryDao.addDepositHistory(result, historyPo);
            }
        }
        
        return result;
    }


    /**
     *  根据订单号删除供应商信息
     *  
     * @param result
     * @return
     */
    public Result deleteDepositByTradingCode(Result result,String tradingCode) {
        return depositDao.deleteDepositByTradingCode(result, tradingCode);
    }
    
    
    /**
     * 根据tradingCode 获取初始状态的供应商记录
     * 
     * @param result
     * @param depostiCode
     * @return
     */
    public Result getDepositRechargeingByTradingCode(Result result,String tradingCode){
         return depositDao.queryDepositByTradingCode(result, tradingCode, Configurations.OrderStatus.RECHARGEING);
    }
    

}
