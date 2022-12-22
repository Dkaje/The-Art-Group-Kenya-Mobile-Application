package com.example.artgroup.Uriah;

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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.MultiAda;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.ReviMode;
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

public class SelfDeli extends AppCompatActivity {
    ImageView imageView, kartex, Cartel;
    TextView textView, head;
    BottomNavigationView botaz;
    AlertDialog.Builder builder, alert;
    AlertDialog dialog;
    Spinner spinner;
    View major, begger, samoa;
    Rect rect;
    Window window;
    EditText qty;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    Spinner categor, typed;
    String myTpe;
    ReviMode bidModel;
    ArrayList<ReviMode> bidModelArrayList = new ArrayList<>();
    MultiAda bidAda;
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
    String mdater, md, mdt;
    CustSess custSess;
    UserModel userModel;
    RelativeLayout relativeLayout, relColor, relImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_deli);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.noti);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), UpcomingPay.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), SendRequirement.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        head = findViewById(R.id.myTxt);
        button = findViewById(R.id.addNEw);
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getRecords();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            bidModel = (ReviMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Delivery</u></b></font>"));
            if (bidModel.getVideo().equals("Pending")) {
                mdater = "";
            } else {
                mdater = bidModel.getVideo_date();
            }
            if (bidModel.getDrive().equals("Pending")) {
                mdt = "";
            } else {
                mdt = bidModel.getDrive_date();
            }
            if (bidModel.getCustom().equals("Pending")) {
                md = "";
            } else {
                md = bidModel.getCustom_date();
            }
            builder.setMessage("ATTACHMENT_DATE\n" + bidModel.getReg_date() + "\n\nHi " + bidModel.getDriver_name() + ",\nYou were assigned the role of shipping products related to:-\n\nCustomer: " + bidModel.getCust_name() + "\nPhone: " + bidModel.getCust_phone() + "\n\nto\nLocation: " + bidModel.getLocation() + " - " + bidModel.getLandmark() + "\non Date: " + bidModel.getReg_date() + "\nClick Confirm Below if Delivered");
            if (bidModel.getCustom().equals("Pending")) {
                builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Confirm</font>"), (dd, d) -> {
                });
            }
            builder.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>View</font>"), (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            if (bidModel.getCustom().equals("Pending")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Confirm</u></b></font>"));
                    alert.setMessage("Hi " + bidModel.getDriver_name() + ",\nBy Clicking CONFIRM\nYou agree that the delivery was Successful\nDo you want to proceed?\nYou cannot undo this process");
                    alert.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Yes_Agreed</font>"), (dd, d) -> {
                    });
                    alert.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Not_Yet</font>"), (dd, d) -> {
                    });
                    AlertDialog otis = alert.create();
                    otis.setCancelable(false);
                    otis.setCanceledOnTouchOutside(false);
                    otis.show();
                    otis.getWindow().setGravity(Gravity.CENTER);
                    otis.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1r -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.updt,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int status = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (status == 1) {
                                            startActivity(new Intent(getApplicationContext(), SelfDeli.class));
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(this, "Server Error!!", Toast.LENGTH_SHORT).show();
                                    }
                                }, error -> {
                            Toast.makeText(this, "Network Connection!!", Toast.LENGTH_SHORT).show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> para = new HashMap<>();
                                para.put("id", bidModel.getId());
                                para.put("custom", "Delivered");
                                para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                return para;
                            }
                        });
                    });
                    otis.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1r -> {
                        otis.cancel();
                    });
                });
            }
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
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
                                    for (int ir = 0; ir < jsonArray.length(); ir++) {
                                        jsonObject = jsonArray.getJSONObject(ir);
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
                                    build.setNeutralButton("FinalOutlook", (dialogInterface, i1) -> {
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
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.kubaba,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                bidModel = new ReviMode(jsonObject.getString("id"), jsonObject.getString("form"), jsonObject.getString("entry"), jsonObject.getString("driver_id"), jsonObject.getString("driver_name"),
                                        jsonObject.getString("driver_phone"), jsonObject.getString("drive"), jsonObject.getString("drive_date"),
                                        jsonObject.getString("video_id"), jsonObject.getString("video_name"), jsonObject.getString("video_phone"), jsonObject.getString("video"), jsonObject.getString("video_date"),
                                        jsonObject.getString("cust_id"), jsonObject.getString("cust_name"), jsonObject.getString("cust_phone"), jsonObject.getString("location"),
                                        jsonObject.getString("landmark"), jsonObject.getString("custom"), jsonObject.getString("custom_date"), jsonObject.getString("reg_date"));
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new MultiAda(SelfDeli.this, R.layout.marathon, bidModelArrayList);
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
                para.put("drive", "Delivered");
                para.put("form", "4");
                para.put("cust_id", userModel.getUser_id());
                return para;
            }
        });
    }
}