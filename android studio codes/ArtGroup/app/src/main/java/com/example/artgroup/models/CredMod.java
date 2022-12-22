package com.example.artgroup.models;

public class CredMod {
    String creditor = null;
    String credit = null;
    String accredit = null;
    String fname = null;
    String lname = null;
    String phone = null;

    public CredMod(String creditor, String credit, String accredit, String fname, String lname, String phone) {
        this.creditor = creditor;
        this.credit = credit;
        this.accredit = accredit;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAccredit() {
        return accredit;
    }

    public void setAccredit(String accredit) {
        this.accredit = accredit;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return creditor + " " + fname + " " + lname + " " + phone + " " + credit + " " + accredit;
    }
}