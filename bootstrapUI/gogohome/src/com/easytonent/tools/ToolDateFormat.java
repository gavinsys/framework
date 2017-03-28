package com.easytonent.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolDateFormat {
	public static final String pattern_1 = "yyyy-MM-dd";
	public static final String pattern_2 = "yyyyMMddHHmmssSSS";
	
	public static Date string2Date(String date, String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String date2String(Date date, String pattern){
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
