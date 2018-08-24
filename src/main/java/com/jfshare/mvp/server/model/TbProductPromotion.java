package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProductPromotion {
    private Integer id;

    private Integer promotionNo;

    private String promotionPicUrl;

    private String promotionUrl;

    private String productOneId;

    private String productOnePicUrl;

    private String productTwoId;

    private String productTwoPicUrl;

    private String productThreeId;

    private String productThreePicUrl;

    private String productFourId;

    private String productFourPicUrl;

    private String productFiveId;

    private String productFivePicUrl;

    private String productSixId;

    private String productSixPicUrl;

    private Boolean publishInd;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPromotionNo() {
        return promotionNo;
    }

    public void setPromotionNo(Integer promotionNo) {
        this.promotionNo = promotionNo;
    }

    public String getPromotionPicUrl() {
        return promotionPicUrl;
    }

    public void setPromotionPicUrl(String promotionPicUrl) {
        this.promotionPicUrl = promotionPicUrl == null ? null : promotionPicUrl.trim();
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl == null ? null : promotionUrl.trim();
    }

    public String getProductOneId() {
        return productOneId;
    }

    public void setProductOneId(String productOneId) {
        this.productOneId = productOneId == null ? null : productOneId.trim();
    }

    public String getProductOnePicUrl() {
        return productOnePicUrl;
    }

    public void setProductOnePicUrl(String productOnePicUrl) {
        this.productOnePicUrl = productOnePicUrl == null ? null : productOnePicUrl.trim();
    }

    public String getProductTwoId() {
        return productTwoId;
    }

    public void setProductTwoId(String productTwoId) {
        this.productTwoId = productTwoId == null ? null : productTwoId.trim();
    }

    public String getProductTwoPicUrl() {
        return productTwoPicUrl;
    }

    public void setProductTwoPicUrl(String productTwoPicUrl) {
        this.productTwoPicUrl = productTwoPicUrl == null ? null : productTwoPicUrl.trim();
    }

    public String getProductThreeId() {
        return productThreeId;
    }

    public void setProductThreeId(String productThreeId) {
        this.productThreeId = productThreeId == null ? null : productThreeId.trim();
    }

    public String getProductThreePicUrl() {
        return productThreePicUrl;
    }

    public void setProductThreePicUrl(String productThreePicUrl) {
        this.productThreePicUrl = productThreePicUrl == null ? null : productThreePicUrl.trim();
    }

    public String getProductFourId() {
        return productFourId;
    }

    public void setProductFourId(String productFourId) {
        this.productFourId = productFourId == null ? null : productFourId.trim();
    }

    public String getProductFourPicUrl() {
        return productFourPicUrl;
    }

    public void setProductFourPicUrl(String productFourPicUrl) {
        this.productFourPicUrl = productFourPicUrl == null ? null : productFourPicUrl.trim();
    }

    public String getProductFiveId() {
        return productFiveId;
    }

    public void setProductFiveId(String productFiveId) {
        this.productFiveId = productFiveId == null ? null : productFiveId.trim();
    }

    public String getProductFivePicUrl() {
        return productFivePicUrl;
    }

    public void setProductFivePicUrl(String productFivePicUrl) {
        this.productFivePicUrl = productFivePicUrl == null ? null : productFivePicUrl.trim();
    }

    public String getProductSixId() {
        return productSixId;
    }

    public void setProductSixId(String productSixId) {
        this.productSixId = productSixId == null ? null : productSixId.trim();
    }

    public String getProductSixPicUrl() {
        return productSixPicUrl;
    }

    public void setProductSixPicUrl(String productSixPicUrl) {
        this.productSixPicUrl = productSixPicUrl == null ? null : productSixPicUrl.trim();
    }

    public Boolean getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(Boolean publishInd) {
        this.publishInd = publishInd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}