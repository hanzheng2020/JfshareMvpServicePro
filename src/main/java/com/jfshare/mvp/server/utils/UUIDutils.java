package com.jfshare.mvp.server.utils;

import java.util.UUID;

public class UUIDutils {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}
	/**
	 * 时间戳转ASCII字符串
	 * @return
	 */
	public static String getLongToStr(long data) {
		StringBuffer sb = new StringBuffer();
		long s = 100;
		while (data > 0) {
			long c = data%s + 32;
			data = data/s;
			if (c > 126) {
				c = 126;
			}
			sb.append((char) c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
//		System.out.println(getTimeStr());
	}
}
