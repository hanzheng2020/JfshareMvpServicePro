package com.jfshare.mvp.server.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 加密工具类
 * 
 * @author fengxiang
 * @date 2018-06-25
 */
public class EncryptUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

	/**
	 * 3DES加密算法
	 * @param data 需要加密的数据
	 * @param desKey 加密私钥
	 * @param desIv 偏移向量
	 * @return
	 */
	public static byte[] des3Encrypt(String data, String desKey, String desIv) {
		byte[] ret = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(desIv.getBytes());
			DESedeKeySpec key = new DESedeKeySpec(desKey.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(key);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
			ret = cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return ret;
	}

	/**
	 * MD5加密算法
	 * @param data 需要加密的数据
	 * @return
	 */
	public static String md5Encrypt(String data) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = data.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * BASE64加密算法
	 * @param data 需要加密的数据
	 * @return
	 * @throws Exception
	 */
	public static String base64Encrypt(byte[] data) {
		Base64 base64 = new Base64();
		base64.encodeToString(data);
		return base64.encodeToString(data);
	}
	
	/**
	 * 券码加密方式
	 * @param data 8位需要加密的券码
	 * @param secret 8位加密秘钥
	 * @return
	 */
	public static String couponCodeEncrypt(String data, String secret) {
		logger.info("券码加密前：" + data);
		String oriData = DecryptUtils.oriData;
		char[] chars = oriData.toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] dataByte = data.getBytes();
	    byte[] scByte = secret.getBytes();
	    for (int i = 0; i < dataByte.length; i++) {
	    	int bit = Byte.valueOf(dataByte[i]).intValue() + Byte.valueOf(scByte[i]).intValue();
	    	sb.append(chars[bit/chars.length]);
    		sb.append(chars[bit%chars.length]);
	    }
	    logger.info("券码加密后：" + sb.toString());
	    return sb.toString();
	}
	public static void main(String[] args) {
		//System.out.println(couponCodeEncrypt("treqwe33","&&4ertsw"));
		String con="9N909I81AA9E9H9G";
		System.out.println(con.substring(con.length()-8,con.length()));
	}

	
	 final static char[] digits = {
		      '0', '1', '2', '3', '4', '5', '6', '7',
		      '8','9', 'A', 'B', 'C', 'D', 'E', 'F',
		      'G', 'H','J', 'K', 'L','M', 'N',  'P',
		      'R', 'S', 'T', 'U', 'W', 'X', 'Y','Z' };
	 
	 //转进制
	public static String numericToString(int i, int system) {
		  long num = 0;
		  if (i < 0) {
		   num = ((long) 2 * 0x7fffffff) + i + 2;
		  } else {
		   num = i;
		  }
		  char[] buf = new char[32];
		  int charPos = 32;
		  while ((num / system) > 0) {
		   buf[--charPos] = digits[(int) (num % system)];
		   num /= system;
		  }
		  buf[--charPos] = digits[(int) (num % system)];
		  return new String(buf, charPos, (32 - charPos));
	}
	
	//合并卷码与卡密
	public static String mergeCouPonCode(String data,String secret) {
		StringBuffer sb = new StringBuffer();
		sb.append(data);
		sb.append(secret);
		return sb.toString();
	}
	
	
	public static  String couPon(String couPonCode) {
		String couCodeSecret = couPonCode.substring(couPonCode.length()-8,couPonCode.length());
		String couCode=couPonCode.substring(0,8);
		couCode=couponCodeEncrypt(couCode,couCodeSecret);
		return (couCode+couCodeSecret);
	}
	
}
