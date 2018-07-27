package com.jfshare.mvp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jfshare.mvp.server.config.RabbitMQConfig;

/**
 * @author fengxiang
 * @date 2018-07-19
 */
@SpringBootApplication
@MapperScan("com.jfshare.mvp.server.mapper")
public class JfshareMvpServer implements CommandLineRunner{
	
	@Autowired
	private RabbitMQConfig rabbitMQConfig;
	
    public static void main( String[] args ) {
        SpringApplication.run(JfshareMvpServer.class, args);
    }

	public void run(String... args) throws Exception {
		rabbitMQConfig.sendMsg("dead-exchange", "deadqueue", "test", 1000);
	}
}
