package com.zalocoders.cornerstonekangemi.Network;


import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Models.Sermon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CornerstoneApi {


    @GET("sermons")
    Call<List<Sermon>> getSermons();


    @GET("events")
    Call<List<News>> getEvents();








}
