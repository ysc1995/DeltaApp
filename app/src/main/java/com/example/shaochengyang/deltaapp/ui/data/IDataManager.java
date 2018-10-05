package com.example.shaochengyang.deltaapp.ui.data;

import com.example.shaochengyang.deltaapp.ui.data.database.IDbHelper;
import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.model.RItem;

import java.util.List;

public interface IDataManager extends INetworkHelper ,IDbHelper{

    interface onSeatInformationListener{

    }

    interface onCityInformationListener{

        void passCityInfo(String cityName, String cityLati, String cityLong);
    }

    interface onRouteIdListener{

        void passRouteInfo(List<RItem> rItemList);
    }

    interface onBusInformationListener{
        void bindFlightListToView(BusInformation busList);
    }


    interface onDatabaseListener{

        void passCityPositions(String fromCityLati, String fromCityLong, String toCityLati, String toCityLong);
    }


}
