package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.constants.Constant;
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
    
	//@Cacheable(cacheNames="levelInfos")
	public List<TbLevelInfo> selectJvjindouRuleByUserId(int userId){
		TbLevelInfoExample example=new TbLevelInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<TbLevelInfo> levelInfos =levelInfoMapper.selectByExample(example);
		return levelInfos;
	}
	
	//根据用户id查询用户积分聚金豆信息
	public TbLevelInfo selectLevelInfoByUserId(int userId){
		TbLevelInfoExample example=new TbLevelInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<TbLevelInfo> levelInfos =levelInfoMapper.selectByExample(example);
		if(levelInfos.size()>0) {
			return levelInfos.get(0);
		}
		return null;
	}
	
	public int updateLevelInfo(TbLevelInfo record) {
		record.setUpdateTime(new Date());
		int growth_point = record.getGrowthPoint();
		if(growth_point>=2000) {
			record.setGrade(Constant.DIAMOND);
		}else if(growth_point>=1000) {
			record.setGrade(Constant.BLACK);
		}else if(growth_point>=200) {
			record.setGrade(Constant.PLATIMUM);
		}
		return levelInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	
	@Cacheable(cacheNames="levelInfos")
	public TbLevelInfo selectLevelInfoById(int id){
		return levelInfoMapper.selectByPrimaryKey(id);
	}
    //	@CacheEvict(删除)
	//@CachePut(cacheNames="levelInfos")
	//public int updateLevelInfoById(TbLevelInfo record){
	//	return levelInfoMapper.updateByPrimaryKeySelective(record);
	//}
	
	@CachePut(cacheNames="levelInfos")
	public int insertSelective(TbLevelInfo record){
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		return levelInfoMapper.insertSelective(record);
	}
}
