package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;



import com.jfshare.mvp.server.mapper.TbJvjindouRuleMapper;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample.Criteria;



@Repository
public class JvjindouRuleDao {
	@Autowired
	private TbJvjindouRuleMapper jvjindouRuleMapper;
    

	public List<TbJvjindouRule> selectJvjindouRule(){
		TbJvjindouRuleExample example=new TbJvjindouRuleExample();
		return jvjindouRuleMapper.selectByExample(example);
	}
	

	public TbJvjindouRule selectByPrimaryKey(Integer id) {
		return jvjindouRuleMapper.selectByPrimaryKey(id);
	}

	
	public int updateByPrimaryKeySelective(TbJvjindouRule record){
		record.setUpdateTime(new Date());
		return jvjindouRuleMapper.updateByPrimaryKeySelective(record);
	}

	public int insertSelective(TbJvjindouRule jvjindouRule) {
		return jvjindouRuleMapper.insertSelective(jvjindouRule);
	}
	
	public List<TbJvjindouRule> selectByExample(TbJvjindouRuleExample example){
		List<TbJvjindouRule> jvjindouRules=jvjindouRuleMapper.selectByExample(example);
		return jvjindouRules;
	}
}
