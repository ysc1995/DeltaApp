package com.example.shaochengyang.deltaapp.ui.data.network;

import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102
    @GET("seatinfo.php")
    Call<SeatInformation> getSeatInformation(@Query("busid") int busid);



}
