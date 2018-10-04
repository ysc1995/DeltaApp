package com.example.shaochengyang.deltaapp.ui.data;

import android.content.Context;

import com.example.shaochengyang.deltaapp.ui.data.network.INetworkHelper;
import com.example.shaochengyang.deltaapp.ui.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    INetworkHelper iNetworkHelper;

    public DataManager(Context context) {
        this.iNetworkHelper = new NetworkHelper();
    }

    /*---------------------------------------------------------------------------------------------
       Server Related Methods
     --------------------------------------------------------------------------------------------*/
    @Override
    public void getSeatInformation(IDataManager.onSeatInformationListener listener) {
        iNetworkHelper.getSeatInformation(listener);
    }


}
