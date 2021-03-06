package com.android.androidproject.data.api.model;

/**
 * object model which is used to retrieve the articles of an API call
 */
public class ArticleModel {
    private ArticleSource m_articleSource;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private boolean isFavorite;

    public ArticleModel(ArticleSource m_articleSource, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.m_articleSource = m_articleSource;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.isFavorite = false;
    }

    public ArticleSource getM_articleSource() {
        return m_articleSource;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public Boolean getFavorite() {
        return this.isFavorite;
    }

    public void setM_articleSource(ArticleSource m_articleSource) {
        this.m_articleSource = m_articleSource;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addFavorite() {
        this.isFavorite = true;
    }
}
