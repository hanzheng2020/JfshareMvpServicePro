package com.jfshare.mvp.server.finagle.server;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.finagle.thrift.pagination.Pagination;
import com.jfshare.finagle.thrift.product.Product;
import com.jfshare.finagle.thrift.product.ProductCardListResult;
import com.jfshare.finagle.thrift.product.ProductCardViewParam;
import com.jfshare.finagle.thrift.product.ProductResult;
import com.jfshare.finagle.thrift.product.ProductRetParam;
import com.jfshare.finagle.thrift.product.ProductServ;
import com.jfshare.finagle.thrift.product.ProductSurveyQueryParam;
import com.jfshare.finagle.thrift.product.ProductSurveyResult;
import com.jfshare.mvp.server.config.ConfigManager;
import com.twitter.util.Await;

@Service
public class ProductClient {
	private Logger logger = LoggerFactory.getLogger(ScoreClient.class);
	private ProductServ.ServiceIface service = null;
	@Autowired
	private ConfigManager configManager;

	@PostConstruct
	public void init() {
		// 获取zk地址
		String zkPath = configManager.getConfigValue("zk-boot", "zk_addr");
		logger.info("zk info " + zkPath);
		service = Utils4Brain.createThriftClient(zkPath, Constants4Brain.Jfshare_productServ,
				ProductServ.ServiceIface.class);
	}

	public ProductSurveyResult queryProduct() {
		try {
			ProductSurveyQueryParam param = new ProductSurveyQueryParam();
			param.setPagination(new Pagination(0, 0, 1, 2));
			param.setActiveState(0);
			param.setSellerId(115);
			ProductSurveyResult result = Await.result(service.productSurveyBackendQuery(param));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用service.queryProduct失败！", e);
		}
		return null;
	}

	public int getProductCardByState(String productId) {
		try {
			ProductCardViewParam param = new ProductCardViewParam();
			param.setProductId(productId);
			param.setState(1);
			ProductCardListResult result = Await.result(service.getProductCardByState(param));
			if (result.getResult().getCode() == 0) {
				return result.getCardList().size();
			}
		} catch (Exception e) {
			logger.error("查询聚分享商品库存失败：" + e);
		}
		return 0;
	}
	
	/**
	 * 获取商品的信息
	 * @param productId
	 * @return
	 */
	public Product getProduct(String productId){
		
		if ("".equals(productId) || productId == null) {
			return null;
		}
		
		ProductRetParam param = new ProductRetParam();
		param.setBaseTag(1);
		param.setSkuTag(1);
		param.setSkuTemplateTag(1);
		try {
			ProductResult result = Await.result(service.queryProduct(productId, param));
			if(result.getResult().getCode() ==0){
				return result.getProduct();
			}
		} catch (Exception e) {
			logger.error("查询商品的信息错误"+e);
		}
		return null;
	}
}
