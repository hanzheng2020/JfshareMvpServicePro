package com.jfshare.mvp.server.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jfshare.finagle.thrift.product.ProductSurvey;
import com.jfshare.finagle.thrift.product.ProductSurveyResult;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.finagle.server.ProductClient;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.ProductService;

/**
 * @author fengxiang
 * @date 2018-06-27
 */
@EnableScheduling
@Component
@Service
public class ProductScheduler {
	private final static Logger logger = LoggerFactory.getLogger(ProductScheduler.class);

	@Autowired
	private ProductClient productClient;

	@Autowired
	private ProductService productService;

	public void queryJfshareProduct() {
		// 查询聚分享的虚拟商品
		ProductSurveyResult jProduct = productClient.queryProduct();
		logger.info("jProduct: " + jProduct);
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
