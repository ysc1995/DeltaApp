package com.example.shaochengyang.deltaapp.ui.upcomingflight;

import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;

import java.util.List;

public interface IUpcomingFlightView {

    void bindMyFlightListToView(List<MyFlightTicket> myFlightTickets);
}
