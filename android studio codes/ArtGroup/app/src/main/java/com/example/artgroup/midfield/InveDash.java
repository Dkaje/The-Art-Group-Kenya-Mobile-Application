package com.example.artgroup.midfield;

import android.app.Dialog;
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
import android.widget.Button;
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
import com.example.artgroup.invent.Bidding;
import com.example.artgroup.invent.Messages;
import com.example.artgroup.invent.RawMate;
import com.example.artgroup.invent.RequestEd;
import com.example.artgroup.invent.Supply;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.ProdAda;
import com.example.artgroup.models.ProdModel;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InveDash extends AppCompatActivity {
    TextView textMee, text;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    Toast toast;
    View layer, samoa, begger;
    Rect rect;
    Window window;
    EditText price, description;
    ImageView imageView, profile, Cartel;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    InveSess custSess;
    UserModel userModel;
    TextView textView;
    BottomNavigationView botaz, topaz;
    ProdModel bidModel;
    ArrayList<ProdModel> bidModelArrayList = new ArrayList<>();
    ProdAda bidAda;
    ListView listView;
    SearchView searchView;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new InveSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_inve_dash);
        inflater = getLayoutInflater();
        samoa = inflater.inflate(R.layout.toaster, null);
        textMee = samoa.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(samoa);
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        topaz = findViewById(R.id.Best);
        topaz.setSelectedItemId(R.id.noti);
        topaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), RawMate.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), Bidding.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Supply.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), RequestEd.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
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
        findViewById(R.id.chatTex).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Messages.class));
        });
        listView = findViewById(R.id.availableGrid);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getSupp();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (ProdModel) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Product Details</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<br>Price: Kes" + bidModel.getPrice() + "<br>Date: " + bidModel.getReg_date() + "<font>"));
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
            Glide.with(this).load(bidModel.getImage()).into(imageView);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Update", (dd, d) -> {
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
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.updatePR,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    String mess = jsonObject.getString("message");
                                    int Status = jsonObject.getInt("success");
                                    textMee.setText(mess);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (Status == 1) {
                                        startActivity(new Intent(getApplicationContext(), InveDash.class));
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
                            params.put("id", bidModel.getId());
                            params.put("price", myQty);
                            return params;
                        }
                    });
                }
            });
        });

    }

    private void getSupp() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewPr,
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
                            bidAda = new ProdAda(InveDash.this, R.layout.marathon, bidModelArrayList);
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

        }));
    }
}