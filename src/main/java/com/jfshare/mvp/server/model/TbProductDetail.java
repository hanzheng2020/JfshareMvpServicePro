package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProductDetail {
    private Integer id;

    private String detailKey;

    private String productInstructionsUrl;

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

    public String getProductInstructionsUrl() {
        return productInstructionsUrl;
    }

    public void setProductInstructionsUrl(String productInstructionsUrl) {
        this.productInstructionsUrl = productInstructionsUrl == null ? null : productInstructionsUrl.trim();
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