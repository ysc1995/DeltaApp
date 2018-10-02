package com.example.shaochengyang.deltaapp.ui.seatreserve.model;

import com.example.shaochengyang.deltaapp.R;

public class Seat {
    public int selectedtype = R.drawable.selected_seat;
    public int unselectedtype = R.drawable.available_seat;
    public int reservedtype = R.drawable.booked_seat;
    Boolean isselected;
    String id;
    int type;
    boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

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
