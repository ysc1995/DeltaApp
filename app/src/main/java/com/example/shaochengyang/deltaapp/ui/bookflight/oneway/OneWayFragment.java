package com.example.shaochengyang.deltaapp.ui.bookflight.oneway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.compare.CompareActivity;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.network.comparedemo.model.DemoItem;
import com.example.shaochengyang.deltaapp.ui.data.network.model.RItem;
import com.example.shaochengyang.deltaapp.ui.selectflight.SelectFlightActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneWayFragment extends Fragment implements IOneWayFragmentView,IDataManager.onDemoListener, IDataManager.onCityInformationListener, IDataManager.onRouteIdListener,IDataManager.onDatabaseListener {

    private static final String TAG = "OneWayFragment";
    @BindView(R.id.btn_bf_minus)
    Button btnBfMinus;
    @BindView(R.id.btn_bf_plus)
    Button btnBfPlus;
    Unbinder unbinder;
    @BindView(R.id.txt_bf_nums)
    TextView txtBfNums;
    @BindView(R.id.tv_bf_title1)
    TextView tvBfTitle1;
    @BindView(R.id.tv_bf_title2)
    TextView tvBfTitle2;
    @BindView(R.id.tv_bf_miles)
    TextView tvBfMiles;
    @BindView(R.id.tv_bf_money)
    TextView tvBfMoney;
    @BindView(R.id.tv_bf_find)
    TextView tvBfFind;

    IOneWayFragmentPresenter oneWayFragmentPresenter;
    SharedPreferences sharedPreferences;
    IDataManager iDataManager;
    @BindView(R.id.txt_bf_from)
    EditText txtBfFrom;
    @BindView(R.id.txt_bf_to)
    EditText txtBfTo;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one_way, container, false);

        unbinder = ButterKnife.bind(this, view);
        sharedPreferences = this.getActivity().getSharedPreferences("mySP",0);

        oneWayFragmentPresenter = new OneWayFragmentPresenter(this);

        //IDataManager iDataManager = new DataManager(this);
        //iDataManager.getCityInformation(this);

        iDataManager = new DataManager(getActivity());
        iDataManager.getCityInformation(this);


        String num = txtBfNums.getText().toString();
        int numberOfTicket = Integer.parseInt(num);
        if (numberOfTicket <= 1) {
            btnBfMinus.setClickable(false);
        }
        if (numberOfTicket > 5) {
            btnBfPlus.setClickable(false);
        }


        //oneWayFragmentPresenter.getCityInformation();




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.btn_bf_minus, R.id.btn_bf_plus, R.id.tv_bf_miles, R.id.tv_bf_money, R.id.tv_bf_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bf_minus:
                oneWayFragmentPresenter.minusOneTicket();
                break;
            case R.id.btn_bf_plus:
                oneWayFragmentPresenter.plusOneTicket();
                break;
            case R.id.tv_bf_miles:
                //tvBfShowSelection.setText("Using Millage Point");

                break;
            case R.id.tv_bf_money:
                //tvBfShowSelection.setText("Using USD");

                break;
            case R.id.tv_bf_find:

                Boolean checkFlag = true;
                String fromCity = txtBfFrom.getText().toString();
                String toCity = txtBfTo.getText().toString();
                if(fromCity.contains("Charles")){
                    fromCity = "St. Charles, IL";
                }else if(fromCity.contains("New York")||fromCity.contains("NY")){
                    fromCity = "New York City, NY";
                }else if(fromCity.contains("Dallas")){
                    fromCity = "Dallas, TX";
                }else if(fromCity.contains("Atlanta")){
                    fromCity = "Atlanta, GA";
                }
                else if(fromCity.contains("ashington")){
                    fromCity = "Washington, DC";
                }
                else if(fromCity.contains("hiladelp")){
                    fromCity = "Philadelphia, PA";
                }else if(fromCity.contains("enver")){
                    fromCity = "Denver, CO";
                }else{
                    Toast.makeText(getActivity(), "Please Type Valid Departure City", Toast.LENGTH_SHORT).show();
                    checkFlag = false;
                }


                if(toCity.contains("Charles")){
                    toCity = "St. Charles, IL";
                }else if(toCity.contains("New York")||toCity.contains("NY")){
                    toCity = "New York City, NY";
                }else if(toCity.contains("Dallas")){
                    toCity = "Dallas, TX";
                }else if(toCity.contains("Atlanta")){
                    toCity = "Atlanta, GA";
                }
                else if(toCity.contains("ashington")){
                    toCity = "Washington, DC";
                }
                else if(toCity.contains("hiladelp")){
                    toCity = "Philadelphia, PA";
                }else if(toCity.contains("enver")){
                    toCity = "Denver, CO";
                }else{
                    Toast.makeText(getActivity(), "Please Type Valid Destination City", Toast.LENGTH_SHORT).show();
                    checkFlag = false;
                }


                if(checkFlag&&fromCity.equals("St. Charles, IL")&&toCity.equals("New York City, NY")){
                    iDataManager.getCompareDemo(this);

                    break;
                }


                if(checkFlag){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String numofTicket = txtBfNums.getText().toString();
                    editor.putString("numofTicket", numofTicket);

                    getCityPosition(fromCity,toCity);
                }

                break;
        }
    }

    private void getCityPosition(String fromCity, String toCity) {
        iDataManager.getCityPosition(this,fromCity,toCity);
    }

    @Override
    public void minusOneTicket() {
        String num = txtBfNums.getText().toString();
        btnBfPlus.setClickable(true);
        int numberOfTicket = Integer.parseInt(num);
        if (numberOfTicket < 2) {
            btnBfMinus.setClickable(false);
        } else {
            numberOfTicket--;
        }
        txtBfNums.setText(String.valueOf(numberOfTicket));
        if (numberOfTicket == 1) {
            btnBfMinus.setClickable(false);
        }
    }

    @Override
    public void plusOneTicket() {
        String num = txtBfNums.getText().toString();
        btnBfMinus.setClickable(true);
        int numberOfTicket = Integer.parseInt(num);

        if (numberOfTicket < 6) {
            numberOfTicket++;
            txtBfNums.setText(String.valueOf(numberOfTicket));
        } else {
            btnBfPlus.setClickable(false);
        }

        if (numberOfTicket == 6) {
            btnBfPlus.setClickable(false);
        }
    }

    @Override
    public void passCityInfo(String cityName, String cityLati, String cityLong) {
        Log.d(TAG, "passCityInfo: " + cityName);
        iDataManager.addRow(this, cityName, cityLati, cityLong);


    }

    @Override
    public void passCityPositions(String fromCityLati, String fromCityLong, String toCityLati, String toCityLong) {
        Log.d(TAG, "passCityPositions: "+fromCityLati+" "+fromCityLong+" "+toCityLati+" "+toCityLong);
        iDataManager.getRouteId(this,fromCityLati,fromCityLong,toCityLati,toCityLong);
    }

    @Override
    public void passRouteInfo(List<RItem> rItemList) {
        String numofTicket = txtBfNums.getText().toString();
        Intent intent = new Intent(getActivity(), SelectFlightActivity.class);
        String rid = rItemList.get(0).getRid();
        String rname = rItemList.get(0).getRname();
        String rstart = rItemList.get(0).getRstart();
        String rdestination = rItemList.get(0).getRdestination();
        intent.putExtra("rid",rid);
        intent.putExtra("rname",rname);
        intent.putExtra("rstart",rstart);
        intent.putExtra("rdestination",rdestination);
        intent.putExtra("numofTicket",numofTicket);

        startActivity(intent);
    }

    @Override
    public void passDemoInfo(List<DemoItem> demoItemList) {
        //TODO add demoItem to intent
        String numofTicket = txtBfNums.getText().toString();

        String first_id = demoItemList.get(0).getDemoName();
        String first_route = demoItemList.get(0).getDemoNumber();
        String first_duration = demoItemList.get(0).getDemoDuration();
        String first_stops = demoItemList.get(0).getDemoStop();
        String first_stopDuration = demoItemList.get(0).getDemoStopDuration();
        String first_price = demoItemList.get(0).getDemoPrice();

        String second_id = demoItemList.get(1).getDemoName();
        String second_route = demoItemList.get(1).getDemoNumber();
        String second_duration = demoItemList.get(1).getDemoDuration();
        String second_stops = demoItemList.get(1).getDemoStop();
        String second_stopDuration = demoItemList.get(1).getDemoStopDuration();
        String second_price = demoItemList.get(1).getDemoPrice();

        String third_id = demoItemList.get(2).getDemoName();
        String third_route = demoItemList.get(2).getDemoNumber();
        String third_duration = demoItemList.get(2).getDemoDuration();
        String third_stops = demoItemList.get(2).getDemoStop();
        String third_stopDuration = demoItemList.get(2).getDemoStopDuration();
        String third_price = demoItemList.get(2).getDemoPrice();

        Intent intent = new Intent(getActivity(), CompareActivity.class);

        intent.putExtra("numofTicket",numofTicket);

        intent.putExtra("first_id",first_id);
        intent.putExtra("first_route",first_route);
        intent.putExtra("first_duration",first_duration);
        intent.putExtra("first_stops",first_stops);
        intent.putExtra("first_stopDuration",first_stopDuration);
        intent.putExtra("first_price",first_price);

        intent.putExtra("second_id",second_id);
        intent.putExtra("second_route",second_route);
        intent.putExtra("second_duration",second_duration);
        intent.putExtra("second_stops",second_stops);
        intent.putExtra("second_stopDuration",second_stopDuration);
        intent.putExtra("second_price",second_price);

        intent.putExtra("third_id",third_id);
        intent.putExtra("third_route",third_route);
        intent.putExtra("third_duration",third_duration);
        intent.putExtra("third_stops",third_stops);
        intent.putExtra("third_stopDuration",third_stopDuration);
        intent.putExtra("third_price",third_price);
        startActivity(intent);
        /*CompareActivity compareActivity = new CompareActivity();
        compareActivity.showDemoFlightList(demoItemList);*/

    }

   /* @Override
    public void storeCityInfo(String cityName, String cityLati, String cityLong) {
        oneWayFragmentPresenter.addRow(cityName,cityLati,cityLong);
    }


    @Override
    public void passCityInfo(String cityName, String cityLati, String cityLong) {

    }*/
}
