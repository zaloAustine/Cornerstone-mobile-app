package com.zalocoders.cornerstonekangemi.activities;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zalocoders.cornerstonekangemi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GivingActivity extends AppCompatActivity {

   EditText edittextItem1,edittextItem2,name,email;
    MaterialSpinner spinnerItem1,spinnerItem2;
    List<String> items = new ArrayList<>();
    Button give;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving);


        spinnerItem1 = findViewById(R.id.spinnerItem1);
        spinnerItem2 = findViewById(R.id.spinnerItem2);

        edittextItem1 = findViewById(R.id.edittextItem1);
        edittextItem2 = findViewById(R.id.edittextItem2);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);


        give = findViewById(R.id.give);
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(validate()&&validate2()){
                        createJson();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        generateInputs();
        genenrateItem();
    }


    public void generateInputs(){
        spinnerItem1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                edittextItem1.setVisibility(View.VISIBLE);
                edittextItem1.requestFocus();
                edittextItem1.setTag(view.getText());
            }
        });


        spinnerItem2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            edittextItem2.setVisibility(View.VISIBLE);
                edittextItem2.requestFocus();
                edittextItem2.setTag(view.getText());
            }
        });
    }


    public void genenrateItem(){


        for(int i=0;i<=20;i++){

            items.add("Tithe");
            items.add("Giving");
        }

        spinnerItem1.setItems(items);
        spinnerItem2.setItems(items);

    }


    public void createJson() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("name",name.getText().toString());
        obj.put("email",email.getText().toString());
        obj.put("paymentType",spinnerItem1.getText());
        obj.put("amount",edittextItem1.getText().toString());

        JSONObject obj2 = new JSONObject();
        obj2.put("name",name.getText().toString());
        obj2.put("email",email.getText().toString());
        obj2.put("paymentType",spinnerItem2.getText());
        obj2.put("amount",edittextItem2.getText().toString());


        JSONArray array = new JSONArray();
        array.put(obj);
        array.put(obj2);


        Log.e("Final json",array.toString());
    }


    public boolean validate(){
        if(email.getText().toString().equals("")||name.getText().toString().equals("")){

                Toast.makeText(this,"fill all required",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"pick at least one item",Toast.LENGTH_SHORT).show();

            return false;
        }


        return true;
    }

    public boolean validate2(){

            if(edittextItem1.getText().toString().equals("")&&edittextItem2.getText().toString().equals("")){
                Toast.makeText(this,"fill all required",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"pick at least one item",Toast.LENGTH_SHORT).show();
                return false;
            }


        return true;
    }

}
