package com.jfshare.mvp.server.model;


public class Captcha {
    private String id;
	private String value;
    private byte[] captchaBytes;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public byte[] getCaptchaBytes() {
		return captchaBytes;
	}
	public void setCaptchaBytes(byte[] captchaBytes) {
		this.captchaBytes = captchaBytes;
	}
}
