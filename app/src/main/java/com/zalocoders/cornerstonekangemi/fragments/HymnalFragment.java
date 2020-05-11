package com.zalocoders.cornerstonekangemi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.zalocoders.cornerstonekangemi.Adapters.HymnalAdapter;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.Utils.Utils;
import com.zalocoders.cornerstonekangemi.models.Hymnal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HymnalFragment extends Fragment {
    private Context mContext;
    List<String> hymnal = new ArrayList<>();
    View v;
    EditText inputSearch;
    RecyclerView songsRecycler;
    ArrayAdapter<String> arrayAdapter;
    List<Hymnal> mHymnalList;
    HymnalAdapter mHymnalAdapter;


    public HymnalFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_hymnal, container, false);
        mContext = container.getContext();
        mHymnalList = new ArrayList<>();

        loadTitles();
        Populate();
        searchSongs();

         return v;
    }


    public void Populate(){

        mHymnalAdapter = new HymnalAdapter(mContext,mHymnalList);

        songsRecycler  = v.findViewById(R.id.song);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        songsRecycler.setLayoutManager(manager);

        songsRecycler.setAdapter(mHymnalAdapter);
        mHymnalAdapter.notifyDataSetChanged();


    }

    public void Search(String number){

        BufferedReader reader = null;
        String filename = number+".txt";

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open(filename.trim())));

           String mLine;
            String body;
            StringBuilder builder = new StringBuilder();

            mLine = reader.readLine();


            while ((body = reader.readLine()) != null) {
               builder.append(body);
            }

            //mapping titles with text
            Utils.getInstance().put(mLine.trim().toLowerCase(),builder.toString());


            //loading Hymnal in o list for recycler view
            Hymnal h = new Hymnal();
            h.setTitle(mLine);
            h.setDescription(builder.toString());
            mHymnalList.add(h);


        } catch (IOException e) {
//            Toast.makeText(mContext.getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }


        }

    }



    public void loadTitles(){
        String si;
        for(int i =1;i<=695;i++){
            si = String.valueOf(i);

            if(si.length()==1){
                si = "00"+si;
            }
            if(si.length()==2){
                si = "0"+si;
            }


            Search(si);

        }

    }

public void searchSongs(){

    inputSearch = v.findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence si, int start, int before, int count) {

                if(si.length()==1){
                    si = "00"+si;
                }
                if(si.length()==2){
                    si = "0"+si;
                }
                mHymnalAdapter.getFilter().filter(si);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
}

}
