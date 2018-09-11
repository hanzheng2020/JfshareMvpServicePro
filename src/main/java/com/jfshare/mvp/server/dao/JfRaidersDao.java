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
		Date date = new Date();
		jfRaiders.setCreateTime(date);
		jfRaiders.setUpdateTime(date);
		jfRaiders.setStatus(1);
		return tbJfRaidersMapper.insert(jfRaiders);
	}
	
	public int delete(int id) {
		return tbJfRaidersMapper.deleteByPrimaryKey(id);
	}
	
	public int updateJfRaiders(TbJfRaiders jfRaiders) {
		jfRaiders.setUpdateTime(new Date());
		TbJfRaidersExample jfRaidersExample =new TbJfRaidersExample();
		jfRaidersExample.createCriteria().andIdEqualTo(jfRaiders.getId());
		return tbJfRaidersMapper.updateByExampleSelective(jfRaiders,jfRaidersExample);
	}
	
	public List<TbJfRaiders> selectJfRaiders(TbJfRaidersExample example){
		return  tbJfRaidersMapper.selectByExampleWithBLOBs(example);
	}
	
	public TbJfRaiders selectJfRaidersOne(Integer id) {
		return tbJfRaidersMapper.selectByPrimaryKey(id);
	}
}

