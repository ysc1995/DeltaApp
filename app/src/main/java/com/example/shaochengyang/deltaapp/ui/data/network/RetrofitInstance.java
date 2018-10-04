package com.example.shaochengyang.deltaapp.ui.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    static Retrofit retrofit;

    //http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102
    final static String BASE_URL_SEATINFO ="http://rjtmobile.com/aamir/otr/android-app/";


    public static Retrofit getRetrofitInstance() {

        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_SEATINFO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
