package com.android.androidproject.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;

public class ViewModelFactoryFavorite implements ViewModelProvider.Factory{

    private final ArticleDisplayDataRepository articleDisplayRepository;

    public ViewModelFactoryFavorite(ArticleDisplayDataRepository articleDisplayRepository) {
        this.articleDisplayRepository = articleDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(articleDisplayRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
