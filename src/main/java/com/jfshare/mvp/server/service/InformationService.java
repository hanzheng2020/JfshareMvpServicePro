package com.jfshare.mvp.server.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.utils.SystemInformation;

@Service
public class InformationService {
	
	
	
	public void sendMsg(String content) {
		SystemInformation.send(content);
	}
	
	
	public void sendMsg(String userId,String title,String content,String orderId) {
		try {
			String mobileMd5 = DigestUtils.md5Hex(userId).toUpperCase();
			SystemInformation.buildPushObject_android_and_iosByAlias(mobileMd5,title,content,content,orderId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
