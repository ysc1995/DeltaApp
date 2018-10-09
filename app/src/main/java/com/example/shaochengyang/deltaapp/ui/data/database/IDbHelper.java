package com.example.shaochengyang.deltaapp.ui.data.database;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;

import java.util.List;

public interface IDbHelper {

    void addRow(IDataManager.onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong);

    void getCityPosition(IDataManager.onDatabaseListener onDatabaseListener, String fromCity, String toCity);


    void sendPurchasedTicketToDBWithCustomerInfo(
            IDataManager.onPurchasedTicketListener listener,
            List<CustomerFlight> customerFlights,
            FlightTicket ticket);

    void getMyFlightListFromDb(IDataManager.onUpcomingFlightListener listener);

    void updateTicket(IDataManager.onUpdatingTicketListener onUpdatingTicketListener, String id);
}