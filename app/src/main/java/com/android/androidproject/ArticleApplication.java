package com.android.androidproject;

import android.app.Application;

import com.android.androidproject.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

public class ArticleApplication extends Application {
    public static final String API_KEY = "878fcfea2e684c49ac47a8d7cf75c704";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
