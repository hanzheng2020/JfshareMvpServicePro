package com.jfshare.mvp.server.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.mapper.TbProductDetailMapper;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;

@Service
public class ProductService {
	@Autowired
	private TbProductDao tbProductDao;
	@Autowired
	private TbProductDetailMapper tbProductDetailMapper;
	@Autowired
	private ProductItemService productItemService;
	
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
	
	public int addProduct(Product product) {
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
		TbProductDetail tbProductDetail = new TbProductDetail();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		tbProductDetail.setCreateTime(new Date());
		tbProductDetail.setUpdateTime(new Date());
		int count = tbProductDetailMapper.insert(tbProductDetail);
		int result = 0;
		if(count > 0) {
			result = tbProductDao.addProduct(tbProductWithBLOBs);
		}
		return result;
	}
	
	public int deleteProduct(String productId) {
		int count = 0;
		if(!StringUtils.isEmpty(productId)) {
			count = tbProductDao.deleteProduct(productId);
		}
		return count;
	}
	
	public int updateProduct(Product product) {
		TbProduct tbProduct = new TbProduct();
		tbProduct.setProductId(product.getProductId());
		tbProduct.setProductName(product.getProductName());
		tbProduct.setItemNo(product.getItemNo());
		tbProduct.setProductHeader(product.getProductHeader());
		tbProduct.setCurPrice(product.getCurPrice());
		tbProduct.setPresentexp(product.getPresentexp());
		tbProduct.setProductStock(product.getProductStock());
		tbProduct.setActiveState(product.getActiveState());
		tbProduct.setImgKey(product.getImgKey());
		//更新商品详情表
		TbProductDetail tbProductDetail = new TbProductDetail();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		tbProductDetail.setCreateTime(new Date());
		tbProductDetail.setUpdateTime(new Date());
		int count = tbProductDetailMapper.updateByPrimaryKey(tbProductDetail);
		int result = 0;
		if(count > 0) {
			result = tbProductDao.updateProduct(tbProduct);
		}
		return result;
	}
}
