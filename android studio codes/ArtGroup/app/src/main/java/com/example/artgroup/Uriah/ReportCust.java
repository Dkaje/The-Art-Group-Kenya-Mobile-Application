package com.example.artgroup.Uriah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustAda;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.RecentMode;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportCust extends AppCompatActivity {
    ImageView imageView, Cartel;
    TextView texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    RecentMode bidModel;
    ArrayList<RecentMode> bidModelArrayList = new ArrayList<>();
    CustAda custAda;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    SearchView searchView;
    Button button;
    RequestQueue requestQueue;
    CustSess custSess;
    UserModel userModel;
    TextView loadIt, loadDt, loadAmount;
    RequestedM requestedM;
    ArrayList<RequestedM> requestedMArrayList = new ArrayList<>();
    RequestedAda requestedAda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_cust);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MyReports.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), ReportServ.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        button = findViewById(R.id.addNEw);
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getRecords();
        button.setOnClickListener(view -> {
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (RecentMode) parent.getItemAtPosition(position);
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewRe,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    requestedM = new RequestedM(jsonObject.getString("slf"), jsonObject.getString("dsc"), jsonObject.getString("custid"),
                                            jsonObject.getString("name"), jsonObject.getString("phone"), jsonObject.getString("category"),
                                            jsonObject.getString("type"), jsonObject.getString("description"), ModelUrl.img + jsonObject.getString("image"),
                                            jsonObject.getString("size"),jsonObject.getString("rgb"),jsonObject.getString("hexa"),jsonObject.getString("red"),
                                            jsonObject.getString("green"),jsonObject.getString("blue"),jsonObject.getString("motive"),
                                            jsonObject.getString("quantity"), jsonObject.getString("price"),
                                            jsonObject.getString("status"), jsonObject.getString("pay"),
                                            jsonObject.getString("fina"), ModelUrl.img + jsonObject.getString("image_desgn"), jsonObject.getString("design"), jsonObject.getString("date"));
                                    requestedMArrayList.add(requestedM);
                                }
                                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                Rect rect = new Rect();
                                Window window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                layer = layoutInflater.inflate(R.layout.drama, null);
                                layer.setMinimumWidth((int) (rect.width() * 0.9f));
                                layer.setMinimumHeight((int) (rect.height() * 0.08f));
                                texter = layer.findViewById(R.id.myTexter);
                                loadAmount = layer.findViewById(R.id.pesa);
                                loadDt = layer.findViewById(R.id.detl);
                                loadIt = layer.findViewById(R.id.itemed);
                                loadIt.setText(requestedM.getCategory());
                                loadAmount.setText("KES" + bidModel.getAmount());
                                loadDt.setText(requestedM.getType());
                                texter.setText("Receipt: " + bidModel.getPayid() + "\nCustomer: " + bidModel.getName() + "\nphone: " + bidModel.getPhone() + "\nLocation: " + bidModel.getLocation() + " " + bidModel.getLandmark() + "\nDate: " + bidModel.getReg_date());
                                alert.setView(layer);
                                alert.setPositiveButton("Load", (dialog, ids) -> {
                                });
                                alert.setNegativeButton("close", (dialog, ids) -> {
                                });
                                AlertDialog alertDialog = alert.create();
                                alertDialog.show();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                alertDialog.getWindow().setGravity(Gravity.CENTER);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                    printManager.print(getString(R.string.app_name), new PDFGenerator(this, layer.findViewById(R.id.titled)), null);
                                });
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                    alertDialog.cancel();
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
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("slf", bidModel.getEntry());
                    return para;
                }
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCustom,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String payid = jsonObject.getString("payid");
                                String entry = jsonObject.getString("entry");
                                String mpesa = jsonObject.getString("mpesa");
                                String amount = jsonObject.getString("amount");
                                String orders = jsonObject.getString("orders");
                                String ship = jsonObject.getString("ship");
                                String custid = jsonObject.getString("custid");
                                String name = jsonObject.getString("name");
                                String phone = jsonObject.getString("phone");
                                String location = jsonObject.getString("location");
                                String landmark = jsonObject.getString("landmark");
                                String status = jsonObject.getString("status");
                                String comment = jsonObject.getString("comment");
                                String design = jsonObject.getString("design");
                                String disb = jsonObject.getString("disb");
                                String reg_date = jsonObject.getString("reg_date");
                                bidModel = new RecentMode(payid, entry, mpesa, amount, orders, ship, custid, name, phone, location, landmark, status, comment, design, disb, reg_date);
                                bidModelArrayList.add(bidModel);
                            }
                            custAda = new CustAda(ReportCust.this, R.layout.sammy, bidModelArrayList);
                            listView.setAdapter(custAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    custAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            button.setVisibility(View.VISIBLE);
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("custid", userModel.getUser_id());
                para.put("status", "1");
                return para;
            }
        });
    }
}