package com.example.shaochengyang.deltaapp.ui.data;

import com.example.shaochengyang.deltaapp.ui.data.database.IDbHelper;
import com.example.shaochengyang.deltaapp.ui.data.model.BusInformation;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;
import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.comparedemo.model.DemoItem;
import com.example.shaochengyang.deltaapp.ui.data.network.model.RItem;

import java.util.List;

public interface IDataManager extends INetworkHelper ,IDbHelper{

    interface onSeatInformationListener{

        void passSeatInfo(SeatInformation seatInformation);
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

    interface onDemoListener{

        void passDemoInfo(List<DemoItem> demoItemList);
    }

    interface onDatabaseListener{

        void passCityPositions(String fromCityLati, String fromCityLong, String toCityLati, String toCityLong);
    }

    interface onPurchasedTicketListener{
        void addedTicketToDb(boolean result);
        void linkedTicketToCustomerDb(boolean result);
    }

}
