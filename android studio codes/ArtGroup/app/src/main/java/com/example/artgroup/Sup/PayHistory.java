package com.example.artgroup.Sup;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.SupDash;
import com.example.artgroup.models.PesaAda;
import com.example.artgroup.models.PesaMode;
import com.example.artgroup.models.SuppSess;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayHistory extends AppCompatActivity {
    ImageView imageView, profile, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    SuppSess custSess;
    UserModel userModel;
    ListView listView;
    SearchView searchView;
    ArrayList<PesaMode> credModArrayList = new ArrayList<>();
    PesaAda credAda;
    PesaMode credMod;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_history);
        custSess = new SuppSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), History.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Communicate.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), SupDash.class));
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
        listView = findViewById(R.id.listed);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getCredit();
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            builder = new AlertDialog.Builder(this);
            Rect reta = new Rect();
            Window window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(reta);
            LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.king, null);
            amount = layer.findViewById(R.id.txtBig);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            amount.setText(Html.fromHtml("<font><strong><b><u>The Art Group Nairobi<br>0116 284 3691, 0706 287510</u></b></strong><br><br>SupplierID: " + credMod.getSupplier() + "::" + credMod.getFullname() + "<br>Phone: " + credMod.getPhone() + "<br><br>MPESA: " + credMod.getMpesa() + "<br>Amount: KShs<strong>" + credMod.getAmount() + "</strong><br>Date: " + credMod.getDate() + "</font>"));
            builder.setPositiveButton("Print", (dialog, idm) -> {
            });
            builder.setNegativeButton("Close", (dialo, iof) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                genPDF();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                startActivity(new Intent(getApplicationContext(), PayHistory.class));
                finish();
            });
        }));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void genPDF() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PDFGenerator(this, layer.findViewById(R.id.monti)), null);
    }

    private void getCredit() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.sawaPesa,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                credMod = new PesaMode(jsonObject.getString("mpesa"), jsonObject.getString("supplier"), jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                        jsonObject.getString("amount"), jsonObject.getString("date"));
                                credModArrayList.add(credMod);
                            }
                            credAda = new PesaAda(PayHistory.this, R.layout.render, credModArrayList);
                            listView.setAdapter(credAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    credAda.getFilter().filter(newText);
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
                params.put("supplier", userModel.getUser_id());
                return params;
            }
        });

    }
}