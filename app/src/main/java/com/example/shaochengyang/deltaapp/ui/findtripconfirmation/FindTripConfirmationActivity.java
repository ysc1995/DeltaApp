package com.example.shaochengyang.deltaapp.ui.findtripconfirmation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.TicketInfo;

public class FindTripConfirmationActivity extends AppCompatActivity {

    MyFlightTicket ticket;
    String ticketID,fName,lName,seatID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trip_confirmation);


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

    }
}
