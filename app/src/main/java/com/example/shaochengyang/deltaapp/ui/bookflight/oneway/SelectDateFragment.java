package com.example.shaochengyang.deltaapp.ui.bookflight.oneway;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;

import org.w3c.dom.Text;

import java.util.Calendar;


public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        TextView clickDate = (TextView)getActivity().findViewById(R.id.textView7);
        clickDate.setText(month+"/"+day+"/"+year);
    }



}