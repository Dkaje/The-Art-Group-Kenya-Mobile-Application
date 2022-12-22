package com.example.artgroup.Uriah;

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
import com.example.artgroup.models.Asapa;
import com.example.artgroup.models.CartMod;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.PayMod;
import com.example.artgroup.models.ResistanceAda;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyReports extends AppCompatActivity {
    ImageView imageView, Cartel;
    TextView texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    PayMod bidModel;
    ArrayList<PayMod> bidModelArrayList = new ArrayList<>();
    Asapa asapa;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView, lister;
    SearchView searchView;
    Button button;
    RequestQueue requestQueue;
    CustSess custSess;
    UserModel userModel;
    CartMod cartMod;
    ArrayList<CartMod> cartModArrayList = new ArrayList<>();
    ResistanceAda cartAda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), ReportCust.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), ReportServ.class));
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
            bidModel = (PayMod) parent.getItemAtPosition(position);
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCa,
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
                                    cartMod = new CartMod(reg, entry, custid, product, category, type, price, quantity, imagery, status, reg_date);
                                    cartModArrayList.add(cartMod);
                                }
                                AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.Arap);
                                Rect rect = new Rect();
                                Window window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                layer = layoutInflater.inflate(R.layout.recite, null);
                                layer.setMinimumWidth((int) (rect.width() * 0.9f));
                                layer.setMinimumHeight((int) (rect.height() * 0.08f));
                                texter = layer.findViewById(R.id.myTexter);
                                amount = layer.findViewById(R.id.shipper);
                                lister = layer.findViewById(R.id.listerS);
                                lister.setTextFilterEnabled(true);
                                cartAda = new ResistanceAda(MyReports.this, R.layout.loader, cartModArrayList);
                                lister.setAdapter(cartAda);
                                if (Float.parseFloat(bidModel.getShip()) == 0) {
                                    amount.setText("Order KES" + bidModel.getOrders() + "\nno shipment\nTotal KES" + bidModel.getAmount());
                                } else {
                                    amount.setText("Order KES" + bidModel.getOrders() + "\nShipping: KES" + bidModel.getShip() + "\nTotal KES" + bidModel.getAmount());
                                }
                                texter.setText("Receipt: " + bidModel.getEntry() + "\nCustomer: " + bidModel.getName() + "\nphone: " + bidModel.getPhone() + "\nDate: " + bidModel.getReg_date());
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
                                alertDialog.getWindow().setGravity(Gravity.TOP);
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                    printManager.print(getString(R.string.app_name), new PDFGenerator(this, layer.findViewById(R.id.titled)), null);
                                });
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                    startActivity(new Intent(getApplicationContext(), MyReports.class));
                                    finish();
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
                    para.put("entry", bidModel.getEntry());
                    return para;
                }
            });
        });
    }


    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.slipped,
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
                                String disb = jsonObject.getString("disb");
                                String reg_date = jsonObject.getString("reg_date");
                                bidModel = new PayMod(payid, entry, mpesa, amount, orders, ship, custid, name, phone, location, landmark, status, comment, disb, reg_date);
                                bidModelArrayList.add(bidModel);
                            }
                            asapa = new Asapa(MyReports.this, R.layout.sammy, bidModelArrayList);
                            listView.setAdapter(asapa);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    asapa.getFilter().filter(newText);
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