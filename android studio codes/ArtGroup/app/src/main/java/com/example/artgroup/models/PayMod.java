package com.example.artgroup.models;

public class PayMod {
    String payid = null;
    String entry = null;
    String mpesa = null;
    String amount = null;
    String orders = null;
    String ship = null;
    String custid = null;
    String name = null;
    String phone = null;
    String location = null;
    String landmark = null;
    String status = null;
    String comment = null;
    String disb = null;
    String reg_date = null;

    public PayMod(String payid, String entry, String mpesa, String amount, String orders, String ship, String custid, String name, String phone, String location, String landmark, String status, String comment, String disb, String reg_date) {
        this.payid = payid;
        this.entry = entry;
        this.mpesa = mpesa;
        this.amount = amount;
        this.orders = orders;
        this.ship = ship;
        this.custid = custid;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.landmark = landmark;
        this.status = status;
        this.comment = comment;
        this.disb = disb;
        this.reg_date = reg_date;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDisb() {
        return disb;
    }

    public void setDisb(String disb) {
        this.disb = disb;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String toString() {
        return payid + " " + entry + " " + mpesa + " " + amount + " " + orders + " " + ship + " " + custid + " " + name + " " + phone + " " + landmark + " " + location + " " + status + " " + disb + " " + comment + " " + reg_date;
    }
}
