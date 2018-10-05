package com.example.shaochengyang.deltaapp.ui.data;

import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;

import java.util.List;

public interface IDataManager extends INetworkHelper {

    interface onSeatInformationListener{

    }

    interface onCityInformationListener{

    }

    interface onRouteIdListener{

    }

    interface onBusInformationListener{
        void bindFlightListToView(BusInformation busList);
    }


}
