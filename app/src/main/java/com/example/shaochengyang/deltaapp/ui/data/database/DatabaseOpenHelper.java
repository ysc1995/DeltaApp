package com.example.shaochengyang.deltaapp.ui.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shaochengyang.deltaapp.ui.data.database.CustomerFlightContract.CustomerFlightEntry;
import com.example.shaochengyang.deltaapp.ui.data.database.MyFlightTicketContract.MyFlightTicketEntry;
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String MYDATABASE = "DeltaDB";
    public static final int VERSION = 5;
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
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + CITY_NAMES + " TEXT,"
                + CITY_LATI + " TEXT,"
                + CITY_LONG + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);

        String CREATE_CUSTOMERFLIGHT_TABLE = "CREATE TABLE "
                + CustomerFlightEntry.TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + CustomerFlightEntry.CusEmail + " TEXT,"
                + CustomerFlightEntry.CusFname + " TEXT,"
                + CustomerFlightEntry.CusLname + " TEXT,"
                + CustomerFlightEntry.CusPassport + " TEXT,"
                + CustomerFlightEntry.FticketID + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CUSTOMERFLIGHT_TABLE);

        String CREATE_MyFlightTicket_TABLE = "CREATE TABLE "
                + MyFlightTicketEntry.TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + MyFlightTicketEntry.FticketID + " TEXT,"
                + MyFlightTicketEntry.FnumOfPassenger + " TEXT,"
                + MyFlightTicketEntry.Fnumber + " TEXT,"
                + MyFlightTicketEntry.Fcabin + " TEXT,"
                + MyFlightTicketEntry.Fprice + " TEXT,"
                + MyFlightTicketEntry.FdepAirport + " TEXT,"
                + MyFlightTicketEntry.FarrAirport + " TEXT,"
                + MyFlightTicketEntry.FdepTime + " TEXT,"
                + MyFlightTicketEntry.FarrTime + " TEXT,"
                + MyFlightTicketEntry.Fduration + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_MyFlightTicket_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCTABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MyFlightTicketEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CustomerFlightEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
