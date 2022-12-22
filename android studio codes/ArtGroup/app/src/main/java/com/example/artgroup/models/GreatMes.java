package com.example.artgroup.models;

public class GreatMes {
    String sen = null;
    String senid = null;
    String phone = null;
    String name = null;
    String rec = null;
    String message = null;
    String timing = null;
    String date = null;
    String temple = null;
    String temple2 = null;
    String meme = null;

    public GreatMes(String sen, String senid, String phone, String name, String rec, String message, String timing, String date, String temple, String temple2, String meme) {
        this.sen = sen;
        this.senid = senid;
        this.phone = phone;
        this.name = name;
        this.rec = rec;
        this.message = message;
        this.timing = timing;
        this.date = date;
        this.temple = temple;
        this.temple2 = temple2;
        this.meme = meme;
    }

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }

    public String getSenid() {
        return senid;
    }

    public void setSenid(String senid) {
        this.senid = senid;
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

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemple() {
        return temple;
    }

    public void setTemple(String temple) {
        this.temple = temple;
    }

    public String getTemple2() {
        return temple2;
    }

    public void setTemple2(String temple2) {
        this.temple2 = temple2;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    @Override
    public String toString() {
        return meme + " " + sen + " " + phone + " " + name + " " + message + " " + senid + " " + date + " " + rec + " " + timing + " " + temple + " " + temple2;
    }
}