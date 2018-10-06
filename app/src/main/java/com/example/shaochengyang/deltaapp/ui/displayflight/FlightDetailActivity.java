package com.example.shaochengyang.deltaapp.ui.displayflight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.flightconfirmation.ConfirmationPageActivity;
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatReserveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightDetailActivity extends AppCompatActivity implements IFlightDetailView {

    IFlightDetailPresenter iFlightDetailPresenter;
    BusinformationItem flight;
    String numofTicket;

    @BindView(R.id.tv_fd_flightno)
    TextView tvFdFlightno;
    @BindView(R.id.tv_fd_cabin)
    TextView tvFdCabin;
    @BindView(R.id.tv_fd_depart_time)
    TextView tvFdDepartTime;
    @BindView(R.id.tv_fd_arrive_time)
    TextView tvFdArriveTime;
    @BindView(R.id.tv_depart_airport)
    TextView tvDepartAirport;
    @BindView(R.id.tv_arrive_airport)
    TextView tvArriveAirport;
    @BindView(R.id.tv_fd_duration)
    TextView tvFdDuration;
    @BindView(R.id.tv_fd_price)
    TextView tvFdPrice;
    @BindView(R.id.tv_fd_tickets)
    TextView tvFdTickets;

    String busid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        ButterKnife.bind(this);

        iFlightDetailPresenter = new FlightDetailPresenter(this);
        numofTicket = getIntent().getExtras().getString("numofTicket");
        busid = getIntent().getExtras().getString("busid");
        showFlightOnScreen();

    }

    private void showFlightOnScreen() {
        flight = getIntent().getExtras().getParcelable("flight_detail");
        final String rstart = getIntent().getExtras().getString("rstart");
        final String rdestination = getIntent().getExtras().getString("rdestination");
        String tickets = getIntent().getExtras().getString("numofTicket");

        tvFdFlightno.setText("Flight No: " + flight.getBusregistrationno());
        tvFdCabin.setText("Cabin: " + flight.getBustype());
        tvFdArriveTime.setText(flight.getBoardingtime());
        tvFdDepartTime.setText(flight.getDropingtime());
        tvFdDuration.setText("Flight Duration: " +flight.getJournyduration());
        tvDepartAirport.setText(rstart);
        tvArriveAirport.setText(rdestination);
        tvFdTickets.setText("Number of Ticket Purchase: " + tickets);


        String fare = flight.getFare();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fare);
        stringBuilder.deleteCharAt(fare.length()-1);


        float price = Float.parseFloat(stringBuilder.toString());

        price = price * Integer.parseInt(tickets);
        String prices = String.format("%.2f", price );

        tvFdPrice.setText("$" + prices + " USD");

    }


    @OnClick(R.id.btn_fd_pay)
    public void onViewClicked() {
        //if else for economy and first cabin seat selection activity
        Intent intent = new Intent(this, ConfirmationPageActivity.class);
        intent.putExtra("busid",busid);
        intent.putExtra("flight_confirmation", flight);
        intent.putExtra("numofTicket",numofTicket);
        startActivity(intent);
    }
}
