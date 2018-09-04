package com.jfshare.mvp.server.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "java-mvp-es", type = "product")
public class ESProduct {

    @Id
    private String id;
    private String productName;
    private Double price;
    private String productDesc;
    @Field(type = FieldType.Text)
    private String productPic;

    public ESProduct() {
    }

    public ESProduct(String id, String productName, Double price, String productPic) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productPic = productPic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productDesc='" + productDesc + '\'' +
                ", productPic='" + productPic + '\'' +
                '}';
    }
}