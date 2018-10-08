package com.example.shaochengyang.deltaapp.ui.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.database.MyFlightTicketContract.MyFlightTicketEntry;
import com.example.shaochengyang.deltaapp.ui.data.database.CustomerFlightContract.CustomerFlightEntry;

import java.util.List;

public class DbHelper implements IDbHelper {
    private static final String TAG = "DbHelper";

    DatabaseOpenHelper openHelper;
    SQLiteDatabase scdatabase;

    public DbHelper(Context context) {
        openHelper = new DatabaseOpenHelper(context);
        scdatabase = openHelper.getWritableDatabase();
    }

    @Override
    public void addRow(IDataManager.onDatabaseListener onDatabaseListener, String cityName, String cityLati, String cityLong) {
        //scdatabase = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.CITY_NAMES,cityName);
        values.put(DatabaseOpenHelper.CITY_LATI,cityLati);
        values.put(DatabaseOpenHelper.CITY_LONG,cityLong);

        scdatabase.insert(openHelper.SCTABLE,null,values);
    }

    @Override
    public void getCityPosition(IDataManager.onDatabaseListener onDatabaseListener, String fromCity, String toCity) {
        //scdatabase=openHelper.getWritableDatabase();
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

    @Override
    public void addPurchasedTicketToDB(
            IDataManager.onPurchasedTicketListener listener, FlightTicket ticket) {

        ContentValues values = new ContentValues();
        values.put(MyFlightTicketEntry.FticketID, ticket.getTicketID());
        values.put(MyFlightTicketEntry.FnumOfPassenger, ticket.getNumOfPassenger());
        values.put(MyFlightTicketEntry.Fnumber, ticket.getFlightDetails().getBusid());
        values.put(MyFlightTicketEntry.Fcabin, ticket.getFlightDetails().getBustype());
        values.put(MyFlightTicketEntry.Fprice, ticket.getTotalPrice());
        values.put(MyFlightTicketEntry.FdepAirport, ticket.getDepartAirport());
        values.put(MyFlightTicketEntry.FarrAirport, ticket.getArriveAirport());
        values.put(MyFlightTicketEntry.FdepTime,
                ticket.getFlightDetails().getBusdeparturetime());
        values.put(MyFlightTicketEntry.FarrTime, ticket.getFlightDetails().getDropingtime());
        values.put(MyFlightTicketEntry.Fduration, ticket.getFlightDetails().getJournyduration());

        Log.d(TAG, "ticket added to db: ");
        scdatabase.insert(MyFlightTicketEntry.TABLE_NAME,null,values);
        listener.addedTicketToDb(true);
    }

    @Override
    public void linkTicketToCustomerDB(IDataManager.onPurchasedTicketListener listener, List<CustomerFlight> customerFlights) {

        for(int i = 0; i<customerFlights.size(); i++){
            ContentValues values = new ContentValues();
            values.put(CustomerFlightEntry.CusFname, customerFlights.get(i).getFname());
            values.put(CustomerFlightEntry.CusLname, customerFlights.get(i).getLname());
            values.put(CustomerFlightEntry.CusPassport, customerFlights.get(i).getPassport());
            values.put(CustomerFlightEntry.FticketID, customerFlights.get(i).getTicketNum());

            scdatabase.insert(CustomerFlightEntry.TABLE_NAME, null, values);
        }
        listener.linkedTicketToCustomerDb(true);

    }

    @Override
    public void sendPurchasedTicketToDBWithCustomerInfo(IDataManager.onPurchasedTicketListener listener, List<CustomerFlight> customerFlights, FlightTicket ticket) {

        for(int i = 0; i<customerFlights.size(); i++){
            ContentValues values = new ContentValues();
            values.put(CustomerFlightEntry.CusFname, customerFlights.get(i).getFname());
            values.put(CustomerFlightEntry.CusLname, customerFlights.get(i).getLname());
            values.put(CustomerFlightEntry.CusPassport, customerFlights.get(i).getPassport());
            values.put(CustomerFlightEntry.FticketID, customerFlights.get(i).getTicketNum());

            scdatabase.insert(CustomerFlightEntry.TABLE_NAME, null, values);
        }

        ContentValues values = new ContentValues();
        values.put(MyFlightTicketEntry.FticketID, ticket.getTicketID());
        values.put(MyFlightTicketEntry.FnumOfPassenger, ticket.getNumOfPassenger());
        values.put(MyFlightTicketEntry.Fnumber, ticket.getFlightDetails().getBusid());
        values.put(MyFlightTicketEntry.Fcabin, ticket.getFlightDetails().getBustype());
        values.put(MyFlightTicketEntry.Fprice, ticket.getTotalPrice());
        values.put(MyFlightTicketEntry.FdepAirport, ticket.getDepartAirport());
        values.put(MyFlightTicketEntry.FarrAirport, ticket.getArriveAirport());
        values.put(MyFlightTicketEntry.FdepTime,
                ticket.getFlightDetails().getBusdeparturetime());
        values.put(MyFlightTicketEntry.FarrTime, ticket.getFlightDetails().getDropingtime());
        values.put(MyFlightTicketEntry.Fduration, ticket.getFlightDetails().getJournyduration());

        Log.d(TAG, "ticket added to db: ");
        scdatabase.insert(MyFlightTicketEntry.TABLE_NAME,null,values);

        listener.linkedTicketToCustomerDb(true);
    }
}