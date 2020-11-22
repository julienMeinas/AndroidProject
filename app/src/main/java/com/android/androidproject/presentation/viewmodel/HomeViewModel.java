package com.android.androidproject.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.androidproject.data.api.model.NicePlace;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace >> m_NicePlaces;

    public LiveData<List<NicePlace>> getNicePlaces() {
        return this.m_NicePlaces;
    }
}
