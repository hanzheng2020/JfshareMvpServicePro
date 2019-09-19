package com.jfshare.mvp.server.rabbitmq;

import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.service.InformationService;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.ProductService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 同步积分赠送聚金豆
 * @author Dell
 *
 */
@Component
@RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange(value = "exchangeTest",durable = "true"),
		key = "routingkey_orderMvp",
		value = @Queue(value = "orderMvp",durable = "true")
))
public class ReceiverPresentJvjindou {
	private Logger logger = LoggerFactory.getLogger(ReceiverPresentJvjindou.class);
	@Autowired
	private LevelInfoService levelInfoService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private ProductService productService;
	
	@RabbitHandler
	public void process(String message) throws Exception {
		StringResult result;
		if(message!=null && !"".equals(message)) {
			
		logger.info("下单:"+message);
			//{"amont":1,"productId":"ze180911110631000381","orderId":"70491952","userid":551952}
			JSONObject obj = JSONObject.fromObject(message);
			if(obj.get("payScore")==null) {
				int userid=Integer.parseInt(obj.get("userid").toString());
				Object productId = obj.get("productId");
				String orderId=obj.get("orderId").toString();
				int amont = Integer.parseInt(obj.get("amont").toString());
				//if(amont>0) {
					if(productId==null) {
						logger.info("商品id为空！");
						return;
					}
					TbProduct product = productService.getProductOne(productId.toString());
					if(null == product){
						logger.error("未找到商品id为 {}",productId);
						return;
					}
					int integral=product.getPresentexp();
					result=levelInfoService.addlevelInfo(userid, integral, orderId, amont);
					logger.info("返回接口:"+message);
					if(result.getResult().getCode()==0) {
							informationService.sendMsg(userid+"", "支付成功提醒", "商品购买成功，点击查看订单券码详情>>", orderId);
					}
				//}
			}else {
				logger.info("取消订单"+message);
			}
		}
	}
}
