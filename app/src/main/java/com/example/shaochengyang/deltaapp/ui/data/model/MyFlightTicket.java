package com.example.shaochengyang.deltaapp.ui.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyFlightTicket implements Parcelable {
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

    protected MyFlightTicket(Parcel in) {
        ticketId = in.readString();
        create_time = in.readString();
        numOfPass = in.readString();
        flightnum = in.readString();
        cabin = in.readString();
        price = in.readString();
        depAirport = in.readString();
        arrAirport = in.readString();
        depTime = in.readString();
        arrTime = in.readString();
        fduration = in.readString();
    }

    public static final Creator<MyFlightTicket> CREATOR = new Creator<MyFlightTicket>() {
        @Override
        public MyFlightTicket createFromParcel(Parcel in) {
            return new MyFlightTicket(in);
        }

        @Override
        public MyFlightTicket[] newArray(int size) {
            return new MyFlightTicket[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ticketId);
        dest.writeString(create_time);
        dest.writeString(numOfPass);
        dest.writeString(flightnum);
        dest.writeString(cabin);
        dest.writeString(price);
        dest.writeString(depAirport);
        dest.writeString(arrAirport);
        dest.writeString(depTime);
        dest.writeString(arrTime);
        dest.writeString(fduration);
    }
}
