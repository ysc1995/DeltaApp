package com.example.shaochengyang.deltaapp.ui.data.network.comparedemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    //http://demo7832595.mockable.io/traveldomain/compareroutes
    static Retrofit retrofit;

    //http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102
    final static String BASE_URL ="http://demo7832595.mockable.io/traveldomain/";


    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
