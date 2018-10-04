package com.example.shaochengyang.deltaapp.ui.data.network;

import android.util.Log;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.City;
import com.example.shaochengyang.deltaapp.ui.data.model.Route;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHelper implements INetworkHelper{
    private static final String TAG = "NetworkHelper";
    ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

    @Override
    public void getSeatInformation(IDataManager.onSeatInformationListener listener) {

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

    @Override
    public void getCityInformation(IDataManager.onCityInformationListener listener) {

        Call<City> call = apiService.getCityInformation();
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                City city = response.body();
                Log.d(TAG, "onResponse: " + city.toString());
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());

            }
        });
    }

    @Override
    public void getRouteId(IDataManager.onRouteIdListener listener) {
        Call<Route> call = apiService.getRouteId(41.914196, -88.308685,
                40.73061, -73.935242);
        call.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                Route route = response.body();
                Log.d(TAG, "onResponse: " + route.toString());
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void getBusInformation(IDataManager.onBusInformationListener listener) {
        Call<BusInformation> call = apiService.getBusInformation(2);
        call.enqueue(new Callback<BusInformation>() {
            @Override
            public void onResponse(Call<BusInformation> call, Response<BusInformation> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                BusInformation busInformation = response.body();
                Log.d(TAG, "onResponse: " + busInformation.toString());
            }

            @Override
            public void onFailure(Call<BusInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());
            }
        });
    }


}
