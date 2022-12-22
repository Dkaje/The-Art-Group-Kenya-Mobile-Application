package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
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
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CartAda;
import com.example.artgroup.models.CartMod;
import com.example.artgroup.models.CustSess;
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

public class Cart extends AppCompatActivity {
    AlertDialog.Builder builder;
    Dialog dialog;
    Toast toast;
    View samoa;
    BottomNavigationView botaz;
    CustSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    TextView textView;
    TextView textMee, text;
    AlertDialog alertDialog;
    View layer, begger;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    CartMod bidModel;
    ArrayList<CartMod> bidModelArrayList = new ArrayList<>();
    CartAda bidAda;
    ListView listView;
    SearchView searchView;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String newQuantity, newSum, newTimer;
    Button pay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        inflater = getLayoutInflater();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), CustDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.cart:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Payments.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Messeging.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.deliv:
                    startActivity(new Intent(getApplicationContext(), Delivery.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Location</b>: " + userModel.getCounty() + "<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
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
        pay = findViewById(R.id.myPay);
        pay.setAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));
        samoa = inflater.inflate(R.layout.toaster, null);
        textMee = samoa.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(samoa);
        listView = findViewById(R.id.availableGrid);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getSupp();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (CartMod) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.ametoka, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            price = layer.findViewById(R.id.edtQty);
            imageView = layer.findViewById(R.id.tego);
            text = layer.findViewById(R.id.memeToka);
            price.setText(bidModel.getQuantity());
            Glide.with(this).load(bidModel.getImage()).into(imageView);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            price.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String myMpesa = price.getText().toString();
                    if (!myMpesa.isEmpty() & !myMpesa.equals("0")) {
                        newQuantity = myMpesa;
                        newSum = String.format("%.0f", (Float.parseFloat(myMpesa) * Float.parseFloat(bidModel.getPrice())));
                        newTimer = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
                        text.setText("Total: KES" + newSum + "\nas at: " + newTimer);
                        text.setVisibility(View.VISIBLE);
                    } else if (myMpesa.equals(bidModel.getQuantity())) {
                        text.setVisibility(View.GONE);
                    } else {
                        text.setVisibility(View.GONE);
                    }
                }

                public void afterTextChanged(Editable s) {
                }
            });
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Item Details</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<br>Price: KES" + bidModel.getPrice() + "<br>Cost: KES" + String.format("%.0f", Float.parseFloat(bidModel.getQuantity()) * Float.parseFloat(bidModel.getPrice())) + "<br>Date: " + bidModel.getReg_date() + "<br>CustomerID: " + bidModel.getCustid() + "<font>"));
            builder.setPositiveButton("Update", (dd, d) -> {
            });
            builder.setNegativeButton("Close", (dd, d) -> {
            });
            builder.setNeutralButton("Remove", (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(viewd -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.johnCena,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    alertDialog.cancel();
                                    startActivity(new Intent(getApplicationContext(), Cart.class));
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "network error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("id", bidModel.getProduct());
                        para.put("quantity", bidModel.getQuantity());
                        para.put("reg", bidModel.getReg());
                        return para;
                    }
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewd -> {
                final String myQty = price.getText().toString().trim();
                if (myQty.isEmpty()) {
                    price.requestFocus();
                    textMee.setText("Quantity");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else if (Float.parseFloat(myQty) == 0) {
                    price.requestFocus();
                    textMee.setText("You entered nothing");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else if (Float.parseFloat(myQty) == Float.parseFloat(bidModel.getQuantity())) {
                    price.setError("Nothing edited!!");
                    price.requestFocus();
                    textMee.setText("You edited nothing");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.kemuso,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int stat = jsonObject.getInt("status");
                                    String msg = jsonObject.getString("message");
                                    textMee.setText(msg);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (stat == 1) {
                                        startActivity(new Intent(getApplicationContext(), Cart.class));
                                        finish();
                                    } else if (stat == 6) {
                                        price.setError(msg);
                                        price.requestFocus();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    textMee.setText("an error occurred");
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }, error -> {
                        textMee.setText("connection not established");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("quota", myQty);
                            para.put("quantity", bidModel.getQuantity());
                            para.put("id", bidModel.getProduct());
                            para.put("reg", bidModel.getReg());
                            return para;
                        }
                    });
                }
            });
        });
        pay.setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.advise,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    String orders = jsonObject.getString("orders");
                                    String shipping = jsonObject.getString("shipping");
                                    String total = jsonObject.getString("total");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Arap);
                                    builder.setTitle("Make Order");
                                    builder.setMessage("Cost: KES" + orders);
                                    builder.setPositiveButton("Make Payment", (dialoga, o1) -> {
                                    });
                                    builder.setNegativeButton("Close", (dialoga, o1) -> {
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.setCancelable(false);
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                                    alertDialog.show();
                                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        alertDialog.cancel();
                                    });
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        AlertDialog.Builder bilo = new AlertDialog.Builder(this, R.style.CustomDialog);
                                        bilo.setMessage("Choose Option Below");
                                        bilo.setPositiveButton("+ SHIPMENT", (dialoga, o1) -> {
                                        });
                                        bilo.setNeutralButton("no SHIPMENT", (dialoga, o1) -> {
                                        });
                                        bilo.setNegativeButton("Close", (dialoga, o1) -> {
                                        });
                                        AlertDialog alert = bilo.create();
                                        alert.setCanceledOnTouchOutside(false);
                                        alert.setCancelable(false);
                                        alert.getWindow().setGravity(Gravity.CENTER);
                                        alert.show();
                                        alert.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                            alert.cancel();
                                        });
                                        alert.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                            AlertDialog.Builder pen = new AlertDialog.Builder(this, R.style.MemSahib);
                                            pen.setMessage("Shipping Cost KES" + shipping + "\nClick Continue to Proceed");
                                            pen.setPositiveButton("Continue", (dialoga, o1) -> {
                                            });
                                            pen.setNegativeButton("Close", (dialoga, o1) -> {
                                            });
                                            AlertDialog book = pen.create();
                                            book.setCanceledOnTouchOutside(false);
                                            book.setCancelable(false);
                                            book.getWindow().setGravity(Gravity.CENTER);
                                            book.show();
                                            book.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                            book.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            book.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view0 -> {
                                                book.cancel();
                                            });
                                            book.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view0 -> {
                                                AlertDialog.Builder brathe = new AlertDialog.Builder(this, R.style.MemSahib);
                                                brathe.setTitle("Location");
                                                rect = new Rect();
                                                window = this.getWindow();
                                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                layer = layoutInflater.inflate(R.layout.current, null);
                                                layer.setMinimumWidth((int) (rect.width() * 1.0));
                                                layer.setMinimumHeight((int) (rect.height() * 0.01));
                                                spinner = layer.findViewById(R.id.spinCurrent);
                                                land = layer.findViewById(R.id.edtLand);
                                                house = layer.findViewById(R.id.edtHouse);
                                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Current, android.R.layout.simple_spinner_item);
                                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinner.setAdapter(adapter);
                                                frameLayout = new FrameLayout(this);
                                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                layer.setLayoutParams(params);
                                                frameLayout.addView(layer);
                                                brathe.setView(frameLayout);
                                                brathe.setPositiveButton("Submit", (dialoga, o1) -> {
                                                });
                                                brathe.setNegativeButton("Close", (dialoga, o1) -> {
                                                });
                                                AlertDialog mug = brathe.create();
                                                mug.setCanceledOnTouchOutside(false);
                                                mug.setCancelable(false);
                                                mug.getWindow().setGravity(Gravity.CENTER);
                                                mug.show();
                                                mug.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                mug.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                mug.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view8 -> {
                                                    mug.cancel();
                                                });
                                                mug.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view8 -> {
                                                    final String spin = spinner.getSelectedItem().toString().trim();
                                                    final String myLan = land.getText().toString().trim();
                                                    final String myHouse = house.getText().toString().trim();
                                                    if (spin.equals("Current Location")) {
                                                        textMee.setText("Current Location Required");
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    } else if (myLan.isEmpty()) {
                                                        land.requestFocus();
                                                        textMee.setText("Add some known landmark");
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    } else if (myHouse.isEmpty()) {
                                                        house.requestFocus();
                                                        textMee.setText("Add House/Village");
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    } else {
                                                        AlertDialog.Builder ranyard = new AlertDialog.Builder(this, R.style.MemSahib);
                                                        ranyard.setMessage("Amount KES" + orders + "\nShipping KES" + shipping + "\nTotal: KES" + total + "\nMPESA PAYBILL 456123 \nAccountNO:" + userModel.getUser_id() + "\nEnter MPESA CODE below");
                                                        rect = new Rect();
                                                        window = this.getWindow();
                                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                        layer = layoutInflater.inflate(R.layout.mpe, null);
                                                        layer.setMinimumWidth((int) (rect.width() * 1.0));
                                                        layer.setMinimumHeight((int) (rect.height() * 0.01));
                                                        description = layer.findViewById(R.id.edtMpesa);
                                                        frameLayout = new FrameLayout(this);
                                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                        layer.setLayoutParams(params);
                                                        frameLayout.addView(layer);
                                                        ranyard.setView(frameLayout);
                                                        ranyard.setPositiveButton("Submit", (dialoga, o1) -> {
                                                        });
                                                        ranyard.setNegativeButton("Close", (dialoga, o1) -> {
                                                        });
                                                        AlertDialog hustla = ranyard.create();
                                                        hustla.setCanceledOnTouchOutside(false);
                                                        hustla.setCancelable(false);
                                                        hustla.getWindow().setGravity(Gravity.CENTER);
                                                        hustla.show();
                                                        hustla.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                        hustla.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        hustla.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view78 -> {
                                                            hustla.cancel();
                                                        });
                                                        hustla.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view78 -> {
                                                            final String myQty = description.getText().toString().trim();
                                                            if (myQty.isEmpty()) {
                                                                description.requestFocus();
                                                                textMee.setText("MPESA CODE");
                                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                                toast.show();
                                                            } else if (myQty.length() < 10) {
                                                                description.requestFocus();
                                                                textMee.setText("invalid");
                                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                                toast.show();
                                                            } else {
                                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.asleep,
                                                                        respons -> {
                                                                            try {
                                                                                jsonObject = new JSONObject(respons);
                                                                                int stat = jsonObject.getInt("success");
                                                                                String msg = jsonObject.getString("message");
                                                                                textMee.setText(msg);
                                                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                                                toast.show();
                                                                                if (stat == 1) {
                                                                                    startActivity(new Intent(getApplicationContext(), Cart.class));
                                                                                    finish();
                                                                                } else if (stat == 6) {
                                                                                    description.setError(msg);
                                                                                    description.requestFocus();
                                                                                }
                                                                            } catch (Exception e) {
                                                                                e.printStackTrace();
                                                                                textMee.setText("an error occurred");
                                                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                                                toast.show();
                                                                            }
                                                                        }, error -> {
                                                                    textMee.setText("connection not established");
                                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                                    toast.show();
                                                                }) {
                                                                    @Nullable
                                                                    @Override
                                                                    protected Map<String, String> getParams() throws AuthFailureError {
                                                                        Map<String, String> params = new HashMap<>();
                                                                        params.put("mpesa", myQty);
                                                                        params.put("amount", total);
                                                                        params.put("orders", orders);
                                                                        params.put("ship", shipping);
                                                                        params.put("custid", userModel.getUser_id());
                                                                        params.put("name", userModel.getFname() + " " + userModel.getLname());
                                                                        params.put("phone", userModel.getPhone());
                                                                        params.put("location", spin);
                                                                        params.put("landmark", myLan + "-" + myHouse);
                                                                        params.put("reg_date",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                                                        return params;
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            });
                                        });
                                        alert.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view5 -> {
                                            AlertDialog.Builder pen = new AlertDialog.Builder(this, R.style.MemSahib);
                                            pen.setMessage("Amount KES" + orders + "\nMPESA PAYBILL 456123 \nAccountNO:" + userModel.getUser_id() + "\nEnter MPESA CODE below");
                                            rect = new Rect();
                                            window = this.getWindow();
                                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            layer = layoutInflater.inflate(R.layout.mpe, null);
                                            layer.setMinimumWidth((int) (rect.width() * 1.0));
                                            layer.setMinimumHeight((int) (rect.height() * 0.01));
                                            description = layer.findViewById(R.id.edtMpesa);
                                            frameLayout = new FrameLayout(this);
                                            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                            layer.setLayoutParams(params);
                                            frameLayout.addView(layer);
                                            pen.setView(frameLayout);
                                            pen.setPositiveButton("Submit", (dialoga, o1) -> {
                                            });
                                            pen.setNegativeButton("Close", (dialoga, o1) -> {
                                            });
                                            AlertDialog book = pen.create();
                                            book.setCanceledOnTouchOutside(false);
                                            book.setCancelable(false);
                                            book.getWindow().setGravity(Gravity.CENTER);
                                            book.show();
                                            book.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                            book.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            book.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view0 -> {
                                                book.cancel();
                                            });
                                            book.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view0 -> {
                                                final String myQty = description.getText().toString().trim();
                                                if (myQty.isEmpty()) {
                                                    description.requestFocus();
                                                    textMee.setText("MPESA CODE");
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                } else if (myQty.length() < 10) {
                                                    description.requestFocus();
                                                    textMee.setText("invalid");
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                } else {
                                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.asleep,
                                                            respons -> {
                                                                try {
                                                                    jsonObject = new JSONObject(respons);
                                                                    int stat = jsonObject.getInt("success");
                                                                    String msg = jsonObject.getString("message");
                                                                    textMee.setText(msg);
                                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                                    toast.show();
                                                                    if (stat == 1) {
                                                                        startActivity(new Intent(getApplicationContext(), Cart.class));
                                                                        finish();
                                                                    } else if (stat == 6) {
                                                                        description.setError(msg);
                                                                        description.requestFocus();
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                    textMee.setText("an error occurred");
                                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                                    toast.show();
                                                                }
                                                            }, error -> {
                                                        textMee.setText("connection not established");
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    }) {
                                                        @Nullable
                                                        @Override
                                                        protected Map<String, String> getParams() throws AuthFailureError {
                                                            Map<String, String> params = new HashMap<>();
                                                            params.put("mpesa", myQty);
                                                            params.put("amount", orders);
                                                            params.put("orders", orders);
                                                            params.put("ship", "0");
                                                            params.put("custid", userModel.getUser_id());
                                                            params.put("name", userModel.getFname() + " " + userModel.getLname());
                                                            params.put("phone", userModel.getPhone());
                                                            params.put("location", "");
                                                            params.put("landmark", "");
                                                            params.put("reg_date",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                                            return params;
                                                        }
                                                    });
                                                }
                                            });
                                        });
                                    });
                                }
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
                    return para;
                }
            });
        });
    }

    private void getSupp() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.cart_Ge,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
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
                                bidModel = new CartMod(reg, entry, custid, product, category, type, price, quantity, imagery, status, reg_date);
                                bidModelArrayList.add(bidModel);
                            }
                            pay.setVisibility(View.VISIBLE);
                            bidAda = new CartAda(Cart.this, R.layout.marathon, bidModelArrayList);
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
                return para;
            }
        });
    }
}