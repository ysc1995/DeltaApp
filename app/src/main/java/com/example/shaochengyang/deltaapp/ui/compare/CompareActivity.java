package com.example.shaochengyang.deltaapp.ui.compare;

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
import com.example.shaochengyang.deltaapp.ui.data.network.comparedemo.model.DemoItem;
import com.example.shaochengyang.deltaapp.ui.displayflight.FlightDetailActivity;
import com.example.shaochengyang.deltaapp.ui.selectflight.FlightListAdapter;
import com.example.shaochengyang.deltaapp.ui.selectflight.SelectFlightActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompareActivity extends AppCompatActivity {
    private static final String TAG = "CompareActivity";

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
    String numofTicket, first_id,first_route,first_duration,first_stops,first_stopDuration,first_price,second_id,second_route,second_duration,second_stops,second_stopDuration,second_price,third_id,third_route,third_duration,third_stops,third_stopDuration,third_price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        ButterKnife.bind(this);

        numofTicket = getIntent().getExtras().getString("numofTicket");

        first_id = getIntent().getExtras().getString("first_id");
        first_route = getIntent().getExtras().getString("first_route");
        first_duration = getIntent().getExtras().getString("first_duration");
        first_stops = getIntent().getExtras().getString("first_stops");
        first_stopDuration = getIntent().getExtras().getString("first_stopDuration");
        first_price = getIntent().getExtras().getString("first_price");

        DemoItem demoItem1 = new DemoItem(first_id,first_route,first_duration,first_stops,first_stopDuration,first_price);

        second_id = getIntent().getExtras().getString("second_id");
        second_route = getIntent().getExtras().getString("second_route");
        second_duration = getIntent().getExtras().getString("second_duration");
        second_stops = getIntent().getExtras().getString("second_stops");
        second_stopDuration = getIntent().getExtras().getString("second_stopDuration");
        second_price = getIntent().getExtras().getString("second_price");

        DemoItem demoItem2 = new DemoItem(second_id,second_route,second_duration,second_stops,second_stopDuration,second_price);

        third_id = getIntent().getExtras().getString("third_id");
        third_route = getIntent().getExtras().getString("third_route");
        third_duration = getIntent().getExtras().getString("third_duration");
        third_stops = getIntent().getExtras().getString("third_stops");
        third_stopDuration = getIntent().getExtras().getString("third_stopDuration");
        third_price = getIntent().getExtras().getString("third_price");

        DemoItem demoItem3 = new DemoItem(third_id,third_route,third_duration,third_stops,third_stopDuration,third_price);

        List<DemoItem> demoItemList = new ArrayList<>();
        demoItemList.add(demoItem1);
        demoItemList.add(demoItem2);
        demoItemList.add(demoItem3);

        showDemoFlightList(demoItemList);

    }

    public void showDemoFlightList(List<DemoItem> demoItemList) {
        final String rstart = "St. Charles, IL";
        final String rdestination = "New York City, NY";
        tvSfTitle.setText("Select Flight:\n" + rstart + " -> " + rdestination);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFlightList.setLayoutManager(layoutManager);
        rvFlightList.setItemAnimator(new DefaultItemAnimator());

        adapter = new CompareListAdapter(demoItemList, new CompareListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoItem bus) {
                //Toast.makeText(SelectFlightActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CompareActivity.this, FlightDetailActivity.class);
                BusinformationItem item = new BusinformationItem();
                item.setBusid(bus.getDemoNumber());
                item.setBoardingtime("07:00:00 AM");
                item.setBusdeparturetime("07:00:00 AM");
                item.setBusregistrationno(bus.getDemoNumber());
                item.setBustype("ECONOMY EXTRA");
                item.setDropingtime("03:45:00 PM");
                item.setFare(bus.getDemoPrice());
                item.setJournyduration(bus.getDemoDuration());


                intent.putExtra("flight_detail", item);
                intent.putExtra("rstart", rstart);
                intent.putExtra("rdestination", rdestination);
                intent.putExtra("numofTicket",numofTicket);
                startActivity(intent);
            }
        }, rstart, rdestination);
        rvFlightList.setAdapter(adapter);



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


}
