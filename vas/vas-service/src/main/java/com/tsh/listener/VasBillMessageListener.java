package com.tsh.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.commons.utility.DateUtil;
import com.dtds.platform.util.SerializeUtil;
import com.dtds.platform.util.bean.Result;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.tsh.dubbo.bis.vo.CompanyServiceDetailVO;
import com.tsh.dubbo.bis.vo.SupplierVO;
import com.tsh.dubbo.gms.vo.VirtualGoodsSupplierInfoVo;
import com.tsh.recharge.facade.PhoneRechargeFacade;
import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.model.PhoneLocationVo;
import com.tsh.share.vo.AreaVo;
import com.tsh.vas.metaq.vo.TradingVo;
import com.tsh.vas.po.bill.DepositPo;
import com.tsh.vas.po.bill.DepositStatisPo;
import com.tsh.vas.po.bill.TradingPo;
import com.tsh.vas.service.bill.DepositService;
import com.tsh.vas.service.bill.DepositStatisService;
import com.tsh.vas.service.bill.PrepaidRechargeService;
import com.tsh.vas.service.bill.PrepaidRechargeService.Operators;
import com.tsh.vas.service.bill.TradingService;
import com.vas.util.VasConstant;
import com.vas.util.VasStringUtil;
/**
 * 话费充值消费者消息监听器
 * @version 1.0.0 2016年8月9日<br>
 * @see 
 * @since JDK 1.7.0
 */
@SuppressWarnings("all")
public class VasBillMessageListener extends DefaultMessageListener<String> {
	private static final Logger log = Logger.getLogger(VasBillMessageListener.class);

	@Autowired
	private PrepaidRechargeService prepaidRechargeService;

	@Autowired
	private PhoneRechargeFacade phoneRechargeFacade;
	
	@Autowired
	private TradingService tradingService;
	
	@Autowired
	private DepositStatisService depositStatisService;
	
	@Autowired
	private DepositService depositService;

	@Override
	public void onReceiveMessages(MetaqMessage<String> metaqMessage) {
		log.info("receive trade message:" + metaqMessage);
	}
	/**
	 * 接收消息后的实现
	 */
	@Override
	public void recieveMessages(Message msg){
		log.info("接收日期：" + DateUtil.getDate(new Date()));
		log.info("接收到的内容是：" + SerializeUtil.unserialize(msg.getData()).toString());
		Result result = new Result();
		try{
			if(VasConstant.VirtualServiceTopic.ordVirRechargeTopic.equals(msg.getTopic())){
				//System.out.println("==============="+msg.getId()); //每个消息都会生成一个全局唯一ID
				//System.out.println( "Attribute:"+ msg.getAttribute()); //备用字段，每条消息产生可以选择是否设置备用字段。可用于接收消息之后的其他业务判断
				TradingVo tradingVo = (TradingVo) SerializeUtil.unserialize(msg.getData());
				com.tsh.vas.vo.bill.TradingVo t = new com.tsh.vas.vo.bill.TradingVo();
				BeanUtils.copyProperties(tradingVo, t);
				dealWithOrder(result, t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 同步处理订单
	 * @param result
	 * @param t
	 * @throws Exception
	 */
	public synchronized void dealWithOrder(Result result,com.tsh.vas.vo.bill.TradingVo t) throws Exception{
		addTradingAndDepositAndDepositStatis(result, t);
		String tradigNo = result.getData();
		if(StringUtils.isNotEmpty(tradigNo)){
			phoneRechargeFacade.callRetryRecharge(tradigNo);
		}
	}
	/**
	 * 订单通知过来同时插入trading、deposit_statis、deposit
	 * @param result
	 * @param tradingVo
	 * @return
	 */
	public Result addTradingAndDepositAndDepositStatis(Result result,com.tsh.vas.vo.bill.TradingVo tradingVo){
		String mobile = tradingVo.getMobile();
		List<DepositPo> depositPos = new ArrayList<DepositPo>();
		List<DepositStatisPo> depositStatisPos = new ArrayList<DepositStatisPo>();
		List<Long> supplierIdList = new ArrayList<Long>();
		String depositCode = VasStringUtil.createDepositCode();
		List<DepositStatisPo> list = new ArrayList<DepositStatisPo>();
		//根据手机号码获取所属省份名称
		AreaVo areaVo;
		try {
			PhoneLocationVo phoneLocationVo = phoneRechargeFacade.queryPhoneSegment(mobile);
			if(null != phoneLocationVo){
				areaVo = prepaidRechargeService.getAreaByTelAreaName(result, phoneLocationVo.getProvinceName());
				
				log.info("prepaidRechargeService.getAreaByTelAreaName :" + areaVo);
				
				List<VirtualGoodsSupplierInfoVo> virtualGoodsSupplierInVos = prepaidRechargeService.getAllSypplierByPropertyAndAreaId(result, tradingVo.getPostAmount().longValue(),VasConstant.SupplierType.RECHARGE,Long.valueOf(String.valueOf(areaVo.getId()))).getData();
				
				log.info("prepaidRechargeService.getAllSypplierByPropertyAndAreaId :" + virtualGoodsSupplierInVos);
				
				if(null != virtualGoodsSupplierInVos && virtualGoodsSupplierInVos.size() > 0){
					//修改状态
					tradingVo.setState(Long.valueOf(Configurations.OrderStatus.INITIAL));
					tradingVo.setTradingCode(depositCode);
					//外部订单入库
					TradingPo tradingPo =  tradingService.addTrading(result, tradingVo).getData();
					for(VirtualGoodsSupplierInfoVo vgsiv : virtualGoodsSupplierInVos){
						supplierIdList.add(vgsiv.getSupplierId());
					}
					List<SupplierVO> supplierVos = prepaidRechargeService.getDymmySupplierList(result, supplierIdList).getData();
					
					log.info("prepaidRechargeService.getDymmySupplierList :" + supplierVos);
					
					for(SupplierVO sv : supplierVos){
						List<CompanyServiceDetailVO> companyServiceDetailVOs = sv.getCompanyServiceDetailVOs();
						if(companyServiceDetailVOs.size() <= 0){
							log.info("companyServiceDetailVOs 小于等于");
						}
						for(CompanyServiceDetailVO csdv : companyServiceDetailVOs){
							//1.充话费 2.交水费 3.汽车票 4.火车票
							if(VasConstant.SupplierType.RECHARGE == csdv.getServiceTypeId()){
								//deposit
								DepositPo dp = new DepositPo();
								dp.setSupplierName(sv.getCompanyName());
								dp.setTradingCode(depositCode);
								dp.setWeight(csdv.getPriority() == null ? 0 : csdv.getPriority());
								dp.setMobile(mobile);
								dp.setSupplierCode(sv.getSupplierNo());
								dp.setSupplierId(sv.getId());
								dp.setSupplierName(sv.getShowName());
								dp.setNumberType(Long.valueOf(String.valueOf(Operators.getType(phoneLocationVo.getType()))));
								dp.setPostAmount(tradingVo.getPostAmount());
								dp.setState(Long.valueOf(Configurations.OrderStatus.INITIAL));
								dp.setTradingId(tradingPo.getId());
								depositPos.add(dp);
								
								DepositStatisPo depositStatisPo = depositStatisService.getDepositStatisListBySupplierId(result, csdv.getSupplierId()).getData();
								if(null == depositStatisPo){
									//deposit_statis
									DepositStatisPo dsp = new DepositStatisPo();
									dsp.setSupplierId(sv.getId());
									dsp.setSupplierCode(sv.getSupplierNo());
									dsp.setSupplierName(sv.getCompanyName());
									dsp.setSuccCount(0L);
									dsp.setFailCount(0L);
									depositStatisPos.add(dsp);
								}
							}
						}
					}
					//入 deposit
					depositService.batchSaveDeposit(result, depositPos);
					//入 deposit_statis
					if(null != depositStatisPos && depositStatisPos.size() > 0){
						list = VasStringUtil.removeDuplicate(depositStatisPos);
						depositStatisService.batchSaveDepositStatis(result, list);
					}
					result.setData(null);
					result.setData(depositCode);
				}
			}else{
				log.error("获取手机号码段 为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取手机号码段 为空!" + e.getMessage());
		}
		return result;
	}
}

