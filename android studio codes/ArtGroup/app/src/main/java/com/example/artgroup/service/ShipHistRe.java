package com.example.artgroup.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
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
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.models.RecentMode;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.ReviMode;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.UserModel;
import com.example.artgroup.models.WeweAda;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipHistRe extends AppCompatActivity {
    ImageView imageView, kartex, Cartel, profile;
    TextView textView, head;
    BottomNavigationView botaz;
    AlertDialog.Builder builder, alert;
    AlertDialog dialog;
    Spinner spinner;
    View layer, begger, samoa, major;
    Rect rect;
    Window window;
    EditText qty;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    Spinner categor, typed;
    String myTpe;
    RecentMode bidModel;
    ArrayList<RecentMode> bidModelArrayList = new ArrayList<>();
    WeweAda bidAda;
    ListView listView, rasta;
    SearchView searchView, yea;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Button button;
    RequestedM requestedM;
    ArrayList<RequestedM> requestedMArrayList = new ArrayList<>();
    RequestedAda requestedAda;
    TextView texter, amount;
    AlertDialog alertDialog;
    SerSess custSess;
    UserModel userModel;
    ArrayList<ReviMode> reviModeArrayList = new ArrayList<>();
    ReviMode reviMode;
    String mdater, md, mdt;
    RelativeLayout relativeLayout, relColor, relImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_hist_re);
        custSess = new SerSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
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
        textView.setText("Welcome " + userModel.getFname());
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
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ReqShip.class));
            finish();
        });
        button = findViewById(R.id.addNEw);
        head = findViewById(R.id.myTxt);
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
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (RecentMode) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Shipment Details</u></b></font>"));
            builder.setMessage("CustomerID: " + bidModel.getCustid() + "\nName: " + bidModel.getName() + "\nPhone: " + bidModel.getPhone() + "\nLocation: " + bidModel.getLocation() + " - " + bidModel.getLandmark() + "\nDate: " + bidModel.getReg_date());
            builder.setPositiveButton("Close", (dd, d) -> {
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Driver</font>"), (dd, d) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>View</font>"), (dd, d) -> {
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getAllBB,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        jsonObject = jsonArray.getJSONObject(j);
                                        reviMode = new ReviMode(jsonObject.getString("id"), jsonObject.getString("form"), jsonObject.getString("entry"), jsonObject.getString("driver_id"), jsonObject.getString("driver_name"),
                                                jsonObject.getString("driver_phone"), jsonObject.getString("drive"), jsonObject.getString("drive_date"),
                                                jsonObject.getString("video_id"), jsonObject.getString("video_name"), jsonObject.getString("video_phone"), jsonObject.getString("video"), jsonObject.getString("video_date"),
                                                jsonObject.getString("cust_id"), jsonObject.getString("cust_name"), jsonObject.getString("cust_phone"), jsonObject.getString("location"),
                                                jsonObject.getString("landmark"), jsonObject.getString("custom"), jsonObject.getString("custom_date"), jsonObject.getString("reg_date"));
                                        reviModeArrayList.add(reviMode);
                                    }
                                    AlertDialog.Builder build = new AlertDialog.Builder(this);
                                    if (reviMode.getVideo().equals("Pending")) {
                                        mdater = "";
                                    } else {
                                        mdater = reviMode.getVideo_date();
                                    }
                                    if (reviMode.getDrive().equals("Pending")) {
                                        mdt = "";
                                    } else {
                                        mdt = reviMode.getDrive_date();
                                    }
                                    if (reviMode.getCustom().equals("Pending")) {
                                        md = "";
                                    } else {
                                        md = reviMode.getCustom_date();
                                    }
                                    build.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Delivery</u></b></font>"));
                                    build.setMessage("ATTACHMENT_DATE\n" + reviMode.getReg_date() + "\n\nCUSTOMER\nname: " + reviMode.getCust_name() + "\nphone: " + reviMode.getCust_phone() + "\nEvent: " + reviMode.getLocation() + " - " + reviMode.getLandmark() + "\nDelivery: " + reviMode.getCustom() + "\ndate: " + md + "\n\nDRIVER\nname: " + reviMode.getDriver_name() + "\nphone: " + reviMode.getDriver_phone() + "\nDelivery: " + reviMode.getDrive() + "\nDate: " + mdt);
                                    build.setPositiveButton("Close", (tr, r) -> {
                                    });
                                    AlertDialog bon = build.create();
                                    bon.show();
                                    bon.setCancelable(false);
                                    bon.setCanceledOnTouchOutside(false);
                                    bon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    bon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                        bon.cancel();
                                    });
                                    bon.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                } else {
                                    AlertDialog.Builder beber = new AlertDialog.Builder(this);
                                    beber.setMessage("Not Found");
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
                        para.put("form", "4");
                        para.put("entry", bidModel.getEntry());
                        return para;
                    }
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.resQue,
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
                                                jsonObject.getString("size"), jsonObject.getString("rgb"), jsonObject.getString("hexa"), jsonObject.getString("red"),
                                                jsonObject.getString("green"), jsonObject.getString("blue"), jsonObject.getString("motive"),
                                                jsonObject.getString("quantity"), jsonObject.getString("price"),
                                                jsonObject.getString("status"), jsonObject.getString("pay"),
                                                jsonObject.getString("fina"), ModelUrl.img + jsonObject.getString("image_desgn"), jsonObject.getString("design"), jsonObject.getString("date"));
                                        requestedMArrayList.add(requestedM);
                                    }
                                    AlertDialog.Builder build = new AlertDialog.Builder(this);
                                    build.setTitle(requestedM.getCategory() + " " + requestedM.getType());
                                    build.setMessage("CUSTOMER\nID: " + requestedM.getCustid() + "\nname: " + requestedM.getName() + "\nphone: " + requestedM.getPhone() + "\n\nPRODUCT REQUEST\ncategory: " + requestedM.getCategory() + "\ntype: " + requestedM.getType() + "\ndescription: " + requestedM.getDescription() + "\nsize: " + requestedM.getSize() + "\nQuantity: " + requestedM.getQuantity() + "\n\nSTATUS\nstatus: " + requestedM.getStatus() + "\nrequestDate: " + requestedM.getDate());
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    begger = layoutInflater.inflate(R.layout.comardos, null);
                                    begger.setMinimumWidth((int) (rect.width() * 1.0));
                                    begger.setMinimumHeight((int) (rect.height() * 0.01));
                                    relColor = begger.findViewById(R.id.relledColor);
                                    relImage = begger.findViewById(R.id.rellledImg);
                                    major = begger.findViewById(R.id.velvet);
                                    Cartel = begger.findViewById(R.id.imgDesc);
                                    frameLayout = new FrameLayout(this);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                    begger.setLayoutParams(params);
                                    frameLayout.addView(begger);
                                    if (Float.parseFloat(requestedM.getDsc()) == 8) {
                                        Glide.with(this).load(requestedM.getImage()).into(Cartel);
                                        relImage.setVisibility(View.VISIBLE);
                                        build.setView(frameLayout);
                                    }
                                    if (Float.parseFloat(requestedM.getMotive()) == 1) {
                                        int red = Integer.parseInt(requestedM.getRed()), green = Integer.parseInt(requestedM.getGreen()), blue = Integer.parseInt(requestedM.getBlue());
                                        major.setBackgroundColor(Color.rgb(red, green, blue));
                                        relColor.setVisibility(View.VISIBLE);
                                        build.setView(frameLayout);
                                    }
                                    build.setPositiveButton("Close", (dialogInterface, i1) -> {
                                    });
                                    build.setNeutralButton("FinalOutlook", (dialogInterface, i) -> {
                                    });
                                    AlertDialog bon = build.create();
                                    bon.show();
                                    bon.setCanceledOnTouchOutside(false);
                                    bon.setCancelable(false);
                                    bon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    bon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view11 -> {
                                        bon.cancel();
                                    });
                                    bon.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                        alert.setTitle("An image for final the product");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        samoa = layoutInflater.inflate(R.layout.add_image, null);
                                        samoa.setMinimumWidth((int) (rect.width() * 1.0));
                                        samoa.setMinimumHeight((int) (rect.height() * 0.01));
                                        kartex = samoa.findViewById(R.id.imgDesc);
                                        kartex.setVisibility(View.VISIBLE);
                                        Glide.with(this).load(requestedM.getImage_desgn()).into(kartex);
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        samoa.setLayoutParams(params);
                                        frameLayout.addView(samoa);
                                        alert.setView(frameLayout);
                                        alert.setNegativeButton("Close", (dialogInterface, i1) -> {
                                        });
                                        AlertDialog mbao = alert.create();
                                        mbao.show();
                                        mbao.setCanceledOnTouchOutside(false);
                                        mbao.setCancelable(false);
                                        mbao.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        mbao.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view11 -> {
                                            mbao.cancel();
                                        });
                                    });
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
                        para.put("slf", bidModel.getEntry());
                        return para;
                    }
                });
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.shipaSpu,
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
                            bidAda = new WeweAda(ShipHistRe.this, R.layout.marathon, bidModelArrayList);
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
                            button.setOnClickListener(view -> {
                                head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510\nApproved Custom Shipment");
                                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
                            });
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
                para.put("design", "Ready");
                para.put("disb", "1");
                return para;
            }
        });
    }
}