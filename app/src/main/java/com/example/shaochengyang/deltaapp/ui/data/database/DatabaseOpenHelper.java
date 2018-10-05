package com.example.shaochengyang.deltaapp.ui.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String MYDATABASE = "DeltaDB";
    public static final int VERSION = 1;
    public static final String SCTABLE = "DCityTable";
    public static final String KEY_ID = "key_id";

    public static final String CITY_NAMES = "cityname";
    public static final String CITY_LATI = "Citylatitude";
    public static final String CITY_LONG = "Citylongtitude";




    public DatabaseOpenHelper(Context context) {
        super(context,MYDATABASE,null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + SCTABLE + "("

                + KEY_ID + " INTEGER PRIMARY KEY," + CITY_NAMES + " TEXT," + CITY_LATI + " TEXT," + CITY_LONG + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCTABLE);
        onCreate(sqLiteDatabase);
    }
}
