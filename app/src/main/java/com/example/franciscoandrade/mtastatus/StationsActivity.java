package com.example.franciscoandrade.mtastatus;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.franciscoandrade.mtastatus.database.AppDatabase;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;

import java.util.ArrayList;
import java.util.List;

public class StationsActivity extends AppCompatActivity {

    private TextView lineLetterTV, lineDetailsTV;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private List<StationsEntity> lineList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        showToolBar("STATIONS", true);

        lineLetterTV = findViewById(R.id.lineLetterTV);
        lineDetailsTV = findViewById(R.id.lineDetailsTV);
        recyclerView = findViewById(R.id.stations_recyclerview);
        Intent intent = getIntent();
        final String line = intent.getStringExtra("station_line");
        lineList = new ArrayList<>();
        lineLetterTV.setText(line);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Stations").build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<StationsEntity> fullList = db.stationsDao().getALL();
                for (StationsEntity s : fullList) {
                    if (s.getStationID().substring(0,1).equals(line)){
                        lineList.add(s);
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinesAdapter linesAdapter = new LinesAdapter(this);
        linesAdapter.addLines(lineList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(linesAdapter);

    }
    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
