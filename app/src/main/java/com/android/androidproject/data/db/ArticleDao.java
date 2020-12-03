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
    Flowable<List<ArticleDao>> loadFavorites();

    @Insert
    public Completable addBookToFavorites(ArticleEntity bookEntity);

    @Query("DELETE FROM articleentity WHERE title = :title")
    public Completable deleteBookFromFavorites(String title);

}