package com.example.artgroup.invent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.bumptech.glide.Glide;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.InveDash;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.SupAda;
import com.example.artgroup.models.SupMod;
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

public class Supply extends AppCompatActivity {
    ImageView imageView, profile, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    InveSess custSess;
    UserModel userModel;
    TextView textMee, text;
    Toast toast;
    Rect rect;
    Window window;
    EditText price, description;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    SupMod bidModel;
    ArrayList<SupMod> bidModelArrayList = new ArrayList<>();
    SupAda bidAda;
    ListView listView;
    SearchView searchView;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);
        inflater = getLayoutInflater();
        samoa = inflater.inflate(R.layout.toaster, null);
        textMee = samoa.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(samoa);
        custSess = new InveSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.noti);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), InveDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), RequestEd.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), Bidding.class));
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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        findViewById(R.id.btnPast).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SupHis.class));
            finish();
        });
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getSupp();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (SupMod) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getClassification() + "<br>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<br>supplierPrice KES" + bidModel.getPrice() + "<br>Date: " + bidModel.getReg_date() + "<br>Status: " + bidModel.getStatus() + "<br>SupplierID: " + bidModel.getSupplier() + "<font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.ametoka, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            price = layer.findViewById(R.id.edtQty);
            imageView = layer.findViewById(R.id.tego);
            price.setText(bidModel.getPrice());
            if (bidModel.getClassification().equals("Raw Material")) {
                builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Raw Material Details</u></b></font>"));
                price.setEnabled(false);
            } else {
                price.setEnabled(true);
                builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Edit Price</u></b></font>"));
            }
            Glide.with(this).load(bidModel.getImage()).into(imageView);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Submit", (dd, d) -> {
            });
            builder.setNegativeButton("Reject", (dd, d) -> {
            });
            builder.setNeutralButton("Close", (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(viewd -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewd -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.lostSheep,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                String mess = jsonObject.getString("message");
                                int Status = jsonObject.getInt("success");
                                textMee.setText(mess);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                                if (Status == 1) {
                                    startActivity(new Intent(getApplicationContext(), Supply.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                textMee.setText("Server Error");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }, error -> {
                    textMee.setText("Network Error");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("sup", bidModel.getSup());
                        params.put("bid", bidModel.getBid());
                        params.put("quantity", bidModel.getQuantity());
                        return params;
                    }
                });
            });
            if (bidModel.getClassification().equals("Raw Material")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewd -> {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getRa,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    String mess = jsonObject.getString("message");
                                    int Status = jsonObject.getInt("success");
                                    textMee.setText(mess);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (Status == 1) {
                                        startActivity(new Intent(getApplicationContext(), Supply.class));
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    textMee.setText("Server Error");
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }, error -> {
                        textMee.setText("Network Error");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("sup", bidModel.getSup());
                            params.put("category", bidModel.getCategory());
                            params.put("type", bidModel.getType());
                            params.put("description", bidModel.getDescription());
                            params.put("quantity", bidModel.getQuantity());
                            params.put("price", bidModel.getPrice());
                            params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                            return params;
                        }
                    });
                });
            } else {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewd -> {
                    final String myQty = price.getText().toString().trim();
                    if (myQty.isEmpty()) {
                        price.requestFocus();
                        textMee.setText("Price");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myQty) < Float.parseFloat(bidModel.getPrice())) {
                        price.requestFocus();
                        textMee.setText("Low Price");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.syphony,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        String mess = jsonObject.getString("message");
                                        int Status = jsonObject.getInt("success");
                                        textMee.setText(mess);
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                        if (Status == 1) {
                                            startActivity(new Intent(getApplicationContext(), Supply.class));
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        textMee.setText("Server Error");
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }, error -> {
                            textMee.setText("Network Error");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("sup", bidModel.getSup());
                                params.put("category", bidModel.getCategory());
                                params.put("type", bidModel.getType());
                                params.put("description", bidModel.getDescription());
                                params.put("quantity", bidModel.getQuantity());
                                params.put("price", myQty);
                                params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                return params;
                            }
                        });
                    }
                });
            }
        });
    }

    private void getSupp() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.pendSup,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                bidModel = new SupMod(jsonObject.getString("sup"), jsonObject.getString("bid"), jsonObject.getString("classification"), jsonObject.getString("category"),
                                        jsonObject.getString("type"), jsonObject.getString("quantity"), jsonObject.getString("price"),
                                        jsonObject.getString("description"), ModelUrl.img + jsonObject.getString("image"), jsonObject.getString("supplier"),
                                        jsonObject.getString("status"), jsonObject.getString("pay"), jsonObject.getString("reg_date"));
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new SupAda(Supply.this, R.layout.marathon, bidModelArrayList);
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