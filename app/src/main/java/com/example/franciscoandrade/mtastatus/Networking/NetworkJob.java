package com.example.franciscoandrade.mtastatus.Networking;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by bobbybah on 2/6/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkJob extends JobService {

   private static final String TAG = NetworkJob.class.getSimpleName();


    @Override
    public boolean onStartJob(JobParameters params) {


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
