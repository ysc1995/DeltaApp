package com.example.shaochengyang.deltaapp.ui.data.network;

import android.util.Log;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.ui.bookflight.oneway.OneWayFragment;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.City;
import com.example.shaochengyang.deltaapp.ui.data.model.Route;
import com.example.shaochengyang.deltaapp.ui.data.model.RouteItem;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;
import com.example.shaochengyang.deltaapp.ui.data.network.model.RItem;

import java.util.ArrayList;
import java.util.List;

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
    public void getCityInformation(final IDataManager.onCityInformationListener listener) {

        Call<City> call = apiService.getCityInformation();
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                City city = response.body();
                for(int i = 0 ; i < city.getCity().size(); i ++){
                    String cityName = city.getCity().get(i).getCityname();
                    String cityLati = city.getCity().get(i).getCitylatitude();
                    String cityLong = city.getCity().get(i).getCitylongtitude();

                    listener.passCityInfo(cityName,cityLati,cityLong);
                }
                Log.d(TAG, "onResponse: " + city.toString());
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());

            }
        });
    }



    @Override
    public void getBusInformation(final IDataManager.onBusInformationListener listener) {
        Call<BusInformation> call = apiService.getBusInformation(2);
        call.enqueue(new Callback<BusInformation>() {
            @Override
            public void onResponse(Call<BusInformation> call, Response<BusInformation> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                BusInformation busInformation = response.body();
                Log.d(TAG, "onResponse: " + busInformation.toString());

                listener.bindFlightListToView(busInformation);

            }

            @Override
            public void onFailure(Call<BusInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void getRouteId(final IDataManager.onRouteIdListener onRouteIdListener, String fromCityLati, String fromCityLati1, String toCityLati, String toCityLong) {
        double fcLati = Double.parseDouble(fromCityLati);
        double fcLong = Double.parseDouble(fromCityLati1);
        double tcLati = Double.parseDouble(toCityLati);
        double tcLong = Double.parseDouble(toCityLong);

        final List<RItem> rItemList = new ArrayList<>();
        Call<Route> call = apiService.getRouteId(fcLati, fcLong,
                tcLati, tcLong);
        call.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                Route route = response.body();
                for(int i = 0 ; i < route.getRoute().size();i++) {
                    String rid = route.getRoute().get(i).getId();
                    String rname = route.getRoute().get(i).getRoutename();
                    String rstart = route.getRoute().get(i).getRouteStartfrom();
                    String rdestination = route.getRoute().get(i).getRouteDestination();
                    RItem routeItem = new RItem(rid,rname,rstart,rdestination);
                    rItemList.add(routeItem);
                }
                onRouteIdListener.passRouteInfo(rItemList);



                Log.d(TAG, "onResponse: " + route.toString());
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());
            }
        });


    }




}
