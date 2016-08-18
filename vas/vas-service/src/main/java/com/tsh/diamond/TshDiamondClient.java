package com.tsh.diamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dtds.platform.diamond.TshPropertyPlaceholderConfigurer;

/**
 * 初始化配置信息
 * 
 * @author cwli
 * 
 */
public class TshDiamondClient extends TshPropertyPlaceholderConfigurer {

	private static TshDiamondClient tshDiamondClient = new TshDiamondClient();

	public static TshDiamondClient getInstance() {
		return tshDiamondClient;
	}

	public void init() {
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("group", "vas");
		map1.put("dataId", "vas-jdbc");
		list.add(map1);

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("group", "common");
		map2.put("dataId", "zookeeper");
		list.add(map2);
		
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("group", "common");
		map3.put("dataId", "redis");
		list.add(map3);
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("group", "common");
		map4.put("dataId", "sms");
		list.add(map4);

		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("group", "common");
		map5.put("dataId", "metaq");
		list.add(map5);

		// solr
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("group", "common");
		map6.put("dataId", "solr");
		list.add(map6);

		// lts
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("group", "common");
		map7.put("dataId", "lts");
		list.add(map7);

		// 阿里mq(暂时未用)
		/*Map<String, String> map8 = new HashMap<String, String>();
		map8.put("group", "common");
		map8.put("dataId", "rocketmq");
		list.add(map8);*/

		// 图片上传
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("group", "common");
		map9.put("dataId", "oss");
		list.add(map9);
		
		//话费充值服务
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("group", "vas");
		map10.put("dataId", "vas-recharge");
		list.add(map10);
		
		

		this.loadMultConfigFromDiamond(list);
	}
	

}
