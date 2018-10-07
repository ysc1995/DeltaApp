package com.example.shaochengyang.deltaapp.ui.data.database;

import android.provider.BaseColumns;

public class MyFlightTicketContract {

    public MyFlightTicketContract() {
    }

    public static abstract class MyFlightTicketEntry implements BaseColumns{
        public static final String TABLE_NAME = "FlightTicket";
        public static final String FticketID = "TicketID";
        public static final String Fnumber = "FlightNum";
        public static final String Fcabin = "CabinType";
        public static final String Fprice = "Price";
        public static final String FdepAirport = "DepartAirport";
        public static final String FarrAirport = "ArriveAirport";
        public static final String FdepTime = "DepartTime";
        public static final String FarrTime = "ArriveTime";
        public static final String Fduration = "Duration";

    }
}
