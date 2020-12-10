package com.android.androidproject.presentation.articledisplay.favorite.adapter;

public class ArticleFavoriteViewModel {
    private String author;
    private String title;
    private String urlToImage;
    private String publishedAt;
    private String description;
    private String url;

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

    public String getDescription() { return description; }

    public String getUrl() { return url; }

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

    public void setDescription(String description) { this.description = description; }

    public void setUrl(String url) { this.url = url; }
}
