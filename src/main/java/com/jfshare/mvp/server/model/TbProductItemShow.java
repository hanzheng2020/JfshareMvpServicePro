package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProductItemShow {
    private Integer id;

    private Integer itemShowNo;

    private String itemShowDesc;

    private String products;

    private String appVersion;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemShowNo() {
        return itemShowNo;
    }

    public void setItemShowNo(Integer itemShowNo) {
        this.itemShowNo = itemShowNo;
    }

    public String getItemShowDesc() {
        return itemShowDesc;
    }

    public void setItemShowDesc(String itemShowDesc) {
        this.itemShowDesc = itemShowDesc == null ? null : itemShowDesc.trim();
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
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