package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbLevelInfoMapper;
import com.jfshare.mvp.server.model.TbLevelInfo;
import com.jfshare.mvp.server.model.TbLevelInfoExample;
import com.jfshare.mvp.server.model.TbLevelInfoExample.Criteria;

/**
 *等级信息
 */
@Repository
public class LevelInfoDao {
	@Autowired
	private TbLevelInfoMapper levelInfoMapper;
    
	@Cacheable(cacheNames="levelInfos")
	public List<TbLevelInfo> selectJvjindouRuleByUserId(int userId){
		TbLevelInfoExample example=new TbLevelInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		return levelInfoMapper.selectByExample(example);
	}
	
	public int insertLevelInfo(TbLevelInfo record){
		return levelInfoMapper.insertSelective(record);
	}
	
	@Cacheable(cacheNames="levelInfos")
	public TbLevelInfo selectLevelInfoById(int id){
		return levelInfoMapper.selectByPrimaryKey(id);
	}
    //	@CacheEvict(删除)
	@CachePut(cacheNames="levelInfos")
	public int updateLevelInfoById(TbLevelInfo record){
		return levelInfoMapper.updateByPrimaryKeySelective(record);
	}
}
