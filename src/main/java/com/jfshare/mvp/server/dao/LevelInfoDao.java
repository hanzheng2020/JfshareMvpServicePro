package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbLevelInfo;
import com.jfshare.mvp.server.mapper.TbLevelInfoExample;
import com.jfshare.mvp.server.mapper.TbLevelInfoExample.Criteria;
import com.jfshare.mvp.server.mapper.TbLevelInfoMapper;

/**
 *等级信息
 */
@Repository
public class LevelInfoDao {
	@Autowired
	private TbLevelInfoMapper levelInfoMapper;
    
	@Cacheable(cacheNames="levelInfos")
	public List<TbLevelInfo> selectJvjindouRuleByUserId(int userId, int jvjindou){
		TbLevelInfoExample example=new TbLevelInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		return levelInfoMapper.selectByExample(example);
	}
}
