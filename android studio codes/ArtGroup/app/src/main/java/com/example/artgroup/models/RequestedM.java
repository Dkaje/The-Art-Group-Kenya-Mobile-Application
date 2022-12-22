package com.example.artgroup.models;

public class RequestedM {
    String slf = null;
    String dsc = null;
    String custid = null;
    String name = null;
    String phone = null;
    String category = null;
    String type = null;
    String description = null;
    String image = null;
    String size = null;
    String rgb = null;
    String hexa = null;
    String red = null;
    String green = null;
    String blue = null;
    String motive = null;
    String quantity = null;
    String price = null;
    String status = null;
    String pay = null;
    String fina = null;
    String image_desgn = null;
    String design = null;
    String date = null;

    public RequestedM(String slf, String dsc, String custid, String name, String phone, String category, String type, String description, String image, String size, String rgb, String hexa, String red, String green, String blue, String motive, String quantity, String price, String status, String pay, String fina, String image_desgn, String design, String date) {
        this.slf = slf;
        this.dsc = dsc;
        this.custid = custid;
        this.name = name;
        this.phone = phone;
        this.category = category;
        this.type = type;
        this.description = description;
        this.image = image;
        this.size = size;
        this.rgb = rgb;
        this.hexa = hexa;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.motive = motive;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.pay = pay;
        this.fina = fina;
        this.image_desgn = image_desgn;
        this.design = design;
        this.date = date;
    }

    public String getSlf() {
        return slf;
    }

    public void setSlf(String slf) {
        this.slf = slf;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getHexa() {
        return hexa;
    }

    public void setHexa(String hexa) {
        this.hexa = hexa;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getFina() {
        return fina;
    }

    public void setFina(String fina) {
        this.fina = fina;
    }

    public String getImage_desgn() {
        return image_desgn;
    }

    public void setImage_desgn(String image_desgn) {
        this.image_desgn = image_desgn;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return size + " " + pay + " " + status + " " + slf + " " + custid + " " + name + " " + phone + " " + category + " " + type + " " + date + " " + size + " " + description + " " + dsc;
    }
}
