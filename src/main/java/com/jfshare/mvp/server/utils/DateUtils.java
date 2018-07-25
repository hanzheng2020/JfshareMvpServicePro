package com.jfshare.mvp.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 日期转换为制定的字符串格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (null == date)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
