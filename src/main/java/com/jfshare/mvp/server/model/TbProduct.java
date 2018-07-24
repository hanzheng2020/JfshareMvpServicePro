package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProduct {
    private Integer id;

    private String productId;

    private Integer sellerId;

    private String productName;

    private String productHeader;

    private Integer itemNo;

    private String curPrice;

    private String orgPrice;

    private Integer presentexp;

    private String imgKey;

    private String detailKey;

    private Integer activeState;

    private String remark;

    private Date createTime;

    private Integer createUserId;

    private Date lastSoldoutTime;

    private Date lastPutawayTime;

    private Integer lastUpdateId;

    private Integer thirdPartyIdentify;

    private String thirdPartyProductId;

    private Integer productStock;

    private String ext1;

    private String ext2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductHeader() {
        return productHeader;
    }

    public void setProductHeader(String productHeader) {
        this.productHeader = productHeader == null ? null : productHeader.trim();
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(String curPrice) {
        this.curPrice = curPrice == null ? null : curPrice.trim();
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice == null ? null : orgPrice.trim();
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
        this.imgKey = imgKey == null ? null : imgKey.trim();
    }

    public String getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(String detailKey) {
        this.detailKey = detailKey == null ? null : detailKey.trim();
    }

    public Integer getActiveState() {
        return activeState;
    }

    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public Integer getThirdPartyIdentify() {
        return thirdPartyIdentify;
    }

    public void setThirdPartyIdentify(Integer thirdPartyIdentify) {
        this.thirdPartyIdentify = thirdPartyIdentify;
    }

    public String getThirdPartyProductId() {
        return thirdPartyProductId;
    }

    public void setThirdPartyProductId(String thirdPartyProductId) {
        this.thirdPartyProductId = thirdPartyProductId == null ? null : thirdPartyProductId.trim();
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
}