package com.example.artgroup.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class CustSess {
    //reg, fname, lname, email, username, location, phone, reg_date
    private static final String mUser_id = "user_id";
    private static final String mfname = "fname";
    private static final String mlname = "lname";
    private static final String muser = "username";
    private static final String memail = "email";
    private static final String mphone = "phone";
    private static final String mlocation = "county";
    private static final String mreg_date = "reg_date";
    private static final String mdura = "";
    private static final String mclear = "";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CustSess(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("mwendawazimu", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void loginUser(String user_id, String fname, String lname, String username, String email, String phone, String location, String reg_date) {//key and value>>>
        editor.putString(mUser_id, user_id);
        editor.putString(mfname, fname);
        editor.putString(mlname, lname);
        editor.putString(muser, username);
        editor.putString(memail, email);
        editor.putString(mphone, phone);
        editor.putString(mlocation, location);
        editor.putString(mreg_date, reg_date);
        Date date = new Date();
        long timing = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        editor.putLong(mdura, timing);
        editor.commit();
    }

    public boolean logged() {
        Date currentDate = new Date();
        long remainder = sharedPreferences.getLong(mdura, 0);
        if (remainder == 0) {
            return false;
        }
        Date closure = new Date(remainder);
        return currentDate.before(closure);
    }

    public UserModel getUserDetails() {
        if (!logged()) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setUser_id(sharedPreferences.getString(mUser_id, mclear));
        userModel.setFname(sharedPreferences.getString(mfname, mclear));
        userModel.setLname(sharedPreferences.getString(mlname, mclear));
        userModel.setUsername(sharedPreferences.getString(muser, mclear));
        userModel.setEmail(sharedPreferences.getString(memail, mclear));
        userModel.setPhone(sharedPreferences.getString(mphone, mclear));
        userModel.setCounty(sharedPreferences.getString(mlocation, mclear));
        userModel.setReg_date(sharedPreferences.getString(mreg_date, mclear));
        return userModel;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}

