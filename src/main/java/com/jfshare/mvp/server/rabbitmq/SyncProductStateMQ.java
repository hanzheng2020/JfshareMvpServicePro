package com.jfshare.mvp.server.rabbitmq;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.service.ProductService;

/**
 * @author fengxiang
 * @date 2018-10-09
 */
@Component
@RabbitListener(queues = "Product_State_Mvp")
public class SyncProductStateMQ {
	
	@Autowired
	private ProductService productService;
	@RabbitHandler
	public void process(String msg) {
		Map<String, Object> msgMap = JSON.parseObject(msg);
		try {
			int activeState = Integer.valueOf(msgMap.get("activeState").toString());
			if (activeState != Constant.PRODUCT_SOLT_OUT && activeState != Constant.PRODUCT_DEL) {
				return;
			}
			productService.changeProductState(msgMap.get("productId").toString(), activeState);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
