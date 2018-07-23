package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


import com.jfshare.mvp.server.mapper.TbJvjindouRuleMapper;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample.Criteria;

/**
 *聚金豆消费
 */
@Repository
public class JvjindouRuleDao {
	@Autowired
	private TbJvjindouRuleMapper JvjindouRuleMapper;
    
	@Cacheable(cacheNames="jvjindouRules")
	public List<TbJvjindouRule> selectJvjindouRuleByUserId(int userId, int jvjindou){
		TbJvjindouRuleExample example=new TbJvjindouRuleExample();
		Criteria criteria = example.createCriteria();
		return JvjindouRuleMapper.selectByExample(example);
	}
}
