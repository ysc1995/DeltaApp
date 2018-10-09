package com.example.shaochengyang.deltaapp.ui.data.database;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.findtrip.FindTripFragment;
import com.example.shaochengyang.deltaapp.ui.upcomingflightconfirm.UpComingFlightConfirmActivity;

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

    void storeSeatID(String id, String ticketID);

    void getPassenterInfo(IDataManager.onPassenterInfoListener onPassenterInfoListener, String ticketID);

    void getSeatInfo(IDataManager.onSeatInfoListener infoListener, String ticketID);

    void getFlightInfo(IDataManager.onFlightInfoListener onFlightInfoListener,String ticketID);
}