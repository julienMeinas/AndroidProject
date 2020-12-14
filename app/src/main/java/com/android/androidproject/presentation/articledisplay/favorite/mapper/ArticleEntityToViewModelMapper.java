package com.android.androidproject.presentation.articledisplay.favorite.mapper;

import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleEntityToViewModelMapper {

    public ArticleViewItem map(ArticleEntity articleEntity) {
        ArticleViewItem articleViewItem = new ArticleViewItem();
        articleViewItem.setAuthor(articleEntity.getAuthor());
        articleViewItem.setDescription(articleEntity.getDescription());
        articleViewItem.setPublishedAt(articleEntity.getDate());
        articleViewItem.setTitle(articleEntity.getTitle());
        articleViewItem.setUrlToImage(articleEntity.getImageUrl());
        articleViewItem.setUrl(articleEntity.getUrl());
        return articleViewItem;
    }

    public List<ArticleViewItem> map (List<ArticleEntity> articleEntities) {
        List<ArticleViewItem> articleDetailViewModels = new ArrayList<>();
        for(ArticleEntity articleEntity : articleEntities) {
            articleDetailViewModels.add(map(articleEntity));
        }
        return articleDetailViewModels;
    }
}
