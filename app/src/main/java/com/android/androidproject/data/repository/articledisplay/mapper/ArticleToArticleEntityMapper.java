package com.android.androidproject.data.repository.articledisplay.mapper;

import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.data.entity.ArticleEntity;

public class ArticleToArticleEntityMapper {

    /**
     * method for transform ArticleModel object to ArticleEntity object
     * @param articleModel the ArticleModel object
     * @return : return new object ArticleEntity
     */
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
