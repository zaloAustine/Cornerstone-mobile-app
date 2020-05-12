package com.zalocoders.cornerstonekangemi.fragments;

import android.content.Context;
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


import com.zalocoders.cornerstonekangemi.Adapters.NewsAdapter;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.viewModels.FeedViewModel;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;
    NewsAdapter mNewsAdapter;
    View v;
    RecyclerView news_recyclerview;
    SwipeRefreshLayout swipeRefreshLayout;
    Context mContext;
    List<News> mNews;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.feed_fragment, container, false);
        mNewsAdapter = new NewsAdapter(mContext,mNews);

        mContext = container.getContext();
        mNews = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(mContext,mNews);

        mViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        setUpRecyclerView();

        swipeRefresh();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    public FeedFragment() {

    }


    private void setUpRecyclerView(){
        news_recyclerview = v.findViewById(R.id.news_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        news_recyclerview.setLayoutManager(linearLayoutManager);
        news_recyclerview.setAdapter(mNewsAdapter);
        loadData();

    }

    private void loadData() {
        mViewModel.getNews().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                mNews.clear();
                mNews.addAll(news);
                mNewsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }


    private void swipeRefresh(){
        swipeRefreshLayout = v.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });



    }
}
