package com.example.shaochengyang.deltaapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public class TestServerActivity extends AppCompatActivity implements IDataManager.onSeatInformationListener {

    IDataManager iDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_server);

        iDataManager = new DataManager(this);

        iDataManager.getSeatInformation(this);
    }
}
