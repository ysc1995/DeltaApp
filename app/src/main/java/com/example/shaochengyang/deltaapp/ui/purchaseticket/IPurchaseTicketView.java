package com.example.shaochengyang.deltaapp.ui.purchaseticket;

import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;

import java.util.List;

public interface IPurchaseTicketView {

    List<CustomerFlight> getUserInputData();
    FlightTicket getPurchasedTicket();
    void toConfirmationPage();

    void passTicketInfo(List<CustomerFlight> customerFlightList, FlightTicket ticket);
}
