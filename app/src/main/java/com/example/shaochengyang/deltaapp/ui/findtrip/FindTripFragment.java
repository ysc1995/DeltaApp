package com.example.shaochengyang.deltaapp.ui.findtrip;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.PassenterInfo;
import com.example.shaochengyang.deltaapp.ui.findtripconfirmation.FindTripConfirmationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FindTripFragment extends Fragment implements IDataManager.onFlightInfoListener,IDataManager.onPassenterInfoListener, IDataManager.onSeatInfoListener{


    @BindView(R.id.tv_fname)
    EditText tvFname;
    @BindView(R.id.tv_lname)
    EditText tvLname;
    @BindView(R.id.tv_confirmation)
    EditText tvConfirmation;
    Unbinder unbinder;
    IDataManager iDataManager;
    String confirmationNum,fName,lName;

    List<PassenterInfo> passenterInfoList;
    List<String> seatList;
    String seatID;
    MyFlightTicket ticket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_find_trip, container, false);

        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        confirmationNum = tvConfirmation.getText().toString();
        fName = tvFname.getText().toString();
        lName = tvLname.getText().toString();

        iDataManager = new DataManager(getActivity());
        iDataManager.getPassenterInfo(this, confirmationNum);
        iDataManager.getSeatInfo(this, confirmationNum);

        iDataManager.getFlightInfo(this,confirmationNum);


        for(int i = 0 ; i < seatList.size();i++){
            if(passenterInfoList.get(i).getFname().equals(fName)&&passenterInfoList.get(i).getLname().equals(lName)){
                seatID = seatList.get(i);
                Intent intent= new Intent(getActivity(),FindTripConfirmationActivity.class);
                intent.putExtra("ticket",ticket);
                intent.putExtra("seatID",seatID);
                intent.putExtra("fName",fName);
                intent.putExtra("lName",lName);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(), "Can Find Passenger and Confirmation# Match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void passPassengerInfo(List<PassenterInfo> passenterInfoList) {
        this.passenterInfoList=passenterInfoList;
    }

    @Override
    public void passSeatInfo(List<String> seatList) {
        this.seatList=seatList;

    }

    @Override
    public void passFlightInfo(MyFlightTicket ticket) {
        this.ticket = ticket;
    }
}
