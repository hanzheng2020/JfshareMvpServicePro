package com.jfshare.mvp.server.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.device.TagListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.report.ReceivedsResult;

public class SystemInformation {
	
	private static Logger LOG = LoggerFactory
			.getLogger(SystemInformation.class);
	private static String JPUSH_PUSH_MASTER_SECRET="f5e5bc9a362e7d8549dfcf75";
	//private static String JPUSH_PUSH_APPKEY="ad0a39eae9f5b0e699bb40db";
	private static String JPUSH_PUSH_APPKEY="aeb39fd7b8e60ac1d6a20a2a";
	
	
	private static  JPushClient jpushClient = new JPushClient(JPUSH_PUSH_MASTER_SECRET, JPUSH_PUSH_APPKEY);
	

	//发送所有
	public static void send(String msg) {
	   //JPushClient jpushClient = new JPushClient(JPUSH_PUSH_MASTER_SECRET, JPUSH_PUSH_APPKEY, 0, null, ClientConfig.getInstance());

	    // For push, all you need do is to build PushPayload object.
	    PushPayload payload =PushPayload.alertAll(msg);

	    try {
	    	PushResult result = jpushClient.sendPush(payload);
	        LOG.info("Got result - " + result);
	    } catch (APIConnectionException e1) {
	        // Connection error, should retry later
	        LOG.error("Connection error, should retry later", e1);

	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	        LOG.error("Should review the error, and fix the request", e);
	        LOG.info("HTTP Status: " + e.getStatus());
	        LOG.info("Error Code: " + e.getErrorCode());
	        LOG.info("Error Message: " + e.getErrorMessage());
	    }
	}
	
	//给指定目标发送消息
	//alias 目标别名
	//msg  推送的消息
	public static void  sendOnes(String alias,String msg) {
		 PushPayload payload = PushPayload.newBuilder()
		                .setPlatform(Platform.all())
		                .setAudience(Audience.alias(alias))
		                .setNotification(Notification.alert(msg))
		                .build();
		 try {
			jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
