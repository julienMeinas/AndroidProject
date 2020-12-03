package com.android.androidproject.data.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.androidproject.data.entity.ArticleEntity;


@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao bookDao();
}