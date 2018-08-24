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

import liquibase.util.MD5Util;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	@Override
	public Map<String, Object> adminLogin(String loginId, String pwd) {
		TbAdmin admin = adminDao.adminLogin(loginId,pwd);
		if(admin==null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		//限制一处登陆
		String token = MD5Util.computeMD5(loginId+pwd+new Date().toString());
		//生成redis标识
		JedisClusterUtils.saveNX("MVP:admin:"+token, token, 2*60);
		admin.setPwd("");
		map.put("admin", admin);
		map.put("token", token);
		return map;
	}

	@Override
	public Map<String, Object> checkToken(String token) {
		String key = "MVP:admin:"+token;
		String string = JedisClusterUtils.getString(key);
		Map<String, Object> map = new HashMap<>();
		if(null==string||"".equals(string)){
			map.put("result", false);
			map.put("msg", "登陆失效，请重新登陆");
		}else{
			//刷新token生存时间
			JedisClusterUtils.expire(key, 60);
			map.put("result", true);
		}
		return map;
	}

}
