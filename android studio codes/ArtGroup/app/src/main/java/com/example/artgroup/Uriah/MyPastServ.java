package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ManuAda;
import com.example.artgroup.models.ServModel;
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

public class MyPastServ extends AppCompatActivity {
    ImageView imageView, profile;
    TextView textView, head;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, logger;
    RequestQueue requestQueue;
    View layer;
    Rect rect;
    Window window;
    EditText price, description;
    LayoutInflater layoutInflater, inflater;
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
        setContentView(R.layout.activity_my_past_serv);
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
        inflater = getLayoutInflater();
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        getRecords();
        button.setOnClickListener(view -> {
            head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510");
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        findViewById(R.id.btnHis).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LindaMama.class));
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            servModel = (ServModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(this, R.style.Arap);
            alert.setTitle("Service");
            alert.setMessage("CustomerID: " + servModel.getCustid() + "\nName: " + servModel.getName() + "\nPhone: " + servModel.getPhone() + "\nLocation: " + servModel.getLocation() + " - " + servModel.getLandmark() + "\n\nSERVICE\nCategory: " + servModel.getCategory() + "\nType: " + servModel.getType() + "\nDescription: " + servModel.getDescription() + "\n\nSTATUS\nCharge: KES" + servModel.getCharge() + "\ndateOrdered: " + servModel.getReg_date() + "\nStatus: " + servModel.getStatus() + "\npayment: " + servModel.getPay());
            alert.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            alert.setPositiveButton("Make_Payment", (dialogInterface, i1) -> {
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
            logger.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder ranyard = new AlertDialog.Builder(this, R.style.MemSahib);
                ranyard.setTitle("Full Service + Transport charge");
                ranyard.setMessage("Amount KES" + servModel.getCharge() + "\nMPESA PAYBILL 456123 \nAccountNO:" + userModel.getUser_id() + "\nEnter MPESA CODE below");
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
                        textView.setText("MPESA CODE");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (myQty.length() < 10) {
                        description.requestFocus();
                        textView.setText("invalid");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.waaOk,
                                respons -> {
                                    try {
                                        jsonObject = new JSONObject(respons);
                                        int stat = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        textView.setText(msg);
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                        if (stat == 1) {
                                            startActivity(new Intent(getApplicationContext(), MyPastServ.class));
                                            finish();
                                        } else if (stat == 6) {
                                            description.setError(msg);
                                            description.requestFocus();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        textView.setText("an error occurred");
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }, error -> {
                            textView.setText("connection not established");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("mpesa", myQty);
                                params.put("amount", servModel.getCharge());
                                params.put("serv", servModel.getServ());
                                params.put("pay", "Paid");
                                params.put("category", servModel.getCategory());
                                params.put("type", servModel.getType());
                                params.put("description", servModel.getDescription());
                                params.put("custid", servModel.getCustid());
                                params.put("name", servModel.getName());
                                params.put("phone", servModel.getPhone());
                                params.put("location", servModel.getLocation());
                                params.put("landmark", servModel.getLandmark());
                                params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                return params;
                            }
                        });
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
                            servAda = new ManuAda(MyPastServ.this, R.layout.marathon, servModelArrayList);
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
                params.put("pay", "Pending");
                return params;
            }
        });
    }
}