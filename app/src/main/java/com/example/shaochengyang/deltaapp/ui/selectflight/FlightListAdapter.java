package com.example.shaochengyang.deltaapp.ui.selectflight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.MyViewHolder> {

    String depart_airport, arrive_airport;

    public interface OnItemClickListener {
        void onItemClick(BusinformationItem bus);
    }

    private List<BusinformationItem> businformationItemList;
    private OnItemClickListener listener;

    public FlightListAdapter(List<BusinformationItem> list, OnItemClickListener listener,
                             String depart_airport, String arrive_airport) {
        businformationItemList = list;
        this.listener = listener;
        this.depart_airport = depart_airport;
        this.arrive_airport = arrive_airport;
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
        holder.price.setText("$" + businformationItemList.get(position).getFare());
        holder.duration.setText(businformationItemList.get(position).getBoardingtime() + " > " +
                businformationItemList.get(position).getDropingtime());
        holder.route_duration.setText(depart_airport + " > " + arrive_airport +
        "/" + "\nduration: " + businformationItemList.get(position).getJournyduration());

        holder.bind(businformationItemList.get(position), listener);
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return businformationItemList.size();
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

        public void bind(final BusinformationItem bus, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(bus);

                }
            });
        }


    }
}
