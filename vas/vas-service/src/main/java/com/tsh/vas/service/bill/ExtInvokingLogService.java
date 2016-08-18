package com.tsh.vas.service.bill;

import java.util.List;
import java.util.ArrayList;

import com.dtds.platform.util.bean.Result;

import org.springframework.stereotype.Service;

import com.dtds.platform.util.security.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Page;
import com.tsh.vas.po.bill.ExtInvokingLogPo;
import com.tsh.vas.vo.bill.ExtInvokingLogVo;
import com.tsh.vas.dao.bill.ExtInvokingLogDao;


@Service
@SuppressWarnings("all")
public class ExtInvokingLogService {
    @Autowired
    private ExtInvokingLogDao extInvokingLogDao;

    /**
     * 新增充值接口对象
     * @param result
     * @param extInvokingLog
     * @return
     */
    public Result addExtInvokingLog(Result result,ExtInvokingLogVo extInvokingLogVo)throws Exception{
        ExtInvokingLogPo extInvokingLogPo = new ExtInvokingLogPo();

        if (extInvokingLogVo != null) {
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
        }

        result = extInvokingLogDao.addExtInvokingLog(result,extInvokingLogPo);
        return result;
    }



    /**
     * 保存 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result saveExtInvokingLog(Result result,ExtInvokingLogVo extInvokingLogVo,UserInfo user) throws Exception {
        if(extInvokingLogVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = extInvokingLogVo.getId();
        result = extInvokingLogDao.getExtInvokingLogById(result,id);
        ExtInvokingLogPo extInvokingLogPo  = (ExtInvokingLogPo)result.getData();

        if (extInvokingLogPo != null) {
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
        }else{
            extInvokingLogPo = new ExtInvokingLogPo();
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
            result = extInvokingLogDao.addExtInvokingLog(result,extInvokingLogPo);
        }
        return result;
    }



    /**
     * 保存 充值接口对象
     * @param result
     * @return
     */
    public Result saveExtInvokingLog(Result result,ExtInvokingLogVo extInvokingLogVo) throws Exception {
        if(extInvokingLogVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = extInvokingLogVo.getId();
        result = extInvokingLogDao.getExtInvokingLogById(result,id);
        ExtInvokingLogPo extInvokingLogPo  = (ExtInvokingLogPo)result.getData();

        if (extInvokingLogPo != null) {
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
        }else{
            extInvokingLogPo = new ExtInvokingLogPo();
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
            result = extInvokingLogDao.addExtInvokingLog(result,extInvokingLogPo);
        }
        return result;
    }


    /**
     * 批量新增充值接口对象
     * @param result
     * @param extInvokingLog
     * @return
     */
    public Result batchSaveExtInvokingLog(Result result, List<ExtInvokingLogVo> extInvokingLog_list) throws Exception {
        List<ExtInvokingLogPo> list = new ArrayList<ExtInvokingLogPo>();
        result = extInvokingLogDao.batchSaveExtInvokingLog(result,list);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deleteExtInvokingLog(Result result, Long id) throws Exception {
        result = extInvokingLogDao.deleteExtInvokingLog(result,id);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param extInvokingLog
     * @return
     */
    public Result batchDelExtInvokingLog(Result result, List<ExtInvokingLogVo> extInvokingLog_list)throws Exception{
        List<ExtInvokingLogPo> list = new ArrayList<ExtInvokingLogPo>(); 
        extInvokingLogDao.batchDelete(list);
        return result;
    }


    /**
     * 批量删除充值接口对象ByIds
     * @param result
     * @param extInvokingLog
     * @return
     */
    public Result batchDelExtInvokingLogByIds(Result result,Long[] ids)throws Exception{
        extInvokingLogDao.batchDelExtInvokingLogByIds(result,ids);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryExtInvokingLogList(Result result,Page page,ExtInvokingLogVo extInvokingLogVo){
        ExtInvokingLogPo extInvokingLogPo = new ExtInvokingLogPo();
        result = extInvokingLogDao.queryExtInvokingLogList(result,page,extInvokingLogPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表 带User
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryExtInvokingLogList(Result result,Page page,ExtInvokingLogVo extInvokingLogVo,UserInfo user){
        ExtInvokingLogPo extInvokingLogPo = new ExtInvokingLogPo();
        /**
         *自行匹配需要查询的字段及值
         **/
        result = extInvokingLogDao.queryExtInvokingLogList(result,page,extInvokingLogPo);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result getExtInvokingLogById(Result result,Long id,UserInfo user) throws Exception{
        result = extInvokingLogDao.getExtInvokingLogById(result,id);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getExtInvokingLogById(Result result,Long id) throws Exception{
        result = extInvokingLogDao.getExtInvokingLogById(result,id);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updateExtInvokingLog(Result result,ExtInvokingLogVo extInvokingLogVo) throws Exception {
        Long id = extInvokingLogVo.getId();
        result = extInvokingLogDao.getExtInvokingLogById(result,id);
        ExtInvokingLogPo extInvokingLogPo  = (ExtInvokingLogPo)result.getData();
        if (extInvokingLogPo != null) {
            if(extInvokingLogVo.getDepositId()!=null){
                extInvokingLogPo.setDepositId(extInvokingLogVo.getDepositId());
            }
            if(extInvokingLogVo.getDepositCode()!=null){
                extInvokingLogPo.setDepositCode(extInvokingLogVo.getDepositCode());
            }
            if(extInvokingLogVo.getOpAction()!=null){
                extInvokingLogPo.setOpAction(extInvokingLogVo.getOpAction());
            }
            if(extInvokingLogVo.getOpParam()!=null){
                extInvokingLogPo.setOpParam(extInvokingLogVo.getOpParam());
            }
            if(extInvokingLogVo.getOpMessage()!=null){
                extInvokingLogPo.setOpMessage(extInvokingLogVo.getOpMessage());
            }
            if(extInvokingLogVo.getState()!=null){
                extInvokingLogPo.setState(extInvokingLogVo.getState());
            }
            if(extInvokingLogVo.getSource()!=null){
                extInvokingLogPo.setSource(extInvokingLogVo.getSource());
            }
            if(extInvokingLogVo.getSourceCode()!=null){
                extInvokingLogPo.setSourceCode(extInvokingLogVo.getSourceCode());
            }
            if(extInvokingLogVo.getType()!=null){
                extInvokingLogPo.setType(extInvokingLogVo.getType());
            }
            if(extInvokingLogVo.getPostTime()!=null){
                extInvokingLogPo.setPostTime(extInvokingLogVo.getPostTime());
            }
            if(extInvokingLogVo.getRemarks()!=null){
                extInvokingLogPo.setRemarks(extInvokingLogVo.getRemarks());
            }
        }
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdateExtInvokingLog(Result result,List<ExtInvokingLogVo> extInvokingLog_list) throws Exception {
        List<ExtInvokingLogPo> list = new ArrayList<ExtInvokingLogPo>(); 
        extInvokingLogDao.batchUpdateExtInvokingLog(result,list);
        return result;
    }

}
