package com.example.shaochengyang.deltaapp.ui.seatreserve;

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
import com.example.shaochengyang.deltaapp.ui.seatreserve.model.Seat;

import java.util.List;

public class SeatAdaptor extends RecyclerView.Adapter<SeatAdaptor.MyViewHolder> {

    private static final String TAG = "CollectionListAdaptor";


    List<Seat> seatList;
    Context context;
    //CustomItemListener customItemListener;



    public SeatAdaptor(List<Seat> list, Context context) {
        seatList = list;
        this.context = context;

    }


    @NonNull
    @Override
    //where we going to create the view holder, and set layout, etc
    //ViewGroup == xml, view == button.
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,
                parent,false);
        /*final MyViewHolder myholder = new MyViewHolder(rootView);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onadaptorClick: "+myholder.getLayoutPosition());
                customItemListener.onItemClick(rootView,myholder.getLayoutPosition(),collectonList);

            }
        });*/
        return new MyViewHolder(rootView);
    }

    @Override
    //bind data with the holder
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.img.setImageResource(seatList.get(position).getType());
        holder.textView.setText(seatList.get(position).getId());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seatList.get(position).getIsselected()) {
                    if (seatList.get(position).getType() == R.drawable.double_arrow) {
                        holder.img.setImageResource(seatList.get(position).selectedtype);
                    } else {
                        holder.img.setImageResource(seatList.get(position).unselectedtype);
                    }
                }
            }
        });
       /* holder.tv_name.setText(collectonList.get(position).getCname());
        //holder.tv_desccription.setText(collectonList.get(position).getCdiscription());

        Picasso.get()
                .load(collectonList.get(position).getCimagerl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.img);*/


       /* holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,SubCategoryActivity.class);
                i.putExtra("cid",collectonList.get(position).getCid());
                context.startActivity(i);


            }
        });*/
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return seatList.size();
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView img;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_category);
            textView = itemView.findViewById(R.id.textView2);

        }


    }

}
