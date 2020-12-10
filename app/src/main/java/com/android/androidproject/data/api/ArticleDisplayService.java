package com.android.androidproject.data.api;

import com.android.androidproject.ArticleApplication;
import com.android.androidproject.data.api.model.ArticleResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Classes that call uses the news API
 */
public interface ArticleDisplayService {
    /**
     * Method to retrieve popular articles in France
     *
     * @return popular articles in France
     */
    @GET("https://newsapi.org/v2/top-headlines?country=fr&apiKey="+ ArticleApplication.API_KEY)
    Single<ArticleResponse> getBestsArticles();


    /**
     * Method to retrieve articles found with the keyword
     * @param search : keyword
     * @return : articles found with the keyword
     */
    @GET("http://newsapi.org/v2/everything?sortBy=publishedAt&apiKey="+ ArticleApplication.API_KEY)
    Single<ArticleResponse> searchArticles(@Query("q") String search);
}
