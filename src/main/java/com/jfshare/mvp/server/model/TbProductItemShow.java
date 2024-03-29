package com.jfshare.mvp.server.model;

import java.util.Date;

public class TbProductItemShow {
    private Integer id;

    private Integer itemShowNo;

    private String itemNo;

    private String itemName;

    private String products;

    private Boolean publishInd;

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

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
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