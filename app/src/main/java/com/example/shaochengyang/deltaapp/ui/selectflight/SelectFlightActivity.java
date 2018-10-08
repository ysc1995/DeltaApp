package com.example.shaochengyang.deltaapp.ui.selectflight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.displayflight.FlightDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.shaochengyang.deltaapp.R.color.colorWhite;

public class SelectFlightActivity extends AppCompatActivity implements ISelectFlightView {

    private static final String TAG = "SelectFlightActivity";

    @BindView(R.id.tv_sf_title)
    TextView tvSfTitle;
    @BindView(R.id.tv_sf_date)
    TextView tvSfDate;
    @BindView(R.id.tv_sf_money)
    TextView tvSfMoney;
    @BindView(R.id.tv_sf_main_cabin)
    TextView tvSfMainCabin;
    @BindView(R.id.tv_sf_first_cabin)
    TextView tvSfFirstCabin;
    @BindView(R.id.rv_flight_list)
    RecyclerView rvFlightList;

    RecyclerView.Adapter adapter;
    ISelectFlightPresenter iSelectFlightPresenter;
    String numofTicket;
    FlightTicket flightTicket;
    @BindView(R.id.firstUnderColor)
    Button firstUnderColor;
    @BindView(R.id.mainUnderColor)
    Button mainUnderColor;

    Boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //mainUnderColor.setBackgroundResource(R.color.colorWhite);
        isFirst = false;
        setContentView(R.layout.activity_select_flight);
        ButterKnife.bind(this);
        numofTicket = getIntent().getExtras().getString("numofTicket");
        String date = getIntent().getExtras().getString("date");
        String rid = getIntent().getExtras().getString("rid");

        int routeId = Integer.parseInt(rid);
        tvSfDate.setText(date);
        iSelectFlightPresenter = new SelectFlightPresenter(this);
        iSelectFlightPresenter.onActivityCreate(routeId);

    }


    @Override
    public void showFlightList(List<BusinformationItem> flightList) {
        String rname = getIntent().getExtras().getString("rname");
        final String rstart = getIntent().getExtras().getString("rstart");
        final String rdestination = getIntent().getExtras().getString("rdestination");

        Log.d(TAG, "onCreate: " + "\nrname: " + rname + "\nrstart" + rstart
                + "\nrdestination: " + rdestination);

        tvSfTitle.setText("Select Flight:\n" + rstart + " -> " + rdestination);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFlightList.setLayoutManager(layoutManager);
        rvFlightList.setItemAnimator(new DefaultItemAnimator());

        final BusinformationItem flight = flightList.get(0);
        Log.d("show", "showFlightList: " + flight.getBusid());
        final String busid = flight.getBusid();

        adapter = new FlightListAdapter(flightList,
                new FlightListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BusinformationItem bus) {


                        flightTicket = new FlightTicket(bus);
                        flightTicket.setNumOfPassenger(numofTicket);
                        flightTicket.setDepartAirport(rstart);
                        flightTicket.setArriveAirport(rdestination);

                        Intent intent = new Intent(SelectFlightActivity.this, FlightDetailActivity.class);

                /*intent.putExtra("busid",busid);
                intent.putExtra("flight_detail", bus);
                intent.putExtra("rstart", rstart);
                intent.putExtra("rdestination", rdestination);
                intent.putExtra("numofTicket",numofTicket);*/
                        intent.putExtra("ticket", flightTicket);

                        intent.putExtra("isFirst",isFirst);
                        startActivity(intent);
                    }
                }, rstart, rdestination);
        rvFlightList.setAdapter(adapter);

    }


    @OnClick({R.id.tv_sf_main_cabin, R.id.tv_sf_first_cabin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sf_main_cabin:

                mainUnderColor.setBackgroundResource(R.color.colorWhite);
                firstUnderColor.setBackgroundResource(R.color.colorMainBackground);
                isFirst = false;
                break;
            case R.id.tv_sf_first_cabin:
                firstUnderColor.setBackgroundResource(R.color.colorWhite);
                mainUnderColor.setBackgroundResource(R.color.colorMainBackground);
                isFirst = true;
                break;
        }
    }
}
