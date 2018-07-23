package com.jfshare.mvp.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.TbProductPromotionDao;
import com.jfshare.mvp.server.mapper.TbProductPromotion;
import com.jfshare.mvp.server.mapper.TbProductPromotionExample;

/**
 * 商品推广
 * @author fengxiang
 * @date 2018-07-23
 */
@Service
public class ProductPromotionService {
	
	private final static Logger logger = LoggerFactory.getLogger(ProductPromotionService.class);
	
	private TbProductPromotionDao tbProductPromotionDao;
	
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
			logger.error("更新推广商品配置失败", e);
			return false;
		}
		return true;
	}
	
	
	public List<TbProductPromotion> getProductPromotions() {
		TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
		List<TbProductPromotion> tbProductPromotions = null;
		try {
			tbProductPromotions = tbProductPromotionDao.selectByExample(tbProductPromotionExample);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推广商品配置失败", e);
		}
		return tbProductPromotions;
	}
}
