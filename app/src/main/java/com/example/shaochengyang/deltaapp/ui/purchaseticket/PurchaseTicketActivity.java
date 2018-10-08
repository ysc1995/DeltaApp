package com.example.shaochengyang.deltaapp.ui.purchaseticket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.flightconfirmation.ConfirmationPageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaseTicketActivity extends AppCompatActivity implements IPurchaseTicketView {

    private static final String TAG = "PurchaseTicketActivity";

    IPurchaseTicketPresenter iPurchaseTicketPresenter;
    SharedPreferences sharedPreferences;
    FlightTicket flightTicket;
    RecyclerView.Adapter adapter;
    int numOfTicket;
    String ticketID = "AA135";
    List<CustomerFlight> customerFlightList;

    @BindView(R.id.rv_cus_info)
    RecyclerView rvCusInfo;

    Boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_ticket);
        ButterKnife.bind(this);

        isFirst = getIntent().getExtras().getBoolean("isFirst");

        iPurchaseTicketPresenter = new PurchaseTicketPresenter(this);

        flightTicket = getIntent().getExtras().getParcelable("ticket");
        numOfTicket = Integer.parseInt(flightTicket.getNumOfPassenger());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCusInfo.setLayoutManager(layoutManager);
        rvCusInfo.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomerInfoAdapter(numOfTicket);
        rvCusInfo.setAdapter(adapter);

    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        flightTicket.setTicketID(ticketID);

        /*iPurchaseTicketPresenter.sendUserInputDataToDB();
        iPurchaseTicketPresenter.sendPurchasedTicketToDB();*/
        iPurchaseTicketPresenter.sendPurchasedTicketToDB();

    }

    @Override
    public List<CustomerFlight> getUserInputData(){
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        Log.d(TAG, "getUserInputData Email:++++++++ " + email);
        customerFlightList = new ArrayList<>();

        for(int i = 0; i < numOfTicket; i++){
            View view = rvCusInfo.getChildAt(i);
            EditText firstname = view.findViewById(R.id.txt_fname);
            EditText lastname = view.findViewById(R.id.txt_lname);
            EditText cusPassport = view.findViewById(R.id.txt_passport);

            String fname = firstname.getText().toString();
            String lname = lastname.getText().toString();
            String passport = cusPassport.getText().toString();
            Log.d(TAG, "getUserInputData: " + fname);
            CustomerFlight customerFlight
                    = new CustomerFlight(email, fname, lname, passport, ticketID);
            customerFlightList.add(customerFlight);
        }

        return customerFlightList;
    }

    @Override
    public FlightTicket getPurchasedTicket() {
        return flightTicket;
    }

    @Override
    public void toConfirmationPage() {
        Intent intent = new Intent(this, ConfirmationPageActivity.class);

        intent.putExtra("ticket", flightTicket);
        intent.putExtra("isFirst",isFirst);
        startActivity(intent);
    }
}
