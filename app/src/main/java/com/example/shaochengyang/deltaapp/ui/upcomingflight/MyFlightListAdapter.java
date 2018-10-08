package com.example.shaochengyang.deltaapp.ui.upcomingflight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;

import java.util.List;

public class MyFlightListAdapter extends RecyclerView.Adapter<MyFlightListAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MyFlightTicket ticket);
    }

    private List<MyFlightTicket> flightTickets;
    private OnItemClickListener listener;

    public MyFlightListAdapter(List<MyFlightTicket> list, OnItemClickListener listener) {
        flightTickets = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    //where we going to create the view holder, and set layout, etc
    //ViewGroup == xml, view == button.
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flights,
                parent, false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.price.setText("$" + flightTickets.get(position).getPrice());
        holder.duration.setText(flightTickets.get(position).getDepTime() + " > " +
                flightTickets.get(position).getArrTime());
        holder.route_duration.setText(flightTickets.get(position).getDepAirport()
                + " > " + flightTickets.get(position).getArrAirport() +
                "\nduration: " + flightTickets.get(position).getFduration());
        holder.bind(flightTickets.get(position), listener);
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return flightTickets.size();
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView price, duration, route_duration;

        public MyViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_sf_price);
            duration = itemView.findViewById(R.id.tv_sf_duarion);
            route_duration = itemView.findViewById(R.id.tv_sf_route);
        }

        public void bind(final MyFlightTicket ticket, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(ticket);

                }
            });
        }


    }
}
