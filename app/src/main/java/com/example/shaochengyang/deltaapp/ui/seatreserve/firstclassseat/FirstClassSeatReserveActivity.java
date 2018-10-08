package com.example.shaochengyang.deltaapp.ui.seatreserve.firstclassseat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shaochengyang.deltaapp.R;

public class FirstClassSeatReserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_class_seat_reserve);
    }



    public String getBusId(){
        String busid = getIntent().getExtras().getString("busid");
        return busid;
    }

    public String getNumber(){
        String numofTicket = getIntent().getExtras().getString("numofTicket");

        return numofTicket;
    }
}
