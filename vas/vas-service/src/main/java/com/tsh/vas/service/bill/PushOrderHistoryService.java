package com.tsh.vas.service.bill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.security.UserInfo;
import com.tsh.vas.dao.bill.PushOrderHistoryDao;
import com.tsh.vas.po.bill.PushOrderHistoryPo;
import com.tsh.vas.vo.bill.PushOrderHistoryVo;


@Service
@SuppressWarnings("all")
public class PushOrderHistoryService {
    @Autowired
    private PushOrderHistoryDao pushOrderHistoryDao;

    /**
     * 新增充值接口对象
     * @param result
     * @param pushOrderHistory
     * @return
     */
    public Result addPushOrderHistory(Result result,PushOrderHistoryVo pushOrderHistoryVo)throws Exception{
        PushOrderHistoryPo pushOrderHistoryPo = new PushOrderHistoryPo();

        if (pushOrderHistoryVo != null) {
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
        }

        result = pushOrderHistoryDao.addPushOrderHistory(result,pushOrderHistoryPo);
        return result;
    }



    /**
     * 保存 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result savePushOrderHistory(Result result,PushOrderHistoryVo pushOrderHistoryVo,UserInfo user) throws Exception {
        if(pushOrderHistoryVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = pushOrderHistoryVo.getId();
        result = pushOrderHistoryDao.getPushOrderHistoryById(result,id);
        PushOrderHistoryPo pushOrderHistoryPo  = (PushOrderHistoryPo)result.getData();

        if (pushOrderHistoryPo != null) {
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
        }else{
            pushOrderHistoryPo = new PushOrderHistoryPo();
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
            result = pushOrderHistoryDao.addPushOrderHistory(result,pushOrderHistoryPo);
        }
        return result;
    }



    /**
     * 保存 充值接口对象
     * @param result
     * @return
     */
    public Result savePushOrderHistory(Result result,PushOrderHistoryVo pushOrderHistoryVo) throws Exception {
        if(pushOrderHistoryVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = pushOrderHistoryVo.getId();
        result = pushOrderHistoryDao.getPushOrderHistoryById(result,id);
        PushOrderHistoryPo pushOrderHistoryPo  = (PushOrderHistoryPo)result.getData();

        if (pushOrderHistoryPo != null) {
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
        }else{
            pushOrderHistoryPo = new PushOrderHistoryPo();
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
            result = pushOrderHistoryDao.addPushOrderHistory(result,pushOrderHistoryPo);
        }
        return result;
    }


    /**
     * 批量新增充值接口对象
     * @param result
     * @param pushOrderHistory
     * @return
     */
    public Result batchSavePushOrderHistory(Result result, List<PushOrderHistoryVo> pushOrderHistory_list) throws Exception {
        List<PushOrderHistoryPo> list = new ArrayList<PushOrderHistoryPo>();
        result = pushOrderHistoryDao.batchSavePushOrderHistory(result,list);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deletePushOrderHistory(Result result, Long id) throws Exception {
        result = pushOrderHistoryDao.deletePushOrderHistory(result,id);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param pushOrderHistory
     * @return
     */
    public Result batchDelPushOrderHistory(Result result, List<PushOrderHistoryVo> pushOrderHistory_list)throws Exception{
        List<PushOrderHistoryPo> list = new ArrayList<PushOrderHistoryPo>(); 
        pushOrderHistoryDao.batchDelete(list);
        return result;
    }


    /**
     * 批量删除充值接口对象ByIds
     * @param result
     * @param pushOrderHistory
     * @return
     */
    public Result batchDelPushOrderHistoryByIds(Result result,Long[] ids)throws Exception{
        pushOrderHistoryDao.batchDelPushOrderHistoryByIds(result,ids);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryPushOrderHistoryList(Result result,Page page,PushOrderHistoryVo pushOrderHistoryVo){
        PushOrderHistoryPo pushOrderHistoryPo = new PushOrderHistoryPo();
        result = pushOrderHistoryDao.queryPushOrderHistoryList(result,page,pushOrderHistoryPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表 带User
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryPushOrderHistoryList(Result result,Page page,PushOrderHistoryVo pushOrderHistoryVo,UserInfo user){
        PushOrderHistoryPo pushOrderHistoryPo = new PushOrderHistoryPo();
        /**
         *自行匹配需要查询的字段及值
         **/
        result = pushOrderHistoryDao.queryPushOrderHistoryList(result,page,pushOrderHistoryPo);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result getPushOrderHistoryById(Result result,Long id,UserInfo user) throws Exception{
        result = pushOrderHistoryDao.getPushOrderHistoryById(result,id);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getPushOrderHistoryById(Result result,Long id) throws Exception{
        result = pushOrderHistoryDao.getPushOrderHistoryById(result,id);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updatePushOrderHistory(Result result,PushOrderHistoryVo pushOrderHistoryVo) throws Exception {
        Long id = pushOrderHistoryVo.getId();
        result = pushOrderHistoryDao.getPushOrderHistoryById(result,id);
        PushOrderHistoryPo pushOrderHistoryPo  = (PushOrderHistoryPo)result.getData();
        if (pushOrderHistoryPo != null) {
            if(pushOrderHistoryVo.getPushStatus()!=null){
                pushOrderHistoryPo.setPushStatus(pushOrderHistoryVo.getPushStatus());
            }
            if(pushOrderHistoryVo.getPushTime()!=null){
                pushOrderHistoryPo.setPushTime(pushOrderHistoryVo.getPushTime());
            }
            if(pushOrderHistoryVo.getTradingCode()!=null){
                pushOrderHistoryPo.setTradingCode(pushOrderHistoryVo.getTradingCode());
            }
            if(pushOrderHistoryVo.getPushParams()!=null){
                pushOrderHistoryPo.setPushParams(pushOrderHistoryVo.getPushParams());
            }
        }
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdatePushOrderHistory(Result result,List<PushOrderHistoryVo> pushOrderHistory_list) throws Exception {
        List<PushOrderHistoryPo> list = new ArrayList<PushOrderHistoryPo>(); 
        pushOrderHistoryDao.batchUpdatePushOrderHistory(result,list);
        return result;
    }

}
