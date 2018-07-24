package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductItemMapper;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
@Repository
public class TbProductItemDao {
	@Autowired
	private TbProductItemMapper tbProductItemMapper;
	
	public List<TbProductItem> selectByExample(TbProductItemExample example) {
		return tbProductItemMapper.selectByExample(example);
	}
	
	public int updateByPrimaryKey(TbProductItem record) {
		record.setUpdateTime(new Date());
		return tbProductItemMapper.updateByPrimaryKey(record);
	}
	
	public int deleteByExample(TbProductItemExample example) {
		return tbProductItemMapper.deleteByExample(example);
	}

	public int insert(TbProductItem record) {
		record.setUpdateTime(new Date());
		record.setCreateTime(new Date());
    	return tbProductItemMapper.insert(record);
    }
}
