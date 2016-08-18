package com.vas.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
@SuppressWarnings("all")
public class VasStringUtil {
	/**
	 * list 转 String
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List list, char separator) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(),separator);
	}
	/**
	 * 生成DepositCode
	 * @return
	 */
	public static String createDepositCode(){
		return UUID.randomUUID().toString();
	}
	/**
	 * list去重
	 * @param list
	 * @return
	 */
	public static List removeDuplicate(List list) { 
		HashSet h = new HashSet(list); 
		list.clear(); 
		list.addAll(h); 
		return list;
	}
}
