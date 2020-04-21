package com.example.kawalingkungan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNews {
    @SerializedName("title")
    private String title;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("articles")
    private List<ModelNews> newsList;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public List<ModelNews> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<ModelNews> newsList) {
        this.newsList = newsList;
    }
}
