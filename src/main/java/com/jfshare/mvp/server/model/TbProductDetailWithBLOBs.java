package com.jfshare.mvp.server.model;

public class TbProductDetailWithBLOBs extends TbProductDetail {
    private String productDetail;

    private String productInstructions;

    private String productExchange;

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
}