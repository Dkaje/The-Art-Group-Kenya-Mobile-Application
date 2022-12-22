package com.example.artgroup.models;

public class ServModel {
    String serv = null;
    String custid = null;
    String name = null;
    String phone = null;
    String location = null;
    String landmark = null;
    String category = null;
    String type = null;
    String description = null;
    String charge = null;
    String status = null;
    String pay = null;
    String reg_date = null;

    public ServModel(String serv, String custid, String name, String phone, String location, String landmark, String category, String type, String description, String charge, String status, String pay, String reg_date) {
        this.serv = serv;
        this.custid = custid;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.landmark = landmark;
        this.category = category;
        this.type = type;
        this.description = description;
        this.charge = charge;
        this.status = status;
        this.pay = pay;
        this.reg_date = reg_date;
    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return serv + " " + category + " " + description + " " + charge + " " + reg_date + " " + type + " " + custid + " " + name + " " + phone + " " + location + " " + landmark + " " + status + " " + pay;
    }
}
