package com.example.artgroup.models;

public class BidModel {
    String bid = null;
    String classification = null;
    String category = null;
    String type = null;
    String qty = null;
    String quantity = null;
    String entry_date = null;
    String update_date = null;

    public BidModel(String bid, String classification, String category, String type, String qty, String quantity, String entry_date, String update_date) {
        this.bid = bid;
        this.classification = classification;
        this.category = category;
        this.type = type;
        this.qty = qty;
        this.quantity = quantity;
        this.entry_date = entry_date;
        this.update_date = update_date;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    @Override
    public String toString() {
        return bid + " " + classification + " " + category + " " + qty + " " + quantity + " " + entry_date + " " + type + " " + update_date;
    }
}
