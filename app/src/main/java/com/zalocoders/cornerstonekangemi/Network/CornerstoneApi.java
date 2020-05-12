package com.zalocoders.cornerstonekangemi.Network;


import com.google.gson.JsonObject;
import com.zalocoders.cornerstonekangemi.Models.Item;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Models.Sermon;
import com.zalocoders.cornerstonekangemi.models.CommonResponse;
import com.zalocoders.cornerstonekangemi.models.PayItem;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CornerstoneApi {


    @GET("sermons")
    Call<List<Sermon>> getSermons();


    @GET("events")
    Call<List<News>> getEvents();


    @GET("items")
    Call<List<Item>> getItems();


    @POST("postRecord2")
    Call<CommonResponse> PostRecord(@Body PayItem item);


}
