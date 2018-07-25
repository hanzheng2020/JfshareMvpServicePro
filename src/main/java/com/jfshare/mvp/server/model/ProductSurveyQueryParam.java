package com.jfshare.mvp.server.model;


public class ProductSurveyQueryParam {
	private String productId;
	private String productName;
	private Integer itemNo; //商品分类id
	private Integer activeState;
	private String sort;
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getActiveState() {
		return activeState;
	}
	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
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
