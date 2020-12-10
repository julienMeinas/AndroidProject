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
import io.reactivex.functions.BiFunction;
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

    /**
     * @return : get all bests articles
     */
    public Single<ArticleResponse> getBestsArticles() {
        return this.articleDisplayRemoteDataSource.getBestsArticles()
                .zipWith(articleDisplayLocalDataSource.getFavoriteTitleList(), new BiFunction<ArticleResponse, List<String>, ArticleResponse>() {
                    @Override
                    public ArticleResponse apply(ArticleResponse articleSearchResponse, List<String> titleList) throws Exception {
                        for (ArticleModel article : articleSearchResponse.getArticles()) {
                            if (titleList.contains(article.getTitle())) {
                                article.addFavorite();
                            }
                        }
                        return articleSearchResponse;
                    }
                });
    }

    /**
     * @param searchTerms : search terms
     * @return : get all articles who contains "searchTerms"
     */
    public Single<ArticleResponse> getSearchArticles(@Path("search-terms") String searchTerms) {
        return this.articleDisplayRemoteDataSource.getSearchArticles(searchTerms)
                .zipWith(articleDisplayLocalDataSource.getFavoriteTitleList(), new BiFunction<ArticleResponse, List<String>, ArticleResponse>() {
                    @Override
                    public ArticleResponse apply(ArticleResponse articleSearchResponse, List<String> titleList) throws Exception {
                        for (ArticleModel article : articleSearchResponse.getArticles()) {
                            if (titleList.contains(article.getTitle())) {
                                article.addFavorite();
                            }
                        }
                        return articleSearchResponse;
                    }
                });
    }

    /**
     * @return all articles in favorite data base
     */
    public Flowable<List<ArticleEntity>> getFavoriteArticles() {
        return articleDisplayLocalDataSource.loadFavorites();
    }

    /**
     * @param articleEntity : article
     * @return : add article in data base
     */
    public Completable addArticleToFavorites(ArticleEntity articleEntity) {
        return articleDisplayLocalDataSource.addArticleToFavorites(articleEntity);
    }

    /**
     * @param articleTitle : title of a article
     * @return : remove article in data base with the title "articleTitle"
     */
    public Completable removeArticleFromFavorites(String articleTitle) {
        return articleDisplayLocalDataSource.deleteArticleFromFavorites(articleTitle);
    }
}
