package com.example.shaochengyang.deltaapp.ui.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.CustomerFlight;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.database.MyFlightTicketContract.MyFlightTicketEntry;
import com.example.shaochengyang.deltaapp.ui.data.database.CustomerFlightContract.CustomerFlightEntry;
import com.example.shaochengyang.deltaapp.ui.data.model.MyFlightTicket;
import com.example.shaochengyang.deltaapp.ui.data.model.PassenterInfo;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements IDbHelper {
    private static final String TAG = "DbHelper";

    DatabaseOpenHelper openHelper;
    SQLiteDatabase scdatabase;
    SharedPreferences sharedPreferences;

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
    public void sendPurchasedTicketToDBWithCustomerInfo(IDataManager.onPurchasedTicketListener listener, List<CustomerFlight> customerFlights, FlightTicket ticket) {

        for(int i = 0; i<customerFlights.size(); i++){
            ContentValues values = new ContentValues();
            values.put(CustomerFlightEntry.CusEmail, customerFlights.get(i).getEmail());
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

    @Override
    public void getMyFlightListFromDb(IDataManager.onUpcomingFlightListener listener) {
        List<MyFlightTicket> myFlightTickets = new ArrayList<>();
        Log.d(TAG, "getMyFlightListFromDb: ++++++++++++++++++++++++++++++");
        Cursor cursor = scdatabase.rawQuery("SELECT * FROM " + MyFlightTicketEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                //Log.d(TAG, "readProductListFromShoppingCartDB: ");
                String ticketId = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FticketID));
                String numOfPass = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FnumOfPassenger));
                String flightnum = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fnumber));
                String cabin = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fcabin));
                String price = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fprice));
                String depAirport = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FdepAirport));
                String arrAirport = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FarrAirport));
                String depTime = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FdepTime));
                String arrTime = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FarrTime));
                String fduration = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fduration));

                MyFlightTicket ticket = new MyFlightTicket(ticketId, numOfPass,
                        flightnum, cabin, price, depAirport, arrAirport, depTime,arrTime, fduration);
                Log.d(TAG, "getMyFlightListFromDb: " + ticketId);
                myFlightTickets.add(ticket);

            } while (cursor.moveToNext());
            listener.bindMyFlightListToView(myFlightTickets);
        }


    }

    @Override
    public void updateTicket(IDataManager.onUpdatingTicketListener onUpdatingTicketListener, String id) {
        ContentValues values = new ContentValues();
        values.put(CustomerFlightEntry.FticketID, id);

        scdatabase.update(CustomerFlightEntry.TABLE_NAME, values, CustomerFlightEntry.FticketID + "=" + "'AA135'", null);
        scdatabase.update(MyFlightTicketEntry.TABLE_NAME, values, CustomerFlightEntry.FticketID + "=" + "'AA135'", null);

    }

    @Override
    public void storeSeatID(String id, String ticketID) {
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.STICKET_ID,ticketID);
        values.put(DatabaseOpenHelper.SSEAT_ID,id);


        scdatabase.insert(openHelper.STABLE,null,values);
    }





    @Override
    public void getPassenterInfo(IDataManager.onPassenterInfoListener onPassenterInfoListener, String ticketID) {
        List<PassenterInfo> passenterInfoList = new ArrayList<>();
        Cursor cursor = scdatabase.rawQuery("SELECT * FROM " + CustomerFlightEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            String tid = cursor.getString(cursor.getColumnIndex(CustomerFlightEntry.FticketID));
            String fname = cursor.getString(cursor.getColumnIndex(CustomerFlightEntry.CusFname));
            String lname = cursor.getString(cursor.getColumnIndex(CustomerFlightEntry.CusLname));
            String passport = cursor.getString(cursor.getColumnIndex(CustomerFlightEntry.CusPassport));


            if(tid.equals(ticketID)){
                PassenterInfo passenterInfo = new PassenterInfo(fname,lname,passport);
                passenterInfoList.add(passenterInfo);
            }


        }while(cursor.moveToNext());

        onPassenterInfoListener.passPassengerInfo(passenterInfoList);
    }

    @Override
    public void getSeatInfo(IDataManager.onSeatInfoListener infoListener, String ticketID) {
        List<String> seatList = new ArrayList<>();
        Cursor cursor = scdatabase.rawQuery("SELECT * FROM " + openHelper.STABLE, null);
        cursor.moveToFirst();
        do{
            String tid = cursor.getString(cursor.getColumnIndex(openHelper.STICKET_ID));
            String seatid = cursor.getString(cursor.getColumnIndex(openHelper.SSEAT_ID));


            if(tid.equals(ticketID)){
                seatList.add(seatid);
            }


        }while(cursor.moveToNext());
        infoListener.passSeatInfo(seatList);
    }

    @Override
    public void getFlightInfo(IDataManager.onFlightInfoListener onFlightInfoListener, String ticketID) {
        MyFlightTicket ticket = null;
        Cursor cursor = scdatabase.rawQuery("SELECT * FROM " + MyFlightTicketEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                //Log.d(TAG, "readProductListFromShoppingCartDB: ");
                String tId = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FticketID));
                String numOfPass = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FnumOfPassenger));
                String flightnum = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fnumber));
                String cabin = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fcabin));
                String price = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fprice));
                String depAirport = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FdepAirport));
                String arrAirport = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FarrAirport));
                String depTime = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FdepTime));
                String arrTime = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.FarrTime));
                String fduration = cursor.getString(cursor.getColumnIndex(MyFlightTicketEntry.Fduration));

                if(tId.equals(ticketID)){
                    ticket = new MyFlightTicket(tId, numOfPass,
                            flightnum, cabin, price, depAirport, arrAirport, depTime,arrTime, fduration);
                    break;
                }



            } while (cursor.moveToNext());
            onFlightInfoListener.passFlightInfo(ticket);

        }
    }
}