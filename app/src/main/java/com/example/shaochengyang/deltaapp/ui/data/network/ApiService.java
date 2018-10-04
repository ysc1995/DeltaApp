package com.example.shaochengyang.deltaapp.ui.data.network;

import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.City;
import com.example.shaochengyang.deltaapp.ui.data.model.Route;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102
    @GET("seatinfo.php")
    Call<SeatInformation> getSeatInformation(@Query("busid") int busid);

    //http://rjtmobile.com/aamir/otr/android-app/city.php?
    @GET("city.php")
    Call<City> getCityInformation();

    //http://rjtmobile.com/aamir/otr/android-app/routeinfo.php?
    // route-startpoint-latitude=41.914196
    // &route-startpoint-longitude=-88.308685
    // &route-endpoint-latitude=40.73061
    // &route-endpoint-longiude=-73.935242
    @GET("routeinfo.php")
    Call<Route> getRouteId(@Query("route-startpoint-latitude") double startLat,
                           @Query("route-startpoint-longitude") double startLong,
                           @Query("route-endpoint-latitude") double endLat,
                           @Query("route-endpoint-longiude") double endLong);

    //http://rjtmobile.com/aamir/otr/android-app/businfo.php?routeid=2
    @GET("businfo.php")
    Call<BusInformation> getBusInformation(@Query("routeid") int routeid);


}
