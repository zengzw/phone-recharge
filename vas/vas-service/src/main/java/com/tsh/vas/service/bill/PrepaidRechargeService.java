package com.tsh.vas.service.bill;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtds.platform.data.metaq.Producer;
import com.dtds.platform.util.bean.Result;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.tsh.dubbo.bis.api.AreaApi;
import com.tsh.dubbo.bis.api.ShopApi;
import com.tsh.dubbo.bis.api.SupplierApi;
import com.tsh.dubbo.bis.vo.CompanyServiceDetailVO;
import com.tsh.dubbo.bis.vo.SupplierVO;
import com.tsh.dubbo.gms.api.VirtualGoodsApi;
import com.tsh.dubbo.gms.vo.VirtualGoodsLadderPriceVo;
import com.tsh.dubbo.gms.vo.VirtualGoodsPropertyAndValueVo;
import com.tsh.dubbo.gms.vo.VirtualGoodsPropertyValueVo;
import com.tsh.dubbo.gms.vo.VirtualGoodsSupplierInfoVo;
import com.tsh.dubbo.share.api.AddressApi;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.share.vo.AreaVo;
import com.tsh.vas.metaq.vo.TradingVo;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.vo.bill.IntInvokingLogVo;
import com.tsh.vas.vo.bill.PushOrderHistoryVo;
import com.vas.util.VasConstant;
import com.vas.util.VasConstant.VirtualServiceTopic;
import com.vas.util.VasStringUtil;

/**
 * 话费充值调用dubbo服务
 * 
 * @version 1.0.0 2016年7月29日<br>
 * @see
 * @since JDK 1.7.0
 */
@Service
@SuppressWarnings("all")
public class PrepaidRechargeService {

	private static final Logger log = Logger.getLogger(PrepaidRechargeService.class);
	/*商家*/
	@Autowired
	private SupplierApi supplierApi;
	@Autowired
	private AreaApi areaApi;
	@Autowired
	private ShopApi shopApi;
	@Autowired
	private IntInvokingLogService intInvokingLogService;
	/*商品*/
	@Autowired
	private VirtualGoodsApi virtualGoodsApi;
	@Autowired
	private MetaqTemplate metaqTemplate;
	/*区域*/
	@Autowired
	private AddressApi addressApi;
	@Autowired
	private PhoneRechargeFacade phoneRechargeFacade;
	@Autowired
	private TradingService tradingService;
	@Autowired
	private DepositService depositService;
	@Autowired
	private DepositStatisService depositStatisService;
	@Autowired
	private PushOrderHistoryService pushOrderHistoryService;
	
	/*===================商品接口====================*/
	/**
	 * 根据供应商id、运营商名称(中国移动)、面额(20块)，查询goodsId  skuId
	 * @param result
	 * @param supplierId
	 * @param operators
	 * @param den
	 * @return
	 */
	public VirtualGoodsLadderPriceVo getGoodsInfo(Result result,Long supplierId,String operators,String den){
		VirtualGoodsLadderPriceVo vGoodsLadderPriceVo = null;
		try {
			vGoodsLadderPriceVo = virtualGoodsApi.getVirtualSkuByParam(result, supplierId, operators, den).getData();
			log.info("调用getVirtualSkuByParam返回的数据：" + vGoodsLadderPriceVo);
			//vGoodsLadderPriceVo = virtualGoodsApi.getVirtualSkuByParam(result, 164l, "中国移动", "20元").getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vGoodsLadderPriceVo;
	}
	/**
	 * 
	 * @param result
	 * @param provinceId 省份id，为null就查询所有
	 * @return  获取面额和运营商集合
	 * @throws Exception
	 */
	public Result getPropertyByAreaId(Result result,String mobile) throws Exception{
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		List<VirtualGoodsPropertyValueVo> virtualGoodsInfoVos = null;
		Long type = null;
		AreaVo areaVo = null;
		if(null != mobile){
			try {
				//根据手机号码获取归属地和运营商
				PhoneLocationVo phoneLocationVo = phoneRechargeFacade.queryPhoneSegment(mobile);
				//运营商类型(1-移动、2-联通、3-电信)
				type = Long.valueOf(String.valueOf(Operators.getType(phoneLocationVo.getType())));
				
				//根据省份名称获取省份id
				areaVo = addressApi.getAreaByTelAreaName(result, phoneLocationVo.getProvinceName()).getData();
				log.info("调用getAreaByTelAreaName返回的数据：" + areaVo);
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),mobile,JSON.toJSONString(areaVo),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getAreaByTelAreaName");
				
			} catch (Exception e) {
				log.error("调用方法 getPropertyByAreaId" + e.getMessage());
				
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(mobile),e.getMessage(),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getAreaByTelAreaName");
				
				e.printStackTrace();
				
				result.setData(null);
			} finally{
				intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
			}
			if(null != areaVo.getId()){
				virtualGoodsInfoVos = getVgsv(result, Long.valueOf(String.valueOf(areaVo.getId())),type);
			}
		}else{
			virtualGoodsInfoVos = getVgsv(result, null,type);
		}
		result.setData(virtualGoodsInfoVos);
		return result;
	}
	/**
	 * 根据省份id查询商品面额和运营商关系
	 * @param result
	 * @param provinceId 省份id，为null 查询所有面额和运营商关系
	 * @param operatorsType 运营商类型 1-移动、2-联通、3-电信
	 * @return
	 * @throws Exception
	 */
	public List<VirtualGoodsPropertyValueVo> getVgsv(Result result,Long provinceId,Long operatorsType) throws Exception{
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		List<VirtualGoodsPropertyValueVo> virtualGoodsInfoVos =null;
		try {
			//根据省份id或者null查询商品面额和运营商关系
			virtualGoodsInfoVos = virtualGoodsApi.getPropertyByAreaId(result, provinceId,operatorsType).getData();
			
			log.info("调用getPropertyByAreaId返回的数据：" + virtualGoodsInfoVos);
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(provinceId),JSON.toJSONString(virtualGoodsInfoVos),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getPropertyByAreaId");
			
		} catch (Exception e) {
			log.error("调用方法 getPropertyByAreaId" + e.getMessage());
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(provinceId),e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getPropertyByAreaId");
			e.printStackTrace();
			
		} finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		return virtualGoodsInfoVos;
	}
	/**
	 * 根据商品id获取虚拟商品属性(暂时没用)
	 * @param result
	 * @param goodsId 商品id
	 * @return
	 * @throws Exception 
	 */ 
	@Deprecated
	public Result getVitrualGoodsPropertyByGoodsId(Result result,long goodsId) throws Exception{
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		goodsId = 23l;
		List<VirtualGoodsPropertyAndValueVo> vaGoodsPropertyAndValueVos = new ArrayList<VirtualGoodsPropertyAndValueVo>();
		try {
			vaGoodsPropertyAndValueVos = virtualGoodsApi.getVitrualGoodsPropertyByGoodsId(result, 23l).getData();
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(goodsId),JSON.toJSONString(vaGoodsPropertyAndValueVos),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getVitrualGoodsPropertyByGoodsId");
			
		} catch (Exception e) {
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(goodsId),e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getVitrualGoodsPropertyByGoodsId");
			e.printStackTrace();
		} finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		result.setData(vaGoodsPropertyAndValueVos);
		return result;
	}
	/**
	 * 根据商品属性和县域id获取个有哪些供应商提供该商品
	 * @param result
	 * @param map {{key:value},{key:value},{key:value}}//商品属性
	 * @param provinceId 省份id
	 * @return
	 * @throws Exception 
	 */
	@Deprecated
	public Result getSupplierByProperty(Result result,long provinceId) throws Exception{
		Map<Long, String> map = new HashMap<Long, String>();
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		List<Long> supplierIds = null;
		try {
			supplierIds = virtualGoodsApi.getSupplierByProperty(result, map, provinceId).getData();
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(provinceId),VasStringUtil.listToString(supplierIds, ','),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getSupplierByProperty");
			
		} catch (Exception e) {
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),String.valueOf(provinceId),e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getSupplierByProperty");
			e.printStackTrace();
		} finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		return result;
	}
	/**
	 * 根据商品类型和省份id 获取虚拟商品供应商
	 * @param result
	 * @param type 话费充值类型的商品
	 * @param den 面额
	 * @param provinceId 省份id  
	 * @return
	 * @throws Exception 
	 */
	public Result getAllSypplierByPropertyAndAreaId(Result result,long den,long type,Long provinceId) throws Exception{
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		List<VirtualGoodsSupplierInfoVo> virtualGoodsSupplierInVos = new ArrayList<VirtualGoodsSupplierInfoVo>();
		try {
			result.setData(null);
			result = virtualGoodsApi.getAllSupplierByPropertyAndAreaId(result,String.valueOf(den) + "元",type, provinceId);
			virtualGoodsSupplierInVos = result.getData();
			log.info("调用getAllSupplierByPropertyAndAreaId返回的数据：" + virtualGoodsSupplierInVos);
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),"面额:" + den + "provinceId: " + provinceId + "type: " + type,JSON.toJSONString(virtualGoodsSupplierInVos),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getAllSupplierByPropertyAndAreaId");
			
			if(null == virtualGoodsSupplierInVos || virtualGoodsSupplierInVos.size() <= 0){
				throw new Exception("调用virtualGoodsApi.getAllSupplierByPropertyAndAreaId接口" + "参数：面额" + den + "服务类型：" + type + "省份id：" + provinceId + "返回的结果为：" + virtualGoodsSupplierInVos);
			}
		} catch (Exception e) {
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),"provinceId" + provinceId + "type" + type,e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getAllSupplierByPropertyAndAreaId");
			
			e.printStackTrace();
			
		}finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		result.setData(virtualGoodsSupplierInVos);
		return result;
	}
	/**
	 * 更新订单状态
	 * @param orderNo 订单号
	 * @param status 状态
	 * @return
	 * @throws Exception 
	 */
	public boolean senMsgToOrd(Result result,String orderNo,String thridOrderNo,String status,Long skuId,Long goodsId,long supplierId,double payAmount,int provinceId){
        if(StringUtils.isNotBlank(orderNo) && StringUtils.isNotBlank(status)){
            TradingVo tradingVo = new TradingVo();
            tradingVo.setOrderCode(orderNo);
            tradingVo.setState(Long.valueOf(status));
            tradingVo.setSkuId(skuId);
            tradingVo.setGoodsId(goodsId);
            tradingVo.setSupplierId(supplierId);
            tradingVo.setPayAmount(payAmount);
            tradingVo.setDepositNo(thridOrderNo);
            tradingVo.setProvinceId((long) provinceId);
            tradingVo.setServerType((long) VasConstant.SupplierType.RECHARGE);
            boolean flag = false;
            try {
            	flag = sendMsgObject(result,VirtualServiceTopic.vasRechargeTopic, tradingVo, null);
                String callResult = flag ? VasConstant.MethodCallState.success+"" : VasConstant.MethodCallState.failure+"";
                PushOrderHistoryVo pushVO = new PushOrderHistoryVo();
                pushVO.setPushParams(JSON.toJSONString(tradingVo));
                pushVO.setPushStatus(Long.parseLong(callResult));
                pushVO.setPushTime(new Date());
                pushVO.setTradingCode(orderNo);

                pushOrderHistoryService.addPushOrderHistory(result, pushVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return flag;
        }else{
            log.info("call senMsgToOrd params is" + "订单号：" + orderNo + "状态：" + status);
            return false;
        }
    }
	/**
	 * 发送消息通知订单组
	 * @param result
	 * @param topic 主题
	 * @param object 消息内容
	 * @param attribute 过滤字段
	 * @return 是否发送成功  true 成功  false 失败
	 * @throws Exception
	 */
	public boolean sendMsgObject(Result result,String topic,Object object,String attribute) throws Exception{
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		boolean flag = false;
		try {
			flag = Producer.getInstance().producerRun(topic, object, attribute);
			log.info("调用producerRun返回的数据：" + flag);
			if(flag){
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),object.toString(),"发送成功" + flag,
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : producerRun");
			}else{
				
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),object.toString(),"发送失败" + flag,
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : producerRun");
			}
		} catch (InterruptedException | MetaClientException e) {
			log.error("发送消息失败！" + e.getMessage());
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),object.toString(),e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : producerRun");
			flag = false;
			e.printStackTrace();
		} finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		return flag;
	}
	/*===================商品接口====================*/
	
	/*===============供应商接口================*/
	/**
	 * 根据供应商ID获取供应商列表（包含权重和经营类型、成功率）
	 * @param result
	 * @param supplierIdList 供应商id集合
	 * @return 供应商对象
	 * @throws Exception
	 */
	public Result getDymmySupplierList(Result result,List<Long> supplierIdList) throws Exception {
		if(null != supplierIdList && supplierIdList.size() > 0){
			IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
			/*List<Long> supplierIdList = new ArrayList<Long>();
			supplierIdList.add(137l);
			supplierIdList.add(169l);*/
			List<SupplierVO> supplierVos = new ArrayList<SupplierVO>();
			try {
				supplierVos = supplierApi.getDummySupplierListService(result, supplierIdList).getData();
				
				log.info("调用getDummySupplierListService返回的数据：" + supplierVos);
				
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),VasStringUtil.listToString(supplierIdList,','),JSON.toJSONString(JSON.toJSONString(supplierVos)),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getDummySupplierListService");
				
				result.setData(supplierVos);
			} catch (Exception e) {
				
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),VasStringUtil.listToString(supplierIdList,','),e.getMessage(),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getDummySupplierListService");
				e.printStackTrace();
				result.setData(null);
			}finally{
				intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
				result.setData(supplierVos);
			}
		}else{
			log.info("供应商id集合为空");
			result.setData(null);
		}
		return result;
		
	}

	/**
	 * 更新供应商成功率
	 * 
	 * @author Administrator <br>
	 * @Date 2016年8月4日<br>
	 * @param result
	 * @return
	 */
	public Result updateCompany(Result result,List<CompanyServiceDetailVO> companyServiceDetailVOs) throws Exception {
		IntInvokingLogVo intInvokingLogVo = new IntInvokingLogVo();
		/*List<CompanyServiceDetailVO> companyServiceDetailVOs = new ArrayList<CompanyServiceDetailVO>();
		CompanyServiceDetailVO cs1 = new CompanyServiceDetailVO();
		//cs1.setId(26l); // 服务id
		cs1.setSuccessRate(52.23); // 成功率
		cs1.setSupplierId(137l); // 供应商id
		cs1.setServiceTypeId(VasConstant.SupplierType.recharge);
		companyServiceDetailVOs.add(cs1);*/
		//supplierApi.updateCompanyServiceDetailVO(result, supplierVOs);  改之前的接口
		if(null != companyServiceDetailVOs && companyServiceDetailVOs.size() > 0){
			try {
				result = supplierApi.updateCompanyServiceDetailVO(result, companyServiceDetailVOs);
				
				log.info("调用updateCompanyServiceDetailVO返回的数据：" + result);
				
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),JSON.toJSONString(companyServiceDetailVOs),JSON.toJSONString(result.getData()),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : updateCompanyServiceDetailVO");
				
			} catch (Exception e) {
				log.info("调用失败", e);
				intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),JSON.toJSONString(companyServiceDetailVOs),e.getMessage(),
						Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : updateCompanyServiceDetailVO");
				e.printStackTrace();
				result.setData(null);
			} finally{
				intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
			}
		}else{
			log.info("CompanyServiceDetailVO 对象为空");
			result.setData(null);
		}
		return result;
	}
	/*===============供应商接口================*/
	
	/*===============区域================*/
	/**
	 * 根据省份名称模糊查询
	 * @param result
	 * @param name 省份名称
	 * @return
	 * @throws Exception 
	 */
	public AreaVo getAreaByTelAreaName(Result result,String name) throws Exception{
		AreaVo areaVo = null;
		IntInvokingLogVo intInvokingLogVo = null;
		try {
			result = addressApi.getAreaByTelAreaName(result,name);
			areaVo = result.getData();
			
			log.info("调用getAreaByTelAreaName返回的数据：" + areaVo);
			
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),name,JSON.toJSONString(areaVo),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.success)),new Date(),"调用方法名称为 : getAreaByTelAreaName");
			
		} catch (Exception e) {
			intInvokingLogVo = new IntInvokingLogVo(Long.valueOf(String.valueOf(VasConstant.OpAction.search)),name,e.getMessage(),
					Long.valueOf(String.valueOf(VasConstant.MethodCallState.failure)),new Date(),"调用方法名称为 : getAreaByTelAreaName");
			e.printStackTrace();
		} finally{
			intInvokingLogService.addIntInvokingLog(result, intInvokingLogVo);
		}
		return areaVo;
	}
	/*===============区域================*/
	
	
	/*===============工具类================*/
	/**
	 * 淘宝网API 根据手机号码判断运营商类型
	 * 
	 * @param mobile
	 * @return
	 */
	public String getMobileByTaobao(String mobile)throws Exception{
		String reqUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+ mobile;
		HttpClient httpclient = new DefaultHttpClient();
		String respStr = "";
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(reqUrl);
			// 执行get请求.
			HttpResponse response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				respStr = EntityUtils.toString(entity, "gb2312");
				respStr = respStr.substring(respStr.indexOf("=") + 1, respStr.length() - 1);
				JSONObject jsonObject = JSONObject.parseObject(respStr);
				String catName = jsonObject.getString("catName");
				respStr = catName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
		}
		return respStr;
	}
	/**
	 * 通过正则根据手机号码判断运营商类型
	 * @param mobile
	 * @return 运营商类型(1-移动、2-联通、3-电信)
	 */
	public int matchesPhoneNumber(String mobile) {
		String cm = "^1(34[0-8]|705|(3[5-9]|5[0127-9]|8[23478]|78)\\d)\\d{7}$";
		String cu = "^1((3[0-2]|45|5[56]|8[56])\\d{8}|709\\d{7})$";
		String ct = "^1((33|53|8[019])[0-9]|349|700)\\d{7}$";
		int type = 0;
		if (mobile.matches(cm)) {
			type = 1;	//移动
		} else if (mobile.matches(cu)) {
			type = 2;	//联通
		} else if (mobile.matches(ct)) {
			type = 3;	//电信
		} else {
			type = 4;	//未知运营商
		}
		return type;
	}
	
	/**
	 * 根据手机号码获取运营商类型(1-移动、2-联通、3-电信、4-未知运营商)
	 * @param mobile
	 * @return
	 */
	public int getOperators(String mobile){
		String operators = "";
		int type = 0;
		try {
			operators = getMobileByTaobao(mobile);
			type = Operators.getType(operators);
			if(1 == type || 2 == type || 3 == type){
				return type;
			}else{
				type = matchesPhoneNumber(mobile);
			}
		} catch (Exception e) {
			type = matchesPhoneNumber(mobile);
			e.printStackTrace();
		}
		return type;
	}
	
	
	/**
	 * 运营商枚举类型
	 * @see 
	 * @since JDK 1.7.0
	 */
	public enum Operators{
		CMCC("中国移动", 1), CUCC("中国联通", 2), CTC("中国电信", 3), OTHER("其他未知", 4);
		
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Operators(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (Operators o : Operators.values()) {
                if (o.getIndex() == index) {
                    return o.name;
                }
            }
            return null;
        }
        /**
         * 根据运营商名称获取index
         * @param type
         * @return
         */
        public static int getType(String type){
        	for(Operators o : Operators.values()){
        		if(o.getName().equals(type) || o.getName().contains(type)){
        			return o.index;
        		}
        	}
        	return OTHER.index;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
	/*===============工具类================*/
	
}
