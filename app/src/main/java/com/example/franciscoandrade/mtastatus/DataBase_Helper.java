package com.example.franciscoandrade.mtastatus;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.franciscoandrade.mtastatus.Networking.MTA_Service;
import com.example.franciscoandrade.mtastatus.Networking.RetrofitClient;
import com.example.franciscoandrade.mtastatus.database.AppDatabase;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;
import com.example.franciscoandrade.mtastatus.model.MTA_Stations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bobbybah on 2/7/18.
 */

public class DataBase_Helper {

    private AppDatabase db;
    private static final String TAG = "HELP!!";
    private MTA_Service mtaService;
    private List<StationsEntity> stationsEntityList;
    private Context context;

    public DataBase_Helper(Context context) {
        this.context = context;
    }

    public void makeAndFillDatabase() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(context,
                        AppDatabase.class, "Stations").build();

                stationsEntityList = db.stationsDao().getALL();
                if (stationsEntityList.size() > 0) {
                    Log.d(TAG, "database is filled");
                    Log.d(TAG, "size of stations list: " + String.valueOf(stationsEntityList.size()));
                    for (StationsEntity s : stationsEntityList) {
                        Log.d(TAG, "Stations have been saved: ID:" + s.getStationID() + " Name: " + s.getStationName());
                    }
                    db.close();
                } else {
                    API_Caller();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}

    public void API_Caller() {

        db.close();
        mtaService = RetrofitClient.getRetrofit("http://mtaapi.herokuapp.com/")
                .create(MTA_Service.class);

        Call<MTA_Stations> getStations = mtaService.getResultList();
        getStations.enqueue(new Callback<MTA_Stations>() {
            @Override
            public void onResponse(Call<MTA_Stations> call, Response<MTA_Stations> response) {
                if (response.isSuccessful()) {
                    MTA_Stations mta_stations;
                    mta_stations = response.body();
                    Log.d(TAG, "onResponse: " + mta_stations.getResult().get(0).getId());
                    for (MTA_Stations.Results r : mta_stations.getResult()) {
                        stationsEntityList.add(new StationsEntity(r.getId(), r.getName()));

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
                db = Room.databaseBuilder(context,
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

    private void checkIfDBIsUpdated() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<StationsEntity> allStations;

                db = Room.databaseBuilder(context,
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

}

