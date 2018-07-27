package com.jfshare.mvp.server.rabbitmq;

import com.jfshare.mvp.server.service.LevelInfoService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
	public void process(String message) throws Exception {
		logger.info("process() called with: message = [" + message + "]");
		if(!message.contains("{")){
			return;
		}
		String jsonStr = message.replace("\\\"", "'");
		//加非空判断 格式判断 如果消息有异常 则return 打印错误信息
		//{"totalScoreOrMileage":"677","canuseScoreOrMileage":"677","userName":"**斌","accountMoney":"66.90","canUseFlow":"5794.85","memberLevel":"1","memberLevelValidtime":"20180930","ex1":"8.12","channelId":1,"thirdLoginName":"15815542122","userId":288108}
		JSONObject fromObject = JSONObject.fromObject(jsonStr);
		logger.info(String.format("同步积分赠送聚金豆json:{}", fromObject));
		if(StringUtils.isEmpty(fromObject)){
			logger.info("接收消息异常");
			return; 
		}else if(fromObject.containsKey("userId")==false){
			logger.info("接收消息异常");
			return; 
		}
		// 包含 userId 当前时间对照数据的修改事假  修改数据库时间
		Integer userId=Integer.parseInt(fromObject.get("userId").toString());
		try {
			levelInfoService.presentJvjindouByuserId(userId);
		} catch (Exception e) {
			logger.info("同步异常"+userId);
			e.printStackTrace();
			return;
		}
	}
}
