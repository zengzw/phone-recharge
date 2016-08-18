package com.tsh.vas.service.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtds.platform.util.bean.Page;
import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.security.UserInfo;
import com.tsh.vas.dao.bill.DepositStatisDao;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.vo.bill.DepositStatisVo;
import com.vas.util.DigitUtils;


@Service
@SuppressWarnings("all")
public class DepositStatisService {
    @Autowired
    private DepositStatisDao depositStatisDao;

    /**
     * 新增充值接口对象
     * @param result
     * @param depositStatis
     * @return
     */
    public Result addDepositStatis(Result result,DepositStatisVo depositStatisVo)throws Exception{
        DepositStatisPo depositStatisPo = new DepositStatisPo();

        if (depositStatisVo != null) {
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
        }

        result = depositStatisDao.addDepositStatis(result,depositStatisPo);
        return result;
    }



    /**
     * 保存 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result saveDepositStatis(Result result,DepositStatisVo depositStatisVo,UserInfo user) throws Exception {
        if(depositStatisVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = depositStatisVo.getId();
        result = depositStatisDao.getDepositStatisById(result,id);
        DepositStatisPo depositStatisPo  = (DepositStatisPo)result.getData();

        if (depositStatisPo != null) {
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
        }else{
            depositStatisPo = new DepositStatisPo();
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
            result = depositStatisDao.addDepositStatis(result,depositStatisPo);
        }
        return result;
    }



    /**
     * 保存 充值接口对象
     * @param result
     * @return
     */
    public Result saveDepositStatis(Result result,DepositStatisVo depositStatisVo) throws Exception {
        if(depositStatisVo == null){
            result.setData("参数为空，保存失败");
            return result;
        }

        Long id = depositStatisVo.getId();
        result = depositStatisDao.getDepositStatisById(result,id);
        DepositStatisPo depositStatisPo  = (DepositStatisPo)result.getData();

        if (depositStatisPo != null) {
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
        }else{
            depositStatisPo = new DepositStatisPo();
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
            result = depositStatisDao.addDepositStatis(result,depositStatisPo);
        }
        return result;
    }


    /**
     * 批量新增充值接口对象
     * @param result
     * @param depositStatis
     * @return
     */
    public Result batchSaveDepositStatis(Result result, List<DepositStatisPo> depositStatisPos) throws Exception {
        result = depositStatisDao.batchSaveDepositStatis(result,depositStatisPos);
        return result;
    }

    /**
     * 删除充值接口对象
     * @param id 充值接口对象标识
     * @return
     */
    public Result deleteDepositStatis(Result result, Long id) throws Exception {
        result = depositStatisDao.deleteDepositStatis(result,id);
        return result;
    }


    /**
     * 批量删除充值接口对象
     * @param result
     * @param depositStatis
     * @return
     */
    public Result batchDelDepositStatis(Result result, List<DepositStatisVo> depositStatis_list)throws Exception{
        List<DepositStatisPo> list = new ArrayList<DepositStatisPo>(); 
        depositStatisDao.batchDelete(list);
        return result;
    }


    /**
     * 批量删除充值接口对象ByIds
     * @param result
     * @param depositStatis
     * @return
     */
    public Result batchDelDepositStatisByIds(Result result,Long[] ids)throws Exception{
        depositStatisDao.batchDelDepositStatisByIds(result,ids);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryDepositStatisList(Result result,Page page,DepositStatisVo depositStatisVo){
        DepositStatisPo depositStatisPo = new DepositStatisPo();
        result = depositStatisDao.queryDepositStatisList(result,page,depositStatisPo);
        return result;
    }


    /**
     * 根据条件获取 充值接口对象列表 带User
     * @param result
     * @param page
     * @param screenAdvertisementQuery
     * @return
     */
    public Result queryDepositStatisList(Result result,Page page,DepositStatisVo depositStatisVo,UserInfo user){
        DepositStatisPo depositStatisPo = new DepositStatisPo();
        /**
         *自行匹配需要查询的字段及值
         **/
        result = depositStatisDao.queryDepositStatisList(result,page,depositStatisPo);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象 带User对象
     * @param result
     * @return
     */
    public Result getDepositStatisById(Result result,Long id,UserInfo user) throws Exception{
        result = depositStatisDao.getDepositStatisById(result,id);
        return result;
    }


    /**
     * 根据ID获取 充值接口对象
     * @param result
     * @return
     */
    public Result getDepositStatisById(Result result,Long id) throws Exception{
        result = depositStatisDao.getDepositStatisById(result,id);
        return result;
    }


    /**
     * 更新 充值接口对象
     * @param result
     * @return
     */
    public Result updateDepositStatis(Result result,DepositStatisVo depositStatisVo) throws Exception {
        Long id = depositStatisVo.getId();
        result = depositStatisDao.getDepositStatisById(result,id);
        DepositStatisPo depositStatisPo  = (DepositStatisPo)result.getData();
        if (depositStatisPo != null) {
            if(depositStatisVo.getSupplierId()!=null){
                depositStatisPo.setSupplierId(depositStatisVo.getSupplierId());
            }
            if(depositStatisVo.getSupplierCode()!=null){
                depositStatisPo.setSupplierCode(depositStatisVo.getSupplierCode());
            }
            if(depositStatisVo.getSupplierName()!=null){
                depositStatisPo.setSupplierName(depositStatisVo.getSupplierName());
            }
            if(depositStatisVo.getSuccCount()!=null){
                depositStatisPo.setSuccCount(depositStatisVo.getSuccCount());
            }
            if(depositStatisVo.getFailCount()!=null){
                depositStatisPo.setFailCount(depositStatisVo.getFailCount());
            }
            if(depositStatisVo.getSource()!=null){
                depositStatisPo.setSource(depositStatisVo.getSource());
            }
            if(depositStatisVo.getSourceCode()!=null){
                depositStatisPo.setSourceCode(depositStatisVo.getSourceCode());
            }
        }
        return result;
    }


    /**
     * 批量更新 充值接口对象
     * @param result
     * @return
     */
    public Result batchUpdateDepositStatis(Result result,List<DepositStatisVo> depositStatis_list) throws Exception {
        List<DepositStatisPo> list = new ArrayList<DepositStatisPo>(); 
        depositStatisDao.batchUpdateDepositStatis(result,list);
        return result;
    }


    /**
     * 根据供应商ID 获取对象
     * @param result
     * @return
     */
    public Result getDepositStatisBySpId(Result result,Long spId) throws Exception {

        return depositStatisDao.getDepositStatisBySpId(result, spId);
    }

    /**
     * 根据供应商ID 获取对象
     * @param result
     * @return
     */
    public Result calculateSuccessCount(Result result,Long spId) throws Exception {

        depositStatisDao.getDepositStatisBySpId(result, spId);
        DepositStatisPo depositStatisPo = result.getData();

        if(depositStatisPo != null){
            Long susCount = depositStatisPo.getSuccCount() == null ? 0 : depositStatisPo.getSuccCount();
            Long failCount = depositStatisPo.getFailCount() == null ? 0 : depositStatisPo.getFailCount();
            double resultCount = (double)susCount / (susCount + failCount);

            result.setData(DigitUtils.round(resultCount,2));
        }

        return result;
    }
    
    
    
    /**
     * 获取可用供应商列表
     * 
     * @param result
     * @return
     * @throws Exception
     */
    public Result getDepositStatisList(Result result) throws Exception {
        
        return depositStatisDao.getDepositStatisList(result);
    }
    
    /**
     * 根据供应商ID 获取供应商对象
     * @param result
     * @param supplierId
     * @return
     * @throws Exception
     */
    public Result getDepositStatisListBySupplierId(Result result,Long supplierId) throws Exception {
        return depositStatisDao.getDepositStatisListBySupplierId(result,supplierId);
    }

}
