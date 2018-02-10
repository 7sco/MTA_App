package com.example.franciscoandrade.mtastatus;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.franciscoandrade.mtastatus.Networking.NetworkJob;

/**
 * Created by bobbybah on 2/7/18.
 */

public class MTA_JobScheduler {

    public final static int NETWORK_JOB_ID = 0;
    private static Context context;



    @RequiresApi(api = Build.VERSION_CODES.N)

    public static void start(Context applicationContext){
        JobScheduler jobScheduler = (JobScheduler) applicationContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        //add buildJob Here
        buildNetworkJob(applicationContext, jobScheduler);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)

    private static void buildNetworkJob(Context appContext, JobScheduler jobScheduler){

        JobInfo.Builder networkJob = new JobInfo
                .Builder(NETWORK_JOB_ID, new ComponentName(appContext, NetworkJob.class))
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(1000,86400000);
               jobScheduler.schedule(networkJob.build());
    }

}
