package com.jfshare.mvp.server.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author fengxiang
 * @date 2018-10-09
 */
@Component
@RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange("exchangeTest"),
		key = "routingkey_product_state",
		value = @Queue("Product_State_Mvp")
))
public class SyncProductStateMQ {
	private static Logger logger = LoggerFactory.getLogger(SyncProductStateMQ.class);
	@Autowired
	private ProductService productService;
	@RabbitHandler
	public void process(String msg) {
		try {
			logger.info("开始商品信息同步，{}", msg);
			Map<String, Object> msgMap = JSON.parseObject(msg);
			if (msgMap.containsKey("activeState")) {
				logger.info("同步商品状态...");
				int activeState = Integer.valueOf(msgMap.get("activeState").toString());
				if (activeState != Constant.PRODUCT_SOLT_OUT && activeState != Constant.PRODUCT_DEL) {
					return;
				}
				productService.changeProductState(msgMap.get("productId").toString(), activeState);
			}
			if (msgMap.containsKey("price")) {
				logger.info("同步商品价格...");
				String price = msgMap.get("price").toString();
				productService.changeProductPrice(msgMap.get("productId").toString(), price);
				
			}
			
		} catch (Exception e) {
			logger.error("商品信息同步失败，{}", e.getMessage());
			e.printStackTrace();
		}
	}
}
