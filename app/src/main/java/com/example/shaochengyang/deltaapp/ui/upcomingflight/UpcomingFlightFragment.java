package com.example.shaochengyang.deltaapp.ui.upcomingflight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;
import com.example.shaochengyang.deltaapp.ui.flightconfirmation.ConfirmationPageActivity;
import com.example.shaochengyang.deltaapp.ui.upcomingflightconfirm.UpComingFlightConfirmActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingFlightFragment extends Fragment implements IUpcomingFlightView {

    private static final String TAG = "UpcomingFlightFragment";
    RecyclerView rvMyflight;
    RecyclerView.Adapter adapter;
    IUpcomingFlightPresenter iUpcomingFlightPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_upcomingflight, container, false);

        rvMyflight = view.findViewById(R.id.rv_myflight);

        iUpcomingFlightPresenter = new UpcomingFlightPresenter(this);

        iUpcomingFlightPresenter.getFlightFromDb();


        return view;
    }


    @Override
    public void bindMyFlightListToView(List<MyFlightTicket> myFlightTickets) {
        Log.d(TAG, "bindMyFlightListToView: " + myFlightTickets.get(0).getTicketId());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMyflight.setLayoutManager(layoutManager);
        rvMyflight.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyFlightListAdapter(myFlightTickets
                , new MyFlightListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyFlightTicket ticket) {
                Intent intent = new Intent(getActivity(), UpComingFlightConfirmActivity.class);



                intent.putExtra("ticketID",ticket.getTicketId());
                intent.putExtra("depAirport",ticket.getDepAirport());
                intent.putExtra("arrAirport",ticket.getArrAirport());
                intent.putExtra("cabin",ticket.getCabin());
                intent.putExtra("depTime",ticket.getDepTime());
                intent.putExtra("arrTime",ticket.getArrTime());
                intent.putExtra("flightNum",ticket.getFlightnum());
                intent.putExtra("flightDuration",ticket.getFduration());


                startActivity(intent);

                /*Toast.makeText(getActivity(), "" +ticket.getTicketId(),
                        Toast.LENGTH_SHORT).show();*/
            }
        });
        rvMyflight.setAdapter(adapter);
    }
}
