package com.jfshare.mvp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.JvjindouRuleDao;
import com.jfshare.mvp.server.dao.LevelInfoDao;

/**
 * 等级信息
 */
@Service
public class LevelInfoService {
	@Autowired
	private LevelInfoDao levelInfoDao;
	@Transactional
	public int openOrdisableJvjindou(int userId, int jvjindou){
		levelInfoDao.selectJvjindouRuleByUserId(userId,jvjindou);
		return 0;
	}

}
