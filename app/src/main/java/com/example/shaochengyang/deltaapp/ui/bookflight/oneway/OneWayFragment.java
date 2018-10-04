package com.example.shaochengyang.deltaapp.ui.bookflight.oneway;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneWayFragment extends Fragment implements IOneWayFragmentView {

    @BindView(R.id.btn_bf_minus)
    Button btnBfMinus;
    @BindView(R.id.btn_bf_plus)
    Button btnBfPlus;
    Unbinder unbinder;
    @BindView(R.id.txt_bf_nums)
    TextView txtBfNums;
    @BindView(R.id.tv_bf_title1)
    TextView tvBfTitle1;
    @BindView(R.id.tv_bf_title2)
    TextView tvBfTitle2;
    @BindView(R.id.tv_bf_miles)
    TextView tvBfMiles;
    @BindView(R.id.tv_bf_money)
    TextView tvBfMoney;
    @BindView(R.id.tv_bf_find)
    TextView tvBfFind;

    IOneWayFragmentPresenter oneWayFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one_way, container, false);

        unbinder = ButterKnife.bind(this, view);

        oneWayFragmentPresenter = new OneWayFragmentPresenter(this);

        String num = txtBfNums.getText().toString();
        int numberOfTicket = Integer.parseInt(num);
        if(numberOfTicket<2){
            btnBfMinus.setClickable(false);
        }
        if(numberOfTicket>5){
            btnBfPlus.setClickable(false);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_bf_minus, R.id.btn_bf_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bf_minus:
                oneWayFragmentPresenter.minusOneTicket();
                break;
            case R.id.btn_bf_plus:
                oneWayFragmentPresenter.plusOneTicket();
                break;
        }
    }

    @Override
    public void minusOneTicket() {
        String num = txtBfNums.getText().toString();
        btnBfPlus.setClickable(true);
        int numberOfTicket = Integer.parseInt(num);
        if(numberOfTicket<2){
            btnBfMinus.setClickable(false);
        }
        else {
            numberOfTicket--;
        }
        txtBfNums.setText(String.valueOf(numberOfTicket));
        if(numberOfTicket==1){
            btnBfMinus.setClickable(false);
        }
    }

    @Override
    public void plusOneTicket() {
        String num = txtBfNums.getText().toString();
        btnBfMinus.setClickable(true);
        int numberOfTicket = Integer.parseInt(num);

        if(numberOfTicket<6){
            numberOfTicket++;
            txtBfNums.setText(String.valueOf(numberOfTicket));
        }
        else{
            btnBfPlus.setClickable(false);
        }

        if(numberOfTicket==6){
            btnBfPlus.setClickable(false);
        }
    }
}
