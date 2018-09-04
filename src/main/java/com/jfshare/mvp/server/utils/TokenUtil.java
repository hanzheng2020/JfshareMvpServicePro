package com.jfshare.mvp.server.utils;

import org.springframework.util.Base64Utils;

/**
 * 张佳斌
 * @author Administrator
 * 简易字符串加密解密工具
 */
public class TokenUtil {
	
	/**
	 * 字符串加密
	 * @param data
	 * @return
	 */
	public static String enStr(String data){
		 char[] c = data.toCharArray();
		 //使用for循环给字符数组加密
         for(int i=0;i<c.length;i++){
             c[i] = (char)(c[i]^20000);
         }
         String string = new String(c);
        byte[] decode = Base64Utils.encode(string.getBytes());
		return new String(decode);
	}
	
	
	/**
	 * 字符串解密
	 * @param data
	 * @return
	 */
	public static String deStr(String data){
		byte[] decode2 = Base64Utils.decode(data.getBytes());
        char[] array = new String(decode2).toCharArray();
        for(int i=0;i<array.length;i++){
       	 array[i] = (char)(array[i]^20000);
        }
       return new String(array);
	}
}
