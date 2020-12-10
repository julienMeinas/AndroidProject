package com.android.androidproject.presentation.articledisplay.MainApplication.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.data.di.FakeDependencyInjection;
import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.presentation.InfoActivity.InfoActivity;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleActionInterface;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.grille.RecyclerViewGrilleAdapter;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.list.RecyclerViewListAdapter;
import com.android.androidproject.presentation.viewmodel.FavoriteViewModel;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

public class HomeFragment extends Fragment implements ArticleActionInterface {
    private static HomeFragment singleton = null;
    private static final String TAG = "HomeFragment";
    private View m_view;
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private HomeViewModel m_homeViewModel;
    private FavoriteViewModel favoriteViewModel;
    private RecyclerViewListAdapter m_recyclerViewListAdapter;
    private RecyclerViewGrilleAdapter m_recyclerViewGrilleAdapter;
    private boolean layoutManagerList;

    public HomeFragment(){
    }

    public static HomeFragment newInstance() {
        if(singleton == null) {
            singleton = new HomeFragment();
        }
        return singleton;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView: started");
        m_view = inflater.inflate(R.layout.fragment_home, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutManagerList = true;
        initRecyclerViewList();
        coordinatorLayout = m_view.findViewById(R.id.home);
        m_view.findViewById(R.id.switch_layout_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutManagerList) {
                    initRecyclerViewGrid();
                    layoutManagerList = false;
                }
                else {
                    initRecyclerViewList();
                    layoutManagerList = true;
                }
            }
        });
    }



    public void initRecyclerViewList() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewListAdapter = new RecyclerViewListAdapter(this);
        recyclerView.setAdapter(m_recyclerViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerViewModelsList();
    }

    private void registerViewModelsList() {
        Log.d(TAG, "registerViewModels call");
        m_homeViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(HomeViewModel.class);
        favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getFavoriteViewModel()).get(FavoriteViewModel.class);
        m_homeViewModel.getBestArticles();
        m_homeViewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> bookItemViewModelList) {
                m_recyclerViewListAdapter.bindViewModels(bookItemViewModelList);
            }
        });
    }


    public void initRecyclerViewGrid() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewGrilleAdapter = new RecyclerViewGrilleAdapter(this);
        recyclerView.setAdapter(m_recyclerViewGrilleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        registerViewModelsGrid();
    }


    private void registerViewModelsGrid() {
        Log.d(TAG, "registerViewModels call");
        m_homeViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(HomeViewModel.class);
        m_homeViewModel.getBestArticles();
        m_homeViewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> bookItemViewModelList) {
                m_recyclerViewGrilleAdapter.bindViewModels(bookItemViewModelList);
            }
        });
    }

    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onInfoClicked(String articleTitle, String articleAuthor,
                              String articleDate, String articleDescription,
                              String articleUrlImage) {
        Log.d(TAG, "onInfoClicked call");
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(InfoActivity.TITLE_MESSAGE, articleTitle);
        intent.putExtra(InfoActivity.AUTHOR_MESSAGE, articleAuthor);
        intent.putExtra(InfoActivity.DATE_MESSAGE, articleDate);
        intent.putExtra(InfoActivity.DESCRIPTION_MESSAGE, articleDescription);
        intent.putExtra(InfoActivity.URL_IMAGE_MESSAGE, articleUrlImage);
        startActivity(intent);
    }

    @Override
    public void onFav(String articleTitle, String articleAuthor,
                      String articleDate, String articleDescription, String articleUrlImage) {
        Log.d(TAG, "onFav call");
        displaySnackBar("Ajout au favoris");
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleTitle);
        articleEntity.setAuthor(articleAuthor);
        articleEntity.setDate(articleDate);
        articleEntity.setDescription(articleDescription);
        articleEntity.setImageUrl(articleUrlImage);

        favoriteViewModel.addBookToFavorite(articleEntity);
    }

}
