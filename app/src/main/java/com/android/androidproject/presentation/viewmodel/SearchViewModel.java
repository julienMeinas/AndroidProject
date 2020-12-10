package com.android.androidproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.androidproject.data.api.model.ArticleResponse;
import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;
import com.android.androidproject.presentation.articledisplay.search.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.search.mapper.ArticleToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {
    private ArticleDisplayDataRepository articleDisplayDataRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    private ArticleToViewModelMapper articleToViewModelMapper;
    private MutableLiveData<List<ArticleViewItem>> m_Articles = new MutableLiveData<List<ArticleViewItem>>();


    public SearchViewModel(ArticleDisplayDataRepository articleDisplayDataRepository) {
        this.articleDisplayDataRepository = articleDisplayDataRepository;
        this.articleToViewModelMapper = new ArticleToViewModelMapper();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getArticlesByKeyWork(String keyword) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(articleDisplayDataRepository.getSearchArticles(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArticleResponse>() {
                    @Override
                    public void onSuccess(ArticleResponse articleResponse) {
                        m_Articles.setValue(articleToViewModelMapper.map(articleResponse.getArticles()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                    }

                }));
    }

    public LiveData<List<ArticleViewItem>> getArticles() {return this.m_Articles; }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }
}
