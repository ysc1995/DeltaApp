package com.example.shaochengyang.deltaapp.ui.data;

import android.content.Context;

import com.example.shaochengyang.deltaapp.ui.data.database.DbHelper;
import com.example.shaochengyang.deltaapp.ui.data.database.IDbHelper;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.NetworkHelper;

import java.util.List;

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
    public void getSeatInformation(onSeatInformationListener listener, String busid) {
        iNetworkHelper.getSeatInformation(listener, busid);
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
    public void getBusInformation(onBusInformationListener listener, int rid) {
        iNetworkHelper.getBusInformation(listener, rid);
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
    public void updateTicket(onUpdatingTicketListener onUpdatingTicketListener, String id) {
        dbHelper.updateTicket(onUpdatingTicketListener, id);
    }

    @Override
    public void storeSeatID(String id, String ticketID) {
        dbHelper.storeSeatID(id,ticketID);
    }

    @Override
    public void getPassenterInfo(onPassenterInfoListener onPassenterInfoListener, String ticketID) {
        dbHelper.getPassenterInfo(onPassenterInfoListener,ticketID);
    }

    @Override
    public void getSeatInfo(onSeatInfoListener infoListener, String ticketID) {
        dbHelper.getSeatInfo(infoListener,ticketID);
    }


    @Override
    public void addRow(onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong) {
        dbHelper.addRow(onDatabaseListener,cityName,cityLati,cityLong);
    }

    @Override
    public void getCityPosition(onDatabaseListener onDatabaseListener, String fromCity, String toCity) {
        dbHelper.getCityPosition(onDatabaseListener,fromCity,toCity);
    }


    @Override
    public void sendPurchasedTicketToDBWithCustomerInfo(onPurchasedTicketListener listener, List<CustomerFlight> customerFlights, FlightTicket ticket) {
        dbHelper.sendPurchasedTicketToDBWithCustomerInfo(listener, customerFlights, ticket);
    }

    @Override
    public void getMyFlightListFromDb(onUpcomingFlightListener listener) {
        dbHelper.getMyFlightListFromDb(listener);
    }
}
