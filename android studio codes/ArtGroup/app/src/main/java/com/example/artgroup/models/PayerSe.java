package com.example.artgroup.models;

public class PayerSe {
    String serid = null;
    String mpesa = null;
    String amount = null;
    String category = null;
    String type = null;
    String description = null;
    String serv = null;
    String custid = null;
    String name = null;
    String phone = null;
    String location = null;
    String landmark = null;
    String status = null;
    String comment = null;
    String disb = null;
    String reg_date = null;

    public PayerSe(String serid, String mpesa, String amount, String category, String type, String description, String serv, String custid, String name, String phone, String location, String landmark, String status, String comment, String disb, String reg_date) {
        this.serid = serid;
        this.mpesa = mpesa;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.description = description;
        this.serv = serv;
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

    public String getSerid() {
        return serid;
    }

    public void setSerid(String serid) {
        this.serid = serid;
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
        return category + " " + type + " " + mpesa + " " + amount + " " + description + " " + serid + " " + serv + " " + custid + " " + name + " " + phone + " " + landmark + " " + location + " " + status + " " + disb + " " + comment + " " + reg_date;
    }
}
