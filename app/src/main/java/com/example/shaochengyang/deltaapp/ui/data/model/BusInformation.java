package com.example.shaochengyang.deltaapp.ui.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BusInformation{

	@SerializedName("businformation")
	private List<BusinformationItem> businformation;

	public void setBusinformation(List<BusinformationItem> businformation){
		this.businformation = businformation;
	}

	public List<BusinformationItem> getBusinformation(){
		return businformation;
	}

	@Override
 	public String toString(){
		return 
			"BusInformation{" + 
			"businformation = '" + businformation + '\'' + 
			"}";
		}
}