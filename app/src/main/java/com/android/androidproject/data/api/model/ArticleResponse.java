package com.android.androidproject.data.api.model;

import java.util.List;

/**
 * object model which is used to retrieve the response of an API call
 */
public class ArticleResponse {
    private String status;
    private int totalResults;
    List<ArticleModel> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

}
