package com.android.androidproject.data.repository.articledisplay.remote;

import com.android.androidproject.data.api.ArticleDisplayService;
import com.android.androidproject.data.api.model.ArticleResponse;

import io.reactivex.Single;
import retrofit2.http.Path;

public class ArticleDisplayRemoteDataSource {
    private ArticleDisplayService m_articleDisplayService;

    public ArticleDisplayRemoteDataSource(ArticleDisplayService m_articleDisplayService) {
        this.m_articleDisplayService = m_articleDisplayService;
    }

    /**
     * @return : get all bests articles
     */
    public Single<ArticleResponse> getBestsArticles() {
        return this.m_articleDisplayService.getBestsArticles();
    }

    /**
     * @param searchTerms : search terms
     * @return : get all articles who contains "searchTerms"
     */
    public Single<ArticleResponse> getSearchArticles(@Path("search-terms") String searchTerms) {
        return this.m_articleDisplayService.searchArticles(searchTerms);
    }
}
