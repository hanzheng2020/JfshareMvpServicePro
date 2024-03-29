package com.jfshare.mvp.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.dao.JfRaidersDao;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbJfRaidersExample;
import com.jfshare.mvp.server.model.TbJfRaidersExample.Criteria;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

@Service
public class JfRaidersService {

	@Autowired
	private JfRaidersDao jfRaidersDao;
	
	
	public int addjfRaiders(TbJfRaiders jfRaiders) {
		return jfRaidersDao.addRaider(jfRaiders);
	}
	
	public PageInfo<Object> queryJfRaiders(TbJfRaiders jfRaiders,int page,int pageSize){
		TbJfRaidersExample jfRaidersExample = new TbJfRaidersExample();
		Criteria criteria  = jfRaidersExample.createCriteria();
		if(jfRaiders.getTitle()!=null && !"".equals(jfRaiders.getTitle())) {
			criteria.andTitleLike(jfRaiders.getTitle());
		}
		if(jfRaiders.getId()!=null && !"".equals(jfRaiders.getId())) {
			criteria.andIdEqualTo(jfRaiders.getId());
		}
		if(jfRaiders.getStatus()!=null&&!"".equals(jfRaiders.getStatus())) {
			criteria.andStatusEqualTo(jfRaiders.getStatus());
		}
		
		jfRaidersExample.setOrderByClause("create_time DESC");
		PageHelper.startPage(page, pageSize);
		List<TbJfRaiders> JfRaiders =  jfRaidersDao.selectJfRaiders(jfRaidersExample);
		PageInfo<Object> pageInfo =new PageInfo(JfRaiders);
		Map<String, Object> map;
		 byte[] content;
		 List<Object> JfRaiderss  = new ArrayList<Object>();
		for(TbJfRaiders jfRaider:JfRaiders) {
			map  = ConvertBeanToMapUtils.convertBeanToMap(jfRaider, "");
			content=jfRaider.getContent();
			map.put("content", new String(content));
			map.put("id",jfRaider.getId());
			JfRaiderss.add(map);
		}
		pageInfo.setList(JfRaiderss);
		return pageInfo;
	}
	
	public PageInfo<Object> queryJfRaidersApp(int page,int pageSize){
		TbJfRaidersExample jfRaidersExample = new TbJfRaidersExample();
		Criteria criteria  = jfRaidersExample.createCriteria();
		criteria.andStatusEqualTo(2);//移动端默认查询以及发布的数据
		jfRaidersExample.setOrderByClause("release_time DESC");
		PageHelper.startPage(page, pageSize);
		List<TbJfRaiders> JfRaiders =  jfRaidersDao.selectJfRaiders(jfRaidersExample);
		PageInfo<Object> pageInfo =new PageInfo(JfRaiders);
		Map<String, Object> map;
		 byte[] content;
		 List<Object> JfRaiderss  = new ArrayList<Object>();
		for(TbJfRaiders jfRaider:JfRaiders) {
			map  = ConvertBeanToMapUtils.convertBeanToMap(jfRaider, "");
			content=jfRaider.getContent();
			map.put("content", new String(content));
			map.put("id",jfRaider.getId());
			JfRaiderss.add(map);
		}
		pageInfo.setList(JfRaiderss);
		return pageInfo;
	}
	
	
	
	public int updateJfRaiders(TbJfRaiders jfRaiders) {
		return jfRaidersDao.updateJfRaiders(jfRaiders);
	}
	
	
	public int deletejfRaiders(int id) {
		return jfRaidersDao.delete(id);
	}
	
	public TbJfRaiders queryJfRaidersOne(Integer id) {
		 return jfRaidersDao.selectJfRaidersOne(id);
	}
}