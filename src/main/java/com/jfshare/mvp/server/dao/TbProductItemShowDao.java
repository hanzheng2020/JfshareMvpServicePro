package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductItemShowMapper;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductItemShowExample;

/**
 * @author fengxiang
 * @date 2018-07-23
 */
@Repository
public class TbProductItemShowDao {
	@Autowired
	private TbProductItemShowMapper tbProductItemShowMapper;
	
	public List<TbProductItemShow> selectByExample(TbProductItemShowExample example) {
		return tbProductItemShowMapper.selectByExample(example);
	}
	
	public int updateByPrimaryKey(TbProductItemShow record) {
		record.setUpdateTime(new Date());
		return tbProductItemShowMapper.updateByPrimaryKey(record);
	}
	
	public int updateByExampleSelective(TbProductItemShow record, TbProductItemShowExample example) {
		record.setUpdateTime(new Date());
		return tbProductItemShowMapper.updateByExampleSelective(record, example);
	}
	
	public int deleteByExample(TbProductItemShowExample example) {
		return tbProductItemShowMapper.deleteByExample(example);
	}

	public int insert(TbProductItemShow record) {
		record.setUpdateTime(new Date());
		record.setCreateTime(new Date());
    	return tbProductItemShowMapper.insert(record);
    }
}
