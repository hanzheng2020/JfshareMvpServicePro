package com.jfshare.mvp.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.AdminDao;
import com.jfshare.mvp.server.model.TbAdmin;
import com.jfshare.mvp.server.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	@Override
	public TbAdmin adminLogin(String loginId, String pwd) {
		TbAdmin admin = adminDao.adminLogin(loginId,pwd);
		//生成redis标识
		
		return admin;
	}

}
