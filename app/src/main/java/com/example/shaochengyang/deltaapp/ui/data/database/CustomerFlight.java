package com.example.shaochengyang.deltaapp.ui.data.database;

import android.provider.BaseColumns;

public class CustomerFlight {
    public CustomerFlight() {
    }

    public static abstract class CustomerFlightEntry implements BaseColumns {
        public static final String TABLE_NAME = "CustomerFlight";
        public static final String CusFname = "CusFirstname";
        public static final String CusLname = "CusLastname";
        public static final String FticketID = "TicketID";

    }
}
