package com.example.shaochengyang.deltaapp.ui.data.network.comparedemo.model;

public class DemoItem {

    String demoName; String demoNumber; String demoDuration; String demoStop; String demoStopDuration; String demoPrice;

    public DemoItem(String demoName, String demoNumber, String demoDuration, String demoStop, String demoStopDuration, String demoPrice) {
        this.demoName = demoName;
        this.demoNumber = demoNumber;
        this.demoDuration = demoDuration;
        this.demoStop = demoStop;
        this.demoStopDuration = demoStopDuration;
        this.demoPrice = demoPrice;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public String getDemoNumber() {
        return demoNumber;
    }

    public void setDemoNumber(String demoNumber) {
        this.demoNumber = demoNumber;
    }

    public String getDemoDuration() {
        return demoDuration;
    }

    public void setDemoDuration(String demoDuration) {
        this.demoDuration = demoDuration;
    }

    public String getDemoStop() {
        return demoStop;
    }

    public void setDemoStop(String demoStop) {
        this.demoStop = demoStop;
    }

    public String getDemoStopDuration() {
        return demoStopDuration;
    }

    public void setDemoStopDuration(String demoStopDuration) {
        this.demoStopDuration = demoStopDuration;
    }

    public String getDemoPrice() {
        return demoPrice;
    }

    public void setDemoPrice(String demoPrice) {
        this.demoPrice = demoPrice;
    }

    /*public DemoItem(String demoName, String demoNumber, String demoDuration, String demoStop, String demoStopDuration, String demoPrice) {
    }*/
}
