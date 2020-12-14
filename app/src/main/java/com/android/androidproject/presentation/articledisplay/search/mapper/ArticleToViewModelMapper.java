package com.android.androidproject.presentation.articledisplay.search.mapper;

import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.presentation.articledisplay.search.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleToViewModelMapper {

    /**
     * map date
     * @param date
     * @return date mapped
     */
    private String mapDate(String date) {
        return date.substring(0, 10);
    }

    /**
     * allow to map ArticleModel to ArticleViewItem
     * @param articleModel : article
     * @return ArticleViewItem object
     */
    private ArticleViewItem map(ArticleModel articleModel) {
        ArticleViewItem res = new ArticleViewItem();
        res.setTitle(articleModel.getTitle());
        if(articleModel.getAuthor() != null) {
            res.setAuthor(articleModel.getAuthor());
        }
        else {
            res.setAuthor("No Author");
        }
        if(articleModel.getUrlToImage() != null) {
            res.setUrlToImage(articleModel.getUrlToImage());
        }
        else{
            res.setUrlToImage("https://static1.seekingalpha.com/uploads/2020/11/22/173432-16060661162838283.png");
        }
        res.setPublishedAt(mapDate(articleModel.getPublishedAt()));
        res.setDescription(articleModel.getDescription());
        res.setUrl(articleModel.getUrl());
        res.setIsFavorite(articleModel.getFavorite());
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
