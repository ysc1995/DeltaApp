package com.example.shaochengyang.deltaapp.ui.seatreserve.firstclassseat;

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
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatAdaptor;
import com.example.shaochengyang.deltaapp.ui.seatreserve.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class FirstClassSeatMapFragment extends Fragment implements IDataManager.onSeatInformationListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Seat> seatList;
    Button clearButton;
    int numTicket;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_firstclassseatmap, container, false);


        recyclerView = view.findViewById(R.id.recyclerView_fcs);


        clearButton = view.findViewById(R.id.clearButton);
        RecyclerView.LayoutManager manager = new GridLayoutManager(view.getContext(), 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        seatList = new ArrayList<>();

        //TODO pass NumofTicket
        //numTicket = Integer.parseInt(nTicket);


        IDataManager iDataManager = new DataManager(getActivity());
        iDataManager.getSeatInformation(this);


        numTicket =1;


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 14; i++) {
                    seatList.get(i).setIschoosed(false);
                }

                adapter = new FirstClassSeatAdaptor(seatList, view.getContext(), numTicket);
                recyclerView.setAdapter(adapter);

            }
        });

        return view;
    }

    @Override
    public void passSeatInfo(SeatInformation seatInformation) {
        String s1 = seatInformation.getSeatinformation().get(0).getS1();
        String s2 = seatInformation.getSeatinformation().get(0).getS2();
        String s3 = seatInformation.getSeatinformation().get(0).getS3();
        String s4 = seatInformation.getSeatinformation().get(0).getS4();
        String s5 = seatInformation.getSeatinformation().get(0).getS5();
        String s6 = seatInformation.getSeatinformation().get(0).getS6();
        String s7 = seatInformation.getSeatinformation().get(0).getS7();
        String s8 = seatInformation.getSeatinformation().get(0).getS8();
        String s9 = seatInformation.getSeatinformation().get(0).getS9();
        String s10 = seatInformation.getSeatinformation().get(0).getS10();
        String s11 = seatInformation.getSeatinformation().get(0).getS11();
        String s12 = seatInformation.getSeatinformation().get(0).getS12();
        String s13 = seatInformation.getSeatinformation().get(0).getS13();
        String s14 = seatInformation.getSeatinformation().get(0).getS14();

        List<String> seatArray = new ArrayList<>();
        seatArray.add(s1);
        seatArray.add(s2);
        seatArray.add(s3);
        seatArray.add(s4);
        seatArray.add(s5);
        seatArray.add(s6);
        seatArray.add(s7);
        seatArray.add(s8);
        seatArray.add(s9);
        seatArray.add(s10);
        seatArray.add(s11);
        seatArray.add(s12);
        seatArray.add(s13);
        seatArray.add(s14);

        int count = 1;
        for (int i = 1; i < 15; i++) {

            //33 seats total in main cabin
            //14 seats (7 rows in First Class
            Seat seat = new Seat();
            seat.setVisible(true);
            seat.setIschoosed(false);
            String sInfo = seatArray.get(count - 1);
            if (sInfo.equals("0")) {
                seat.setIsselected(false);
                seat.setType(seat.unselectedtype);
            } else {
                seat.setIsselected(true);
                seat.setType(seat.reservedtype);
            }

            seat.setId("S" + count);

            count++;

            seatList.add(seat);


        }

        adapter = new FirstClassSeatAdaptor(seatList, getContext(), numTicket);
        recyclerView.setAdapter(adapter);

    }
}
