package com.zalocoders.cornerstonekangemi.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.zalocoders.cornerstonekangemi.Models.Sermon;
import com.zalocoders.cornerstonekangemi.Network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SermonViewModel extends ViewModel {
MutableLiveData<List<Sermon>> mListMutableLiveData;
NetworkService mNetworkService;
    List<Sermon> mSermonList = new ArrayList<>();
    // TODO: Implement the ViewModel

    public MutableLiveData<List<Sermon>> getSermons(){

        //Network service
        mNetworkService = NetworkService.getInstance();

        mListMutableLiveData = new MutableLiveData<>();

        mNetworkService.getAPI().getSermons().enqueue(new Callback<List<Sermon>>() {
            @Override
            public void onResponse(Call<List<Sermon>> call, Response<List<Sermon>> response) {
                mListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Sermon>> call, Throwable t) {

            }
        });



        return mListMutableLiveData;
    }
}