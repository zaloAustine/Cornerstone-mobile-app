package com.zalocoders.cornerstonekangemi.activities;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.downloader.Progress;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zalocoders.cornerstonekangemi.Models.Item;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.models.Commands;
import com.zalocoders.cornerstonekangemi.models.CommonResponse;
import com.zalocoders.cornerstonekangemi.models.PayItem;
import com.zalocoders.cornerstonekangemi.viewModels.PaymentViewModel;
import com.zalocoders.cornerstonekangemi.viewModels.SermonViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GivingActivity extends AppCompatActivity {

   EditText edittextItem1,name,email;
    MaterialSpinner spinnerItem1;
    List<String> items = new ArrayList<>();
    Button give;
    public static AlertDialog d;

    SermonViewModel mSermonViewModel ;
    PaymentViewModel mPaymentViewModel;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving);

        mSermonViewModel = ViewModelProviders.of(this).get(SermonViewModel.class);
        mPaymentViewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);


        spinnerItem1 = findViewById(R.id.spinnerItem1);

        edittextItem1 = findViewById(R.id.edittextItem1);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);


        give = findViewById(R.id.give);
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialog(GivingActivity.this, "Giving", "Dou you want to pay" + edittextItem1.getText().toString() + " to cornerstone sda church", new Commands() {
                    @Override
                    public void execute() {

                        try {
                            if(validate()&&validate2()){
                                createJson();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },"PAy");


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

    }


    public void genenrateItem(){

        loadItems();
        spinnerItem1.setItems(items);

    }


    public void createJson() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("name",name.getText().toString());
        obj.put("email",email.getText().toString());
        obj.put("item",spinnerItem1.getText());
        obj.put("amount",edittextItem1.getText().toString());


        PayItem pi = new PayItem();
        pi.setAmount(edittextItem1.getText().toString());
        pi.setName(name.getText().toString());
        pi.setEmail(email.getText().toString());
        pi.setItem(spinnerItem1.getText().toString());


        pay(pi);
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

            if(edittextItem1.getText().toString().equals("")){
                Toast.makeText(this,"fill all required",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"pick at least one item",Toast.LENGTH_SHORT).show();
                return false;
            }


        return true;
    }


    public void loadItems(){
        progress2();
        mSermonViewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> itemsList) {

                for(Item i:itemsList){
                    items.add(i.getName());

                }

                pd.dismiss();
            }
        });
    }


    public void pay(PayItem item){
        progress();
        mPaymentViewModel.paymentRecord(item).observe(this, new Observer<CommonResponse>() {
            @Override
            public void onChanged(CommonResponse responses) {

                pd.dismiss();

                ConfirmationDialog(GivingActivity.this, "Payment Confirmation", responses.getAmount(), new Commands() {
                    @Override
                    public void execute() {
                    startActivity(new Intent(GivingActivity.this,MainActivity.class));
                    }
                });

            }
        });
    }

    public static void OptionDialog(Context context, String title, String message, Commands command, String word){
        MaterialButton submit,cancel;

        LayoutInflater factory = LayoutInflater.from(context);
        View custom_dialog = factory.inflate(R.layout.custom_dialog, null);
        d = new AlertDialog.Builder(context, R.style.NewDialog).setView(custom_dialog).create();
        d.setCancelable(true);



        TextView tx = custom_dialog.findViewById(R.id.message);
        tx.setText(message);

        TextView tl = custom_dialog.findViewById(R.id.title);
        tl.setText(title);
        tx.setText(message);


        d.setView(custom_dialog);

        submit = custom_dialog.findViewById(R.id.submit);
        submit.setText(word);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.execute();
                d.dismiss();

            }
        });

        cancel = custom_dialog.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        try {
            d.show();

        }catch (WindowManager.BadTokenException e){

        }

    }

    public static void ConfirmationDialog(Context context,String title,String message,Commands commands){
        LayoutInflater factory = LayoutInflater.from(context);
        MaterialButton submit;
        View custom_dialog = factory.inflate(R.layout.custom_dialog2, null);
        d = new AlertDialog.Builder(context, R.style.NewDialog).create();
        d.setCancelable(true);



        TextView tx = custom_dialog.findViewById(R.id.message);
        tx.setText(message);

        TextView tl = custom_dialog.findViewById(R.id.title);
        tl.setText(title);
        tx.setText(message);

        submit = custom_dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commands.execute();
                d.dismiss();

            }
        });

        d.setView(custom_dialog);

        try {
            d.show();

        }catch (WindowManager.BadTokenException e){

        }
    }


    public void progress(){
        pd = new ProgressDialog(this);
        pd.setMessage("requesting payment Please wait...");
        pd.show();
    }


    public void progress2(){
        pd = new ProgressDialog(this);
        pd.setMessage("please wait..");
        pd.show();
    }


}
