package com.example.shaochengyang.deltaapp.ui.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public class DbHelper implements IDbHelper {
    DatabaseOpenHelper openHelper;
    SQLiteDatabase scdatabase;

    public DbHelper(Context context) {
        openHelper = new DatabaseOpenHelper(context);

    }

    @Override
    public void addRow(IDataManager.onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong) {
        scdatabase = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.CITY_NAMES,cityName);
        values.put(DatabaseOpenHelper.CITY_LATI,cityLati);
        values.put(DatabaseOpenHelper.CITY_LONG,cityLong);

        scdatabase.insert(openHelper.SCTABLE,null,values);
    }

    @Override
    public void getCityPosition(IDataManager.onDatabaseListener onDatabaseListener, String fromCity, String toCity) {
        scdatabase=openHelper.getWritableDatabase();
        Cursor cursor = scdatabase.rawQuery("SELECT * FROM " + openHelper.SCTABLE, null);
        cursor.moveToFirst();
        String fromCityLati = null, fromCityLong = null, toCityLati= null, toCityLong= null;
        do{
            String cityName = cursor.getString(cursor.getColumnIndex(openHelper.CITY_NAMES));
            String cityLati = cursor.getString(cursor.getColumnIndex(openHelper.CITY_LATI));
            String cityLong = cursor.getString(cursor.getColumnIndex(openHelper.CITY_LONG));

            if(cityName.equals(fromCity)){
                fromCityLati=cityLati;
                fromCityLong=cityLong;
            }
            if(cityName.equals(toCity)){
                toCityLati=cityLati;
                toCityLong=cityLong;
            }

        }while(cursor.moveToNext());

        onDatabaseListener.passCityPositions(fromCityLati,fromCityLong,toCityLati,toCityLong);
    }
}