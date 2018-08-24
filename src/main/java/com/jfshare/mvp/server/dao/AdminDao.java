package com.jfshare.mvp.server.dao;

import com.jfshare.mvp.server.model.TbAdmin;

public interface AdminDao {
	
	TbAdmin adminLogin(String loginId, String pwd);
	
}
