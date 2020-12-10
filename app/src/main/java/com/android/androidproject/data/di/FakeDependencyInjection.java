package com.android.androidproject.data.di;

import android.content.Context;

import androidx.room.Room;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import com.android.androidproject.ArticleApplication;
import com.android.androidproject.data.api.ArticleDisplayService;
import com.android.androidproject.data.db.ArticleDatabase;
import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;
import com.android.androidproject.data.repository.articledisplay.local.ArticleDisplayLocalDataSource;
import com.android.androidproject.data.repository.articledisplay.remote.ArticleDisplayRemoteDataSource;
import com.android.androidproject.presentation.viewmodel.FavoriteViewModel;
import com.android.androidproject.presentation.viewmodel.ViewModelFactoryFavorite;
import com.android.androidproject.presentation.viewmodel.ViewModelFactoryHome;
import com.android.androidproject.presentation.viewmodel.ViewModelFactorySearch;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * method that simulates the use of the library Dagger
 */
public class FakeDependencyInjection {
    private static Retrofit retrofit;
    private static Gson gson;
    private static ArticleDisplayService articleDisplayService;
    private static ArticleDisplayDataRepository articleDisplayDataRepository;
    private static ViewModelFactoryHome viewModelFactory;
    private static ViewModelFactorySearch viewModelFactorySearch;
    private static Context applicationContext;
    private static ArticleDatabase articleDatabase;
    private static ViewModelFactoryFavorite viewModelFactoryFavorite;

    public static ViewModelFactoryHome getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactoryHome(getArticleDisplayRepository());
        }
        return viewModelFactory;
    }

    public static ViewModelFactorySearch getViewModelFactorySearch() {
        if (viewModelFactorySearch == null) {
            viewModelFactorySearch = new ViewModelFactorySearch(getArticleDisplayRepository());
        }
        return viewModelFactorySearch;
    }

    public static ViewModelFactoryFavorite getFavoriteViewModel() {
        if (viewModelFactoryFavorite == null) {
            viewModelFactoryFavorite = new ViewModelFactoryFavorite(getArticleDisplayRepository());
        }
        return viewModelFactoryFavorite;
    }


    public static ArticleDisplayDataRepository getArticleDisplayRepository() {
        if (articleDisplayDataRepository == null) {
            articleDisplayDataRepository = new ArticleDisplayDataRepository(
                    new ArticleDisplayRemoteDataSource(getArticleDisplayService()),
                    new ArticleDisplayLocalDataSource(getArticleDatabase())
            );
        }
        return articleDisplayDataRepository;
    }

    public static ArticleDisplayService getArticleDisplayService() {
        if (articleDisplayService == null) {
            articleDisplayService = getRetrofit().create(ArticleDisplayService.class);
        }
        return articleDisplayService;
    }



    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static ArticleDatabase getArticleDatabase() {
    if (articleDatabase == null) {
        articleDatabase = Room.databaseBuilder(applicationContext,
                    ArticleDatabase.class, "article-database").build();
        }
        return articleDatabase;
    }
}
