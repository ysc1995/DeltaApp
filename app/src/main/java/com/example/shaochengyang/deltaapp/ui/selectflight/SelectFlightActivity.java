package com.example.shaochengyang.deltaapp.ui.selectflight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.displayflight.FlightDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectFlightActivity extends AppCompatActivity implements ISelectFlightView {

    @BindView(R.id.tv_sf_title)
    TextView tvSfTitle;
    @BindView(R.id.tv_sf_date)
    TextView tvSfDate;
    @BindView(R.id.tv_sf_discription)
    TextView tvSfDiscription;
    @BindView(R.id.textView5)
    TextView textView5;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight);
        ButterKnife.bind(this);
        numofTicket = getIntent().getExtras().getString("numofTicket");

        String rid = getIntent().getExtras().getString("rid");
        String rname = getIntent().getExtras().getString("rname");
        String rstart = getIntent().getExtras().getString("rstart");
        String rdestination = getIntent().getExtras().getString("rdestination");


        //Toast.makeText(this, ""+rname, Toast.LENGTH_SHORT).show();

        iSelectFlightPresenter = new SelectFlightPresenter(this);
        iSelectFlightPresenter.onActivityCreate();


    }

    @OnClick({R.id.tv_sf_main_cabin, R.id.tv_sf_first_cabin})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_sf_main_cabin:
                break;
            case R.id.tv_sf_first_cabin:
                break;

        }
    }



    @Override
    public void showFlightList(List<BusinformationItem> flightList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFlightList.setLayoutManager(layoutManager);
        rvFlightList.setItemAnimator(new DefaultItemAnimator());

        BusinformationItem flight = flightList.get(0);
        Log.d("show", "showFlightList: " + flight.getBusid());
        adapter = new FlightListAdapter(flightList, new FlightListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BusinformationItem bus) {
                Toast.makeText(SelectFlightActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectFlightActivity.this, FlightDetailActivity.class);
                intent.putExtra("flight_detail", bus);
                intent.putExtra("numofTicket",numofTicket);
                startActivity(intent);
            }
        });
        rvFlightList.setAdapter(adapter);
    }
}
