package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.jfshare.mvp.server.mapper.TbJfRaidersMapper;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbJfRaidersExample;

@Repository
public class JfRaidersDao {
	
	@Autowired
	private TbJfRaidersMapper tbJfRaidersMapper;
	
	public int addRaider(TbJfRaiders jfRaiders) {
		jfRaiders.setCreateTime(new Date());
		jfRaiders.setStatus(1);
		return tbJfRaidersMapper.insert(jfRaiders);
	}
	
	public int delete(int id) {
		return tbJfRaidersMapper.deleteByPrimaryKey(id);
	}
	
	public int updateJfRaiders(TbJfRaiders jfRaiders) {
		return tbJfRaidersMapper.updateByPrimaryKey(jfRaiders);
	}
	
	public List<TbJfRaiders> selectJfRaiders(TbJfRaidersExample example){
		return  tbJfRaidersMapper.selectByExampleWithBLOBs(example);
	}
	
	public TbJfRaiders selectJfRaidersOne(Integer id) {
		return tbJfRaidersMapper.selectByPrimaryKey(id);
	}
}
