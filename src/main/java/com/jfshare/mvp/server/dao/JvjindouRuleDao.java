package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbJvjindouRule;
import com.jfshare.mvp.server.mapper.TbJvjindouRuleExample;
import com.jfshare.mvp.server.mapper.TbJvjindouRuleMapper;

/**
 *聚金豆消费
 */
@Repository
public class JvjindouRuleDao {
	@Autowired
	private TbJvjindouRuleMapper JvjindouRuleMapper;
    
	@Cacheable(cacheNames="jvjindouRules")
	public List<TbJvjindouRule> selectJvjindouRuleByUserId(){
		TbJvjindouRuleExample example=new TbJvjindouRuleExample();
		return JvjindouRuleMapper.selectByExample(example);
	}
}
