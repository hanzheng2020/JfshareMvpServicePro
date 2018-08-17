package com.jfshare.mvp.server.thirdinterface;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088701691132875";
	
	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "windy@jfshare.com";  //elaine@jfshare.com   windy@jfshare.com

	// 商户的私钥
	public static String key = "err1r74okaardf0zrm3cir1o1zznrvzp";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
//	public static String sign_type = "MD5";


	public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?" +
			"_input_charset=" + AlipayConfig.input_charset;

	/**
	 * RSA私钥 pkcs8格式
	 */
	public static final String ALIPAY_RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANVmgr9np6ygCEqV" +
			"oJiRBKxqQkOy0ihwlOtcKc2gUTmYVYbKYQ5a/CPiLLt1AJrAcTO5w1iqTKpQKZKb" +
			"BoYRrkK//4VAkNgwh8KbeT8zPqfkmXUx8I7IQkmOqRDzwrDC/z46TJvU9oW0l5HB" +
			"5KJqBiob1s2lYXqvYEZNcV9bDOipAgMBAAECgYBNsHM7IY5ujxhVpuF/iZQM/OZA" +
			"5TITXOEqSjRB0vKfKDFC0BzDcCZJHgcw+6iLrjHIJgHTHrSocGSiJeQbHQa2RhTg" +
			"Nee4GE93H1Gez7U72I1nlMvaKHNz1UHfaFouE2f85RHtnpGHFrkhdJke+6Gw+QEK" +
			"5vn6ddzTn66x8+BDAQJBAPB9dChnRzjUllZabAolbcVw0H6UGxljhxRJtWDFzBfD" +
			"KMLWWeZcwrkpejHLqVCqVKlrobDUb4jGfydkUhD9P4kCQQDjKc0UxcTxI1Yr+8T+" +
			"alDyMtiLXewe6fVcg89jX7WPJ4XDWuOwDjx7jRPXs491AS7JJYwzVOwqRGV9/PuU" +
			"Q/ghAkAW4Sia63B4NKl037dlo7f0z83WLQsC+FgSkwyhf0/ydsu69Z+p7etMqGiK" +
			"skCSfxH86FOCukM+NPxF6CJyoVVZAkAbM95DJou29KxVCAlHDiQzXLNhvRWRfiLL" +
			"uV0UYVm0kt8JzdCGPK4xfWtq4S3ErSeVHtHzM7A0P4yH0cuurZUBAkBuN2qOcmGF" +
			"A/1+GJuCsDo86qFO0Dq3j1JWwq9Q1dUvDNjmn5DmHibq3+EncQ71NyuHmofWx3nB" +
			"l+VYAAU1NpYI";

	public static final String ALIPAY_RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVZoK/Z6esoAhKlaCYkQSsakJDstIocJTrXCnNoFE5mFWGymEOWvwj4iy7dQCawHEzucNYqkyqUCmSmwaGEa5Cv/+FQJDYMIfCm3k/Mz6n5Jl1MfCOyEJJjqkQ88Kwwv8+Okyb1PaFtJeRweSiagYqG9bNpWF6r2BGTXFfWwzoqQIDAQAB";




	// 调用接口的版本
 	public static String version = "1.0";
	// 应用的id   https://openhome.alipay.com/platform/keyManage.htm
	public static String APP_ID = "2016041801310729";
	/**  这里的公钥 是阿里自己的公共的公钥,----不是我们自己生成的公钥!!!! -----请勿修改 (仅对RSA1)  */
	public static final String ALIPAY_RSA_PUBLIC_New = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi2FLd8IMYi7llBXM/CpVaKlEDvlK3xW1shz2WB1FBS0EIY+fugxxugKwTiLnLfkA5yiPEhId7PNYDA2lveRF7IceQ6msk4U44yw09jDv6bvBy3XrlQZrVTgeoa4V170IwvQ5W+HHDm9wJwYIyhZ5J35APoW38iGcNQHVmk6KCvCnDcqwToJwuxuzNoK49gTVRyHjv/EDvE76dGkcMCP54OXgnf4DrAv6R7XYi29yA3AkoG4H06lgb1SJLW5hH4bMwUfD4QQDCqzUvwKir3o7fMzTpmbzyZYDAFwSk40bf0qN2TUE62JpnC4fnmuvJYfiXuLcT8epi9/ZQ4jB4W+0hwIDAQAB";



}
