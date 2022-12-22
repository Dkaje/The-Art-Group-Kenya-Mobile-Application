package com.example.artgroup.models;

public class CartMod {
    String reg = null;
    String entry = null;
    String custid = null;
    String product = null;
    String category = null;
    String type = null;
    String price = null;
    String quantity = null;
    String image = null;
    String status = null;
    String reg_date = null;

    public CartMod(String reg, String entry, String custid, String product, String category, String type, String price, String quantity, String image, String status, String reg_date) {
        this.reg = reg;
        this.entry = entry;
        this.custid = custid;
        this.product = product;
        this.category = category;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.status = status;
        this.reg_date = reg_date;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String toString() {
        return price + " " + entry + " " + category + " " + type + " " + reg_date + " " + quantity + " " + reg + " " + product;
    }
}

