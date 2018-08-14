package com.jfshare.mvp.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemShowDao;
import com.jfshare.mvp.server.dao.TbProductPromotionDao;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductItemShowExample;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.model.TbProductPromotionExample;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

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
	
	@Autowired
	private TbProductDao tbProductDao;
	
	@Transactional
	public boolean updateProductPromotion(TbProductPromotion[] tbProductPromotions, Boolean publishInd) {
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
	
	public List<Map<String, Object>> getProductPromotionDetails() {
		TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			List<TbProductPromotion> tbProductPromotions = tbProductPromotionDao.selectByExample(tbProductPromotionExample);
			for (TbProductPromotion tbProductPromotion : tbProductPromotions) {
				Map<String, Object> map = new HashMap<>();
				map = ConvertBeanToMapUtils.convertBeanToMap(tbProductPromotion);
				map.put("oneCurPrice", this.getCurPrice(tbProductPromotion.getProductOneId()));
				map.put("twoCurPrice", this.getCurPrice(tbProductPromotion.getProductTwoId()));
				map.put("threeCurPrice", this.getCurPrice(tbProductPromotion.getProductThreeId()));
				map.put("fourCurPrice", this.getCurPrice(tbProductPromotion.getProductFourId()));
				map.put("fiveCurPrice", this.getCurPrice(tbProductPromotion.getProductFiveId()));
				map.put("sixCurPrice", this.getCurPrice(tbProductPromotion.getProductSixId()));
				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推广商品配置失败", e);
			return result;
		}
		return result;
	}
	
	private String getCurPrice(String productId) {
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria().andProductIdEqualTo(productId);
		List<TbProduct> tbProducts = tbProductDao.selectByExample(tbProductExample);
		String curPrice = "";
		if (!CollectionUtils.isEmpty(tbProducts)) {
			curPrice = tbProducts.get(0).getCurPrice();
		}
		return curPrice;
	}
	
	@Transactional
	public boolean updateProductItemShow(TbProductItemShow[] tbProductItemShows, Boolean publishInd) {
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
	
	public List<TbProductItemShow> getProductItemShows() {
		TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
		List<TbProductItemShow> tbProductItemShows = null;
		try {
			tbProductItemShows = tbProductItemShowDao.selectByExample(tbProductItemShowExample);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取类目商品展示设置失败！", e);
		}
		return tbProductItemShows;
	}
	
	public List<Map<String, Object>> getProductShowDetail(Integer itemShowNo) {
		TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
		tbProductItemShowExample.createCriteria()
								.andItemShowNoEqualTo(itemShowNo);
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			List<TbProductItemShow> tbProductItemShows = tbProductItemShowDao.selectByExample(tbProductItemShowExample);
			if (!CollectionUtils.isEmpty(tbProductItemShows)) {
				TbProductItemShow tbProductItemShow = tbProductItemShows.get(0);
				String products = tbProductItemShow.getProducts();
				if (!StringUtils.isEmpty(products)) {
					String[] productIds = null;
					if (products.contains(",")) {
						productIds = products.split(",");
					} else {
						productIds = new String[] {products};
					}
					for (int i = 0; i < productIds.length; i ++) {
						String productId = productIds[i];
						TbProductExample tbProductExample = new TbProductExample();
						tbProductExample.createCriteria().andProductIdEqualTo(productId);
						List<TbProduct> tbProducts = tbProductDao.selectByExample(tbProductExample);
						Map<String, Object> map = new HashMap<>();
						if (!CollectionUtils.isEmpty(tbProducts)) {
							map.put("productId", tbProducts.get(0).getProductId());
							map.put("curPrice", tbProducts.get(0).getCurPrice());
							map.put("productName", tbProducts.get(0).getProductName());
							map.put("imgKey", tbProducts.get(0).getImgKey());
						}
						result.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取类目商品展示设置失败！", e);
		}
		return result;
	}
}
