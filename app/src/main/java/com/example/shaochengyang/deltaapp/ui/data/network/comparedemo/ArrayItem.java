package com.example.shaochengyang.deltaapp.ui.data.network.comparedemo;


import com.google.gson.annotations.SerializedName;


public class ArrayItem{

	@SerializedName("flight_stop_duration")
	private String flightStopDuration;

	@SerializedName("flight_number")
	private String flightNumber;

	@SerializedName("flight_name")
	private String flightName;

	@SerializedName("flight_stops")
	private String flightStops;

	@SerializedName("flight_duration")
	private String flightDuration;

	@SerializedName("flight_price")
	private String flightPrice;



	public void setFlightStopDuration(String flightStopDuration){
		this.flightStopDuration = flightStopDuration;
	}

	public String getFlightStopDuration(){
		return flightStopDuration;
	}

	public void setFlightNumber(String flightNumber){
		this.flightNumber = flightNumber;
	}

	public String getFlightNumber(){
		return flightNumber;
	}

	public void setFlightName(String flightName){
		this.flightName = flightName;
	}

	public String getFlightName(){
		return flightName;
	}

	public void setFlightStops(String flightStops){
		this.flightStops = flightStops;
	}

	public String getFlightStops(){
		return flightStops;
	}

	public void setFlightDuration(String flightDuration){
		this.flightDuration = flightDuration;
	}

	public String getFlightDuration(){
		return flightDuration;
	}

	public void setFlightPrice(String flightPrice){
		this.flightPrice = flightPrice;
	}

	public String getFlightPrice(){
		return flightPrice;
	}

	@Override
 	public String toString(){
		return 
			"ArrayItem{" + 
			"flight_stop_duration = '" + flightStopDuration + '\'' + 
			",flight_number = '" + flightNumber + '\'' + 
			",flight_name = '" + flightName + '\'' + 
			",flight_stops = '" + flightStops + '\'' + 
			",flight_duration = '" + flightDuration + '\'' + 
			",flight_price = '" + flightPrice + '\'' + 
			"}";
		}
}