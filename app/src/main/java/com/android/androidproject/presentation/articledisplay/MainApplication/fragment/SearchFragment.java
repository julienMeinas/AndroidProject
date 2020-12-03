package com.android.androidproject.presentation.articledisplay.MainApplication.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.androidproject.R;
import com.android.androidproject.data.di.FakeDependencyInjection;
import com.android.androidproject.presentation.InfoActivity.InfoActivity;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleActionInterface;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.RecyclerViewAdapter;
import com.android.androidproject.presentation.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment implements ArticleActionInterface {
    public static SearchFragment singleton = null;
    public static final String TAG = "SearchFragment";
    private View m_view;
    private SearchView searchView;
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();

    private SearchViewModel m_searchViewModel;
    private RecyclerViewAdapter m_recyclerViewAdapter;

    public SearchFragment(){}

    public static SearchFragment newInstance(){
        if(singleton == null) {
            singleton = new SearchFragment();
        }
        return singleton;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView: started");
        m_view = inflater.inflate(R.layout.fragment_search, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();

        registerViewModels();
    }

    private void registerViewModels() {
        m_searchViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactorySearch()).get(SearchViewModel.class);
        //System.out.println("FVVM is " + bookFavoriteViewModel);

        m_searchViewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> bookItemViewModelList) {
                m_recyclerViewAdapter.bindViewModels(bookItemViewModelList);
            }
        });


    }


    private void setupSearchView() {
        searchView = m_view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    m_searchViewModel.cancelSubscription();
                } else {
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            m_searchViewModel.getArticlesByKeyWork(s);
                            System.out.println(m_searchViewModel.getArticles());
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(m_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                      String articleDate, String articleDescription) {
        Log.d(TAG, "onFav call");
    }
}
