package com.zalocoders.cornerstonekangemi.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.card.MaterialCardView;
import com.zalocoders.cornerstonekangemi.Network.NetworkService;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.models.Discover;
import com.zalocoders.cornerstonekangemi.viewModels.DiscoverViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscoverFragment extends Fragment {

   private DiscoverViewModel mViewModel;
   NetworkService mNetworkService;
   MaterialCardView bulletcard,zoocard;
    View v;

   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
     v =  inflater.inflate(R.layout.discover_fragment, container, false);

     mNetworkService = NetworkService.getInstance();


       LoadData();
     return v;
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       mViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
       // TODO: Use the ViewModel
   }

    public DiscoverFragment() {

    }



    public void LoadData(){
       mNetworkService.getAPI().getZoom().enqueue(new Callback<Discover>() {
           @Override
           public void onResponse(Call<Discover> call, Response<Discover> response) {
               zoocard = v.findViewById(R.id.zoocard);
               zoocard.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData()));
                       startActivity(browserIntent);
                   }
               });

           }

           @Override
           public void onFailure(Call<Discover> call, Throwable t) {

           }
       });

       mNetworkService.getAPI().getBuletin().enqueue(new Callback<Discover>() {
           @Override
           public void onResponse(Call<Discover> call, Response<Discover> response) {

               bulletcard = v.findViewById(R.id.bulletcard);
               bulletcard.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cornerstonesda.zalocoders.co.ke/uploads/"+response.body().getData()));
                       startActivity(browserIntent);
                   }
               });

           }

           @Override
           public void onFailure(Call<Discover> call, Throwable t) {

           }
       });
    }
}
