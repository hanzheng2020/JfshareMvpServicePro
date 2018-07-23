package com.jfshare.mvp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.JfRaidersDao;
import com.jfshare.mvp.server.model.TbJfRaiders;

@Service
public class JfRaidersService {

	@Autowired
	private JfRaidersDao jfRaidersDao;
	
	
	public int addjfRaiders(TbJfRaiders jfRaiders) {
		return jfRaidersDao.addRaider(jfRaiders);
	}
	
	
}
