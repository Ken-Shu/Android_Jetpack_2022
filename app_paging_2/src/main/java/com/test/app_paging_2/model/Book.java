package com.test.app_paging_2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @Expose // 要被序列化
    @SerializedName(value = "title") // 序列化名稱
    private String title;

    @Expose // 要被序列化
    @SerializedName(value = "subtitle") // 序列化名稱
    private String subtitle;

    @Expose // 要被序列化
    @SerializedName(value = "isbn13") // 序列化名稱
    private String isbn13;

    @Expose // 要被序列化
    @SerializedName(value = "price") // 序列化名稱
    private String price;

    @Expose // 要被序列化
    @SerializedName(value = "image") // 序列化名稱
    private String image;

    @Expose // 要被序列化
    @SerializedName(value = "url") // 序列化名稱
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
