package com.example.franciscoandrade.mtastatus;

import android.arch.persistence.room.Room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.mtastatus.database.AppDatabase;
import com.example.franciscoandrade.mtastatus.database.StationsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentLocationActivity extends AppCompatActivity {
    private AppDatabase db;
    private List<StationsEntity> lineList;
    List<StationsEntity> fullList;
    SimpleDateFormat simpleDateFormat;
    String time;
    Calendar calander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        showToolBar("CURRENT LOCATION", true);


//        lineList = new ArrayList<>();
//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "Stations").build();
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                 fullList = db.stationsDao().getALL();
////                for (StationsEntity s : fullList) {
////                    if (s.getStationID().substring(0,1).equals(line)) {
////                        lineList.add(s);
////                    }
////                }
//                Log.d("STATIONS", "run: "+fullList.get(0).getStationID());
//                Log.d("STATIONS", "run: "+fullList.get(0).getLongitude());
//                Log.d("STATIONS", "run: "+fullList.get(0).getLatitude());
//                db.close();
//            }
//        });
//        thread.start();

//        try {
//            thread.join();
//            for (StationsEntity c: lineList){
//                Log.d("SECONDD", "onCreate: "+c.getStationID());
//            }
//            Log.d("LATITUDE=", "run: "+fullList.get(1).getLatitude());
//            Log.d("LONGITUDE=", "run: "+fullList.get(2).getLongitude());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String []ids= {"R41S", "R41N"};

//        for (String s: ids) {
//            makeRequestWithOkHttp(s);
//        }
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");

        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        time = simpleDateFormat.format(calander.getTime());


        Log.d("DATE", "onCreate: "+currentDateTimeString);
        Log.d("DATE", "onCreate: "+time);

        makeRequestWithOkHttp("R41S");

    }

    private void makeRequestWithOkHttp(String id) {
        String url = "http://mtaapi.herokuapp.com/api?id="+id;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FAILL==", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("RESPONSE", "onResponse: "+response.body().toString());
                String jsonData = response.body().string();
                //Log.d("JSON", "onResponse: "+jsonData);
                JSONArray jsonArray= null;

                try {
                    JSONObject jsonObject= new JSONObject(jsonData);
                    JSONObject jsonObject1= new JSONObject(String.valueOf(jsonObject.getJSONObject("result")));
//                    Log.d("RESULT=", "onResponse: "+jsonObject1);
//                    Log.d("RESULT=", "onResponse: "+jsonObject1.getJSONArray("arrivals"));
                    jsonArray=jsonObject1.getJSONArray("arrivals");
                    List<String> listTimes= new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        listTimes.add(jsonArray.get(i).toString());
                        Log.d("RESULT=", "onResponse: "+jsonArray.get(i).toString());
                    }




//

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });



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
