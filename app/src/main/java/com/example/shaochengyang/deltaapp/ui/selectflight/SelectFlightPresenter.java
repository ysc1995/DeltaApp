package com.example.shaochengyang.deltaapp.ui.selectflight;

import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;

import java.util.ArrayList;
import java.util.List;

public class SelectFlightPresenter implements ISelectFlightPresenter, IDataManager.onBusInformationListener {

    ISelectFlightView iSelectFlightView;
    IDataManager iDataManager;

    public SelectFlightPresenter(SelectFlightActivity selectFlightActivity) {
        iSelectFlightView = selectFlightActivity;
        iDataManager = new DataManager(selectFlightActivity);

    }

    @Override
    public void onActivityCreate() {
        iDataManager.getBusInformation(this);
    }



    @Override
    public void bindFlightListToView(BusInformation busInformation) {
        List<BusinformationItem> targetBusList;
        BusinformationItem bus = busInformation.getBusinformation().get(0);
        List<BusinformationItem> busList = new ArrayList<>();
        busList.add(bus);
        iSelectFlightView.showFlightList(busList);
    }
}
