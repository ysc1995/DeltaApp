package com.example.shaochengyang.deltaapp.ui.data.network;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public interface INetworkHelper {

    void getSeatInformation(IDataManager.onSeatInformationListener listener);

    void getCityInformation(IDataManager.onCityInformationListener listener);

    void getRouteId(IDataManager.onRouteIdListener listener);

    void getBusInformation(IDataManager.onBusInformationListener listener);

}
