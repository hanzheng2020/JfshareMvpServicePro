package com.jfshare.mvp.server.rabbitmq;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.utils.SystemInformation;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 同步积分赠送聚金豆
 * @author Dell
 *
 */
@Component
@RabbitListener(queues = "orderMvp")
public class ReceiverPresentJvjindou {
	private Logger logger = LoggerFactory.getLogger(ReceiverPresentJvjindou.class);
	@Autowired
	private LevelInfoService levelInfoService;
	@RabbitHandler
	public void process(String message) throws Exception {
		StringResult result;
		if(message!=null && !"".equals(message)) {
			logger.info("下单:"+message);
			JSONObject obj = JSONObject.fromObject(message);
			if(obj.get("payScore")==null) {
				int userid=Integer.parseInt(obj.get("userid").toString());
				int integral = Integer.parseInt(obj.get("integral").toString());
				String orderId=obj.get("orderId").toString();
				int amont = Integer.parseInt(obj.get("amont").toString());
				if(amont<=0) {
					amont=10;
					integral=10;
				}
				if(amont>0&&integral>0) {
					result=levelInfoService.addlevelInfo(userid, integral, orderId, amont);
					logger.info("返回接口:"+message);
					if(result.getResult().getCode()==0) {
						String mobileMd5 = DigestUtils.md5Hex(userid+"").toUpperCase();
						logger.info("用户:"+mobileMd5);
						SystemInformation.send(mobileMd5, "商品购买成功，点击查看订单券码详情>>");
					}
				}
			}else {
				logger.info("取消订单"+message);
			}
		}
	}
}
