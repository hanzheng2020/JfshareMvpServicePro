package com.jfshare.mvp.server.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfshare.mvp.server.service.InformationService;

import cn.jiguang.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange(value = "exchangeTest",durable = "true"),
		key = "routingkey_core_maturity_mesasge",
		value = @Queue(value = "coreMaturity",durable = "true")
))
public class CoreMaturityMesasge {
	
	private Logger logger = LoggerFactory.getLogger(ReceiverPresentJvjindou.class);
	
	@Autowired
	private InformationService informationService;
	
	@RabbitHandler
	public void process(String message) throws Exception {
		
		logger.info("********************:"+message);
		if(StringUtils.isNotEmpty(message)) {
			JSONArray jsonArray=JSONArray.fromObject(message);
			Object object;
			JSONObject jsonObject;
			for(int i=0;i<jsonArray.size();i++) {
				object=jsonArray.get(i);
		        jsonObject=JSONObject.fromObject(object);
		        String days = jsonObject.get("days").toString();
		        String userid= jsonObject.get("userId").toString();
		        String channelName=jsonObject.get("channelName").toString();
		        logger.info("********************:"+userid+":"+days);
		        informationService.sendMsg(userid+"", "积分到期提醒", "你的"+channelName+"积分还有"+days+"天到期，点击查看详情>>", "0");
			}
			
		}
	}

}
