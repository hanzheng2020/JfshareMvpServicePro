/**
 * 
 */
package com.jfshare.mvp.server.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfshare.mvp.server.service.LevelInfoService;

/**
 * 同步积分赠送聚金豆
 * @author Dell
 *
 */
@Component
@RabbitListener(queues = "queue_score_jvjindou")
public class ReceiverPresentJvjindou {
	private Logger logger = LoggerFactory.getLogger(ReceiverPresentJvjindou.class);
	@Autowired
	private LevelInfoService levelInfoService;
	@RabbitHandler
	@Transactional
	public void process(String message) throws Exception {
		String jsonStr = message.replace("\\\"", "'");
		JSON json= JSONObject.parseObject(jsonStr);
		JSONObject b = ((JSONObject) json).getJSONObject("result");
		// 包含 userId 当前时间对照数据的修改事假  修改数据库时间
		Integer userId=99;
		try {
			levelInfoService.presentJvjindouByuserId(userId);
		} catch (Exception e) {
			logger.info("同步异常"+userId);
			e.printStackTrace();
			return;
		}
	}
}
