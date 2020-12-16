package com.android.androidproject.presentation.articledisplay.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.data.api.model.ArticleModel;
import com.android.androidproject.data.di.FakeDependencyInjection;
import com.android.androidproject.data.entity.ArticleEntity;
import com.android.androidproject.presentation.InfoActivity.InfoActivity;
import com.android.androidproject.presentation.articledisplay.home.adapter.ArticleActionInterface;
import com.android.androidproject.presentation.articledisplay.home.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.home.adapter.RecyclerViewGrilleAdapter;
import com.android.androidproject.presentation.articledisplay.home.adapter.RecyclerViewListAdapter;
import com.android.androidproject.presentation.viewmodel.FavoriteViewModel;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

/**
 * Home Fragment
 */
public class HomeFragment extends Fragment implements ArticleActionInterface {
    private static HomeFragment singleton = null;
    private static final String TAG = "HomeFragment";
    private View m_view;
    private ProgressBar m_progressBar;
    private TextView m_textViewErrorConnexion;
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private HomeViewModel m_homeViewModel;
    private FavoriteViewModel favoriteViewModel;
    private RecyclerViewListAdapter m_recyclerViewListAdapter;
    private RecyclerViewGrilleAdapter m_recyclerViewGrilleAdapter;
    private boolean layoutManagerList;
    private static final String msgAddFavorite = "Ajout aux favoris";

    public HomeFragment(){
    }

    /**
     * Singleton pattern
     * @return instance of Home fragment
     */
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
        m_progressBar = m_view.findViewById(R.id.progress_bar);
        m_textViewErrorConnexion = m_view.findViewById(R.id.textViewError);

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


    /**
     * init recycler view [List]
     */
    public void initRecyclerViewList() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewListAdapter = new RecyclerViewListAdapter(this);
        recyclerView.setAdapter(m_recyclerViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerViewModelsList();
    }

    /**
     *  setup recylcer view [List]
     */
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

        m_homeViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                m_progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * init recycler view [Grid]
     */
    public void initRecyclerViewGrid() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewGrilleAdapter = new RecyclerViewGrilleAdapter(this);
        recyclerView.setAdapter(m_recyclerViewGrilleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        registerViewModelsGrid();
    }

    /**
     *  setup recylcer view [Grid]
     */
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

        m_homeViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                m_progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });

        m_homeViewModel.getErrorConnexion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean errorConnexion) {
                m_textViewErrorConnexion.setVisibility(errorConnexion ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onInfoClicked(String articleTitle, String articleAuthor,
                              String articleDate, String articleDescription,
                              String articleUrlImage, String articleUrl) {
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

    @Override
    public void onFav(String articleTitle, String articleAuthor,
                      String articleDate, String articleDescription,
                      String articleUrlImage, String articleUrl) {
        Log.d(TAG, "onFav call");
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleTitle);
        articleEntity.setAuthor(articleAuthor);
        articleEntity.setDate(articleDate);
        articleEntity.setDescription(articleDescription);
        articleEntity.setImageUrl(articleUrlImage);
        articleEntity.setUrl(articleUrl);

        favoriteViewModel.addBookToFavorite(articleEntity);
    }

}
