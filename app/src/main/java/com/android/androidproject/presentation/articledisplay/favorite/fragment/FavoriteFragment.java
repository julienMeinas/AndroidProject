package com.android.androidproject.presentation.articledisplay.favorite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.androidproject.R;
import com.android.androidproject.data.di.FakeDependencyInjection;
import com.android.androidproject.presentation.InfoActivity.InfoActivity;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.list.RecyclerViewListAdapter;
import com.android.androidproject.presentation.articledisplay.MainApplication.fragment.home.HomeFragment;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleDetailActionInterface;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleDetailAdapter;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleDetailViewModel;
import com.android.androidproject.presentation.viewmodel.Event;
import com.android.androidproject.presentation.viewmodel.FavoriteViewModel;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteFragment extends Fragment implements ArticleDetailActionInterface {
    private static FavoriteFragment singleton = null;
    private static final String TAG = "FavoriteFragment";
    private View m_view;
    private ArrayList<ArticleDetailViewModel> m_articles = new ArrayList<>();

    private FavoriteViewModel m_favoriteViewModel;
    private HomeViewModel m_homeViewModel;
    private ArticleDetailAdapter m_recyclerViewListAdapter;


    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        if(singleton == null) {
            singleton = new FavoriteFragment();
        }
        return singleton;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.m_view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        Log.d(TAG, "onCreateView: started");
        return this.m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerViewList();
    }

    public void initRecyclerViewList() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewListAdapter = new ArticleDetailAdapter(this);
        recyclerView.setAdapter(m_recyclerViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerViewModels();
    }



    private void registerViewModels() {
        Log.d(TAG, "registerViewModels call");
        m_favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);
        m_favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> bookItemViewModelList) {
                m_recyclerViewListAdapter.bindViewModels(bookItemViewModelList);
            }
        });
    }

    private void setupRecyclerView() {
        m_favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);
        m_favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> articleItemViewModelList) {
                m_recyclerViewListAdapter.bindViewModels(articleItemViewModelList);
            }
        });
    }


    @Override
    public void removeFavorite(String title) {
        Log.d(TAG, "onFav call");
        m_favoriteViewModel.removeBookFromFavorites(title);
    }

    @Override
    public void onInfoClicked(String articleTitle, String articleAuthor, String articleDate, String articleDescription, String articleUrlImage) {
        Log.d(TAG, "onInfoClicked call");
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(InfoActivity.TITLE_MESSAGE, articleTitle);
        intent.putExtra(InfoActivity.AUTHOR_MESSAGE, articleAuthor);
        intent.putExtra(InfoActivity.DATE_MESSAGE, articleDate);
        intent.putExtra(InfoActivity.DESCRIPTION_MESSAGE, articleDescription);
        intent.putExtra(InfoActivity.URL_IMAGE_MESSAGE, articleUrlImage);
        startActivity(intent);
    }
}
