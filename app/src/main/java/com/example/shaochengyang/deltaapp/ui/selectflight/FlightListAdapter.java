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


    public interface OnItemClickListener {
        void onItemClick(BusinformationItem bus);
    }

    private List<BusinformationItem> businformationItemList;
    private OnItemClickListener listener;

    public FlightListAdapter(List<BusinformationItem> list, OnItemClickListener listener) {
        businformationItemList = list;
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
        holder.price.setText(businformationItemList.get(position).getFare());
        holder.bind(businformationItemList.get(position), listener);
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return businformationItemList.size();
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_sf_price);
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
