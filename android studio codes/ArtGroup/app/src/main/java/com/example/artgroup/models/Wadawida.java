package com.example.artgroup.models;

public class Wadawida {
    String rec = null;
    String senid = null;
    String count = null;
    String phone = null;
    String name = null;
    String message = null;
    String date = null;
    String timing = null;
    String temple = null;

    public Wadawida(String rec, String senid, String count, String phone, String name, String message, String date, String timing, String temple) {
        this.rec = rec;
        this.senid = senid;
        this.count = count;
        this.phone = phone;
        this.name = name;
        this.message = message;
        this.date = date;
        this.timing = timing;
        this.temple = temple;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getSenid() {
        return senid;
    }

    public void setSenid(String senid) {
        this.senid = senid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getTemple() {
        return temple;
    }

    public void setTemple(String temple) {
        this.temple = temple;
    }

    @Override
    public String toString() {
        return rec + " " + senid + " " + count + " " + phone + " " + name + " " + message + " " + date + " " + timing + " " + temple;
    }
}
