package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;




import com.jfshare.mvp.server.mapper.TbJvjindouRuleMapper;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample.Criteria;
import com.jfshare.mvp.server.model.TbLevelInfo;

/**
 *聚金豆消费
 */
@Repository
public class JvjindouRuleDao {
	@Autowired
	private TbJvjindouRuleMapper jkvjindouRuleMapper;
    
	@Cacheable(cacheNames="jvjindouRules")
	public List<TbJvjindouRule> selectJvjindouRuleByUserId(int userId, int jvjindou){
		TbJvjindouRuleExample example=new TbJvjindouRuleExample();
		Criteria criteria = example.createCriteria();
		return jkvjindouRuleMapper.selectByExample(example);
	}

	//增加赠送规则
	@CachePut(cacheNames="jvjindouRules")
	public int insertSelective(TbJvjindouRule record) {
		return jkvjindouRuleMapper.insertSelective(record);
	}
	
	//查询赠送聚金豆
	//@Cacheable(cacheNames="jvjindouRules")
	public TbJvjindouRule selectByPrimaryKey(Integer id) {
		return jkvjindouRuleMapper.selectByPrimaryKey(id);
	}
	@CachePut(cacheNames="jvjindouRules")
	public int updateByPrimaryKeySelective(TbJvjindouRule record){
		return jkvjindouRuleMapper.updateByPrimaryKeySelective(record);
	}
}
