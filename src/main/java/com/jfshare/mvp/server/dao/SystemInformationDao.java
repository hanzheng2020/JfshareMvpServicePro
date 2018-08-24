package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbSystemInformationMapper;
import com.jfshare.mvp.server.model.TbSystemInformation;
import com.jfshare.mvp.server.model.TbSystemInformationExample;

@Repository
public class SystemInformationDao {

	@Autowired
	private TbSystemInformationMapper systemInformationMapper ;
	//查list
	public List<TbSystemInformation> selectInformation(TbSystemInformationExample example){
		return systemInformationMapper.selectByExample(example);
	}
	//查详情
	public TbSystemInformation selectInformationInfo(int id) {
		return systemInformationMapper.selectByPrimaryKey(id);
	}
	//增
	public int  addInformation(TbSystemInformation systemInformation) {
		return systemInformationMapper.insertSelective(systemInformation);
	} 
	
	//改
	public int updateInformation(TbSystemInformation systemInformation) {
		return systemInformationMapper.updateByPrimaryKey(systemInformation);
	} 
	//删
	public int deleteInformation(Integer id) {
		return systemInformationMapper.deleteByPrimaryKey(id);
	}
	

	
}
