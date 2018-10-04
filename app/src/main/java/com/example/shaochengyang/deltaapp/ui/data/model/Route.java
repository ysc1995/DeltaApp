package com.example.shaochengyang.deltaapp.ui.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Route{

	@SerializedName("route")
	private List<RouteItem> route;

	public void setRoute(List<RouteItem> route){
		this.route = route;
	}

	public List<RouteItem> getRoute(){
		return route;
	}

	@Override
 	public String toString(){
		return 
			"Route{" + 
			"route = '" + route + '\'' + 
			"}";
		}
}