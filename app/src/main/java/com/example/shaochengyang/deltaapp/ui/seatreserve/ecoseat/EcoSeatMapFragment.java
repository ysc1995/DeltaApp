package com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat;

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
import com.example.shaochengyang.deltaapp.ui.seatreserve.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class EcoSeatMapFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Seat> seatList;
    Button clearButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_seatsmap, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);


        clearButton=view.findViewById(R.id.clearButton);
        RecyclerView.LayoutManager manager = new GridLayoutManager(view.getContext(),4);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        seatList = new ArrayList<>();

        for (int i = 0 ; i < 47 ; i ++){
            //33 seats total in main cabin
            //14 seats (7 rows in First Class
            Seat seat = new Seat();
            seat.setId(""+i);
            seat.setIsselected(false);
            seat.setIschoosed(false);
            if(i%4==2||i==24||i==28){  //seat id = 19 & 23 set for the door
                seat.setVisible(false);
            }else{
                seat.setVisible(true);
            }
            if(i==3||i==4){
                seat.setIsselected(true);
                seat.setType(seat.reservedtype);
            }else {
                seat.setType(seat.unselectedtype);
            }
            seatList.add(seat);
        }

        //TODO get the parameter from booking ticket for numofTicket
        final int numofTicket = 2;

        adapter = new EcoSeatAdaptor(seatList, view.getContext() , numofTicket);
        recyclerView.setAdapter(adapter);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0 ; i < 47 ; i ++){
                    seatList.get(i).setIschoosed(false);
                }
                    int numticket = numofTicket;
                    adapter = new EcoSeatAdaptor(seatList, view.getContext(), numticket);
                    recyclerView.setAdapter(adapter);

            }
        });

        return view;
    }
}
