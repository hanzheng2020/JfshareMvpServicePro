package com.jfshare.mvp.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbAppVerifySettingMapper;
import com.jfshare.mvp.server.model.TbAppVerifySetting;
import com.jfshare.mvp.server.model.TbAppVerifySettingExample;

/**
 * @author fengxiang
 * @date 2018-08-20
 */
@Repository
public class TbAppVerifySettingDao {
	
	@Autowired
	private TbAppVerifySettingMapper tbAppVerifySettingMapper;
	
	public List<TbAppVerifySetting> selectByExample(TbAppVerifySettingExample example) {
		return tbAppVerifySettingMapper.selectByExample(example);
	}
	
	public int updateByPrimaryKey(TbAppVerifySetting record) {
		record.setUpdateTime(new Date());
		return tbAppVerifySettingMapper.updateByPrimaryKey(record);
	}
	
	public int deleteByExample(TbAppVerifySettingExample example) {
		return tbAppVerifySettingMapper.deleteByExample(example);
	}

	public int insert(TbAppVerifySetting record) {
		record.setUpdateTime(new Date());
		record.setCreateTime(new Date());
    	return tbAppVerifySettingMapper.insert(record);
    }
}
