package com.jfshare.mvp.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.dao.AdminDao;
import com.jfshare.mvp.server.mapper.TbAdminMapper;
import com.jfshare.mvp.server.model.TbAdmin;
import com.jfshare.mvp.server.model.TbAdminExample;
import com.jfshare.mvp.server.model.TbAdminExample.Criteria;
@Repository
public class AdminDaoImpl implements AdminDao{

	
	@Autowired
	TbAdminMapper tbAdminMapper;
	
	@Override
	public TbAdmin adminLogin(String loginId, String pwd) {
		TbAdminExample example = new TbAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginIdEqualTo(loginId);
		criteria.andPwdEqualTo(pwd);
		List<TbAdmin> list = tbAdminMapper.selectByExample(example);
		if(list==null||list.size()==0){
			return null;
		}
		//登陆成功更新登陆时间
		TbAdmin tbAdmin = list.get(0);
		tbAdmin.setLastLoginTime(new Date());
		tbAdminMapper.updateByPrimaryKeySelective(tbAdmin);
		return list.get(0);
	}
	
}
