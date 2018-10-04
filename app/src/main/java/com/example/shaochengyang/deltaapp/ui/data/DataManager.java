package com.example.shaochengyang.deltaapp.ui.data;

import android.content.Context;

import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    INetworkHelper iNetworkHelper;

    public DataManager(Context context) {
        this.iNetworkHelper = new NetworkHelper();
    }

    /*---------------------------------------------------------------------------------------------
       Server Related Methods
     --------------------------------------------------------------------------------------------*/
    @Override
    public void getSeatInformation(IDataManager.onSeatInformationListener listener) {
        iNetworkHelper.getSeatInformation(listener);
    }

    @Override
    public void getCityInformation(onCityInformationListener listener) {
        iNetworkHelper.getCityInformation(listener);
    }

    @Override
    public void getRouteId(onRouteIdListener listener) {
        iNetworkHelper.getRouteId(listener);
    }

    @Override
    public void getBusInformation(onBusInformationListener listener) {
        iNetworkHelper.getBusInformation(listener);
    }


}
