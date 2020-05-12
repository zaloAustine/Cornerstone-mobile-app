package com.zalocoders.cornerstonekangemi.viewModels;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Network.NetworkService;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.models.CommonResponse;
import com.zalocoders.cornerstonekangemi.models.PayItem;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    NetworkService mNetworkService;
    MutableLiveData<CommonResponse> mPayMutableLiveData;





    public MutableLiveData<CommonResponse> paymentRecord(PayItem item){

        mNetworkService = NetworkService.getInstance();

        mPayMutableLiveData = new MutableLiveData<>();

        mNetworkService.getAPI().PostRecord(item).enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                mPayMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {

            }
        });



        return mPayMutableLiveData;
    }






}
