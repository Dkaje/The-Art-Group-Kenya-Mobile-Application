package com.example.artgroup.service;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.ServAda;
import com.example.artgroup.models.ServModel;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PastServ extends AppCompatActivity {
    SerSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    TextView textView;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog, logger;
    RequestQueue requestQueue;
    View layer;
    LayoutInflater inflater;
    ServModel servModel;
    ArrayList<ServModel> servModelArrayList = new ArrayList<>();
    ServAda servAda;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    BottomNavigationView botaz;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new SerSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_past_serv);
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Role</b>: " + userModel.getCounty() + " Manager<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
            builder.setNeutralButton(Html.fromHtml("<font><b><i>Logout</i></b></font>"), (dial, dd) -> {
                custSess.logoutUser();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            });
            builder.setNegativeButton(Html.fromHtml("<font><b><i>Close</i></b></font>"), (dial, dd) -> dial.cancel());
            dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ServeDash.class));
        });
        textView.setText("Welcome " + userModel.getFname());
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.noti);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), ServeDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), NewAttach.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.att:
                    startActivity(new Intent(getApplicationContext(), PasstAttach.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), NoMoreDis.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        getRecords();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            servModel = (ServModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(this, R.style.Arap);
            alert.setMessage("CustomerID: " + servModel.getCustid() + "\nName: " + servModel.getName() + "\nPhone: " + servModel.getPhone() + "\nLocation: " + servModel.getLocation() + " - " + servModel.getLandmark() + "\n\nSERVICE\nCategory: " + servModel.getCategory() + "\nType: " + servModel.getType() + "\nDescription: " + servModel.getDescription() + "\n\nSTATUS\nCharge: KES" + servModel.getCharge() + "\ndateOrdered: " + servModel.getReg_date() + "\nStatus: " + servModel.getStatus());
            alert.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            logger = alert.create();
            logger.show();
            logger.setCanceledOnTouchOutside(false);
            logger.setCancelable(false);
            logger.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            logger.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            logger.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                logger.cancel();
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.veriSe,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                servModel = new ServModel(jsonObject.getString("serv"), jsonObject.getString("custid"), jsonObject.getString("name"), jsonObject.getString("phone"),
                                        jsonObject.getString("location"), jsonObject.getString("landmark"), jsonObject.getString("category"), jsonObject.getString("type"),
                                        jsonObject.getString("description"), jsonObject.getString("charge"), jsonObject.getString("status"), jsonObject.getString("pay"), jsonObject.getString("reg_date"));
                                servModelArrayList.add(servModel);
                            }
                            servAda = new ServAda(PastServ.this, R.layout.marathon, servModelArrayList);
                            listView.setAdapter(servAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    servAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("status", "Pending");
                return params;
            }
        });
    }
}