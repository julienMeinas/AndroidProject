package com.android.androidproject.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.androidproject.data.entity.ArticleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Class for call request in Room Data base
 */
@Dao
public interface ArticleDao {

    /**
     * @return all articles in database
     */
    @Query("SELECT * from articleentity")
    Flowable<List<ArticleEntity>> loadFavorites();

    /**
     * @param articleEntity : article
     * @return insert new article in data base
     */
    @Insert
    public Completable addArticleToFavorites(ArticleEntity articleEntity);

    /**
     * @param title : title of article
     * @return delete article with the title from data base
     */
    @Query("DELETE FROM articleentity WHERE title = :title")
    public Completable deleteArticleFromFavorites(String title);

    /**
     * @return all titles of all articles in data base
     */
    @Query("SELECT title from articleentity")
    Single<List<String>> getFavoriteTitleList();

}

