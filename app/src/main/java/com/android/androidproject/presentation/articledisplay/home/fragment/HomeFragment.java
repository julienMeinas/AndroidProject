package com.android.androidproject.presentation.articledisplay.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.data.di.FakeDependencyInjection;
import com.android.androidproject.presentation.articledisplay.home.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.home.adapter.RecyclerViewAdapter;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

public class HomeFragment extends Fragment {
    private static HomeFragment singleton = null;
    private static final String TAG = "HomeFragment";
    private View m_view;
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();

    private HomeViewModel m_homeViewModel;
    private RecyclerViewAdapter m_recyclerViewAdapter;

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
        initRecyclerView();
    }



    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        m_recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(m_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerViewModels();
    }

    private void registerViewModels() {
        Log.d(TAG, "registerViewModels call");
        m_homeViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(HomeViewModel.class);
        m_homeViewModel.getBestArticles();
        m_homeViewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {

            @Override
            public void onChanged(List<ArticleViewItem> bookItemViewModelList) {
                m_recyclerViewAdapter.bindViewModels(bookItemViewModelList);
            }
        });



    }

}
