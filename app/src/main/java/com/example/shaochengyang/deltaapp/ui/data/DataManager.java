package com.example.shaochengyang.deltaapp.ui.data;

import android.content.Context;

import com.example.shaochengyang.deltaapp.ui.bookflight.oneway.OneWayFragment;
import com.example.shaochengyang.deltaapp.ui.data.database.DbHelper;
import com.example.shaochengyang.deltaapp.ui.data.database.IDbHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    INetworkHelper iNetworkHelper;

    IDbHelper dbHelper;

    public DataManager(Context context) {
        this.iNetworkHelper = new NetworkHelper();
        dbHelper = new DbHelper(context);
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

    /*@Override
    public void getRouteId(onRouteIdListener listener) {
        iNetworkHelper.getRouteId(listener);
    }*/

    @Override
    public void getBusInformation(onBusInformationListener listener) {
        iNetworkHelper.getBusInformation(listener);
    }

    @Override
    public void getRouteId(onRouteIdListener onRouteIdListener, String fromCityLati, String fromCityLati1, String toCityLati, String toCityLong) {
        iNetworkHelper.getRouteId(onRouteIdListener,fromCityLati,fromCityLati1,toCityLati,toCityLong);
    }

    @Override
    public void getCompareDemo(onDemoListener onDemoListener) {
        iNetworkHelper.getCompareDemo(onDemoListener);
    }


    @Override
    public void addRow(onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong) {
        dbHelper.addRow(onDatabaseListener,cityName,cityLati,cityLong);
    }

    @Override
    public void getCityPosition(onDatabaseListener onDatabaseListener, String fromCity, String toCity) {
        dbHelper.getCityPosition(onDatabaseListener,fromCity,toCity);
    }
}
