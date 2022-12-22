package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ProdAda;
import com.example.artgroup.models.ProdModel;
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

public class CategoryYoo extends AppCompatActivity {
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog, otis;
    View samoa;
    BottomNavigationView botaz;
    ImageView imageView, profile;
    TextView textView, head;
    TextView textMee;
    Toast toast;
    View layer;
    Rect rect;
    Window window;
    EditText price;
    CustSess custSess;
    UserModel userModel;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    ProdModel bidModel;
    ArrayList<ProdModel> bidModelArrayList = new ArrayList<>();
    ProdAda bidAda;
    ListView listView;
    SearchView searchView;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String myCategory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_yoo);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Payments.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), CustDash.class));
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
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), Cart.class));
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
        inflater = getLayoutInflater();
        samoa = inflater.inflate(R.layout.toaster, null);
        textMee = samoa.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(samoa);
        listView = findViewById(R.id.availableGrid);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getSupp();
        Intent inte = getIntent();
        if (inte != null) {
            myCategory = inte.getStringExtra("category");
        }
        head = findViewById(R.id.txtEmo);
        head.setText(myCategory);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (ProdModel) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Product Details</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<br>Price: KES" + bidModel.getPrice() + "<br>Date: " + bidModel.getReg_date() + "<font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.ametoka, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            price = layer.findViewById(R.id.edtQty);
            imageView = layer.findViewById(R.id.tego);
            price.setHint("Enter some Quantity");
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
                        if (Float.parseFloat(myMpesa) > Float.parseFloat(bidModel.getQuantity())) {
                            price.setError("???");
                            price.requestFocus();
                            textMee.setText("You Entered a huge quantity");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                        }
                    }
                }

                public void afterTextChanged(Editable s) {
                }
            });
            builder.setPositiveButton("Add_To_Cart", (dd, d) -> {
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
                } else if (Float.parseFloat(myQty) > Float.parseFloat(bidModel.getQuantity())) {
                    price.requestFocus();
                    textMee.setText("You Entered a huge quantity");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.cartPlus,
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
                            para.put("id", bidModel.getId());
                            para.put("quant", String.format("%.0f", (Float.parseFloat(bidModel.getQuantity()) - Float.parseFloat(myQty))));
                            para.put("quantity", myQty);
                            para.put("price", bidModel.getPrice());
                            para.put("type", bidModel.getType());
                            para.put("category", bidModel.getCategory());
                            para.put("custid", userModel.getUser_id());
                            para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                            return para;
                        }
                    });
                }
            });
        });
    }

    private void getSupp() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getTyp,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String category = jsonObject.getString("category");
                                String type = jsonObject.getString("type");
                                String description = jsonObject.getString("description");
                                String image = jsonObject.getString("image");
                                String imagery = ModelUrl.img + image;
                                String qty = jsonObject.getString("qty");
                                String quantity = jsonObject.getString("quantity");
                                String price = jsonObject.getString("price");
                                String reg_date = jsonObject.getString("reg_date");
                                bidModel = new ProdModel(id, category, type, description, imagery, qty, quantity, price, reg_date);
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new ProdAda(CategoryYoo.this, R.layout.marathon, bidModelArrayList);
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
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("category", myCategory);
                return para;
            }
        });
    }
}