package com.jfshare.mvp.server.utils;

public class WeChatMessageSendPlus {

	private Integer userId;  
	private String formId;     //表单id
	private Integer formType;  //表单类型
	private String data;	   //必要数据，随表单类型变化
	private Integer source; //消息来源 1：mvp app 2:mvp小程序
	private long submitTime; //提交表单时间
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	public long getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public Integer getFormType() {
		return formType;
	}
	public void setFormType(Integer formType) {
		this.formType = formType;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "WeChatMessageSendPlus [userId=" + userId + ", formId=" + formId + ", formType=" + formType + ", data="
				+ data + ", source=" + source + ", submitTime=" + submitTime + "]";
	}

	
	
}
