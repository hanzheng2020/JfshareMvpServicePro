package com.jfshare.mvp.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.AdminDao;
import com.jfshare.mvp.server.model.TbAdmin;
import com.jfshare.mvp.server.service.AdminService;
import com.jfshare.mvp.server.utils.JedisClusterUtils;
import com.jfshare.mvp.server.utils.TokenUtil;

import scala.collection.mutable.StringBuilder;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	public static void main(String[] args) {
		/*String password = "admin&&&&13246543210";
		 char[] c = password.toCharArray();
		 //使用for循环给字符数组加密
         for(int i=0;i<c.length;i++){
             c[i] = (char)(c[i]^20000);
         }
         String string = new String(c);
         byte[] decode = Base64Utils.encode(string.getBytes());
         String string2 = new String(decode);
         System.out.println("加密后："+string2);
         byte[] decode2 = Base64Utils.decode(string2.getBytes());
         char[] array = new String(decode2).toCharArray();
         for(int i=0;i<array.length;i++){
        	 array[i] = (char)(array[i]^20000);
         }
         System.out.println("解密后"+new String(array));*/
		String a = "asdfasdad";
		String s = a.split("&&&&")[0];
		System.out.println(s);
	}
	
	@Override
	public Map<String, Object> adminLogin(String loginId, String pwd) {
		TbAdmin admin = adminDao.adminLogin(loginId,pwd);
		if(admin==null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		//限制一处登陆
		StringBuilder builder = new StringBuilder().append(loginId).append("&&&&").append(new Date().getTime());
		String token = TokenUtil.enStr(builder.toString());
		//生成redis标识
		JedisClusterUtils.putKV("MVP:admin:"+loginId, token, 30*60);
		admin.setPwd("");
		map.put("admin", admin);
		map.put("token", token);
		return map;
	}

	@Override
	public Map<String, Object> checkToken(String token) {
		String data = TokenUtil.deStr(token);
		String loginId = data.split("&&&&")[0];
		
		String key = "MVP:admin:"+loginId;
		String string = JedisClusterUtils.getString(key);
		Map<String, Object> map = new HashMap<>();
		if(null==string||"".equals(string)||!token.equals(string)){
			map.put("result", false);
			map.put("msg", "登陆失效，请重新登陆");
		}else{
			//刷新token生存时间
			JedisClusterUtils.expire(key, 30*60);
			map.put("result", true);
		}
		return map;
	}

}
