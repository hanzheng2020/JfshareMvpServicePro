package com.jfshare.mvp.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author fengxiang
 * @date 2018-06-27
 */
@EnableScheduling
@Component
public class ProductScheduler {
	private final static Logger logger = LoggerFactory.getLogger(ProductScheduler.class);
	
}
