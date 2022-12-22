package com.example.artgroup.service;

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
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.models.ReviMode;
import com.example.artgroup.models.Seeker;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PasstAttach extends AppCompatActivity {
    BottomNavigationView botaz;
    ImageView imageView, profile;
    TextView textView, head;
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
    Button button;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    SerSess custSess;
    UserModel userModel;
    Seeker seeker;
    ArrayList<ReviMode> bidModelArrayList = new ArrayList<>();
    ReviMode reviMode;
    String mdater, md, mdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passt_attach);
        custSess = new SerSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Role</b>: " + userModel.getCounty() + " Manager<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
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
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.att);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.att:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), ServeDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), NewAttach.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), NoMoreDis.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
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
        button = findViewById(R.id.addNEw);
        head = findViewById(R.id.myTxt);
        button.setOnClickListener(view -> {
            head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510");
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            reviMode = (ReviMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Service Delivery</u></b></font>"));
            if (reviMode.getVideo().equals("Pending")) {
                mdater = "";
            } else {
                mdater = reviMode.getVideo_date();
            }
            if (reviMode.getDrive().equals("Pending")) {
                mdt = "";
            } else {
                mdt = reviMode.getDrive_date();
            }
            if (reviMode.getCustom().equals("Pending")) {
                md = "";
            } else {
                md = reviMode.getCustom_date();
            }
            builder.setMessage("ATTACHMENT_DATE\n" + reviMode.getReg_date() + "\n\nCUSTOMER\nname: " + reviMode.getCust_name() + "\nphone: " + reviMode.getCust_phone() + "\nEvent: " + reviMode.getLocation() + " - " + reviMode.getLandmark() + "\nDelivery: " + reviMode.getCustom() + "\nDate: " + md + "\n\nDRIVER\nname: " + reviMode.getDriver_name() + "\nphone: " + reviMode.getDriver_phone() + "\nDelivery: " + reviMode.getDrive() + "\nDate: " + mdt + "\n\nVIDEOGRAPHER\nname: " + reviMode.getVideo_name() + "\nphone: " + reviMode.getVideo_phone() + "\nDelivery: " + reviMode.getVideo() + "\nDate: " + mdater);
            builder.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
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
        }));
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.gettter,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                reviMode = new ReviMode(jsonObject.getString("id"), jsonObject.getString("form"), jsonObject.getString("entry"), jsonObject.getString("driver_id"), jsonObject.getString("driver_name"),
                                        jsonObject.getString("driver_phone"), jsonObject.getString("drive"), jsonObject.getString("drive_date"),
                                        jsonObject.getString("video_id"), jsonObject.getString("video_name"), jsonObject.getString("video_phone"), jsonObject.getString("video"), jsonObject.getString("video_date"),
                                        jsonObject.getString("cust_id"), jsonObject.getString("cust_name"), jsonObject.getString("cust_phone"), jsonObject.getString("location"),
                                        jsonObject.getString("landmark"), jsonObject.getString("custom"), jsonObject.getString("custom_date"), jsonObject.getString("reg_date"));
                                bidModelArrayList.add(reviMode);
                            }
                            seeker = new Seeker(PasstAttach.this, R.layout.marathon, bidModelArrayList);
                            listView.setAdapter(seeker);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    seeker.getFilter().filter(newText);
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
                para.put("form", "9");
                return para;
            }
        });
    }
}