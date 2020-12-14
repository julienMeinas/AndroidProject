package com.android.androidproject.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.androidproject.data.entity.ArticleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ArticleDao {

    @Query("SELECT * from articleentity")
    Flowable<List<ArticleEntity>> loadFavorites();

    @Insert
    public Completable addArticleToFavorites(ArticleEntity bookEntity);

    @Query("DELETE FROM articleentity WHERE title = :title")
    public Completable deleteArticleFromFavorites(String title);

    @Query("SELECT title from articleentity")
    Single<List<String>> getFavoriteTitleList();

}

