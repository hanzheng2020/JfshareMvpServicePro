package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.dao.SystemInformationDao;
import com.jfshare.mvp.server.model.TbSystemInformation;
import com.jfshare.mvp.server.model.TbSystemInformationExample;
import com.jfshare.mvp.server.model.TbSystemInformationExample.Criteria;

@Service
public class SystemInformationService {

	@Autowired
	private SystemInformationDao systemInformationDao;
	
	
	public int saveSystemInformation(TbSystemInformation systemInformation) {
		systemInformation.setCreateTime(new Date());
		return systemInformationDao.addInformation(systemInformation);
	}
	
	public  PageInfo<Map<String, Object>> getSystemInformations(String titleOrContent,int page,int pageSize){
		TbSystemInformationExample example =  new TbSystemInformationExample();
		Criteria criteria1= example.createCriteria();
		Criteria criteria2= example.createCriteria();
		
		if(titleOrContent!=null && !"".equals(titleOrContent)) {
			criteria1.andTitleLike("%"+titleOrContent+"%");
			criteria2.andContentLike("%"+titleOrContent+"%");
			example.or(criteria2);
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, pageSize);
		List<TbSystemInformation>  systemInformations=systemInformationDao.selectInformation(example);
		return new PageInfo(systemInformations);
		
	}
	
	public int updateSystemInformation(TbSystemInformation systemInformation) {
		if(systemInformation!=null) {
			systemInformation.setUpdateTime(new Date());
		}
		return systemInformationDao.updateInformation(systemInformation);
		
	}
	
	public int deleteSystemInformation(int id) {
		return systemInformationDao.deleteInformation(id);
	}
	
	public TbSystemInformation getInformatinInfo(int id) {
	 return systemInformationDao.selectInformationInfo(id);
	
	}
	
}
