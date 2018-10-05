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
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.network.model.RItem;
import com.example.shaochengyang.deltaapp.ui.selectflight.SelectFlightActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneWayFragment extends Fragment implements IOneWayFragmentView, IDataManager.onCityInformationListener, IDataManager.onRouteIdListener,IDataManager.onDatabaseListener {

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
                }else if(fromCity.contains("New York")){
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
                }else if(toCity.contains("New York")){
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
        Intent intent = new Intent(getActivity(), SelectFlightActivity.class);
        String rid = rItemList.get(0).getRid();
        String rname = rItemList.get(0).getRname();
        String rstart = rItemList.get(0).getRstart();
        String rdestination = rItemList.get(0).getRdestination();
        intent.putExtra("rid",rid);
        intent.putExtra("rname",rname);
        intent.putExtra("rstart",rstart);
        intent.putExtra("rdestination",rdestination);

        startActivity(intent);
    }

   /* @Override
    public void storeCityInfo(String cityName, String cityLati, String cityLong) {
        oneWayFragmentPresenter.addRow(cityName,cityLati,cityLong);
    }


    @Override
    public void passCityInfo(String cityName, String cityLati, String cityLong) {

    }*/
}
