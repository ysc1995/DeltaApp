package com.example.shaochengyang.deltaapp.ui.purchaseticket;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;

import java.util.ArrayList;
import java.util.List;

public class PurchaseTicketPresenter implements IPurchaseTicketPresenter, IDataManager.onPurchasedTicketListener {

    private static final String TAG = "PurchaseTicketPresenter";
    IPurchaseTicketView iPurchaseTicketView;
    IDataManager iDataManager;


    public PurchaseTicketPresenter(PurchaseTicketActivity purchaseTicketActivity) {
        iPurchaseTicketView = purchaseTicketActivity;
        iDataManager = new DataManager(purchaseTicketActivity);

    }


    @Override
    public void sendPurchasedTicketToDB() {
        FlightTicket ticket = iPurchaseTicketView.getPurchasedTicket();
        List<CustomerFlight> customerFlightList
                = iPurchaseTicketView.getUserInputData();

        iDataManager.sendPurchasedTicketToDBWithCustomerInfo(this,
                customerFlightList, ticket);
        /*iPurchaseTicketView.passTicketInfo(customerFlightList,ticket);*/
    }


    @Override
    public void linkedTicketToCustomerDb(boolean result) {
        Log.d(TAG, "linkedTicketToCustomerDb: " + result);
        iPurchaseTicketView.toConfirmationPage();
    }
}
