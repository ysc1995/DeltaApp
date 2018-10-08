package com.example.shaochengyang.deltaapp.ui.data.model;

public class MyFlightTicket {
    String ticketId, create_time, numOfPass, flightnum, cabin,
            price, depAirport, arrAirport, depTime,arrTime, fduration;

    public MyFlightTicket(String ticketId,
                          String numOfPass, String flightnum,
                          String cabin, String price, String depAirport,
                          String arrAirport, String depTime, String arrTime,
                          String fduration) {
        this.ticketId = ticketId;
        //this.create_time = create_time;
        this.numOfPass = numOfPass;
        this.flightnum = flightnum;
        this.cabin = cabin;
        this.price = price;
        this.depAirport = depAirport;
        this.arrAirport = arrAirport;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.fduration = fduration;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getNumOfPass() {
        return numOfPass;
    }

    public String getFlightnum() {
        return flightnum;
    }

    public String getCabin() {
        return cabin;
    }

    public String getPrice() {
        return price;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public String getFduration() {
        return fduration;
    }
}
