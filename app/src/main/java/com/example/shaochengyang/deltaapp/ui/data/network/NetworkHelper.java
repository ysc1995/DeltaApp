package com.example.shaochengyang.deltaapp.ui.data.network;

import android.util.Log;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHelper implements INetworkHelper{
    private static final String TAG = "NetworkHelper";

    @Override
    public void getSeatInformation(IDataManager.onSeatInformationListener listener) {
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        Call<SeatInformation> call = apiService.getSeatInformation(102);
        call.enqueue(new Callback<SeatInformation>() {
            @Override
            public void onResponse(Call<SeatInformation> call, Response<SeatInformation> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                SeatInformation seatInformation = response.body();
                Log.d(TAG, "onResponse: " + seatInformation.getSeatinformation());
            }

            @Override
            public void onFailure(Call<SeatInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());

            }
        });
    }
}
