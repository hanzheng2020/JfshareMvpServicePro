/**
 * 
 */
package com.jfshare.mvp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.JvjindouRuleDao;

/**
 * 等级信息
 */
public class LevelInfoService {
	@Autowired
	private JvjindouRuleDao jvjindouRuleDao;
	@Transactional
	public int openOrdisableJvjindou(int userId, int jvjindou){
		jvjindouRuleDao.selectJvjindouRuleByUserId(userId,jvjindou);
		return 0;
	}

}
