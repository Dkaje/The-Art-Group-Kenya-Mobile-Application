package com.example.artgroup.models;

public class BadModel {
    String id = null;
    String cust_id = null;
    String alert = null;
    String reg_date = null;

    public BadModel(String id, String cust_id, String alert, String reg_date) {
        this.id = id;
        this.cust_id = cust_id;
        this.alert = alert;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return id + " " + cust_id + " " + alert + " " + reg_date;
    }
}

