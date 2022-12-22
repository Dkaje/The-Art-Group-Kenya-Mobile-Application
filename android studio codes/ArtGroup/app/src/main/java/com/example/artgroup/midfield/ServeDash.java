package com.example.artgroup.midfield;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.ServAda;
import com.example.artgroup.models.ServModel;
import com.example.artgroup.models.UserModel;
import com.example.artgroup.service.NewAttach;
import com.example.artgroup.service.NoMoreDis;
import com.example.artgroup.service.PasstAttach;
import com.example.artgroup.service.PastServ;
import com.example.artgroup.service.ReqShip;
import com.example.artgroup.service.Shipment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServeDash extends AppCompatActivity {
    SerSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    TextView textView;
    AlertDialog.Builder builder, alert;
    AlertDialog dialog, logger;
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
    BottomNavigationView botaz, topaz;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new SerSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_serve_dash);
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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        inflater = getLayoutInflater();
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        getRecords();
        findViewById(R.id.addNEw).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PastServ.class));
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            servModel = (ServModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(this, R.style.Arap);
            alert.setTitle("Ordered Service");
            alert.setMessage("CustomerID: " + servModel.getCustid() + "\nName: " + servModel.getName() + "\nPhone: " + servModel.getPhone() + "\nLocation: " + servModel.getLocation() + " - " + servModel.getLandmark() + "\n\nSERVICE\nCategory: " + servModel.getCategory() + "\nType: " + servModel.getType()+"\nCharge: KES"+servModel.getCharge() + "\nDescription: " + servModel.getDescription() + "\n\nSTATUS\ndateOrdered: " + servModel.getReg_date() + "\nStatus: " + servModel.getStatus());
            alert.setPositiveButton("Approve", (dialogInterface, i1) -> {
            });
            alert.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            alert.setNeutralButton("Reject", (dialogInterface, i1) -> {
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
            logger.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder priced = new AlertDialog.Builder(this);
                priced.setTitle("Verify Service");
                final EditText editText = new EditText(this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                editText.setHint("charges (service+transport)");
                editText.setText(servModel.getCharge());
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                editText.setLayoutParams(params);
                frameLayout.addView(editText);
                priced.setView(frameLayout);
                priced.setPositiveButton("Submit", (dialogInterface, i1) -> {
                });
                priced.setNegativeButton("Close", (dialogInterface, i1) -> {
                });
                AlertDialog neem = priced.create();
                neem.show();
                neem.setCancelable(false);
                neem.setCanceledOnTouchOutside(false);
                neem.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                neem.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                    neem.cancel();
                });
                neem.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    final String myPr = editText.getText().toString().trim();
                    if (myPr.isEmpty()) {
                        editText.requestFocus();
                        textView.setText("Charge is required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myPr) < 20) {
                        editText.requestFocus();
                        textView.setText("Charge is even low");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCharge,
                                response -> {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int Status = jsonObject.getInt("success");
                                        String mess = jsonObject.getString("message");
                                        if (Status == 1) {
                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), ServeDash.class));
                                            finish();
                                        } else if (Status == 0) {
                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }, error -> {
                            Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("serv", servModel.getServ());
                                params.put("status", "Approved");
                                params.put("charge", myPr);
                                return params;
                            }
                        });
                    }
                });
            });
            logger.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCharge,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int Status = jsonObject.getInt("success");
                                String mess = jsonObject.getString("message");
                                if (Status == 1) {
                                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), ServeDash.class));
                                    finish();
                                } else if (Status == 0) {
                                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("serv", servModel.getServ());
                        params.put("status", "Rejected");
                        params.put("charge", "0");
                        return params;
                    }
                });
            });
        });
        topaz = findViewById(R.id.Best);
        topaz.setSelectedItemId(R.id.noti);
        topaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Shipment.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), ReqShip.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.noti);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
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
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewS,
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
                            servAda = new ServAda(ServeDash.this, R.layout.marathon, servModelArrayList);
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