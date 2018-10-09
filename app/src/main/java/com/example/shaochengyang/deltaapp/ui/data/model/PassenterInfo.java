package com.example.shaochengyang.deltaapp.ui.data.model;

public class PassenterInfo {
    String fname,lname,passport;

    public PassenterInfo(String fname, String lname, String passport) {
        this.fname = fname;
        this.lname = lname;
        this.passport = passport;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
