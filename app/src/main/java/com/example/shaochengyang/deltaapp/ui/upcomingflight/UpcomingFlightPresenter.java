package com.example.shaochengyang.deltaapp.ui.upcomingflight;

import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;

import java.util.List;

public class UpcomingFlightPresenter implements IUpcomingFlightPresenter,
        IDataManager.onUpcomingFlightListener {

    IUpcomingFlightView iUpcomingFlightView;
    IDataManager iDataManager;

    public UpcomingFlightPresenter(UpcomingFlightFragment upcomingFlightFragment) {
        iUpcomingFlightView = upcomingFlightFragment;
        iDataManager = new DataManager(upcomingFlightFragment.getActivity());
    }


    @Override
    public void getFlightFromDb() {
        iDataManager.getMyFlightListFromDb(this);
    }


    @Override
    public void bindMyFlightListToView(List<MyFlightTicket> flightTickets) {
        iUpcomingFlightView.bindMyFlightListToView(flightTickets);
    }
}
