package com.jfshare.mvp.server.config;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Configuration
public class ActiveMQConfig {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");
	}
	
}
