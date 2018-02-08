package com.example.franciscoandrade.mtastatus.Networking;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.franciscoandrade.mtastatus.DataBase_Helper;

/**
 * Created by bobbybah on 2/6/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkJob extends JobService {

   private static final String TAG = NetworkJob.class.getSimpleName();


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: on start called");

        DataBase_Helper dbHelper = new DataBase_Helper(this);
        dbHelper.makeAndFillDatabase();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: ~~~~~~job terminated~~~~~~~");
        return true;
    }
}
