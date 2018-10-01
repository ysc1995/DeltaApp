package com.example.shaochengyang.deltaapp.ui.seatreserve.model;

import com.example.shaochengyang.deltaapp.R;

public class Seat {
    public int selectedtype = R.drawable.ic_launcher_foreground;
    public int unselectedtype = R.drawable.double_arrow;
    Boolean isselected;
    String id;
    int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsselected() {
        return isselected;
    }

    public void setIsselected(Boolean isselected) {
        this.isselected = isselected;
    }
}
