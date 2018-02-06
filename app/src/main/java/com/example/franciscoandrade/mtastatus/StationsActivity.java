package com.example.franciscoandrade.mtastatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class StationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        showToolBar("STATIONS", true);
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
