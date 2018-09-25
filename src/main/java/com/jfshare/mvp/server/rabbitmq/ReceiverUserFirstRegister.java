package com.jfshare.mvp.server.rabbitmq;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfshare.mvp.server.service.LevelInfoService;

/**
 *用户首次注册设置默认为黄金等级
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("queue_first_register"))
public class ReceiverUserFirstRegister {
	private Logger logger = LoggerFactory.getLogger(ReceiverUserFirstRegister.class);
	@Autowired
	private LevelInfoService levelInfoService;
	@RabbitHandler
	public void process(String message) throws Exception {
		String jsonStr = message.replace("\\\"", "'");
		//加非空判断 格式判断 如果消息有异常 则return 打印错误信息
		JSONObject fromObject = JSONObject.fromObject(jsonStr);
		logger.info(String.format("用户首次注册设置默认为黄金等级fromObject{}", fromObject));
		int userId=99;
		try {
			//levelInfoService.userFirstRegister(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return;
		}
	}

}
