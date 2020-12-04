package com.android.androidproject.presentation.articledisplay.favorite.mapper;

import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleEntityToDetailViewModelMapper {

    public ArticleDetailViewModel map(ArticleEntity articleEntity) {
        ArticleDetailViewModel articleDetailViewModel = new ArticleDetailViewModel();
        articleDetailViewModel.setAuthor(articleEntity.getAuthor());
        articleDetailViewModel.setDescription(articleEntity.getDescription());
        articleDetailViewModel.setPublishedAt(articleEntity.getDate());
        articleDetailViewModel.setTitle(articleEntity.getTitle());
        articleDetailViewModel.setUrlToImage(articleEntity.getImageUrl());
        return articleDetailViewModel;
    }

    public List<ArticleDetailViewModel> map (List<ArticleEntity> articleEntities) {
        List<ArticleDetailViewModel> articleDetailViewModels = new ArrayList<>();
        for(ArticleEntity articleEntity : articleEntities) {
            articleDetailViewModels.add(map(articleEntity));
        }
        return articleDetailViewModels;
    }
}
