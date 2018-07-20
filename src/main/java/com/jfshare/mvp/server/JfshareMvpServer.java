package com.jfshare.mvp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengxiang
 * @date 2018-07-19
 */
@SpringBootApplication
@MapperScan("com.jfshare.mvp.server.mapper")
public class JfshareMvpServer implements CommandLineRunner{
    public static void main( String[] args ) {
        SpringApplication.run(JfshareMvpServer.class, args);
    }

	public void run(String... args) throws Exception {
		//这里可以进行初始化
	}
}
