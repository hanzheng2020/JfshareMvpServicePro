package com.jfshare.finagle;

import com.alibaba.fastjson.JSONObject;
import com.jfshare.finagle.thrift.product.ProductSurvey;
import com.jfshare.finagle.thrift.product.ProductSurveyResult;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.finagle.thrift.score.ScoreResult;
import com.jfshare.mvp.es.test.TestProductRepository;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.finagle.server.ProductClient;
import com.jfshare.mvp.server.finagle.server.ScoreClient;
import com.jfshare.mvp.server.finagle.server.StockClient;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    
    @Autowired
    OrderClient orderClient;
    
	@Autowired
	private StockClient stockClient;


	@Test
	public void testGson(){




	}



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

    @Test
    public void testQueryStock() {
    	StringResult result = orderClient.sellerQueryDetail(115,"60396265");
    	String value = result.getValue();
    	JSONObject parseObject = JSONObject.parseObject(value);
    	System.out.println(parseObject.getString("productId"));
    }

    @Test
    public void testqueryStock() throws Exception {
    	System.out.println(stockClient.batchQueryStock("ze180910152429000219"));
    }
    
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

				Product product = new Product();
				// 获取聚分享实际库存
				int count = productClient.getProductCardByState(productId);
				product.setProductStock(count);
				//状态都设置成待上架的状态
				product.setActiveState(Constant.PRODUCT_SOLT_OUT);
						
				if (productOne == null) {
					product.setProductId(productId);
					product.setProductName(productSurvey.getProductName());
					product.setItemNo(productSurvey.getSubjectId());
					product.setSellerId(productSurvey.getSellerId());
					product.setCurPrice(productSurvey.getMinCurPrice());
					product.setOrgPrice(productSurvey.getMinOrgPrice());
					product.setPresentexp(0);// 赠送聚金豆默认为0 后台配置
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
					product.setImgKey(productOne.getImgKey());
					productService.updateProduct(product);
				}
			}
		}
    }
}
