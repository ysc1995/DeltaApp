package com.example.shaochengyang.deltaapp.ui.data.database;

import android.provider.BaseColumns;

public class CustomerFlightContract {
    public CustomerFlightContract() {
    }

    public static abstract class CustomerFlightEntry implements BaseColumns {
        public static final String TABLE_NAME = "CustomerFlightContract";
        public static final String CusEmail = "CusEmail";
        public static final String CusFname = "CusFirstname";
        public static final String CusLname = "CusLastname";
        public static final String CusPassport = "CusPassport";
        public static final String FticketID = "TicketID";

    }
}
