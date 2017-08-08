package com.sports.meetup.field.utils;

import java.text.SimpleDateFormat;

/**
 * 
 * @author 
 *	 按照日期生成文件夹名
 * /2017/08/08/
 */
public class MkdirUtil {
	
	private static SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy-MM-dd");
	 public static String getDirname(long currentTimeMillis)  { 
		 	String dateTime = sdfYmd.format(currentTimeMillis);
	        String nian=dateTime.substring(0,4);  
	        String yue=dateTime.substring(5,7);  
	        String ri=dateTime.substring(8,10);  
	        return "/"+nian+"/"+yue+"/"+ri+"/";  
	    } 
}
