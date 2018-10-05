package com.example.shaochengyang.deltaapp.ui.data.database;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public interface IDbHelper {

    void addRow(IDataManager.onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong);

    void getCityPosition(IDataManager.onDatabaseListener onDatabaseListener, String fromCity, String toCity);
}