package com.example.shaochengyang.deltaapp.ui.data.network.comparedemo;

import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService2 {

    //http://demo7832595.mockable.io/traveldomain/compareroutes
    @GET("compareroutes")
    Call<ArrayResponse> getDemo();
}
