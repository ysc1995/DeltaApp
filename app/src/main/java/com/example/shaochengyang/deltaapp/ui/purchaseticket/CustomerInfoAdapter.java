package com.example.shaochengyang.deltaapp.ui.purchaseticket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.selectflight.FlightListAdapter;
import com.example.shaochengyang.deltaapp.ui.utility.validator.NonEmptyValidator;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CustomerInfoAdapter extends RecyclerView.Adapter<CustomerInfoAdapter.MyViewHolder> {

    private static final String TAG = "CustomerInfoAdapter";
    int numOfPassenger;


    public CustomerInfoAdapter(int numOfPassenger) {

        this.numOfPassenger = numOfPassenger;
    }


    @NonNull
    @Override
    //where we going to create the view holder, and set layout, etc
    //ViewGroup == xml, view == button.
    public CustomerInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cusflightinfo,
                parent, false);

        return new CustomerInfoAdapter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerInfoAdapter.MyViewHolder holder, int position) {
        String pos = String.valueOf(position+1);
        holder.passengerNum.setText("Passenger " + pos);
    }

    @Override
    //get the size of list
    public int getItemCount() {
        return numOfPassenger;
    }


    //holding the item and the view.
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView passengerNum;
        EditText fname, lname, passport;

        public MyViewHolder(View itemView) {
            super(itemView);
            passengerNum = itemView.findViewById(R.id.tv_passenger_num);
            fname = itemView.findViewById(R.id.txt_fname);
            lname = itemView.findViewById(R.id.txt_lname);
            passport = itemView.findViewById(R.id.txt_passport);

            RxValidator.createFor(fname)
                    .nonEmpty()
                    .with(new NonEmptyValidator())
                    .minLength(2, "Min length is 2")
                    .onValueChanged()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RxValidationResult<EditText>>() {
                        @Override public void call(RxValidationResult<EditText> result) {
                            result.getItem().setError(result.isProper() ? null : result.getMessage());
                            Log.i(TAG, "Validation result " + result.toString());
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            Log.e(TAG, "Validation error", throwable);
                        }
                    });

            RxValidator.createFor(lname)
                    .nonEmpty()
                    .with(new NonEmptyValidator())
                    .minLength(2, "Min length is 2")
                    .onValueChanged()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RxValidationResult<EditText>>() {
                        @Override public void call(RxValidationResult<EditText> result) {
                            result.getItem().setError(result.isProper() ? null : result.getMessage());
                            Log.i(TAG, "Validation result " + result.toString());
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            Log.e(TAG, "Validation error", throwable);
                        }
                    });

            RxValidator.createFor(passport)
                    .nonEmpty()
                    .with(new NonEmptyValidator())
                    .minLength(6, "Min length is 6")
                    .onValueChanged()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RxValidationResult<EditText>>() {
                        @Override public void call(RxValidationResult<EditText> result) {
                            result.getItem().setError(result.isProper() ? null : result.getMessage());
                            Log.i(TAG, "Validation result " + result.toString());
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            Log.e(TAG, "Validation error", throwable);
                        }
                    });

        }

    }
}
