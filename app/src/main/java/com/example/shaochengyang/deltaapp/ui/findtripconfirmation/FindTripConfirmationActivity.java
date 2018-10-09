package com.example.shaochengyang.deltaapp.ui.findtripconfirmation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindTripConfirmationActivity extends AppCompatActivity {

    MyFlightTicket ticket;
    String ticketID, fName, lName, seatID;
    @BindView(R.id.tv_ft_ticketid)
    TextView tvFtTicketid;
    @BindView(R.id.tv_ft_flightnum)
    TextView tvFtFlightnum;
    @BindView(R.id.tv_ft_depair)
    TextView tvFtDepair;
    @BindView(R.id.tv_ft_arrair)
    TextView tvFtArrair;
    @BindView(R.id.tv_ft_deptime)
    TextView tvFtDeptime;
    @BindView(R.id.tv_ft_arrtime)
    TextView tvFtArrtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trip_confirmation);
        ButterKnife.bind(this);

        ticket = getIntent().getExtras().getParcelable("ticket");
        fName = getIntent().getExtras().getString("fName");
        lName = getIntent().getExtras().getString("lName");
        seatID = getIntent().getExtras().getString("seatID");

        String flightNum = ticket.getFlightnum();
        String depAirport = ticket.getDepAirport();
        String arrAirport = ticket.getArrAirport();
        String depTime = ticket.getDepTime();
        String arrTime = ticket.getArrTime();
        ticketID = ticket.getTicketId();

        tvFtTicketid.setText("Ticket ID: " + ticketID);
        tvFtFlightnum.setText("Flight No. : " + flightNum);
        tvFtDepair.setText("Departure Airport: " + depAirport);
        tvFtArrair.setText("Arrival Airport: " + arrAirport);
        tvFtDeptime.setText("Departure Time: " + depTime);
        tvFtArrtime.setText("Arrival Time: " + arrTime);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
