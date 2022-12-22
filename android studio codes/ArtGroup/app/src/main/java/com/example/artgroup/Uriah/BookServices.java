package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ServAda;
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

public class BookServices extends AppCompatActivity {
    ImageView viewer, mimi;
    ImageView imageView, profile;
    TextView textView, texter;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    RequestQueue requestQueue;
    View layer;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    LayoutInflater layoutInflater, inflater;
    Spinner categor, typed;
    String myTpe;
    ServModel servModel;
    ArrayList<ServModel> servModelArrayList = new ArrayList<>();
    ServAda servAda;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_services);
        mimi = findViewById(R.id.arrowBack);
        mimi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
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
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            servModel = (ServModel) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Make an Order</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + servModel.getCategory() + "</b><br>Type: " + servModel.getType() + "<br><br>Description: " + servModel.getDescription() + "<br>Charge KES" + servModel.getCharge() + "<br><br>RegDate: " + servModel.getReg_date() + "<font>"));
            builder.setNegativeButton("Close", (dd, d) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Make Order</font>"), (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                AlertDialog.Builder brathe = new AlertDialog.Builder(this, R.style.MemSahib);
                brathe.setTitle("Location");
                brathe.setMessage("Provide some direction to your event");
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
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ugitiri, android.R.layout.simple_spinner_item);
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
                    if (spin.equals("Event Location")) {
                        textView.setText("Event Location Required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (myLan.isEmpty()) {
                        land.requestFocus();
                        textView.setText("Add some known landmark");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (myHouse.isEmpty()) {
                        house.requestFocus();
                        textView.setText("Add House/Village");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        AlertDialog.Builder ranyard = new AlertDialog.Builder(this, R.style.MemSahib);
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
                                                    startActivity(new Intent(getApplicationContext(), BookServices.class));
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
                                        params.put("category", servModel.getCategory());
                                        params.put("type", servModel.getType());
                                        params.put("description", servModel.getDescription());
                                        params.put("custid", userModel.getUser_id());
                                        params.put("name", userModel.getFname() + " " + userModel.getLname());
                                        params.put("phone", userModel.getPhone());
                                        params.put("location", spin);
                                        params.put("landmark", myLan + "-" + myHouse);
                                        params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                        return params;
                                    }
                                });
                            }
                        });
                    }
                });
            });
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.noti);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.noti:
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), MyPastServ.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), SuccessfulPlan.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewS,
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
                            servAda = new ServAda(BookServices.this, R.layout.marathon, servModelArrayList);
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