package com.example.franciscoandrade.mtastatus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolBar("MTA STATUS", false);
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
}
