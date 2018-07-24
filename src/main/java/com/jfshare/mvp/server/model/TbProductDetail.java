package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProductDetail {
    private Integer id;

    private String detailKey;

    private String productDetail;

    private String productInstructions;

    private String productExchange;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(String detailKey) {
        this.detailKey = detailKey == null ? null : detailKey.trim();
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail == null ? null : productDetail.trim();
    }

    public String getProductInstructions() {
        return productInstructions;
    }

    public void setProductInstructions(String productInstructions) {
        this.productInstructions = productInstructions == null ? null : productInstructions.trim();
    }

    public String getProductExchange() {
        return productExchange;
    }

    public void setProductExchange(String productExchange) {
        this.productExchange = productExchange == null ? null : productExchange.trim();
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