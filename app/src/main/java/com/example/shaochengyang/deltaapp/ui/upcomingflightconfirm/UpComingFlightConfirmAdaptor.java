package com.example.shaochengyang.deltaapp.ui.upcomingflightconfirm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.PassenterInfo;
import com.example.shaochengyang.deltaapp.ui.data.model.TicketInfo;

import java.util.List;

public class UpComingFlightConfirmAdaptor extends RecyclerView.Adapter<UpComingFlightConfirmAdaptor.MyViewHolder>{

    List<PassenterInfo> passenterInfoList;
    List<String> seatList;
    TicketInfo ticketInfo;
    Context context;

    public UpComingFlightConfirmAdaptor(List<PassenterInfo> passenterInfoList, List<String> seatList, TicketInfo ticketInfo, Context context) {
        this.passenterInfoList = passenterInfoList;
        this.seatList = seatList;
        this.ticketInfo = ticketInfo;
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcmconfirm,
                parent,false);

        return new MyViewHolder(rootView);
    }

    @Override
    //bind data with the holder
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_flightNumber.setText("FLIGHT NUMBER: DL"+ticketInfo.getFlightNum());
        holder.tv_depAirport.setText(ticketInfo.getDepAirport());
        holder.tv_arrAirport.setText(ticketInfo.getArrAirport());
        holder.tv_depTime.setText(ticketInfo.getDepTime());
        holder.tv_arrTime.setText(ticketInfo.getArrTime());
        holder.tv_duration.setText("FLIGHT DURATION: "+ticketInfo.getFlightDuration());
        holder.tv_passengerName.setText(passenterInfoList.get(position).getFname()+" "+passenterInfoList.get(position).getLname());
        holder.tv_passport.setText("PASSPORT #: "+passenterInfoList.get(position).getPassport());
        holder.tv_seatID.setText(seatList.get(position));
        holder.tv_confirmationNum.setText("  "+ticketInfo.getTicketID());
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return seatList.size();
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_confirmationNum,tv_flightNumber, tv_depTime,tv_arrTime,tv_depAirport,tv_arrAirport,tv_duration, tv_passengerName, tv_seatID, tv_passport;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_confirmationNum = itemView.findViewById(R.id.tv_fd_cabin);
            tv_flightNumber = itemView.findViewById(R.id.tv_fd_flightno);
            tv_depTime = itemView.findViewById(R.id.tv_fd_depart_time);
            tv_arrTime = itemView.findViewById(R.id.tv_fd_arrive_time);
            tv_depAirport = itemView.findViewById(R.id.tv_depart_airport);
            tv_arrAirport = itemView.findViewById(R.id.tv_arrive_airport);
            tv_duration = itemView.findViewById(R.id.tv_fd_duration);
            tv_passengerName = itemView.findViewById(R.id.tv_passName);
            tv_seatID = itemView.findViewById(R.id.tv_seatID);
            tv_passport = itemView.findViewById(R.id.tv_passport);
        }


    }

}


