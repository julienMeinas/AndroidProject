package com.android.androidproject.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ArticleDisplayDataRepository articleDisplayRepository;

    public ViewModelFactory(ArticleDisplayDataRepository articleDisplayRepository) {
        this.articleDisplayRepository = articleDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(articleDisplayRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
