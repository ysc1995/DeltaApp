package com.example.shaochengyang.deltaapp.ui.data.model;

public class TicketInfo {
    String ticketID ;
    String arrAirport ;
    String depAirport;
    String cabin ;
    String depTime ;
    String arrTime ;
    String flightNum ;
    String flightDuration ;

    public TicketInfo(String ticketID, String arrAirport, String depAirport, String cabin, String depTime, String arrTime, String flightNum, String flightDuration) {
        this.ticketID = ticketID;
        this.arrAirport = arrAirport;
        this.depAirport = depAirport;
        this.cabin = cabin;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.flightNum = flightNum;
        this.flightDuration = flightDuration;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }
}
