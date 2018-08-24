package com.jfshare.mvp.server.model;

import java.util.Date;

public class Product {
	private Integer itemNo;//类目
	private String productId;//商品id
	private String productName;//商品名称
	private String productHeader;//商品副标题
	private String curPrice;//商品价格
	private String orgPrice;//商品原价
	private Integer productStock;//商品库存
	private Integer activeState;//商品状态
	private Integer presentexp;//赠送金豆
	private String imgKey;//商品图片
	private String productInstructions;//商品使用说明
	private String productExchange;//商品兑换说明
	private Date lastSoldoutTime;//下架时间
	private Date lastPutawayTime;//上架时间
	
	public String getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(String orgPrice) {
		this.orgPrice = orgPrice;
	}
	public Date getLastSoldoutTime() {
		return lastSoldoutTime;
	}
	public void setLastSoldoutTime(Date lastSoldoutTime) {
		this.lastSoldoutTime = lastSoldoutTime;
	}
	public Date getLastPutawayTime() {
		return lastPutawayTime;
	}
	public void setLastPutawayTime(Date lastPutawayTime) {
		this.lastPutawayTime = lastPutawayTime;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
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
	public String getProductHeader() {
		return productHeader;
	}
	public void setProductHeader(String productHeader) {
		this.productHeader = productHeader;
	}
	public String getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public Integer getActiveState() {
		return activeState;
	}
	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}
	public Integer getPresentexp() {
		return presentexp;
	}
	public void setPresentexp(Integer presentexp) {
		this.presentexp = presentexp;
	}
	public String getImgKey() {
		return imgKey;
	}
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}
	public String getProductInstructions() {
		return productInstructions;
	}
	public void setProductInstructions(String productInstructions) {
		this.productInstructions = productInstructions;
	}
	public String getProductExchange() {
		return productExchange;
	}
	public void setProductExchange(String productExchange) {
		this.productExchange = productExchange;
	}
}
