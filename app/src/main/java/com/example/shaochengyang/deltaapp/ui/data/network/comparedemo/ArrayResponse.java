package com.example.shaochengyang.deltaapp.ui.data.network.comparedemo;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ArrayResponse{

    @SerializedName("array")
    private List<ArrayItem> array;

    @SerializedName("flight_route")
    private String flightRoute;

    @SerializedName("message")
    private String message;

    public void setArray(List<ArrayItem> array){
        this.array = array;
    }

    public List<ArrayItem> getArray(){
        return array;
    }

    public void setFlightRoute(String flightRoute){
        this.flightRoute = flightRoute;
    }

    public String getFlightRoute(){
        return flightRoute;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "array = '" + array + '\'' +
                        ",flight_route = '" + flightRoute + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}