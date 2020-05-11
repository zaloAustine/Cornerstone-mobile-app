package com.zalocoders.cornerstonekangemi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;
import com.google.gson.Gson;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Models.Sermon;
import com.zalocoders.cornerstonekangemi.R;

import java.util.ArrayList;

public class ListenSermonActivity extends AppCompatActivity {
    JcPlayerView jcplayerView;
    TextView topic,decription;
    ArrayList<JcAudio> jcAudios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_sermon);


        jcplayerView = findViewById(R.id.jcplayerView);

         jcAudios = new ArrayList<>();

        topic = findViewById(R.id.topic);
        decription = findViewById(R.id.description);



        jcplayerView.createNotification();

        jcplayerView.initPlaylist(jcAudios);


        getUrl();
    }


    @Override
    protected void onPause() {
        super.onPause();
        jcplayerView.kill();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        jcplayerView.kill();

    }

    public void getUrl(){
        Intent intent = getIntent();
        String song = intent.getStringExtra("News");

        Gson g = new Gson();
        Sermon news1 = g.fromJson(song, Sermon.class);

        topic.setText(news1.getTopic());
        decription.setText(news1.getDescription());

        jcAudios.add(JcAudio.createFromURL("url audio", "http://cornerstonesda.zalocoders.co.ke/uploads/" +news1.getAudoUrl()));


    }
}
