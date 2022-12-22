package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.PayerSe;
import com.example.artgroup.models.SafAda;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportServ extends AppCompatActivity {
    ImageView imageView, Cartel;
    TextView texter, loadIt, loadDt, loadAmount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    PayerSe payerSe;
    ArrayList<PayerSe> payerSeArrayList = new ArrayList<>();
    SafAda safAda;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    SearchView searchView;
    Button button;
    RequestQueue requestQueue;
    CustSess custSess;
    UserModel userModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_serv);
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
                    startActivity(new Intent(getApplicationContext(), ReportCust.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MyReports.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        button = findViewById(R.id.addNEw);
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getRecords();
        button.setOnClickListener(view -> {
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            payerSe = (PayerSe) parent.getItemAtPosition(position);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Rect rect = new Rect();
            Window window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.drama, null);
            layer.setMinimumWidth((int) (rect.width() * 0.9f));
            layer.setMinimumHeight((int) (rect.height() * 0.08f));
            texter = layer.findViewById(R.id.myTexter);
            loadAmount = layer.findViewById(R.id.pesa);
            loadDt = layer.findViewById(R.id.detl);
            loadIt = layer.findViewById(R.id.itemed);
            loadIt.setText(payerSe.getCategory());
            loadAmount.setText("KES" + payerSe.getAmount());
            loadDt.setText(payerSe.getType());
            texter.setText("Receipt: " + payerSe.getSerid() + "\nCustomer: " + payerSe.getName() + "\nphone: " + payerSe.getPhone() + "\nLocation: " + payerSe.getLocation() + " " + payerSe.getLandmark() + "\nDate: " + payerSe.getReg_date());
            alert.setView(layer);
            alert.setPositiveButton("Load", (dialog, ids) -> {
            });
            alert.setNegativeButton("close", (dialog, ids) -> {
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                printManager.print(getString(R.string.app_name), new PDFGenerator(this, layer.findViewById(R.id.titled)), null);
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.gettoSer,
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
                            safAda = new SafAda(ReportServ.this, R.layout.sammy, payerSeArrayList);
                            listView.setAdapter(safAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    safAda.getFilter().filter(newText);
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
                para.put("custid", userModel.getUser_id());
                para.put("status", "1");
                return para;
            }
        });
    }
}