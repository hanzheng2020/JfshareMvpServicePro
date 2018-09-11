package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.dao.SystemInformationDao;
import com.jfshare.mvp.server.model.TbSystemInformation;
import com.jfshare.mvp.server.model.TbSystemInformationExample;
import com.jfshare.mvp.server.model.TbSystemInformationExample.Criteria;
import com.jfshare.mvp.server.utils.JedisClusterUtils;

@Service
public class SystemInformationService {

	private final static Logger logger = LoggerFactory.getLogger(SystemInformationService.class);
	
	
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
		criteria1.andIsDeleteNotEqualTo(2);//2代表已经删除
		criteria2.andIsDeleteNotEqualTo(2);//2代表已经删除
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
	
	
	public  PageInfo<Map<String, Object>> getSystemInformationsApp(Integer userId,int page,int pageSize){
		TbSystemInformationExample example =  new TbSystemInformationExample();
		Criteria criteria= example.createCriteria();
		criteria.andStatusEqualTo(2);//2//代表已经发布
		criteria.andIsDeleteNotEqualTo(2);//2代表已经删除
		example.setOrderByClause("release_time DESC");
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
		TbSystemInformation systemInformation=systemInformationDao.selectInformationInfo(id);
		if(systemInformation!=null) {
			systemInformation.setIsDelete(2);//2：代表删除
			return systemInformationDao.updateInformation(systemInformation);
		}
		return 0;
	}
	
	//系统消息总数查询
	public long countByExample() {
		TbSystemInformationExample example =  new TbSystemInformationExample();
		Criteria criteria= example.createCriteria();
		criteria.andStatusEqualTo(2);//2代表已经发布
		criteria.andIsDeleteNotEqualTo(2);//2代表已经删除
		long informationNuber = systemInformationDao.countByExample(example);
		logger.info("系统消息长度："+informationNuber);
		return informationNuber;
	}
	
	
	
	public TbSystemInformation getInformatinInfo(int id) {
	 return systemInformationDao.selectInformationInfo(id);
	}
	
}
