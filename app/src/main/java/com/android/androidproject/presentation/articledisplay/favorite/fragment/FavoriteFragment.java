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
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.ArticleActionInterface;
import com.android.androidproject.presentation.articledisplay.favorite.adapter.RecyclerViewAdapterFavorite;
import com.android.androidproject.presentation.viewmodel.FavoriteViewModel;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Fragment Favorite
 */
public class FavoriteFragment extends Fragment implements ArticleActionInterface {
    private static FavoriteFragment singleton = null;
    private static final String TAG = "FavoriteFragment";
    private View m_view;
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();
    private FavoriteViewModel m_favoriteViewModel;
    private HomeViewModel m_homeViewModel;
    private RecyclerViewAdapterFavorite m_recyclerViewListAdapter;


    public FavoriteFragment() {
    }

    /**
     * Singleon pattern
     * @return instance of FavoriteFragment
     */
    public static FavoriteFragment newInstance() {
        if(singleton == null) {
            singleton = new FavoriteFragment();
        }
        return singleton;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started");
        super.onCreateView(inflater, container, savedInstanceState);
        this.m_view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        return this.m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: started");
        super.onActivityCreated(savedInstanceState);
        initRecyclerViewList();
    }

    /**
     * init recycler view
     */
    public void initRecyclerViewList() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewListAdapter = new RecyclerViewAdapterFavorite(this);
        recyclerView.setAdapter(m_recyclerViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setupRecyclerView();
    }


    /**
     * setup recycler view
     */
    private void setupRecyclerView() {
        m_favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);
        m_favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> articleItemViewModelList) {
                m_recyclerViewListAdapter.bindViewModels(articleItemViewModelList);
            }
        });
    }


    /**
     * remove article in favorite (dataBase)
     * @param title the title of the article removed
     */
    @Override
    public void removeFavorite(String title) {
        Log.d(TAG, "onRemove call");
        m_favoriteViewModel.removeArticleFromFavorites(title);
    }

    /**
     * method called when we click on info button => going to InfoActivity with element of the article
     * @param articleTitle : title of the article
     * @param articleAuthor : author of the article
     * @param articleDate : date of the article
     * @param articleDescription : description of the article
     * @param articleUrlImage : urlImage of the article
     * @param articleUrl : url of the article
     */
    @Override
    public void onInfoClicked(String articleTitle, String articleAuthor, String articleDate, String articleDescription, String articleUrlImage, String articleUrl) {
        Log.d(TAG, "onInfoClicked call");
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(InfoActivity.TITLE_MESSAGE, articleTitle);
        intent.putExtra(InfoActivity.AUTHOR_MESSAGE, articleAuthor);
        intent.putExtra(InfoActivity.DATE_MESSAGE, articleDate);
        intent.putExtra(InfoActivity.DESCRIPTION_MESSAGE, articleDescription);
        intent.putExtra(InfoActivity.URL_IMAGE_MESSAGE, articleUrlImage);
        intent.putExtra(InfoActivity.URL_MESSAGE, articleUrl);
        startActivity(intent);
    }
}
