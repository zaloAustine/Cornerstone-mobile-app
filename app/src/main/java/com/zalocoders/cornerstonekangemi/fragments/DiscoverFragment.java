package com.zalocoders.cornerstonekangemi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.viewModels.DiscoverViewModel;


public class DiscoverFragment extends Fragment {

   private DiscoverViewModel mViewModel;


   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.discover_fragment, container, false);
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       mViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
       // TODO: Use the ViewModel
   }

    public DiscoverFragment() {

    }
}
