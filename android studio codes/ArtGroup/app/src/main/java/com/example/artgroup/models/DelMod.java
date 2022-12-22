package com.example.artgroup.models;

public class DelMod {
    String entry = null;
    String name = null;
    String phone = null;

    public DelMod(String entry, String name, String phone) {
        this.entry = entry;
        this.name = name;
        this.phone = phone;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
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

    @Override
    public String toString() {
        return entry + " " + name + " " + phone;
    }
}
