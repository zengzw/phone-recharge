package com.tsh.dubbo.diamond;

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
		Map<String, String> map1 = new HashMap<>();
		map1.put("group", "vas");
		map1.put("dataId", "vas-jdbc");
		list.add(map1);
		Map<String, String> map2 = new HashMap<>();
		map2.put("group", "common");
		map2.put("dataId", "zookeeper");
		list.add(map2);
		Map<String, String> map3 = new HashMap<>();
		map3.put("group", "common");
		map3.put("dataId", "redis");
		list.add(map3);
		this.loadMultConfigFromDiamond(list);
	}

}
