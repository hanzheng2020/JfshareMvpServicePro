package com.jfshare.mvp.server.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解密工具类
 * @author fengxiang
 * @date 2018-06-25
 */
public class DecryptUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(DecryptUtils.class);
	
	public final static String oriData = "5a678zq9tABCxDsrc01dv4EMbNOynfw23mPQRugSeFGhHIJjiKLTkoUVlpWXYZ";
	
	public static String couponCodeDecrypt(String data) {
		logger.info("券码解密前：" + data);
		String secret = data.substring(16);
		String code = data.substring(0, 16);
		char[] chars = oriData.toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    char[] dataChar = code.toCharArray();
	    byte[] scByte = secret.getBytes();
	    for (int i = 0; i < dataChar.length/2; i++) {
	    	int bit = oriData.indexOf(dataChar[i*2]) * chars.length + oriData.indexOf(dataChar[i*2+1]) - Byte.valueOf(scByte[i]).intValue();
	    	sb.append((char) bit);
	    }
	    sb.append(secret);
	    logger.info("券码解密后：" + sb.toString());
	    return sb.toString();
	}
	
	/**
	 * BASE64解密算法
	 * @param data 需要加密的数据
	 * @return
	 * @throws Exception
	 */
	public static byte[] base64Decrypt(String data) {
		return Base64.decodeBase64(data);
	}
	
	/**
	 * 3DES解密算法
	 * @param data 需要解密的数据
	 * @param desKey 加密私钥
	 * @param desIv 偏移向量
	 * @return
	 */
	public static String des3Encrypt(byte[] data, String desKey, String desIv) {
		String ret = "";
		try {
			IvParameterSpec iv = new IvParameterSpec(desIv.getBytes());
			DESedeKeySpec key = new DESedeKeySpec(desKey.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(key);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
			ret = new String(cipher.doFinal(data), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return ret;
	}
}
