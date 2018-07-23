package com.jfshare.mvp.server.model;

public class ProductSurveyQueryParam {
	private String productId;
	private String productName;
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
	public ProductSurveyQueryParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductSurveyQueryParam(String productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "ProductSurveyQueryParam [productId=" + productId + ", productName=" + productName + ", getProductId()="
				+ getProductId() + ", getProductName()=" + getProductName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
