package com.jfshare.mvp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jfshare.mvp.server.config.RabbitMQConfig;
import com.jfshare.mvp.server.rabbitmq.RedisLazyQueues;

/**
 * @author fengxiang
 * @date 2018-07-19
 */
@SpringBootApplication
@MapperScan("com.jfshare.mvp.server.mapper")
public class JfshareMvpServer implements CommandLineRunner{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMQConfig rabbitMQConfig;
	
	@Autowired
	private RedisLazyQueues redisLazyQueues;
	
    public static void main( String[] args ) {
        SpringApplication.run(JfshareMvpServer.class, args);
    }

	public void run(String... args) throws Exception {
		redisLazyQueues.afterPropertiesSet();
		System.out.println("send:"+"test"+System.currentTimeMillis());
		rabbitTemplate.convertAndSend("my-mq-exchange", "ROUTINGKEY_JINDOU_MSG", "test");
//		rabbitMQConfig.sendMsg("my-mq-exchange", "ROUTINGKEY_JINDOU_MSG", "test", 5000);

	}
}
