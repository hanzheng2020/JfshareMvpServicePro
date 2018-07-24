package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProduct {
    private Integer id;

    private String productId;

    private Integer sellerId;

    private String productName;

    private Integer subjectId;

    private Integer curPrice;

    private Integer orgPrice;

    private Integer presentexp;

    private String imgKey;

    private String detailKey;

    private Integer activeState;

    private Integer type;

    private String remark;

    private Date createTime;

    private Integer createUserId;

    private Date lastSoldoutTime;

    private Date lastPutawayTime;

    private Integer lastUpdateId;

    private Integer state;

    private Integer thirdPartyIdentify;

    private String thirdPartyProductId;

    private String storehouseIds;

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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(Integer curPrice) {
        this.curPrice = curPrice;
    }

    public Integer getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(Integer orgPrice) {
        this.orgPrice = orgPrice;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getStorehouseIds() {
        return storehouseIds;
    }

    public void setStorehouseIds(String storehouseIds) {
        this.storehouseIds = storehouseIds == null ? null : storehouseIds.trim();
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