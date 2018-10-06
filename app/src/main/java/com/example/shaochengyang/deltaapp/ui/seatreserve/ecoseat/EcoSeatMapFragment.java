package com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.SeatInformation;
import com.example.shaochengyang.deltaapp.ui.seatreserve.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class EcoSeatMapFragment extends Fragment implements IDataManager.onSeatInformationListener {
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Seat> seatList;
    Button clearButton;
    int numTicket;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_seatsmap, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        /*String test = getArguments().getString("numofTicket");*/

        EcoSeatReserveActivity activity = (EcoSeatReserveActivity) getActivity();
        String nTicket = activity.getNumber();

        numTicket = Integer.parseInt(nTicket);

       /* sharedPreferences = this.getActivity().getSharedPreferences("mySP", 0);
        String num = sharedPreferences.getString("numofTicket", "0");
        int numTicket = Integer.parseInt(num);*/

        clearButton = view.findViewById(R.id.clearButton);
        RecyclerView.LayoutManager manager = new GridLayoutManager(view.getContext(), 4);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        seatList = new ArrayList<>();


        //get seat info from api
        IDataManager iDataManager = new DataManager(getActivity());
        iDataManager.getSeatInformation(this);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 46; i++) {
                    seatList.get(i).setIschoosed(false);
                }

                adapter = new EcoSeatAdaptor(seatList, view.getContext(), numTicket);
                recyclerView.setAdapter(adapter);

            }
        });

        return view;
    }

    @Override
    public void passSeatInfo(SeatInformation seatInformation) {
        String s15 = seatInformation.getSeatinformation().get(0).getS15();
        String s16 = seatInformation.getSeatinformation().get(0).getS16();
        String s17 = seatInformation.getSeatinformation().get(0).getS17();
        String s18 = seatInformation.getSeatinformation().get(0).getS18();
        String s19 = seatInformation.getSeatinformation().get(0).getS19();
        String s20 = seatInformation.getSeatinformation().get(0).getS20();
        String s21 = seatInformation.getSeatinformation().get(0).getS21();
        String s22 = seatInformation.getSeatinformation().get(0).getS22();
        String s23 = seatInformation.getSeatinformation().get(0).getS23();
        String s24 = seatInformation.getSeatinformation().get(0).getS24();
        String s25 = seatInformation.getSeatinformation().get(0).getS25();
        String s26 = seatInformation.getSeatinformation().get(0).getS26();
        String s27 = seatInformation.getSeatinformation().get(0).getS27();
        String s28 = seatInformation.getSeatinformation().get(0).getS28();
        String s29 = seatInformation.getSeatinformation().get(0).getS29();
        String s30 = seatInformation.getSeatinformation().get(0).getS30();
        String s31 = seatInformation.getSeatinformation().get(0).getS31();
        String s32 = seatInformation.getSeatinformation().get(0).getS32();
        String s33 = seatInformation.getSeatinformation().get(0).getS33();
        String s34 = seatInformation.getSeatinformation().get(0).getS34();
        String s35 = seatInformation.getSeatinformation().get(0).getS35();
        String s36 = seatInformation.getSeatinformation().get(0).getS36();
        String s37 = seatInformation.getSeatinformation().get(0).getS37();
        String s38 = seatInformation.getSeatinformation().get(0).getS38();
        String s39 = seatInformation.getSeatinformation().get(0).getS39();
        String s40 = seatInformation.getSeatinformation().get(0).getS40();
        String s41 = seatInformation.getSeatinformation().get(0).getS41();
        String s42 = seatInformation.getSeatinformation().get(0).getS42();
        String s43 = seatInformation.getSeatinformation().get(0).getS43();
        String s44 = seatInformation.getSeatinformation().get(0).getS44();
        String s45 = seatInformation.getSeatinformation().get(0).getS45();
        String s46 = seatInformation.getSeatinformation().get(0).getS46();
        String s47 = seatInformation.getSeatinformation().get(0).getS47();

        List<String> seatArray = new ArrayList<>();
        seatArray.add(s15);
        seatArray.add(s16);
        seatArray.add(s17);
        seatArray.add(s18);
        seatArray.add(s19);
        seatArray.add(s20);
        seatArray.add(s21);
        seatArray.add(s22);
        seatArray.add(s23);
        seatArray.add(s24);
        seatArray.add(s25);
        seatArray.add(s26);
        seatArray.add(s27);
        seatArray.add(s28);
        seatArray.add(s29);
        seatArray.add(s30);
        seatArray.add(s31);
        seatArray.add(s32);
        seatArray.add(s33);
        seatArray.add(s34);
        seatArray.add(s35);
        seatArray.add(s36);
        seatArray.add(s37);
        seatArray.add(s38);
        seatArray.add(s39);
        seatArray.add(s40);
        seatArray.add(s41);
        seatArray.add(s42);
        seatArray.add(s43);
        seatArray.add(s44);
        seatArray.add(s45);
        seatArray.add(s46);
        seatArray.add(s47);





        int count = 15;
        for (int i = 1; i < 47; i++) {

            //33 seats total in main cabin
            //14 seats (7 rows in First Class
            Seat seat = new Seat();
            seat.setVisible(true);
            seat.setIschoosed(false);
            String sInfo = seatArray.get(count-15);
            if(sInfo.equals("0")){
                seat.setIsselected(false);
                seat.setType(seat.unselectedtype);
            }else{
                seat.setIsselected(true);
                seat.setType(seat.reservedtype);
            }
            if(i<25) {
                seat.setId("S" + count);
                if(i%4==3) {
                    seat.setVisible(false);
                    seat.setId("invis");
                }else{
                    count++;
                }
            }
            if(i == 25||i==29){
                seat.setVisible(false);
                seat.setId("invisi");
            }
            if(i>25){
                if(i%4==3){
                    seat.setVisible(false);
                }
                if(seat.isVisible()){
                    seat.setId("S"+count);
                    count++;
                }else {
                    seat.setId("invis");
                }
            }
            seatList.add(seat);



        }

        adapter = new EcoSeatAdaptor(seatList, getContext(), numTicket);
        recyclerView.setAdapter(adapter);


    }
}
