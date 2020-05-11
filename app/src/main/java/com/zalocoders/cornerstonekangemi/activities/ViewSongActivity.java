package com.zalocoders.cornerstonekangemi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.zalocoders.cornerstonekangemi.R;

import java.util.Arrays;

public class ViewSongActivity extends AppCompatActivity {
    String descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getValues();


    }



    public void getValues(){

        Intent i =getIntent();
        String titleText = i.getStringExtra("title");
         descriptionText = i.getStringExtra("description");

         arrangeSong();


        TextView title  = findViewById(R.id.title);
        title.setText(titleText);
        TextView description  = findViewById(R.id.description);
        description.setText(descriptionText.replaceAll("0*[1-9]\\d*","\n\n\n"));

    }

    private String arrangeSong() {
        String finalString =  "";

    char[] chars = descriptionText.toCharArray();

        Log.e("song",Arrays.toString(chars));

        finalString.replaceAll("0*[1-9]\\d*","\n\n\n");

        for(char ch:chars){

        }


    return finalString;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
              Intent i = new Intent(ViewSongActivity.this,MainActivity.class);
              i.putExtra("hymnal",2);
              startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
