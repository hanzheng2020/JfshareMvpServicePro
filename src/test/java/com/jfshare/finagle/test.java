package com.jfshare.finagle;

import com.jfshare.finagle.thrift.product.ProductSurvey;
import com.jfshare.finagle.thrift.product.ProductSurveyResult;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.finagle.thrift.score.ScoreResult;
import com.jfshare.mvp.es.test.TestProductRepository;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.finagle.server.ProductClient;
import com.jfshare.mvp.server.finagle.server.ScoreClient;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.ProductService;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chiwenheng on 2018/7/26.
 */
public class test extends TestProductRepository {


    @Autowired
    ScoreClient scoreClient;
    
    @Autowired
    ProductClient productClient;
    
    @Autowired
    ProductService productService;

    @Test
    public void testFinagle(){


        ScoreResult scoreByUserId = scoreClient.getScore(535668);

        System.out.println(scoreByUserId);


    }
    
    @Test
    public void testIncomeScore() {
    	StringResult incomeScore = scoreClient.incomeScore(535668, 1, 6, "123456");
    	System.out.println(incomeScore);
    }

    @Test
    public void testReduceScore() {
    	StringResult reduceScore = scoreClient.reduceScore(535668, 1, 6, "123456");
    	System.out.println(reduceScore);
    }
    
    @Test
    public void testQueryProduct() {
    	ProductSurveyResult queryProduct = productClient.queryProduct();
    	System.out.println(queryProduct);
    }

    /*@Test
    public void testQueryStock() {
    	System.out.println(scoreClient.pointIncomeOrExpenses(288108, "1"));
    }*/
    
    @Test
    public void testPoduct() {
		// 查询聚分享的虚拟商品
		ProductSurveyResult jProduct = productClient.queryProduct();
		if (jProduct != null && jProduct.getProductSurveyList().size() > 0) {
			List<ProductSurvey> jProductList = jProduct.getProductSurveyList();
			for (ProductSurvey productSurvey : jProductList) {
				// 同步聚分享虚拟商品到mvp商品
				String productId = productSurvey.getProductId();
				TbProduct productOne = productService.getProductOne(productId);
				// 获取聚分享实际库存
				int count = productClient.getProductCardByState(productId);
				Product product = new Product();
				
				// 获取商品的状态
				int activeState = productSurvey.getActiveState();
				// 只有商品状态为300是上架状态
				if (activeState == Constant.JPRODUCT_SOLT_OUT) {
					product.setActiveState(Constant.PRODUCT_STATE_ONSELL);
				} else {
					// 其他状态都设置成待上架的状态
					product.setActiveState(Constant.PRODUCT_SOLT_OUT);
				}
						
				if (productOne == null) {
					product.setProductId(productId);
					product.setProductName(productSurvey.getProductName());
					product.setItemNo(productSurvey.getSubjectId());
					product.setCurPrice(productSurvey.getMinCurPrice());
					product.setOrgPrice(productSurvey.getMinOrgPrice());
					product.setPresentexp(0);// 赠送聚金豆默认为0 后台配置
					product.setProductStock(count);
					product.setImgKey(productSurvey.getImgUrl());
					// 同步到mvp商品
					productService.addProduct(product);
				} else {
					product.setProductId(productOne.getProductId());
					product.setProductName(productOne.getProductName());
					product.setItemNo(productOne.getItemNo());
					product.setCurPrice(productOne.getCurPrice());
					product.setOrgPrice(productOne.getOrgPrice());
					product.setPresentexp(productOne.getPresentexp());
					product.setProductStock(count);
					product.setImgKey(productOne.getImgKey());
					productService.updateProduct(product);
				}
			}
		}
    }
}
