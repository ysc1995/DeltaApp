package com.example.shaochengyang.deltaapp.ui.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightTicket implements Parcelable {
    String ticketID, numOfPassenger, departDate, returnDate
    ,departAirport, arriveAirport, totalPrice;

    BusinformationItem flightDetails;

    public FlightTicket(BusinformationItem flightDetails) {
        this.flightDetails = flightDetails;
    }



    protected FlightTicket(Parcel in) {
        ticketID = in.readString();
        numOfPassenger = in.readString();
        departDate = in.readString();
        returnDate = in.readString();
        departAirport = in.readString();
        arriveAirport = in.readString();
        totalPrice = in.readString();
        flightDetails = in.readParcelable(BusinformationItem.class.getClassLoader());
    }

    public static final Creator<FlightTicket> CREATOR = new Creator<FlightTicket>() {
        @Override
        public FlightTicket createFromParcel(Parcel in) {
            return new FlightTicket(in);
        }

        @Override
        public FlightTicket[] newArray(int size) {
            return new FlightTicket[size];
        }
    };

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setNumOfPassenger(String numOfPassenger) {
        this.numOfPassenger = numOfPassenger;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setDepartAirport(String departAirport) {
        this.departAirport = departAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getNumOfPassenger() {
        return numOfPassenger;
    }

    public String getDepartDate() {
        return departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public BusinformationItem getFlightDetails() {
        return flightDetails;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ticketID);
        dest.writeString(numOfPassenger);
        dest.writeString(departDate);
        dest.writeString(returnDate);
        dest.writeString(departAirport);
        dest.writeString(arriveAirport);
        dest.writeString(totalPrice);
        dest.writeParcelable(flightDetails, flags);
    }
}
