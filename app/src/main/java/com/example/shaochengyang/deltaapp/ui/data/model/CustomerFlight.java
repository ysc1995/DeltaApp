package com.example.shaochengyang.deltaapp.ui.data.model;

public class CustomerFlight {
    String email, fname, lname, passport, ticketNum;

    public CustomerFlight(String email, String fname, String lname, String passport, String ticketNum) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.passport = passport;
        this.ticketNum = ticketNum;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPassport() {
        return passport;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public String getEmail() {
        return email;
    }
}
