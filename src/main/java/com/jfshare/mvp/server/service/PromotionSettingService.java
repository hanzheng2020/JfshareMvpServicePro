package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.TbProductItemShowDao;
import com.jfshare.mvp.server.dao.TbProductPromotionDao;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductItemShowExample;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.model.TbProductPromotionExample;

/**
 * 推广微页面设置
 * @author fengxiang
 * @date 2018-07-23
 */
@Service
public class PromotionSettingService {
	
	private final static Logger logger = LoggerFactory.getLogger(PromotionSettingService.class);
	
	@Autowired
	private TbProductPromotionDao tbProductPromotionDao;
	
	@Autowired
	private TbProductItemShowDao tbProductItemShowDao;
	
	@Transactional
	public boolean updateProductPromotion(List<TbProductPromotion> tbProductPromotions) {
		try {
			TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
			tbProductPromotionDao.deleteByExample(tbProductPromotionExample);
			for (TbProductPromotion tbProductPromotion : tbProductPromotions) {
				tbProductPromotionDao.insert(tbProductPromotion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新商品推广设置失败！", e);
			return false;
		}
		return true;
	}
	

	@Cacheable(cacheNames = "productPromotions",cacheManager="cacheManager60")
	public List<TbProductPromotion> getProductPromotions(String appVersion) {
		TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
		if (StringUtils.isEmpty(appVersion)) {
			tbProductPromotionExample.createCriteria()
									 .andAppVersionIsNull();
		} else {
			tbProductPromotionExample.createCriteria()
			 						.andAppVersionEqualTo(appVersion);
		}
		List<TbProductPromotion> tbProductPromotions = null;
		try {
			tbProductPromotions = tbProductPromotionDao.selectByExample(tbProductPromotionExample);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推广商品配置失败", e);
		}
		return tbProductPromotions;
	}
	
	@Transactional
	public boolean updateProductItemShow(List<TbProductItemShow> tbProductItemShows) {
		try {
			TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
			tbProductItemShowDao.deleteByExample(tbProductItemShowExample);
			for (TbProductItemShow tbProductItemShow : tbProductItemShows) {
				tbProductItemShowDao.insert(tbProductItemShow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新类目商品展示设置失败！", e);
			return false;
		}
		return true;
	}
	
	@Cacheable(cacheNames = "productItemShows")
	public List<TbProductItemShow> getProductItemShows(String appVersion) {
		TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
		if (StringUtils.isEmpty(appVersion)) {
			tbProductItemShowExample.createCriteria()
									 .andAppVersionIsNull();
		} else {
			tbProductItemShowExample.createCriteria()
			 						.andAppVersionEqualTo(appVersion);
		}
		List<TbProductItemShow> tbProductItemShows = null;
		try {
			tbProductItemShows = tbProductItemShowDao.selectByExample(tbProductItemShowExample);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取类目商品展示设置失败！", e);
		}
		return tbProductItemShows;
	}
}
