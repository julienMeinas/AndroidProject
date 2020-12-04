package com.android.androidproject.data.repository.articledisplay.local;

import com.android.androidproject.data.db.ArticleDatabase;
import com.android.androidproject.data.entity.ArticleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ArticleDisplayLocalDataSource {
    private ArticleDatabase m_articleDatabase;

    public ArticleDisplayLocalDataSource(ArticleDatabase articleDatabase) {
        this.m_articleDatabase = articleDatabase;
    }

    public Flowable<List<ArticleEntity>> loadFavorites() {
        return m_articleDatabase.bookDao().loadFavorites();
    }

    public Completable addArticleToFavorites(ArticleEntity bookEntity) {
        return m_articleDatabase.bookDao().addArticleToFavorites(bookEntity);
    }

    public Completable deleteArticleFromFavorites(String id) {
        return m_articleDatabase.bookDao().deleteArticleFromFavorites(id);
    }

    public Single<List<String>> getFavoriteTitleList() {
        return m_articleDatabase.bookDao().getFavoriteTitleList();
    }
}
