package com.jfshare.mvp.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfshare.mvp.server.mapper.TbProductMapper;
import com.jfshare.mvp.server.mapper.TbProductUrlMapper;
import com.jfshare.mvp.server.mapper.manual.ManualTbProductMapper;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductUrl;
import com.jfshare.mvp.server.model.TbProductUrlExample;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;

@Repository
public class TbProductDao {
	@Autowired
	private ManualTbProductMapper manualTbProductMapper;
	
	@Autowired
	private TbProductMapper tbProductMapper;
	
	@Autowired
	private TbProductUrlMapper tbProductUrlMapper;
	
	public List<TbProduct> selectByExample(TbProductExample example) {
		return tbProductMapper.selectByExample(example);
	}
	
	//根据商品名称  商品id搜索商品
	public List<TbProductSurvey> productSurveyQuery(ProductSurveyQueryParam param){
		return manualTbProductMapper.productSurveyQuery(param);
	}
	
	public int addProduct(TbProductWithBLOBs record) {
		return tbProductMapper.insert(record);
	}
	
	public int updateProduct(TbProductWithBLOBs record) {
		TbProductExample example = new TbProductExample();
		example.createCriteria().andProductIdEqualTo(record.getProductId());
		return tbProductMapper.updateByExampleSelective(record,example);
	}
	
	public int deleteProduct(String productId) {
		TbProductExample tbProductExample = new TbProductExample();
		tbProductExample.createCriteria().andProductIdEqualTo(productId);
		return tbProductMapper.deleteByExample(tbProductExample);
	}
	
	public int addProductUrl(TbProductUrl tbProductUrl) {
		return tbProductUrlMapper.insert(tbProductUrl);
	}
	
	public int updateProductUrl(TbProductUrl productUrl) {
		return tbProductUrlMapper.updateByPrimaryKey(productUrl);
	}
	
	public List<TbProductUrl> querytProductUrl(String productId) {
		TbProductUrlExample example = new TbProductUrlExample();
		example.createCriteria().andProductIdEqualTo(productId);
		List<TbProductUrl> selectByExample = tbProductUrlMapper.selectByExample(example);
		return selectByExample;
	}
}
