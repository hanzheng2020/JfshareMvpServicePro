package com.jfshare.mvp.server.model;


public class ProductSurveyQueryParam {
	private String param;
	private Integer itemNo; //商品分类id
	private Integer activeState;
	private String sort;
	
	@Override
	public String toString() {
		return "ProductSurveyQueryParam [param=" + param + ", itemNo=" + itemNo + ", activeState=" + activeState
				+ ", sort=" + sort + "]";
	}
	public ProductSurveyQueryParam(String param, Integer itemNo, Integer activeState, String sort) {
		super();
		this.param = param;
		this.itemNo = itemNo;
		this.activeState = activeState;
		this.sort = sort;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
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
	public ProductSurveyQueryParam() {
		super();
		// TODO Auto-generated constructor stub
	}
}
