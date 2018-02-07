package com.example.franciscoandrade.mtastatus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.franciscoandrade.mtastatus.Networking.MTA_Service;
import com.example.franciscoandrade.mtastatus.Networking.RetrofitClient;
import com.example.franciscoandrade.mtastatus.model.MTA_Stations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "HELP!!";
    static MTA_Service mtaService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolBar("MTA STATUS", false);

        API_Caller();
    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mainBtn:
                Intent intent = new Intent(this, CurrentLocationActivity.class);
                startActivity(intent);
                break;
                default:
                    Intent intent2 = new Intent(this, StationsActivity.class);
                    startActivity(intent2);
                    break;

        }

    }

    public static void API_Caller(){

        mtaService = RetrofitClient.getRetrofit("http://mtaapi.herokuapp.com/")
                .create(MTA_Service.class);

        Call<MTA_Stations> getStations = mtaService.getResultList();
        getStations.enqueue(new Callback<MTA_Stations>() {
            @Override
            public void onResponse(Call<MTA_Stations> call, Response<MTA_Stations> response) {
                if(response.isSuccessful()){
                    MTA_Stations mta_stations;
                    mta_stations = response.body();

                    Log.d(TAG, "onResponse: " + mta_stations);
                }
            }

            @Override
            public void onFailure(Call<MTA_Stations> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });
    }
}
