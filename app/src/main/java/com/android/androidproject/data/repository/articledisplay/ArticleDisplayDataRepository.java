package com.android.androidproject.data.repository.articledisplay;

import com.android.androidproject.data.api.model.ArticleResponse;
import com.android.androidproject.data.repository.articledisplay.remote.ArticleDisplayRemoteDataSource;

import io.reactivex.Single;

public class ArticleDisplayDataRepository {
    private ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource;

    public ArticleDisplayDataRepository(ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource) {
        this.articleDisplayRemoteDataSource = articleDisplayRemoteDataSource;
    }

    public Single<ArticleResponse> getBestsArticles() {
        return this.articleDisplayRemoteDataSource.getBestsArticles();
    }
}
