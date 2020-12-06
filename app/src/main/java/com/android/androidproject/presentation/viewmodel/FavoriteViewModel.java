package com.android.androidproject.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.data.repository.articledisplay.ArticleDisplayDataRepository;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.favorite.mapper.ArticleEntityToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavoriteViewModel extends ViewModel {
    private ArticleDisplayDataRepository articleDisplayDataRepository;
    private ArticleEntityToViewModelMapper articleEntityToViewModelMapper;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<ArticleViewItem>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<ArticleEntity>> articleAddedEvent = new MutableLiveData<Event<ArticleEntity>>();
    final MutableLiveData<Event<String>> articleDeletedEvent = new MutableLiveData<Event<String>>();

    public FavoriteViewModel(ArticleDisplayDataRepository articleDisplayDataRepository) {
        this.articleDisplayDataRepository = articleDisplayDataRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.articleEntityToViewModelMapper = new ArticleEntityToViewModelMapper();
    }

    public MutableLiveData<Event<ArticleEntity>> getBookAddedEvent() {
        return articleAddedEvent;
    }

    public MutableLiveData<Event<String>> getBookDeletedEvent() {
        return articleDeletedEvent;
    }

    public MutableLiveData<List<ArticleViewItem>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<ArticleViewItem>>();
            compositeDisposable.add(articleDisplayDataRepository.getFavoriteBooks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<ArticleEntity>>() {

                        @Override
                        public void onNext(List<ArticleEntity> articleEntityList) {
                            isDataLoading.setValue(false);
                            favorites.setValue(articleEntityToViewModelMapper.map(articleEntityList));
                            System.out.println("BIND FAVORITES");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            //Do Nothing
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void addBookToFavorite(final ArticleEntity articleEntity) {
        compositeDisposable.add(articleDisplayDataRepository.addBookToFavorites(articleEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        articleAddedEvent.setValue(new Event<ArticleEntity>(articleEntity));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void removeBookFromFavorites(final String title) {
        compositeDisposable.add(articleDisplayDataRepository.removeBookFromFavorites(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        articleDeletedEvent.setValue(new Event<String>(title));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
