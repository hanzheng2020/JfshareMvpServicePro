package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProductSurvey;

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
}
