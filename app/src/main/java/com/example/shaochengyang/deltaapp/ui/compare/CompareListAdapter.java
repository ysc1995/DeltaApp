package com.example.shaochengyang.deltaapp.ui.compare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.network.comparedemo.model.DemoItem;
import com.example.shaochengyang.deltaapp.ui.selectflight.FlightListAdapter;

import java.util.List;

public class CompareListAdapter extends RecyclerView.Adapter<CompareListAdapter.MyViewHolder> {

    String depart_airport, arrive_airport;

    public interface OnItemClickListener {
        void onItemClick(DemoItem bus);
    }

    private List<DemoItem> demoItemList;
    private CompareListAdapter.OnItemClickListener listener;

    public CompareListAdapter(List<DemoItem> list, CompareListAdapter.OnItemClickListener listener,
                             String depart_airport, String arrive_airport) {
        demoItemList = list;
        this.listener = listener;
        this.depart_airport = depart_airport;
        this.arrive_airport = arrive_airport;
    }


    @NonNull
    @Override
    //where we going to create the view holder, and set layout, etc
    //ViewGroup == xml, view == button.
    public CompareListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compareflights,
                parent, false);

        return new CompareListAdapter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.price.setText("$"+demoItemList.get(position).getDemoPrice());
        holder.duration.setText(demoItemList.get(position).getDemoDuration());
        holder.route.setText(demoItemList.get(position).getDemoName());
        holder.stopDuration.setText(demoItemList.get(position).getDemoStopDuration());
        holder.numofStops.setText(demoItemList.get(position).getDemoStop());

        holder.bind(demoItemList.get(position),listener);

    }



    @Override
    //get the size of list
    public int getItemCount() {
        return demoItemList.size();
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView price, duration, route, numofStops, stopDuration;

        public MyViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_sf_price);
            duration = itemView.findViewById(R.id.tv_sf_duarion);
            route = itemView.findViewById(R.id.tv_sf_route);
            numofStops = itemView.findViewById(R.id.tv_sf_numofstops);
            stopDuration = itemView.findViewById(R.id.tv_sf_stopDuration);

        }

        public void bind(final DemoItem bus, final CompareListAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(bus);

                }
            });
        }


    }
}