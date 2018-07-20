package com.jfshare.mvp.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Configuration
public class RabbitMQConfig {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Bean
	public Queue queue() {
		return new Queue("queue");
	}
	
	
}
