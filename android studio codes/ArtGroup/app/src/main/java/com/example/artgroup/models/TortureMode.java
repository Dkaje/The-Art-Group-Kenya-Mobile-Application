package com.example.artgroup.models;

public class TortureMode {
    String senid = null;
    String rec = null;
    String count = null;
    String phone = null;
    String name = null;
    String message = null;
    String date = null;
    String sen = null;
    String timing = null;
    String temple = null;

    public TortureMode(String senid, String rec, String count, String phone, String name, String message, String date, String sen, String timing, String temple) {
        this.senid = senid;
        this.rec = rec;
        this.count = count;
        this.phone = phone;
        this.name = name;
        this.message = message;
        this.date = date;
        this.sen = sen;
        this.timing = timing;
        this.temple = temple;
    }

    public String getSenid() {
        return senid;
    }

    public void setSenid(String senid) {
        this.senid = senid;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
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

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
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
        return sen + " " + rec + " " + senid + " " + count + " " + phone + " " + name + " " + message + " " + date + " " + timing + " " + temple;
    }
}
