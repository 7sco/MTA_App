package com.example.franciscoandrade.mtastatus.Networking;

import com.example.franciscoandrade.mtastatus.model.MTA_Stations;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bobbybah on 2/6/18.
 */

public interface MTA_Service {

    @GET("stations")
    Call<MTA_Stations> getResultList();
}
