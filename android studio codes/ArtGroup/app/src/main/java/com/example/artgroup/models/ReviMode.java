package com.example.artgroup.models;

public class ReviMode {
    String id = null;
    String form = null;
    String entry = null;
    String driver_id = null;
    String driver_name = null;
    String driver_phone = null;
    String drive = null;
    String drive_date = null;
    String video_id = null;
    String video_name = null;
    String video_phone = null;
    String video = null;
    String video_date = null;
    String cust_id = null;
    String cust_name = null;
    String cust_phone = null;
    String location = null;
    String landmark = null;
    String custom = null;
    String custom_date = null;
    String reg_date = null;

    public ReviMode(String id, String form, String entry, String driver_id, String driver_name, String driver_phone, String drive, String drive_date, String video_id, String video_name, String video_phone, String video, String video_date, String cust_id, String cust_name, String cust_phone, String location, String landmark, String custom, String custom_date, String reg_date) {
        this.id = id;
        this.form = form;
        this.entry = entry;
        this.driver_id = driver_id;
        this.driver_name = driver_name;
        this.driver_phone = driver_phone;
        this.drive = drive;
        this.drive_date = drive_date;
        this.video_id = video_id;
        this.video_name = video_name;
        this.video_phone = video_phone;
        this.video = video;
        this.video_date = video_date;
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.location = location;
        this.landmark = landmark;
        this.custom = custom;
        this.custom_date = custom_date;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        this.driver_phone = driver_phone;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getDrive_date() {
        return drive_date;
    }

    public void setDrive_date(String drive_date) {
        this.drive_date = drive_date;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_phone() {
        return video_phone;
    }

    public void setVideo_phone(String video_phone) {
        this.video_phone = video_phone;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo_date() {
        return video_date;
    }

    public void setVideo_date(String video_date) {
        this.video_date = video_date;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
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

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCustom_date() {
        return custom_date;
    }

    public void setCustom_date(String custom_date) {
        this.custom_date = custom_date;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return custom_date + " " + reg_date + " " + custom + " " + landmark + " " + location + " " + cust_phone + " " + cust_name + " " + cust_id + " " + video_date + " " + id + " " + driver_id + " " + drive_date + " " + driver_name + " " + driver_phone + " " + drive + " " + video_id + " " + video_name + " " + video_phone + " " + video;
    }
}