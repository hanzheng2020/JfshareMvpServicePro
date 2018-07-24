package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductMapper;
import com.jfshare.mvp.server.mapper.manual.ManualTbProductMapper;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;

@Repository
public class TbProductDao {
	@Autowired
	private ManualTbProductMapper manualTbProductMapper;
	
	@Autowired
	private TbProductMapper tbProductMapper;
	
	public List<TbProduct> selectByExample(TbProductExample example) {
		return tbProductMapper.selectByExample(example);
	}
	
	//根据商品名称  商品id搜索商品
	public List<TbProductSurvey> productSurveyQuery(ProductSurveyQueryParam param){
		return manualTbProductMapper.productSurveyQuery(param);
	}
	
	//新增商品
	public int addProduct(TbProductWithBLOBs record) {
		return tbProductMapper.insert(record);
	}
	
	//删除商品
	public int deleteProduct(TbProductExample example) {
		return tbProductMapper.deleteByExample(example);
	}
	
	//更新商品
	public int updateProduct(TbProduct product) {
		return tbProductMapper.updateByPrimaryKey(product);
	}
}
