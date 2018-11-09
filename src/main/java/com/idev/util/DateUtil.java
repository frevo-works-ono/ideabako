package com.idev.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date now() {
		return new Date();
	}
	
	public static String format(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
