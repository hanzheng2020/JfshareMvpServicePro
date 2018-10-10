package com.jfshare.mvp.server.utils;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class SystemInformation {
	
	private static Logger LOG = LoggerFactory
			.getLogger(SystemInformation.class);
	private static String JPUSH_PUSH_MASTER_SECRET="f5e5bc9a362e7d8549dfcf75";
	private static String JPUSH_PUSH_APPKEY="aeb39fd7b8e60ac1d6a20a2a";
	
	
	private static  JPushClient jpushClient = new JPushClient(JPUSH_PUSH_MASTER_SECRET, JPUSH_PUSH_APPKEY);
	

	//发送所有
	public static void send(String msg) {
	   //JPushClient jpushClient = new JPushClient(JPUSH_PUSH_MASTER_SECRET, JPUSH_PUSH_APPKEY, 0, null, ClientConfig.getInstance());

	    // For push, all you need do is to build PushPayload object.
	    //PushPayload payload =PushPayload.alertAll(msg);
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
	public static void  send(String alias,String msg) {
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
	
	
	public static void  buildPushObject_android_and_iosByAlias(String alias,String title,String alert,String content,String orderId) {
        Options option=Options.sendno();
        option.setApnsProduction(true);//推送环境；false：开发环境，true：生成环境
        try {
			jpushClient.sendPush(PushPayload.newBuilder()
			        .setPlatform(Platform.android_ios())
			        .setAudience(Audience.alias(alias))
			        .setOptions(option)
			        .setNotification(Notification.newBuilder()
			        		.setAlert(alert)
			        		.addPlatformNotification(AndroidNotification.newBuilder()
			        				.setTitle(title)
			        				.addExtra("orderId", orderId)
			        				//.addExtra("order_type", orderType)
			        				.build())
			        		.addPlatformNotification(IosNotification.newBuilder()
			        				.incrBadge(1)
			        				.addExtra("title", title)
			        				.addExtra("orderId", orderId)
			        				.setSound("sound.caf")
			        				.addExtra("content", content)
			        				//.addExtra("order_type", orderType)
			        				.build())
			        		.build())
			        .build());
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		//String mobileMd5 = DigestUtils.md5Hex("218").toUpperCase();
		String mobileMd5 = DigestUtils.md5Hex("551952").toUpperCase();
		System.out.println(mobileMd5);
		//SystemInformation.send("商品购买成功，点击查看订单券码详情>>");
		SystemInformation.send(mobileMd5,"218");
		//SystemInformation.buildPushObject_android_and_iosByAlias(mobileMd5,"支付成功提醒","商品购买成功，点击查看订单券码详情>>","商品购买成功，点击查看订单券码详情>>","123432");
	}
	 
}
