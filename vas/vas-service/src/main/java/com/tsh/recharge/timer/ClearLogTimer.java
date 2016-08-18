package com.tsh.recharge.timer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtds.platform.util.bean.Result;
import com.tsh.diamond.TshDiamondClient;
import com.tsh.vas.service.bill.IntInvokingLogService;
/**
 * 预留60天的日志，其他的清理
 * @see 
 * @since JDK 1.7.0
 */
public class ClearLogTimer {
	@Autowired
    private IntInvokingLogService intInvokingLogService;
	
	private static TshDiamondClient tshDiamondClient = TshDiamondClient.getInstance();
	
    private static final Logger LOGGER = Logger.getLogger(ClearLogTimer.class);
    /**
     * 每天定时执行
     * @Date 2016年8月16日<br>
     */
    public void start(){
    	LOGGER.info("开始清理日志");
    	try {
    		//是否进行清理的开关
        	String reservedSwitch = tshDiamondClient.getConfig("reservedSwitch");
        	//动态获取日志文件预留天数
        	String reservedDate = tshDiamondClient.getConfig("reservedDate");
        	if(StringUtils.isNotBlank(reservedSwitch)){
        		boolean flag = Boolean.parseBoolean(reservedSwitch);
        		if(flag){
        			if(!StringUtils.isNotBlank(reservedDate)){
        	    		//默认预留60天的日志
        	    		reservedDate = "60";
        	    	}
        	    	Integer reserved = Integer.valueOf(reservedDate);
        	        Result result = new Result();
        	        int count = intInvokingLogService.ReservedIntInvokingLogThirty(result,reserved).getData();
        	        LOGGER.info("清理" + count + "条日志数据!");
        		}
        	}
		} catch (Exception e) {
			LOGGER.error("清理日志表程序异常" + e.getMessage());
		}
    }
}

