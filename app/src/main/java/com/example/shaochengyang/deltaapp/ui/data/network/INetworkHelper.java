package com.example.shaochengyang.deltaapp.ui.data.network;

import com.example.shaochengyang.deltaapp.ui.bookflight.oneway.OneWayFragment;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public interface INetworkHelper {

    void getSeatInformation(IDataManager.onSeatInformationListener listener);

    void getCityInformation(IDataManager.onCityInformationListener listener);



    void getBusInformation(IDataManager.onBusInformationListener listener, int rid);

    void getRouteId(IDataManager.onRouteIdListener onRouteIdListener, String fromCityLati, String fromCityLati1, String toCityLati, String toCityLong);

    void getCompareDemo(IDataManager.onDemoListener onDemoListener);
}
