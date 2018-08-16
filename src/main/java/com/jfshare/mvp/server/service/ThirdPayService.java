package com.jfshare.mvp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.thirdinterface.WeChatInterface;

/**
 * @author fengxiang
 * @date 2018-08-16
 */
@Service
public class ThirdPayService {
	@Autowired
	private WeChatInterface weChatInterface;
	
	public String createWeChatPayOrder(String orderNo, String userIp) {
		
		return weChatInterface.createPrepayId("test", orderNo, 1, userIp);
	}
	
	public String createAliPayOrder() {
		return "";
	}
}
