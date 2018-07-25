package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbAppVerinfoMapper;
import com.jfshare.mvp.server.model.GetUpgradeParamStr;
import com.jfshare.mvp.server.model.TbAppVerinfo;
import com.jfshare.mvp.server.model.TbAppVerinfoExample;

@Repository
public class AppInfoDao {
	@Autowired
	private TbAppVerinfoMapper tbAppVerinfoMapper;
	
	//获取版本号对应的当前信息
	public List<TbAppVerinfo> getAppVerinfo(GetUpgradeParamStr param) {
		TbAppVerinfoExample example = new TbAppVerinfoExample();
		TbAppVerinfoExample.Criteria criteria = example.createCriteria();
		if(param.getAppType() > 0)
			criteria.andAppTypeEqualTo(param.getAppType());
			
		example.setOrderByClause("id desc");
		return tbAppVerinfoMapper.selectByExample(example);
	}
	
	//更新版本号
	public int updateAppVerinfo(TbAppVerinfo verinfo) {
		return tbAppVerinfoMapper.updateByPrimaryKey(verinfo);
	}
	
}
