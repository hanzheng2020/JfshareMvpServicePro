/**
 * 
 */
package com.jfshare.mvp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.JvjindouRuleDao;

/**
 * 聚金豆service
 * @date 2018-07-23
 */
@Service
public class JvjindouRuleService {
	
	@Autowired
	private JvjindouRuleDao jvjindouRuleDao;
	@Transactional
	public int openOrdisableJvjindou(int userId, int jvjindou){
		jvjindouRuleDao.selectJvjindouRuleByUserId(userId,jvjindou);
		return 0;
	}

}
