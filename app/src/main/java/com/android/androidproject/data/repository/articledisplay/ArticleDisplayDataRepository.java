package com.android.androidproject.data.repository.articledisplay;

import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.data.api.model.ArticleResponse;
import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.data.repository.articledisplay.local.ArticleDisplayLocalDataSource;
import com.android.androidproject.data.repository.articledisplay.remote.ArticleDisplayRemoteDataSource;

import java.util.List;
import java.util.function.Function;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.Path;

public class ArticleDisplayDataRepository {
    private ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource;
    private ArticleDisplayLocalDataSource articleDisplayLocalDataSource;

    public ArticleDisplayDataRepository(ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource,
                                        ArticleDisplayLocalDataSource articleDisplayLocalDataSource) {
        this.articleDisplayRemoteDataSource = articleDisplayRemoteDataSource;
        this.articleDisplayLocalDataSource = articleDisplayLocalDataSource;
    }

    public Single<ArticleResponse> getBestsArticles() {
        return this.articleDisplayRemoteDataSource.getBestsArticles();
    }

    public Single<ArticleResponse> getSearchArticles(@Path("search-terms") String searchTerms) {
        return this.articleDisplayRemoteDataSource.getSearchArticles(searchTerms);
    }

    public Flowable<List<ArticleEntity>> getFavoriteBooks() {
        return articleDisplayLocalDataSource.loadFavorites();
    }

    public Completable addBookToFavorites(ArticleEntity articleEntity) {
        return articleDisplayLocalDataSource.addArticleToFavorites(articleEntity);
    }

    public Completable removeBookFromFavorites(String bookId) {
        return articleDisplayLocalDataSource.deleteArticleFromFavorites(bookId);
    }
}
