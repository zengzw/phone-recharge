package com.tsh.recharge.foundation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 日期工具类
 *
 * @author zengzw
 * @date 2016年7月27日
 */
public class DateUtils {

    
    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String FORMAT_MILLISECOND  = "yyyy-MM-dd HH:mm:ss SSS";
    
    public static final String FORMAT_DAY = "yyyy-MM-dd";
    
    
	public static Date parseDate(String dateStr, String pattern) {
	
	    if(StringUtils.isEmpty(pattern)){
	        pattern = FORMAT_DEFAULT;
	    }
	    
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String dateToString(Date date, String pattern) {
	    if(StringUtils.isEmpty(pattern)){
	        pattern = FORMAT_DEFAULT;
	    }
	    
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
