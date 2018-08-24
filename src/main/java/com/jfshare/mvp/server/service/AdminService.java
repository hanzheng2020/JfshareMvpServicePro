package com.jfshare.mvp.server.service;

import java.util.Map;

public interface AdminService {
	/**
	 * 管理员登陆
	 * @param loginId
	 * @param pwd
	 * @return
	 */
	Map<String, Object> adminLogin(String loginId, String pwd);
	
	/**
	 * 接口权限token校验
	 * @param token
	 */
	Map<String, Object> checkToken(String token);
}
