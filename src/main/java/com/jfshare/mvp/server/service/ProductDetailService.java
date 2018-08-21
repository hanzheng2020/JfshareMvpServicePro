package com.jfshare.mvp.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.ProductDetailDao;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailExample;

@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailDao ProductDetailDao;
	
	
	public List<TbProductDetail> selectByExample(String  productId){
		TbProductDetailExample example =  new TbProductDetailExample();
		example.createCriteria().andDetailKeyEqualTo(productId);
		return ProductDetailDao.selectByExample(example);
	}
}
