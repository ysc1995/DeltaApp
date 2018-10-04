package com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat;

import android.content.Context;
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

public class EcoSeatAdaptor extends RecyclerView.Adapter<EcoSeatAdaptor.MyViewHolder> {

    private static final String TAG = "CollectionListAdaptor";


    List<Seat> seatList;
    Context context;
    int numofTicket;
    int count;
    //CustomItemListener customItemListener;



    public EcoSeatAdaptor(List<Seat> list, Context context, int numofTicket) {
        seatList = list;
        this.context = context;
        this.numofTicket = numofTicket;
        count=0;
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

        if(!seatList.get(position).isVisible()) {
            holder.img.setImageResource(R.drawable.black_img);

           

        }
        else{
            if(seatList.get(position).getIschoosed()){
                holder.img.setImageResource(seatList.get(position).selectedtype);
            }
            else {
                holder.img.setImageResource(seatList.get(position).getType());
            }
        }
        holder.textView.setText(seatList.get(position).getId());

        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(count< numofTicket){
                    if(seatList.get(position).isVisible()) {
                        if (!seatList.get(position).getIsselected()) {

                            if (seatList.get(position).getType() == seatList.get(position).unselectedtype) {
                                holder.img.setImageResource(seatList.get(position).selectedtype);
                                seatList.get(position).setIschoosed(true);
                                count++;
                            } else {
                                holder.img.setImageResource(seatList.get(position).unselectedtype);
                            }
                        }
                    }
                }
            }
        });


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
