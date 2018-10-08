package com.example.shaochengyang.deltaapp.ui.displayflight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.flightconfirmation.ConfirmationPageActivity;
import com.example.shaochengyang.deltaapp.ui.purchaseticket.PurchaseTicketActivity;
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatReserveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightDetailActivity extends AppCompatActivity implements IFlightDetailView {

    IFlightDetailPresenter iFlightDetailPresenter;
    FlightTicket flightTicket;
    BusinformationItem flight;


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

    Boolean isFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        ButterKnife.bind(this);

        iFlightDetailPresenter = new FlightDetailPresenter(this);
        isFirst = getIntent().getExtras().getBoolean("isFirst");
        showFlightOnScreen();

    }

    private void showFlightOnScreen() {

        flightTicket = getIntent().getExtras().getParcelable("ticket");
        flight = flightTicket.getFlightDetails();

        //set view
        tvFdFlightno.setText("Flight No: " + flight.getBusregistrationno());
        tvFdCabin.setText("Cabin: " + flight.getBustype());
        tvFdArriveTime.setText(flight.getBoardingtime());
        tvFdDepartTime.setText(flight.getDropingtime());
        tvFdDuration.setText("Flight Duration: " +flight.getJournyduration());
        tvDepartAirport.setText(flightTicket.getDepartAirport());
        tvArriveAirport.setText(flightTicket.getArriveAirport());
        tvFdTickets.setText("Number of Ticket Purchase: "
                + flightTicket.getNumOfPassenger());


        String fare = flight.getFare();

        float price = Float.parseFloat(fare);
        int numOfTicket = Integer.parseInt(flightTicket.getNumOfPassenger());
        price = price * numOfTicket;
        flightTicket.setTotalPrice(String.valueOf(price));

        String prices = String.format("%.2f", price );

        tvFdPrice.setText("$" + prices + " USD");

    }


    @OnClick(R.id.btn_fd_pay)
    public void onViewClicked() {
        //if else for economy and first cabin seat selection activity
        Intent intent = new Intent(this, PurchaseTicketActivity.class);
        intent.putExtra("ticket", flightTicket);
        intent.putExtra("isFirst",isFirst);

        startActivity(intent);
    }
}
