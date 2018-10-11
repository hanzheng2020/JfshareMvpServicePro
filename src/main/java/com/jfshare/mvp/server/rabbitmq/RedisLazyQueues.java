package com.jfshare.mvp.server.rabbitmq;

import java.util.Date;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.jfshare.mvp.server.service.InformationService;
import com.jfshare.mvp.server.utils.JedisClusterUtils;
import com.jfshare.mvp.server.utils.SystemInformation;
import com.jfshare.mvp.server.utils.WeChatMessageSendPlus;

import net.sf.json.JSONObject;


/**
 * 
 * @author Administrator
 *
 */
@Component
public class RedisLazyQueues implements InitializingBean{


	private static final Logger logger = LoggerFactory.getLogger(RedisLazyQueues.class);

	@Autowired
	private JedisClusterUtils redisTemplate;
	@Autowired
	private InformationService informationService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 Runnable runnable = new OrderPushMessage();
		 new Thread(runnable).start();
	}
	
	class OrderPushMessage implements Runnable{
		@Override
		public void run() {
			 logger.info("开始监听队列，订单延迟推送");
			  long minTime;
			  while(true){
				  minTime = new Date().getTime();
				  //取出5分钟范围内的消息
				  Set<TypedTuple<String>> set = redisTemplate.rangeByScoreWithScores("MVP:ORDER_APP_LIST" , minTime, minTime+1000*60*5);//订单有效时间为30分钟，获取当前时间到未来5分钟内到期未支付的订单

				 // logger.info("待支付订单数量>>>>>:"+set.size());
		          if(set!=null&&set.size()>0){
		        	 logger.info("待支付订单数量>>>>>:"+set.size());
		        	  for (TypedTuple<String> tuple : set) {
						logger.info("获取的值：{},剩余生存时间{},当前时间{}",tuple.getValue(),tuple.getScore().longValue(),minTime);
						String orderId =  tuple.getValue(); 
						WeChatMessageSendPlus bean = redisTemplate.getBean("MVP:ORDER:"+orderId, WeChatMessageSendPlus.class);
						if(bean!=null){
							System.out.println("获取到消息数据："+bean);
							//{userId=288108, formId=9999, formType=2, data={"productName":"商品名称","orderId":"订单id","jumpUrl":"订单详情页路径","orderStatus":50}, source=1, submitTime=1535438442483}							
							//1.处理发消息逻辑
							JSONObject obj = JSONObject.fromObject(bean);
							if(obj.get("payScore")==null) {
								String userId=obj.get("userId").toString();
								informationService.sendMsg(userId, "订单到期支付提醒", "您有一笔待支付订单将于5分钟后失效，请点击付款>>", orderId);
								//2.删除键值对缓存
								redisTemplate.delKey("MVP:ORDER:"+orderId);
								//处理完删除队列中的消息
								redisTemplate.zSetRemove("MVP:ORDER_APP_LIST", orderId);
							}

						}

					}
		         }
		          try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
	}	
	
}
