package com.android.androidproject.presentation.articledisplay.home.mapper;

import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.presentation.articledisplay.home.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArticleToViewModelMapper {

    private ArticleViewItem map(ArticleModel articleModel) {
        ArticleViewItem res = new ArticleViewItem();
        res.setTitle(articleModel.getTitle());
        res.setAuthor(articleModel.getAuthor());
        res.setDescription(articleModel.getDescription());
        res.setUrl(articleModel.getUrl());
        res.setUrlToImage(articleModel.getUrlToImage());
        res.setPublishedAt(articleModel.getPublishedAt());
        return res;
    }

    public List<ArticleViewItem> map(List<ArticleModel> articleModels ) {
        List<ArticleViewItem> res = new ArrayList<>();
        for(ArticleModel a : articleModels) {
            res.add(map(a));
        }
        return res;
    }
}
