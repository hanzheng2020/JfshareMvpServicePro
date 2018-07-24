package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.manual.ManualTbProductMapper;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProductSurvey;

@Repository
public class TbProductDao {
	@Autowired
	private ManualTbProductMapper manualTbProductMapper;
	
	//根据商品名称  商品id搜索商品
	public List<TbProductSurvey> productSurveyQuery(ProductSurveyQueryParam param){
		return manualTbProductMapper.productSurveyQuery(param);
	}
}
