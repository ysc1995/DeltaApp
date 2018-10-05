package com.example.shaochengyang.deltaapp.ui.data.network.model;

public class RItem {
    String rid;
    String rname ;
    String rstart ;
    String rdestination ;



    public RItem(String rid, String rname, String rstart, String rdestination) {
        this.rid = rid;
        this.rname = rname;
        this.rstart = rstart;
        this.rdestination = rdestination;

    }


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRstart() {
        return rstart;
    }

    public void setRstart(String rstart) {
        this.rstart = rstart;
    }

    public String getRdestination() {
        return rdestination;
    }

    public void setRdestination(String rdestination) {
        this.rdestination = rdestination;
    }


}
