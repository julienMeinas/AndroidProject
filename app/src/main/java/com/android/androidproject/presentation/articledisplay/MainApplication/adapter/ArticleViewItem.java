package com.android.androidproject.presentation.articledisplay.MainApplication.adapter;

public class ArticleViewItem {
    private String author;
    private String title;
    private String urlToImage;
    private String publishedAt;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
