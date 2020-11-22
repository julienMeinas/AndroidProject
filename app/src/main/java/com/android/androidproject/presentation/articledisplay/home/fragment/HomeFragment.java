package com.android.androidproject.presentation.articledisplay.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.data.api.model.NicePlace;
import com.android.androidproject.presentation.articledisplay.home.adapter.RecyclerViewAdapter;
import com.android.androidproject.presentation.viewmodel.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static HomeFragment singleton = null;
    private static final String TAG = "HomeFragment";

    private FloatingActionButton m_Fab;
    private ProgressBar m_ProgressBar;
    private View m_view;
    private ArrayList<NicePlace> m_nicePlace = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private HomeViewModel m_HomeViewModel;

    private HomeFragment(){
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
        Log.d(TAG, "onCreateView: started");
        m_view = inflater.inflate(R.layout.fragment_home, container, false);
        m_Fab = m_view.findViewById(R.id.fab);
        m_ProgressBar = m_view.findViewById(R.id.progress_bar);
        //m_HomeViewModel = new ViewModelProvider(requireActivity(), )

        initImageBitmaps();
        return m_view;
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        this.m_nicePlace.add(new NicePlace("Havasu Falls", "https://i.imgur.com/ZcLLrkY.jpg"));

        this.m_nicePlace.add(new NicePlace("Trondheim", "https://i.redd.it/tpsnoz5bzo501.jpg"));

        this.m_nicePlace.add(new NicePlace("Portugal", "https://i.redd.it/qn7f9oqu7o501.jpg"));

        this.m_nicePlace.add(new NicePlace("Rocky Mountain National Park", "https://i.redd.it/j6myfqglup501.jpg"));

        this.m_nicePlace.add(new NicePlace("Mahahual", "https://i.redd.it/0h2gm1ix6p501.jpg"));

        this.m_nicePlace.add(new NicePlace("Frozen Lake", "https://i.redd.it/k98uzl68eh501.jpg"));

        this.m_nicePlace.add(new NicePlace("White Sands Desert", "https://i.redd.it/glin0nwndo501.jpg"));

        this.m_nicePlace.add(new NicePlace("Austrailia", "https://i.redd.it/obx4zydshg601.jpg"));

        this.m_nicePlace.add(new NicePlace("Washington", "https://i.imgur.com/ZcLLrkY.jpg"));

        initRecyclerView();
    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView call");
        RecyclerView recyclerView = m_view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(m_nicePlace, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
