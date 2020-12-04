package com.android.androidproject.data.repository.articledisplay.mapper;

import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.data.entity.ArticleEntity;

public class ArticleToArticleEntityMapper {

    public ArticleEntity map(ArticleModel articleModel) {
        ArticleEntity res = new ArticleEntity();
        res.setAuthor(articleModel.getAuthor());
        res.setDate(articleModel.getPublishedAt());
        res.setDescription(articleModel.getDescription());
        res.setTitle(articleModel.getTitle());
        res.setImageUrl(articleModel.getUrl());
        return res;
    }
}
