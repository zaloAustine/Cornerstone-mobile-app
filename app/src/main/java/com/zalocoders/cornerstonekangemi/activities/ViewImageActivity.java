package com.zalocoders.cornerstonekangemi.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.zalocoders.cornerstonekangemi.Adapters.SlidingImage_Adapter;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewImageActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    List<String> IMAGES;
    String description;

    private ArrayList<String> ImagesArray = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        IMAGES = new ArrayList<>();

        loadImages();
        init();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void loadImages() {
        Intent intent = getIntent();
        String news = intent.getStringExtra("News");

        Gson g = new Gson();
        News news1 = g.fromJson(news,News.class);

        Log.e("img",news);

        description = news1.getDescription();
        IMAGES.addAll(Arrays.asList("http://cornerstonesda.zalocoders.co.ke/uploads/"+news1.getImageUrls()));

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {

        ImagesArray.addAll(IMAGES);

        mPager = (ViewPager) findViewById(R.id.pager);


        SlidingImage_Adapter adapter = new SlidingImage_Adapter(ImagesArray,ViewImageActivity.this,description);
        mPager.setAdapter(adapter);
        adapter.setDescriptionText(description);


        final float density = getResources().getDisplayMetrics().density;


        NUM_PAGES = IMAGES.size()-1;

        if (currentPage == NUM_PAGES) {
            currentPage = 0;
        }
        mPager.setCurrentItem(currentPage++, true);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent i = new Intent(ViewImageActivity.this,MainActivity.class);
                i.putExtra("event",2);
                startActivity(i);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
