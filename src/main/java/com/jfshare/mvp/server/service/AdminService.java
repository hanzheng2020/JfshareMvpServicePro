package com.jfshare.mvp.server.service;

import com.jfshare.mvp.server.model.TbAdmin;

public interface AdminService {
	/**
	 * 管理员登陆
	 * @param loginId
	 * @param pwd
	 * @return
	 */
	TbAdmin adminLogin(String loginId, String pwd);

}
