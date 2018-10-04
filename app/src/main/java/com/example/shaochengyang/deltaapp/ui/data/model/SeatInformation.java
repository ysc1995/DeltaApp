package com.example.shaochengyang.deltaapp.ui.data.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class SeatInformation{

	@SerializedName("seatinformation")
	private List<SeatinformationItem> seatinformation;

	public void setSeatinformation(List<SeatinformationItem> seatinformation){
		this.seatinformation = seatinformation;
	}

	public List<SeatinformationItem> getSeatinformation(){
		return seatinformation;
	}

	@Override
 	public String toString(){
		return 
			"SeatInformation{" + 
			"seatinformation = '" + seatinformation + '\'' + 
			"}";
		}
}