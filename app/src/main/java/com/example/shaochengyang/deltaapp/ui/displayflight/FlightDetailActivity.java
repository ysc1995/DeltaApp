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

    String numofTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        ButterKnife.bind(this);

        iFlightDetailPresenter = new FlightDetailPresenter(this);
        numofTicket = getIntent().getExtras().getString("numofTicket");
        showFlightOnScreen();

    }

    private void showFlightOnScreen() {
        flight = getIntent().getExtras().getParcelable("flight_detail");
        tvFdFlightno.setText(flight.getBusregistrationno());
        tvFdCabin.setText(flight.getBustype());
        tvFdArriveTime.setText(flight.getBoardingtime());
        tvFdDepartTime.setText(flight.getDropingtime());
        tvFdDuration.setText("Flight Duration" +flight.getJournyduration());
        tvFdPrice.setText("$" + flight.getFare() + " USD");

    }


    @OnClick(R.id.btn_fd_pay)
    public void onViewClicked() {
        //if else for economy and first cabin seat selection activity
        Intent intent = new Intent(this, ConfirmationPageActivity.class);
        intent.putExtra("flight_confirmation", flight);
        intent.putExtra("numofTicket",numofTicket);
        startActivity(intent);
    }
}
