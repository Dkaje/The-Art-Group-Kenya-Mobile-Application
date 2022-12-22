package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ManuAda;
import com.example.artgroup.models.PayerSe;
import com.example.artgroup.models.RegretAda;
import com.example.artgroup.models.ServModel;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LindaMama extends AppCompatActivity {
    ImageView imageView, profile;
    TextView textView, head;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, logger;
    RequestQueue requestQueue;
    View layer;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    LayoutInflater layoutInflater, inflater;
    Spinner categor, typed;
    String myTpe;
    PayerSe payerSe;
    ArrayList<PayerSe> payerSeArrayList = new ArrayList<>();
    RegretAda regretAda;
    Button button;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustSess custSess;
    UserModel userModel;
    BottomNavigationView botaz;
    ServModel servModel;
    ArrayList<ServModel> servModelArrayList = new ArrayList<>();
    ManuAda servAda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linda_mama);
        head = findViewById(R.id.myTxt);
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        imageView.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.Ord);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), MyPastServ.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Booker.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Imag:
                    startActivity(new Intent(getApplicationContext(), MyImages.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), SuccessfulPlan.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        button = findViewById(R.id.addNEw);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getRecords();
        button.setOnClickListener(view -> {
            head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510");
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            servModel = (ServModel) parent.getItemAtPosition(position);
            String deal;
            if (servModel.getStatus().equals("Pending")) {
                deal = "";
            } else {
                deal = "KES" + servModel.getCharge();
            }
            alert = new AlertDialog.Builder(this, R.style.Arap);
            alert.setTitle("Ordered Service");
            alert.setMessage("CustomerID: " + servModel.getCustid() + "\nName: " + servModel.getName() + "\nPhone: " + servModel.getPhone() + "\nLocation: " + servModel.getLocation() + " - " + servModel.getLandmark() + "\n\nSERVICE\nCategory: " + servModel.getCategory() + "\nType: " + servModel.getType() + "\nDescription: " + servModel.getDescription() + "\n\nSTATUS\nCharge: " + deal + "\ndateOrdered: " + servModel.getReg_date() + "\nStatus: " + servModel.getStatus() + "\npayment: " + servModel.getPay());
            alert.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            alert.setNeutralButton("MORE", (dialogInterface, i1) -> {
            });
            logger = alert.create();
            logger.show();
            logger.setCanceledOnTouchOutside(false);
            logger.setCancelable(false);
            logger.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            logger.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            logger.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                logger.cancel();
            });
            logger.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.onyii,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        payerSe = new PayerSe(jsonObject.getString("serid"), jsonObject.getString("mpesa"), jsonObject.getString("amount"),
                                                jsonObject.getString("category"), jsonObject.getString("type"), jsonObject.getString("description"),
                                                jsonObject.getString("serv"), jsonObject.getString("custid"), jsonObject.getString("name"),
                                                jsonObject.getString("phone"), jsonObject.getString("location"), jsonObject.getString("landmark"),
                                                jsonObject.getString("status"), jsonObject.getString("comment"), jsonObject.getString("disb"), jsonObject.getString("reg_date"));
                                        payerSeArrayList.add(payerSe);
                                    }
                                    builder = new AlertDialog.Builder(this, R.style.Arap);
                                    builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Service Payment Details</u></b></font>"));
                                    if (payerSe.getStatus().equals("Pending")) {
                                        builder.setMessage("CustomerID: " + payerSe.getCustid() + "\nName: " + payerSe.getName() + "\nPhone: " + payerSe.getPhone() + "\nLocation: " + payerSe.getLocation() + " - " + payerSe.getLandmark() + "\n\nPAYMENT\nMPESA: " + payerSe.getMpesa() + "\nCharge: KES" + payerSe.getAmount() + "\nStatus: " + payerSe.getStatus() + "\n\nSERVICE\nCategory: " + payerSe.getCategory() + "\nType: " + payerSe.getType() + "\nDescription: " + payerSe.getDescription() + "\nDate: " + payerSe.getReg_date());
                                    } else {
                                        builder.setMessage("CustomerID: " + payerSe.getCustid() + "\nName: " + payerSe.getName() + "\nPhone: " + payerSe.getPhone() + "\nLocation: " + payerSe.getLocation() + " - " + payerSe.getLandmark() + "\n\nPAYMENT\nMPESA: " + payerSe.getMpesa() + "\nCharge: KES" + payerSe.getAmount() + "\nStatus: " + payerSe.getStatus() + "\n\nSERVICE\nCategory: " + payerSe.getCategory() + "\nType: " + payerSe.getType() + "\nDescription: " + payerSe.getDescription() + "\nAttachment: " + payerSe.getDisb() + "\nDate: " + payerSe.getReg_date());
                                    }
                                    builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.setCancelable(false);
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.show();
                                    alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                                    alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view11 -> {
                                        alertDialog.cancel();
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
                        para.put("serv", servModel.getServ());
                        return para;
                    }
                });
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.basi,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                servModel = new ServModel(jsonObject.getString("serv"), jsonObject.getString("custid"), jsonObject.getString("name"), jsonObject.getString("phone"),
                                        jsonObject.getString("location"), jsonObject.getString("landmark"), jsonObject.getString("category"), jsonObject.getString("type"),
                                        jsonObject.getString("description"), jsonObject.getString("charge"), jsonObject.getString("status"), jsonObject.getString("pay"), jsonObject.getString("reg_date"));
                                servModelArrayList.add(servModel);
                            }
                            servAda = new ManuAda(LindaMama.this, R.layout.marathon, servModelArrayList);
                            listView.setAdapter(servAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    servAda.getFilter().filter(newText);
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
                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("custid", userModel.getUser_id());
                params.put("status", "Approved");
                params.put("pay", "Paid");
                return params;
            }
        });
    }
}