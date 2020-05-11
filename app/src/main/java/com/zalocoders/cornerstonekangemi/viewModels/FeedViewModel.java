package com.zalocoders.cornerstonekangemi.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    NetworkService mNetworkService;
    MutableLiveData<List<News>> mNewsMutableLiveData;




    public MutableLiveData<List<News>> getNews(){

        mNetworkService = NetworkService.getInstance();

        mNewsMutableLiveData = new MutableLiveData<>();

        mNetworkService.getAPI().getEvents().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                mNewsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });


        return mNewsMutableLiveData;
    }
}
