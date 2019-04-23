package com.example.ysi4ek.taskandroid.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ysi4ek.taskandroid.api.ApiService;
import com.example.ysi4ek.taskandroid.R;
import com.example.ysi4ek.taskandroid.model.RateList;
import com.example.ysi4ek.taskandroid.model.Rates;
import com.example.ysi4ek.taskandroid.adapter.RatesAdapter;
import com.example.ysi4ek.taskandroid.api.RetroClient;
import com.example.ysi4ek.taskandroid.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private View parentView;

    private ArrayList<Rates> ratesList;
    private RatesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ratesList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, ratesList.get(position).getCurName() + " => " + ratesList.get(position).getCurID(), Snackbar.LENGTH_LONG).show();
            }
        });

        Toast toast =
                Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {


                if (InternetConnection.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;

                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiSevice();


                    Call<RateList> call = api.getMyJSON();


                    call.enqueue(new Callback<RateList>() {
                        @Override
                        public void onResponse(Call<RateList> call, Response<RateList> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if (response.isSuccessful()) {

                                ratesList = response.body().getRates();


                                adapter = new RatesAdapter(MainActivity.this, ratesList);
                                listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RateList> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}

