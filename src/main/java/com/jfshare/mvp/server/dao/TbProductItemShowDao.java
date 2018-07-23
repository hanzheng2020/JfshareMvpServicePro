package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.jfshare.mvp.server.mapper.TbProductItemShow;
import com.jfshare.mvp.server.mapper.TbProductItemShowExample;
import com.jfshare.mvp.server.mapper.TbProductItemShowMapper;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
public class TbProductItemShowDao {
	private TbProductItemShowMapper tbProductItemShowMapper;
	
	@Cacheable(cacheNames = "productItemShows")
	public List<TbProductItemShow> selectByExample(TbProductItemShowExample example) {
		return tbProductItemShowMapper.selectByExample(example);
	}
	
	@CachePut(cacheNames = "productItemShows")
	public int updateByPrimaryKey(TbProductItemShow record) {
		record.setUpdateTime(new Date());
		return tbProductItemShowMapper.updateByPrimaryKey(record);
	}
	
	@CacheEvict(cacheNames = "productItemShows")
	public int deleteByExample(TbProductItemShowExample example) {
		return tbProductItemShowMapper.deleteByExample(example);
	}

	public int insert(TbProductItemShow record) {
		record.setUpdateTime(new Date());
		record.setCreateTime(new Date());
    	return tbProductItemShowMapper.insert(record);
    }
}
