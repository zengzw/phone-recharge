package com.tsh.vas.service.bill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.security.UserInfo;
import com.tsh.vas.dao.bill.TradingHistoryDao;
import com.tsh.vas.po.bill.TradingHistoryPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.vo.bill.TradingHistoryVo;


@Service
@SuppressWarnings("all")
public class TradingHistoryService {
    @Autowired
    private TradingHistoryDao tradingHistoryDao;

    @Autowired
    private TradingService tradingService;

    /**
     * 新增充值接口对象
     * @param result
     * @param tradingHistory
     * @return
     */
    public Result addTradingHistory(Result result,TradingHistoryVo tradingHistoryVo)throws Exception{
        TradingHistoryPo tradingHistoryPo = new TradingHistoryPo();

        if (tradingHistoryVo != null) {
        }

        result = tradingHistoryDao.addTradingHistory(result,tradingHistoryPo);
        return result;
    }



    /**
     * 保存 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result saveTradingHistory(Result result,TradingHistoryVo tradingHistoryVo,UserInfo user) throws Exception {
        if(tradingHistoryVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = tradingHistoryVo.getId();
        result = tradingHistoryDao.getTradingHistoryById(result,id);
        TradingHistoryPo tradingHistoryPo  = (TradingHistoryPo)result.getData();

        if (tradingHistoryPo != null) {
        }else{
            tradingHistoryPo = new TradingHistoryPo();
            result = tradingHistoryDao.addTradingHistory(result,tradingHistoryPo);
        }
        return result;
    }



    /**
     * 保存 充值接口对象
     * @param result
     * @return
     */
    public Result saveTradingHistory(Result result,TradingHistoryVo tradingHistoryVo) throws Exception {
        if(tradingHistoryVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = tradingHistoryVo.getId();
        result = tradingHistoryDao.getTradingHistoryById(result,id);
        TradingHistoryPo tradingHistoryPo  = (TradingHistoryPo)result.getData();

        if (tradingHistoryPo != null) {
        }else{
            tradingHistoryPo = new TradingHistoryPo();
            result = tradingHistoryDao.addTradingHistory(result,tradingHistoryPo);
        }
        return result;
    }


    /**
     * 批量新增充值接口对象
     * @param result
     * @param tradingHistory
     * @return
     */
    public Result batchSaveTradingHistory(Result result, List<TradingHistoryVo> tradingHistory_list) throws Exception {
        List<TradingHistoryPo> list = new ArrayList<TradingHistoryPo>();
        result = tradingHistoryDao.batchSaveTradingHistory(result,list);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deleteTradingHistory(Result result, Long id) throws Exception {
        result = tradingHistoryDao.deleteTradingHistory(result,id);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param tradingHistory
     * @return
     */
    public Result batchDelTradingHistory(Result result, List<TradingHistoryVo> tradingHistory_list)throws Exception{
        List<TradingHistoryPo> list = new ArrayList<TradingHistoryPo>(); 
        tradingHistoryDao.batchDelete(list);
        return result;
    }


    /**
     * 批量删除充值接口对象ByIds
     * @param result
     * @param tradingHistory
     * @return
     */
    public Result batchDelTradingHistoryByIds(Result result,Long[] ids)throws Exception{
        tradingHistoryDao.batchDelTradingHistoryByIds(result,ids);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryTradingHistoryList(Result result,Page page,TradingHistoryVo tradingHistoryVo){
        TradingHistoryPo tradingHistoryPo = new TradingHistoryPo();
        result = tradingHistoryDao.queryTradingHistoryList(result,page,tradingHistoryPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表 带User
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryTradingHistoryList(Result result,Page page,TradingHistoryVo tradingHistoryVo,UserInfo user){
        TradingHistoryPo tradingHistoryPo = new TradingHistoryPo();
        /**
         *自行匹配需要查询的字段及值
         **/
        result = tradingHistoryDao.queryTradingHistoryList(result,page,tradingHistoryPo);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result getTradingHistoryById(Result result,Long id,UserInfo user) throws Exception{
        result = tradingHistoryDao.getTradingHistoryById(result,id);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getTradingHistoryById(Result result,Long id) throws Exception{
        result = tradingHistoryDao.getTradingHistoryById(result,id);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updateTradingHistory(Result result,TradingHistoryVo tradingHistoryVo) throws Exception {
        Long id = tradingHistoryVo.getId();
        result = tradingHistoryDao.getTradingHistoryById(result,id);
        TradingHistoryPo tradingHistoryPo  = (TradingHistoryPo)result.getData();
        if (tradingHistoryPo != null) {
        }
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdateTradingHistory(Result result,List<TradingHistoryVo> tradingHistory_list) throws Exception {
        List<TradingHistoryPo> list = new ArrayList<TradingHistoryPo>(); 
        tradingHistoryDao.batchUpdateTradingHistory(result,list);
        return result;
    }

}
