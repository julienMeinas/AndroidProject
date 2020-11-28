package com.android.androidproject.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;

public class ViewModelFactorySearch implements ViewModelProvider.Factory {

    private final ArticleDisplayDataRepository articleDisplayRepository;

    public ViewModelFactorySearch(ArticleDisplayDataRepository articleDisplayRepository) {
        this.articleDisplayRepository = articleDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(articleDisplayRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
