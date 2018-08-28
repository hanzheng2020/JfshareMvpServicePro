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
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.dao.TbProductItemShowDao;
import com.jfshare.mvp.server.dao.TbProductPromotionDao;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductItemShowExample;
import com.jfshare.mvp.server.model.TbProductItemShowExample.Criteria;
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
	
	@Autowired
	private TbProductItemDao tbProductItemDao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public boolean savePromotionSetting(List<Map> productPromotions, 
										List<Map> productItemShows) {
		try {
			TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
			tbProductPromotionExample.createCriteria().andPublishIndEqualTo(true);
			tbProductPromotionDao.deleteByExample(tbProductPromotionExample);
			tbProductPromotionExample.clear();
			for (int i = 0; i < productPromotions.size(); i ++) {
				Map<String, Object> productPromotion = productPromotions.get(i);
				TbProductPromotion tbProductPromotion = new TbProductPromotion();
				tbProductPromotion.setPublishInd(true);
				tbProductPromotion.setPromotionNo(i);
				tbProductPromotion.setPromotionPicUrl(productPromotion.get("promotionPicUrl").toString());
				tbProductPromotion.setPromotionUrl(productPromotion.get("promotionUrl").toString());
				List<Map> productDetails = (List<Map>) productPromotion.get("productDetails");
				for (int index = 0; index < productDetails.size(); index ++) {
					String productId = productDetails.get(index).get("productId").toString();
					String productPicUrl = productDetails.get(index).get("productPicUrl").toString();
					switch (index) {
					case 0:
						tbProductPromotion.setProductOneId(productId);
						tbProductPromotion.setProductOnePicUrl(productPicUrl);
						break;
					case 1:
						tbProductPromotion.setProductTwoId(productId);
						tbProductPromotion.setProductTwoPicUrl(productPicUrl);
						break;
					case 2:
						tbProductPromotion.setProductThreeId(productId);
						tbProductPromotion.setProductThreePicUrl(productPicUrl);
						break;
					case 3:
						tbProductPromotion.setProductFourId(productId);
						tbProductPromotion.setProductFourPicUrl(productPicUrl);
						break;
					case 4:
						tbProductPromotion.setProductFiveId(productId);
						tbProductPromotion.setProductFivePicUrl(productPicUrl);
						break;
					case 5:
						tbProductPromotion.setProductSixId(productId);
						tbProductPromotion.setProductSixPicUrl(productPicUrl);
						break;
					default:
						break;
					}
				}
				tbProductPromotionDao.insert(tbProductPromotion);
			}
			
			TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
			tbProductItemShowExample.createCriteria().andPublishIndEqualTo(true);
			tbProductItemShowDao.deleteByExample(tbProductItemShowExample);
			tbProductItemShowExample.clear();
			for (int i = 0; i < productItemShows.size(); i ++) {
				Map<String, Object> productPromotion = productItemShows.get(i);
				TbProductItemShow tbProductItemShow = new TbProductItemShow();
				tbProductItemShow.setItemShowNo(i);
				String itemNo = productPromotion.get("itemNo").toString();
				tbProductItemShow.setItemNo(itemNo);
				TbProductItemExample tbProductItemExample = new TbProductItemExample();
				tbProductItemExample.createCriteria().andItemNoEqualTo(itemNo);
				List<TbProductItem> tbProductItems = tbProductItemDao.selectByExample(tbProductItemExample);
				String itemName = "";
				if (!CollectionUtils.isEmpty(tbProductItems)) {
					itemName = tbProductItems.get(0).getItemName();
				}
				tbProductItemShow.setItemName(itemName);
				tbProductItemShow.setProducts(productPromotion.get("products").toString());
				tbProductItemShow.setPublishInd(true);
				tbProductItemShowDao.insert(tbProductItemShow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存推广微页面设置失败！", e);
			return false;
		}
		return true;
	}
	
	public Map<String, List<Map<String, Object>>> getPromotionSetting() {
		Map<String, List<Map<String, Object>>> result = new HashMap<>();
		List<Map<String, Object>> productPromotions = getProductPromotionDetails(true);
		result.put("productPromotions", productPromotions);
		List<TbProductItemShow> tbProductItemShows = getProductItemShows(true);
		List<Map<String, Object>> productItemShows = new ArrayList<>();
		for (TbProductItemShow tbProductItemShow : tbProductItemShows) {
			Map<String, Object> productItemShow = ConvertBeanToMapUtils.convertBeanToMap(tbProductItemShow, "products");
			productItemShow.put("products", getProductShowDetail(tbProductItemShow.getItemShowNo(), true));
			productItemShows.add(productItemShow);
		}
		result.put("productItemShows", productItemShows);
		return result;
	}
	
	@Transactional
	public boolean publishPromotionSetting(Boolean publishInd) {
		try {
			if (publishInd) {
				TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
				tbProductPromotionExample.createCriteria().andPublishIndEqualTo(true);
				tbProductPromotionDao.deleteByExample(tbProductPromotionExample);
				tbProductPromotionExample.clear();
				TbProductPromotion tbProductPromotion = new TbProductPromotion();
				tbProductPromotion.setPublishInd(true);
				tbProductPromotionDao.updateByExampleSelective(tbProductPromotion, tbProductPromotionExample);
				
				
				TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
				tbProductItemShowExample.createCriteria().andPublishIndEqualTo(true);
				tbProductItemShowDao.deleteByExample(tbProductItemShowExample);
				tbProductItemShowExample.clear();
				TbProductItemShow tbProductItemShow = new TbProductItemShow();
				tbProductItemShow.setPublishInd(true);
				tbProductItemShowDao.updateByExampleSelective(tbProductItemShow, tbProductItemShowExample);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发布推广微页面设置失败！", e);
			return false;
		}
		return true;
	}

	
	public List<Map<String, Object>> getProductPromotionDetails(Boolean publishInd) {
		TbProductPromotionExample tbProductPromotionExample = new TbProductPromotionExample();
		if (publishInd != null) {
			tbProductPromotionExample.createCriteria().andPublishIndEqualTo(publishInd);
		}
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			List<TbProductPromotion> tbProductPromotions = tbProductPromotionDao.selectByExample(tbProductPromotionExample);
			for (TbProductPromotion tbProductPromotion : tbProductPromotions) {
				List<Map<String, Object>> productList = new ArrayList<>();
				Map<String, Object> map = new HashMap<>();
				map.put("promotionNo", tbProductPromotion.getPromotionNo());
				map.put("promotionPicUrl", tbProductPromotion.getPromotionPicUrl());
				map.put("promotionUrl", tbProductPromotion.getPromotionUrl());
				
				Map<String, Object> dtlMap1 = new HashMap<>();
				TbProduct product1 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product1 != null) {
					dtlMap1.put("curPrice", product1.getCurPrice());
					dtlMap1.put("productId", tbProductPromotion.getProductOneId());
					dtlMap1.put("productName", product1.getProductName());
					dtlMap1.put("productPicUrl", tbProductPromotion.getProductOnePicUrl());
					productList.add(dtlMap1);
				}
				
				Map<String, Object> dtlMap2 = new HashMap<>();
				TbProduct product2 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product2 != null) {
					dtlMap2.put("curPrice", product2.getCurPrice());
					dtlMap2.put("productId", tbProductPromotion.getProductTwoId());
					dtlMap2.put("productName", product2.getProductName());
					dtlMap2.put("productPicUrl", tbProductPromotion.getProductTwoPicUrl());
					productList.add(dtlMap2);
				}
				
				Map<String, Object> dtlMap3 = new HashMap<>();
				TbProduct product3 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product3 != null) {
					dtlMap3.put("curPrice", product3.getCurPrice());
					dtlMap3.put("productId", tbProductPromotion.getProductThreeId());
					dtlMap3.put("productName", product3.getProductName());
					dtlMap3.put("productPicUrl", tbProductPromotion.getProductThreePicUrl());
					productList.add(dtlMap3);
				}
				
				Map<String, Object> dtlMap4 = new HashMap<>();
				TbProduct product4 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product4 != null) {
					dtlMap4.put("curPrice", product4.getCurPrice());
					dtlMap4.put("productId", tbProductPromotion.getProductFourId());
					dtlMap4.put("productName", product4.getProductName());
					dtlMap4.put("productPicUrl", tbProductPromotion.getProductFourPicUrl());
					productList.add(dtlMap4);
				}
				Map<String, Object> dtlMap5 = new HashMap<>();
				TbProduct product5 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product5 != null) {
					dtlMap5.put("curPrice", product5.getCurPrice());
					dtlMap5.put("productId", tbProductPromotion.getProductFiveId());
					dtlMap5.put("productName", product5.getProductName());
					dtlMap5.put("productPicUrl", tbProductPromotion.getProductFivePicUrl());
					productList.add(dtlMap5);
				}
				Map<String, Object> dtlMap6 = new HashMap<>();
				TbProduct product6 = this.getProduct(tbProductPromotion.getProductOneId());
				if (product6 != null) {
					dtlMap6.put("curPrice", product6.getCurPrice());
					dtlMap6.put("productId", tbProductPromotion.getProductSixId());
					dtlMap6.put("productName", product6.getProductName());
					dtlMap6.put("productPicUrl", tbProductPromotion.getProductSixPicUrl());
					productList.add(dtlMap6);
				}
				
				map.put("productDetails", productList);
				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取推广商品配置失败", e);
			return result;
		}
		return result;
	}
	
	private TbProduct getProduct(String productId) {
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria()
						.andProductIdEqualTo(productId)
						.andActiveStateEqualTo(200);
		List<TbProduct> tbProducts = tbProductDao.selectByExample(tbProductExample);
		if (!CollectionUtils.isEmpty(tbProducts)) {
			return tbProducts.get(0);
		}
		return null;
	}
	
	public List<TbProductItemShow> getProductItemShows(Boolean publishInd) {
		TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
		if (publishInd != null) {
			tbProductItemShowExample.createCriteria().andPublishIndEqualTo(publishInd);
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
	
	public List<Map<String, Object>> getProductShowDetail(Integer itemShowNo, Boolean publishInd) {
		TbProductItemShowExample tbProductItemShowExample = new TbProductItemShowExample();
		Criteria criteria= tbProductItemShowExample.createCriteria()
								.andItemShowNoEqualTo(itemShowNo);
		if (publishInd != null) {
			criteria.andPublishIndEqualTo(publishInd);
		}
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
						tbProductExample.createCriteria()
										.andProductIdEqualTo(productId)
										.andActiveStateEqualTo(200);
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
