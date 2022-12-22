package com.example.artgroup.models;

public class ThirdTest {
    String refere = null;
    String category = null;
    String type = null;
    String cust_id = null;
    String image = null;
    String reg_date = null;

    public ThirdTest(String refere, String category, String type, String cust_id, String image, String reg_date) {
        this.refere = refere;
        this.category = category;
        this.type = type;
        this.cust_id = cust_id;
        this.image = image;
        this.reg_date = reg_date;
    }

    public String getRefere() {
        return refere;
    }

    public void setRefere(String refere) {
        this.refere = refere;
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

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return refere + " " + category + " " + cust_id + " " + reg_date + " " + type;
    }
}

