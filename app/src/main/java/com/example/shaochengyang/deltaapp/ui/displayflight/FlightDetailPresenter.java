package com.example.shaochengyang.deltaapp.ui.displayflight;

import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public class FlightDetailPresenter implements IFlightDetailPresenter {

    IFlightDetailView iFlightDetailView;
    IDataManager iDataManager;

    public FlightDetailPresenter(FlightDetailActivity flightDetailActivity) {

        iFlightDetailView = flightDetailActivity;
        iDataManager = new DataManager(flightDetailActivity);

    }





}
