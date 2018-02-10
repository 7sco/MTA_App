package com.example.franciscoandrade.mtastatus;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.franciscoandrade.mtastatus.controller.LinesAdapter;
import com.example.franciscoandrade.mtastatus.database.AppDatabase;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "HELP!!";
    private AppDatabase db;
    private List<StationsEntity> stationsEntityList;
    private RecyclerView linesRecyclerHolder;
    private LinesAdapter linesAdapter;
    private Set<Character> newSet;
    private List<Character> newList;
    private static final int NOTIFICATION_ID = 5555;
    private static final String NOTIFICATION_CHANNEL = "notification_channel";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.stationicon)
                .setContentTitle("MTA Notification")
                .setContentText("Current Subway Lines Running").setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        MTA_JobScheduler.start(getApplicationContext());

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                makeDBAfterJob();
            }
        }, 1000);

        showToolBar("MTA STATUS", false);
    }

    private void makeDBAfterJob() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "Stations").build();
                stationsEntityList = db.stationsDao().getALL();
                newSet = new HashSet<>();
                for (StationsEntity s : stationsEntityList) {
                    Log.d(TAG, "Stations have been saved: ID:" + s.getStationID() + " Name: " + s.getStationName());
                    newSet.add(s.getStationID().charAt(0));
                }
                newList = new ArrayList<>();
                newList.addAll(newSet);
                // Get a handler that can be used to post to the main thread
                Handler mainHandler = new Handler(getApplicationContext().getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (stationsEntityList.size() > 0) {
                            linesAdapter = new LinesAdapter(MainActivity.this);
                            linesAdapter.addLines(stationsEntityList, newList);

                            linesRecyclerHolder = (RecyclerView) findViewById(R.id.linesRecyclerHolder);
                            linesRecyclerHolder.setAdapter(linesAdapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            linesRecyclerHolder.setLayoutManager(linearLayoutManager);
                        }
                    } // This is your code
                };
                mainHandler.post(myRunnable);

                db.close();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {


        }
    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public void onClick(View view) {


        Intent intent = new Intent(this, CurrentLocationActivity.class);
        startActivity(intent);
    }
}
