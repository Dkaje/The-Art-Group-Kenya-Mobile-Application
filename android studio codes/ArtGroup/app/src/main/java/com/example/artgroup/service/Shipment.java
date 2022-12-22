package com.example.artgroup.service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.models.CartAda;
import com.example.artgroup.models.CartMod;
import com.example.artgroup.models.DelMod;
import com.example.artgroup.models.DeliAda;
import com.example.artgroup.models.PayMod;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.ShipaAda;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Shipment extends AppCompatActivity {
    ImageView imageView, profile, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    SerSess custSess;
    UserModel userModel;
    Rect rect;
    Window window;
    EditText qty;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    Spinner categor, typed;
    String myTpe;
    PayMod bidModel;
    ArrayList<PayMod> bidModelArrayList = new ArrayList<>();
    ShipaAda bidAda;
    ListView listView, listing, rasta;
    SearchView searchView, yea;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Button button, btn;
    CartMod cartMod;
    ArrayList<CartMod> cartModArrayList = new ArrayList<>();
    CartAda cartAda;
    DelMod delMod;
    ArrayList<DelMod> delModArrayList = new ArrayList<>();
    DeliAda deliAda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);
        custSess = new SerSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
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
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Role</b>: " + userModel.getCounty() + "<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
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
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        button = findViewById(R.id.addNEw);
        btn = findViewById(R.id.btnPast);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ShipHistory.class));
        });
        button.setOnClickListener(view -> {
            printThis();
        });
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        inflater = getLayoutInflater();
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        getRecords();
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            bidModel = (PayMod) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Customer Shipment Details</u></b></font>"));
            builder.setMessage("CustomerID: " + bidModel.getCustid() + "\nName: " + bidModel.getName() + "\nPhone: " + bidModel.getPhone() + "\nLocation: " + bidModel.getLocation() + " - " + bidModel.getLandmark() + "\nFinanceStatus: " + bidModel.getStatus() + "\nDate: " + bidModel.getReg_date() + "\nDisbursement: " + bidModel.getDisb());
            builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Products</font>"), (dd, d) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Driver</font>"), (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCa,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        jsonObject = jsonArray.getJSONObject(j);
                                        String reg = jsonObject.getString("reg");
                                        String entry = jsonObject.getString("entry");
                                        String custid = jsonObject.getString("custid");
                                        String product = jsonObject.getString("product");
                                        String category = jsonObject.getString("category");
                                        String type = jsonObject.getString("type");
                                        String price = jsonObject.getString("price");
                                        String quantity = jsonObject.getString("quantity");
                                        String image = jsonObject.getString("image");
                                        String imagery = ModelUrl.img + image;
                                        String status = jsonObject.getString("status");
                                        String reg_date = jsonObject.getString("reg_date");
                                        cartMod = new CartMod(reg, entry, custid, product, category, type, price, quantity, imagery, status, reg_date);
                                        cartModArrayList.add(cartMod);
                                    }
                                        AlertDialog.Builder build = new AlertDialog.Builder(this, R.style.Arap);
                                        build.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Items Bought</u></b></font>"));
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        layer = layoutInflater.inflate(R.layout.listed_machinery, null);
                                        layer.setMinimumWidth((int) (rect.width() * 1.0));
                                        layer.setMinimumHeight((int) (rect.height() * 0.01));
                                        listing = layer.findViewById(R.id.availableGrid);
                                        listing.setTextFilterEnabled(true);
                                        cartAda = new CartAda(Shipment.this, R.layout.marathon, cartModArrayList);
                                        listing.setAdapter(cartAda);
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        layer.setLayoutParams(params);
                                        frameLayout.addView(layer);
                                        build.setView(frameLayout);
                                        build.setPositiveButton("Close", (tr, r) -> {
                                        });
                                        AlertDialog bon = build.create();
                                        bon.show();
                                        bon.setCancelable(false);
                                        bon.setCanceledOnTouchOutside(false);
                                        bon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        bon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            startActivity(new Intent(getApplicationContext(), Shipment.class));
                                            finish();
                                        });
                                        bon.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                        para.put("entry", bidModel.getEntry());
                        return para;
                    }
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getDr,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        jsonObject = jsonArray.getJSONObject(j);
                                        delMod = new DelMod(jsonObject.getString("entry"), jsonObject.getString("fname"), jsonObject.getString("phone"));
                                        delModArrayList.add(delMod);
                                        AlertDialog.Builder build = new AlertDialog.Builder(this);
                                        build.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Pick Driver</u></b></font>"));
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        layer = layoutInflater.inflate(R.layout.get_deli, null);
                                        layer.setMinimumWidth((int) (rect.width() * 1.0));
                                        layer.setMinimumHeight((int) (rect.height() * 0.01));
                                        rasta = layer.findViewById(R.id.myList);
                                        rasta.setTextFilterEnabled(true);
                                        yea = layer.findViewById(R.id.mySearch);
                                        deliAda = new DeliAda(Shipment.this, R.layout.quee, delModArrayList);
                                        rasta.setAdapter(deliAda);
                                        yea.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String text) {
                                                return false;
                                            }

                                            @Override
                                            public boolean onQueryTextChange(String newText) {
                                                deliAda.getFilter().filter(newText);
                                                return false;
                                            }
                                        });
                                        rasta.setOnItemClickListener(((adapterView1, view2, i1, l1) -> {
                                            delMod = (DelMod) adapterView1.getItemAtPosition(i1);
                                            alert = new AlertDialog.Builder(this, R.style.Arap);
                                            alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>ADD Driver</u></b></font>"));
                                            alert.setMessage("You selected:-\nName: " + delMod.getName() + "\nPhone: " + delMod.getPhone() + "\nas the driver to disburse\n" + bidModel.getName() + " goods.\nDo You want to proceed?\nYou cannot undo this process!!");
                                            alert.setPositiveButton("Yes_Allocate", (tg, t) -> {
                                            });
                                            alert.setNegativeButton("No_Exit", (tg, t) -> {
                                            });
                                            dialog = alert.create();
                                            dialog.show();
                                            dialog.setCancelable(false);
                                            dialog.setCanceledOnTouchOutside(false);
                                            dialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.assist,
                                                        responses -> {
                                                            try {
                                                                jsonObject = new JSONObject(responses);
                                                                String msg = jsonObject.getString("message");
                                                                int status = jsonObject.getInt("success");
                                                                if (status == 1) {
                                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(getApplicationContext(), Shipment.class));
                                                                    finish();
                                                                } else if (status == 0) {
                                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }, error -> {
                                                    Toast.makeText(this, "There was a connection error", Toast.LENGTH_SHORT).show();

                                                }) {
                                                    @Nullable
                                                    @Override
                                                    protected Map<String, String> getParams() {
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("driver_id", delMod.getEntry());
                                                        params.put("disb", "1");
                                                        params.put("payid", bidModel.getEntry());
                                                        params.put("driver_name", delMod.getName());
                                                        params.put("driver_phone", delMod.getPhone());
                                                        params.put("cust_id", bidModel.getCustid());
                                                        params.put("cust_name", bidModel.getName());
                                                        params.put("cust_phone", bidModel.getPhone());
                                                        params.put("location", bidModel.getLocation());
                                                        params.put("landmark", bidModel.getLandmark());
                                                        params.put("form", "6");
                                                        params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                                                        return params;
                                                    }
                                                });
                                            });
                                            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                                                dialog.cancel();
                                            });
                                        }));
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        layer.setLayoutParams(params);
                                        frameLayout.addView(layer);
                                        build.setView(frameLayout);
                                        build.setPositiveButton("Close", (tr, r) -> {
                                        });
                                        AlertDialog bon = build.create();
                                        bon.show();
                                        bon.setCancelable(false);
                                        bon.setCanceledOnTouchOutside(false);
                                        bon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        bon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            startActivity(new Intent(getApplicationContext(), Shipment.class));
                                            finish();
                                        });
                                        bon.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    }
                                } else {
                                    AlertDialog.Builder beber = new AlertDialog.Builder(this);
                                    beber.setMessage("No Verified Driver was Found");
                                    AlertDialog dodo = beber.create();
                                    dodo.show();
                                    dodo.getWindow().setGravity(Gravity.TOP);
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
                        para.put("entry", bidModel.getEntry());
                        return para;
                    }
                });
            });
        }));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void printThis() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.disba,
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
                                String disb = jsonObject.getString("disb");
                                String reg_date = jsonObject.getString("reg_date");
                                bidModel = new PayMod(payid, entry, mpesa, amount, orders, ship, custid, name, phone, location, landmark, status, comment, disb, reg_date);
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new ShipaAda(Shipment.this, R.layout.marathon, bidModelArrayList);
                            listView.setAdapter(bidAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    bidAda.getFilter().filter(newText);
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
                para.put("status", "1");
                para.put("disb", "0");
                return para;
            }
        });
    }
}