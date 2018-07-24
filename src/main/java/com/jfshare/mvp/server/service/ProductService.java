package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;

@Service
public class ProductService {
	@Autowired
	private TbProductDao tbProductDao;
	
	public List<TbProductSurvey> productSurveyQuery(String productId,String productName){
		ProductSurveyQueryParam param = new ProductSurveyQueryParam();
		if(StringUtils.isEmpty(productId)) {
			param.setProductId(productId);
		}
		if(StringUtils.isEmpty(productName)) {
			param.setProductName(productName);
		}
		
		return tbProductDao.productSurveyQuery(param);
	}
	
	public int addProduct(TbProduct product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		tbProductWithBLOBs.setActiveState(product.getActiveState());
		tbProductWithBLOBs.setImgKey(product.getImgKey());
		//商品使用说明和商品兑换说明使用product_detail表更新
		return tbProductDao.addProduct(tbProductWithBLOBs);
	}
	
	public int deleteProduct(String productId) {
		return 0;
	}
	
	public int updateProduct(String productId) {
		return 0;
	}
}
