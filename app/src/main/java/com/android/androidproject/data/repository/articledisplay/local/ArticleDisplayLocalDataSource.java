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

    /**
     * @return the articles in local dada base
     */
    public Flowable<List<ArticleEntity>> loadFavorites() {
        return m_articleDatabase.bookDao().loadFavorites();
    }

    /**
     * @param bookEntity : object articles
     * @return : add bookEntity in data base
     */
    public Completable addArticleToFavorites(ArticleEntity bookEntity) {
        return m_articleDatabase.bookDao().addArticleToFavorites(bookEntity);
    }

    /**
     * @param title : title of the article to be deleted
     * @return : remove article with the title
     */
    public Completable deleteArticleFromFavorites(String title) {
        return m_articleDatabase.bookDao().deleteArticleFromFavorites(title);
    }

    /**
     * @return : get all title of articles in data base
     */
    public Single<List<String>> getFavoriteTitleList() {
        return m_articleDatabase.bookDao().getFavoriteTitleList();
    }
}
