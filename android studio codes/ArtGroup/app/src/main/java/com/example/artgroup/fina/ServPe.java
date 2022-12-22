package com.example.artgroup.fina;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.FinaDash;
import com.example.artgroup.models.FinaSess;
import com.example.artgroup.models.PayerSe;
import com.example.artgroup.models.RegretAda;
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

public class ServPe extends AppCompatActivity {
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    FinaSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    BottomNavigationView botaz;
    Dialog dial;
    PayerSe payerSe;
    ArrayList<PayerSe> payerSeArrayList = new ArrayList<>();
    RegretAda regretAda;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    Spinner spinner;
    TextView textView, head;
    View layer;
    Rect rect;
    Window window;
    EditText qty;
    LayoutInflater layoutInflater, inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_pe);
        custSess = new FinaSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        head = findViewById(R.id.myTxt);
        botaz.setSelectedItemId(R.id.hire);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.hire:
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.ready:
                    startActivity(new Intent(getApplicationContext(), FinaDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.credit:
                    startActivity(new Intent(getApplicationContext(), Creditors.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), UtajuaBana.class));
                    overridePendingTransition(0, 0);
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Investment.class));
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
        spinner = findViewById(R.id.spinView);
        textView.setText("Welcome " + userModel.getFname());
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        inflater = getLayoutInflater();
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        getRecords();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Viewer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mSpinner = spinner.getSelectedItem().toString();
                if (mSpinner.equals("New")) {
                    startActivity(new Intent(getApplicationContext(), ServPe.class));
                    finish();
                } else if (mSpinner.equals("Approved")) {
                    startActivity(new Intent(getApplicationContext(), ServApp.class));
                } else if (mSpinner.equals("Rejected")) {
                    startActivity(new Intent(getApplicationContext(), ServRej.class));
                } else if (mSpinner.equals("PDF")) {
                    if (payerSeArrayList.isEmpty()) {
                        Toast.makeText(ServPe.this, "You have nothing to print!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        printThis();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            payerSe = (PayerSe) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Service Payment Details</u></b></font>"));
            builder.setMessage("CustomerID: " + payerSe.getCustid() + "\nName: " + payerSe.getName() + "\nPhone: " + payerSe.getPhone() + "\nLocation: " + payerSe.getLocation() + " - " + payerSe.getLandmark() + "\n\nPAYMENT\nMPESA: " + payerSe.getMpesa() + "\nCharge: KES" + payerSe.getAmount() + "\nStatus: " + payerSe.getStatus() + "\n\nSERVICE\nCategory: " + payerSe.getCategory() + "\nType: " + payerSe.getType() + "\nDescription: " + payerSe.getDescription() + "\nDate: " + payerSe.getReg_date());
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
            });
            builder.setPositiveButton("Approve", (dd, d) -> {
            });
            builder.setNegativeButton("Reject", (dd, d) -> {
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.wewee,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int status = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                textView.setText(msg);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                                if (status == 1) {
                                    startActivity(new Intent(getApplicationContext(), ServPe.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                textView.setText("Server Error!!");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }, error -> {
                    textView.setText("Network Connection!!");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("serid", payerSe.getSerid());
                        para.put("comment", "Thanks for shopping with us. Welcome Back");
                        para.put("status", "1");
                        para.put("amount", payerSe.getAmount());
                        para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                        return para;
                    }
                });

            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.wewee,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int status = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                textView.setText(msg);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                                if (status == 1) {
                                    startActivity(new Intent(getApplicationContext(), ServPe.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                textView.setText("Server Error!!");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }, error -> {
                    textView.setText("Network Error!!");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("serid", payerSe.getSerid());
                        para.put("comment", "We are sorry to reject your Payment.");
                        para.put("status", "2");
                        para.put("amount", payerSe.getAmount());
                        para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                        return para;
                    }
                });
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getter,
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
                            regretAda = new RegretAda(ServPe.this, R.layout.marathon, payerSeArrayList);
                            listView.setAdapter(regretAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    regretAda.getFilter().filter(newText);
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
                para.put("status", "0");
                return para;
            }
        });
    }

    private void printThis() {
        head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510\nNew Service Orders");
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
    }
}