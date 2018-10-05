package com.example.shaochengyang.deltaapp.ui.flightconfirmation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatReserveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationPageActivity extends AppCompatActivity {

    BusinformationItem flight;


    @BindView(R.id.tv_fc_flightno)
    TextView tvFcFlightno;
    @BindView(R.id.tv_fc_cabin)
    TextView tvFcCabin;
    @BindView(R.id.tv_fc_depart_time)
    TextView tvFcDepartTime;
    @BindView(R.id.tv_fc_arrive_time)
    TextView tvFcArriveTime;
    @BindView(R.id.tv_depart_airport)
    TextView tvDepartAirport;
    @BindView(R.id.tv_arrive_airport)
    TextView tvArriveAirport;
    @BindView(R.id.tv_fc_duration)
    TextView tvFcDuration;
    @BindView(R.id.tv_fc_price)
    TextView tvFcPrice;

    String numofTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        ButterKnife.bind(this);


        numofTicket = getIntent().getExtras().getString("numofTicket");
    }

    private void showFlightOnScreen() {
        flight = getIntent().getExtras().getParcelable("flight_confirmation");
        tvFcFlightno.setText(flight.getBusregistrationno());
        tvFcCabin.setText(flight.getBustype());
        tvFcArriveTime.setText(flight.getBoardingtime());
        tvFcDepartTime.setText(flight.getDropingtime());
        tvFcDuration.setText("Flight Duration" +flight.getJournyduration());
        tvFcPrice.setText("$" + flight.getFare() + " USD");

    }

    @OnClick(R.id.btn_fc_select_seat)
    public void onViewClicked() {
        numofTicket = getIntent().getExtras().getString("numofTicket");
        Intent intent = new Intent(this, EcoSeatReserveActivity.class);
        intent.putExtra("numofTicket",numofTicket);
        startActivity(intent);
    }
}
