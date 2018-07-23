package com.jfshare.mvp.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.mapper.TbProductPromotion;

/**
 * 商品推广
 * @author fengxiang
 * @date 2018-07-23
 */
@Service
public class ProductPromotionService {
	public boolean updateProductPromotion(List<TbProductPromotion> tbProductPromotions) {
		for (TbProductPromotion tbProductPromotion : tbProductPromotions) {
			
		}
		return false;
	}
}
