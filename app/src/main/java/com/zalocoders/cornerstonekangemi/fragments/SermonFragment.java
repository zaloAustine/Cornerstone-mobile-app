package com.zalocoders.cornerstonekangemi.fragments;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.zalocoders.cornerstonekangemi.Adapters.SermonAdapter;
import com.zalocoders.cornerstonekangemi.Models.Sermon;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.viewModels.SermonViewModel;

import java.util.ArrayList;
import java.util.List;

public class SermonFragment extends Fragment {

    private SermonViewModel mViewModel;
    View v;
    Context mContext;
    RecyclerView sermons;
    List<Sermon> mSermonList;
    SermonAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public static SermonFragment newInstance() {
        return new SermonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.sermon_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(SermonViewModel.class);
        mContext = container.getContext();

        mSermonList = new ArrayList<>();

        setUpRecyclerView();

        configureVideo();

        swipeRefresh();


        return v;
    }

    private void setUpRecyclerView() {
        sermons = v.findViewById(R.id.sermons);
         adapter = new SermonAdapter(mContext,mSermonList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        sermons.setLayoutManager(manager);

        LoadSermons();

        sermons.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


    public void LoadSermons(){

        mViewModel.getSermons().observe(getViewLifecycleOwner(), new Observer<List<Sermon>>() {
            @Override
            public void onChanged(List<Sermon> sermons) {
                mSermonList.clear();
                mSermonList.addAll( sermons);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);


            }
        });
    }


    private void configureVideo(){
        // Make sure to use the correct VideoView import
       VideoView videoView = v.findViewById(R.id.video_view);
        videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
              //  videoView.start();
            }
        });

        videoView.setVideoURI(Uri.parse("http://cornerstonesda.zalocoders.co.ke/uploads/sermon.mp4"));

    }

    private void swipeRefresh(){
        swipeRefreshLayout = v.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadSermons();
            }
        });



    }

}
