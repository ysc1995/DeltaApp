package com.example.shaochengyang.deltaapp.ui.seatreserve;

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

public class SeatMapFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Seat> seatList;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_seatsmap, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        button=view.findViewById(R.id.button);
        RecyclerView.LayoutManager manager = new GridLayoutManager(view.getContext(),5);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        seatList = new ArrayList<>();

        for (int i = 0 ; i < 8 ; i ++){
            Seat seat = new Seat();
            seat.setId(""+i);
            seat.setIsselected(false);
            if(i==3||i==4){
                seat.setIsselected(true);
            }
            seat.setType(seat.selectedtype);
            seatList.add(seat);
        }

        adapter = new SeatAdaptor(seatList, view.getContext());
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0 ; i < 8 ; i ++){
                    adapter = new SeatAdaptor(seatList, view.getContext());
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        return view;
    }
}
