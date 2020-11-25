package com.android.androidproject.data.repository.articledisplay;

import com.android.androidproject.data.api.model.ArticleResponse;
import com.android.androidproject.data.repository.articledisplay.remote.ArticleDisplayRemoteDataSource;

import io.reactivex.Single;
import retrofit2.http.Path;

public class ArticleDisplayDataRepository {
    private ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource;

    public ArticleDisplayDataRepository(ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource) {
        this.articleDisplayRemoteDataSource = articleDisplayRemoteDataSource;
    }

    public Single<ArticleResponse> getBestsArticles() {
        return this.articleDisplayRemoteDataSource.getBestsArticles();
    }

    public Single<ArticleResponse> getSearchArticles(@Path("search-terms") String searchTerms) {
        return this.articleDisplayRemoteDataSource.getSearchArticles(searchTerms);
    }
}
