package com.jfshare.mvp.server.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Configuration
public class RabbitMQConfig {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private AmqpAdmin rabbitAdmin;
	/*@PostConstruct
	public void init () {
		rabbitAdmin.declareExchange(new ExchangeBuilder("dead-exchange", "direct").build());
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", "my-mq-exchange");
		arguments.put("x-dead-letter-routing-key", "ROUTINGKEY_JINDOU_MSG");
		rabbitAdmin.declareQueue(new Queue("deadqueue", true, false, false, arguments));
		rabbitAdmin.declareBinding(new Binding("deadqueue", DestinationType.QUEUE, "dead-exchange", "deadqueue", null));
	}*/
	
	public void sendMsg(String exchange, String routingKey, Object msg, final int delayTime) {
		System.out.println("send:"+msg+System.currentTimeMillis());
		rabbitAdmin.declareExchange(new ExchangeBuilder("dead-exchange", "direct").build());
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", exchange);
		arguments.put("x-dead-letter-routing-key", routingKey);
		rabbitAdmin.declareQueue(new Queue("dead-"+routingKey, true, false, false, arguments));
		rabbitAdmin.declareBinding(new Binding("dead-"+routingKey, DestinationType.QUEUE, "dead-exchange", "dead-"+routingKey, null));
		rabbitTemplate.convertAndSend("dead-exchange", "dead-"+routingKey, msg, message -> {
			message.getMessageProperties().setExpiration(delayTime+"");
			return message;
		});
	}
}
