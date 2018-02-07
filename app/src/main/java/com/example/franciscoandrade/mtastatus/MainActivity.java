package com.example.franciscoandrade.mtastatus;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.franciscoandrade.mtastatus.Networking.MTA_Service;
import com.example.franciscoandrade.mtastatus.Networking.RetrofitClient;
import com.example.franciscoandrade.mtastatus.database.AppDatabase;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;
import com.example.franciscoandrade.mtastatus.model.MTA_Stations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "HELP!!";
    private MTA_Service mtaService;
    private AppDatabase db;
    private List<StationsEntity> stationsEntityList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolBar("MTA STATUS", false);

        stationsEntityList = new ArrayList<>();


        API_Caller();


    }

    private void checkIfDBIsUpdated() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<StationsEntity> allStations;

                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "Stations").build();

                allStations = db.stationsDao().getALL();
                Log.d(TAG, "size of stations list: " + String.valueOf(allStations.size()));
                for (StationsEntity s : allStations) {
                    Log.d(TAG, "Stations have been saved: ID:" + s.getStationID() + " Name: " + s.getStationName());
                }
                db.close();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void API_Caller(){


        mtaService = RetrofitClient.getRetrofit("http://mtaapi.herokuapp.com/")
                .create(MTA_Service.class);

        Call<MTA_Stations> getStations = mtaService.getResultList();
        getStations.enqueue(new Callback<MTA_Stations>() {
            @Override
            public void onResponse(Call<MTA_Stations> call, Response<MTA_Stations> response) {
                if(response.isSuccessful()){
                    MTA_Stations mta_stations;
                    mta_stations = response.body();
                    Log.d(TAG, "onResponse: " + mta_stations.getResult().get(0).getId());
                    for (MTA_Stations.Results r: mta_stations.getResult()) {
                        stationsEntityList.add(new StationsEntity(r.getId(),r.getName()));
                    }

                    updateDatabase();
                    Log.d(TAG, "onResponse: " + mta_stations);
                    checkIfDBIsUpdated();
                }
            }

            @Override
            public void onFailure(Call<MTA_Stations> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });
    }

    private void updateDatabase() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "Stations").build();

                db.stationsDao().insertAll(stationsEntityList.toArray(new StationsEntity[stationsEntityList.size()]));
                db.close();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
