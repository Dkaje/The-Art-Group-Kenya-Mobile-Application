package com.example.artgroup.fina;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.FinaDash;
import com.example.artgroup.models.FinaSess;
import com.example.artgroup.models.Kanyonga;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Investment extends AppCompatActivity {
    FinaSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    TextView textView, head, contents;
    BottomNavigationView botaz;
    RequestQueue requestQueue;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Button button;
    String amt, rdy, cst, serv, sup, bal, dt;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    Dialog dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new FinaSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_investment);
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        contents = findViewById(R.id.nma);
        button = findViewById(R.id.printer);
        profile = findViewById(R.id.myProfile);
        head = findViewById(R.id.myTxt);
        botaz = findViewById(R.id.topper);
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
            dial = builder.create();
            dial.setCancelable(false);
            dial.setCanceledOnTouchOutside(false);
            dial.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
            dial.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            dial.show();
            dial.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        botaz.setSelectedItemId(R.id.chat);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.chat:
                    return true;
                case R.id.credit:
                    startActivity(new Intent(getApplicationContext(), Creditors.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.hire:
                    startActivity(new Intent(getApplicationContext(), ServPe.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), UtajuaBana.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.ready:
                    startActivity(new Intent(getApplicationContext(), FinaDash.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getM,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                amt = jsonObject.getString("amount");
                                rdy = jsonObject.getString("ready");
                                cst = jsonObject.getString("custom");
                                serv = jsonObject.getString("service");
                                sup = jsonObject.getString("supplier");
                                bal = jsonObject.getString("balance");
                                dt = jsonObject.getString("udate");
                            }
                            head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510\nAccounts Record\nas at " + dt);
                            contents.setText("ReadyMade: KES " + rdy + "\nCustomMade: KES " + cst + "\nServiceOrder: KES " + serv + "\n\nAMOUNT: KES " + amt + "\n\nSupplierPayment: KES " + sup + "\nRemainingAmount: KES " + bal + "\n(" + Kanyonga.convert(Long.parseLong(bal)) + ")\nas at " + dt);
                            button.setVisibility(View.VISIBLE);
                            button.setOnClickListener(view -> {
                                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
                            });
                        } else {
                            findViewById(R.id.memes).setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

        }));
    }
}