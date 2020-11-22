package com.android.androidproject.data.api;

import com.android.androidproject.ArticleApplication;
import com.android.androidproject.data.api.model.ArticleResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ArticleDisplayService {
    @GET("https://newsapi.org/v2/top-headlines?country=fr&apiKey="+ ArticleApplication.API_KEY)
    Single<ArticleResponse> searchBooks();
}
