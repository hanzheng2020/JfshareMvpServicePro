package com.jfshare.mvp.server.utils;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;*/

public class SystemInformation {
	/*
	private static Logger LOG = LoggerFactory
			.getLogger(SystemInformation.class);
	private static String JPUSH_PUSH_MASTER_SECRET="f5e5bc9a362e7d8549dfcf75";
	//private static String JPUSH_PUSH_APPKEY="ad0a39eae9f5b0e699bb40db";
	private static String JPUSH_PUSH_APPKEY="aeb39fd7b8e60ac1d6a20a2a";
	
	public static void send() {
	   JPushClient jpushClient = new JPushClient(JPUSH_PUSH_MASTER_SECRET, JPUSH_PUSH_APPKEY, 0, null, ClientConfig.getInstance());

	    // For push, all you need do is to build PushPayload object.
	    PushPayload payload = buildPushObject_all_all_alert();

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
	    public static PushPayload buildPushObject_all_all_alert() {
	        return PushPayload.alertAll("test");
	    }
	    
	    public static void main(String[] args) {
	    	send();
		}*/
}
