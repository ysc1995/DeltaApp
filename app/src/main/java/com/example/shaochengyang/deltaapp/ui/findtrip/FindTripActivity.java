package com.example.shaochengyang.deltaapp.ui.findtrip;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.shaochengyang.deltaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindTripActivity extends AppCompatActivity {
    TabLayout tabLayout;
    @BindView(R.id.tv_fname)
    EditText tvFname;
    @BindView(R.id.tv_lname)
    EditText tvLname;
    @BindView(R.id.tv_confirmation)
    EditText tvConfirmation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trip);
        ButterKnife.bind(this);


        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String fName = tvFname.getText().toString();
        String lName = tvLname.getText().toString();
        String confirmation = tvConfirmation.getText().toString();


    }
}
