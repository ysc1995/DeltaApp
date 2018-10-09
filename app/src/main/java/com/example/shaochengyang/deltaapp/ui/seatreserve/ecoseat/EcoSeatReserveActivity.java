package com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shaochengyang.deltaapp.R;

public class EcoSeatReserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_seat_reserve);



    }

    public String getBusId(){
        String busid = getIntent().getExtras().getString("busid");
        return busid;
    }

    public String getNumber(){
        String numofTicket = getIntent().getExtras().getString("numofTicket");

        return numofTicket;
    }

    public String getTicketID(){
        String ticketID = getIntent().getExtras().getString("ticketID");
        return ticketID;
    }
}
