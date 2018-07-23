package com.jfshare.mvp.server.model;

import java.sql.Date;

public class TbProductSurvey {
	private String productId;
	private String productName;
	private String subjectName;
	private String curPrice;
	private String orgPrice;
	private Integer presentExp;
	private String imgKey;
	private String activeState;
	private Date createTime;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}
	public String getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(String orgPrice) {
		this.orgPrice = orgPrice;
	}
	public Integer getPresentExp() {
		return presentExp;
	}
	public void setPresentExp(Integer presentExp) {
		this.presentExp = presentExp;
	}
	public String getImgKey() {
		return imgKey;
	}
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}
	public String getActiveState() {
		return activeState;
	}
	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "TbProductSurvey [productId=" + productId + ", productName=" + productName + ", subjectName="
				+ subjectName + ", curPrice=" + curPrice + ", orgPrice=" + orgPrice + ", presentExp=" + presentExp
				+ ", imgKey=" + imgKey + ", activeState=" + activeState + ", createTime=" + createTime
				+ ", getProductId()=" + getProductId() + ", getProductName()=" + getProductName()
				+ ", getSubjectName()=" + getSubjectName() + ", getCurPrice()=" + getCurPrice() + ", getOrgPrice()="
				+ getOrgPrice() + ", getPresentExp()=" + getPresentExp() + ", getImgKey()=" + getImgKey()
				+ ", getActiveState()=" + getActiveState() + ", getCreateTime()=" + getCreateTime() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
