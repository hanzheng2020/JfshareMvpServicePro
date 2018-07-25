package com.jfshare.mvp.server.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.JvjindouRuleDao;
import com.jfshare.mvp.server.model.TbJvjindouRule;

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

	
	public int insertJvjindouRule(TbJvjindouRule jvjindouRule){
		jvjindouRule.setCreateTime(new Date());
		return jvjindouRuleDao.insertSelective(jvjindouRule);
	}

	public TbJvjindouRule getJvjindouRule(Integer id) {
		return jvjindouRuleDao.selectByPrimaryKey(id);
	}

	public int updateJvjindouRule(TbJvjindouRule record) {
		return jvjindouRuleDao.updateByPrimaryKeySelective(record);
	}

}
