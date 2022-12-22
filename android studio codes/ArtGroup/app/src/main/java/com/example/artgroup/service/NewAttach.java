package com.example.artgroup.service;

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
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.models.DelMod;
import com.example.artgroup.models.DeliAda;
import com.example.artgroup.models.PayerSe;
import com.example.artgroup.models.PostedAda;
import com.example.artgroup.models.SerSess;
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

public class NewAttach extends AppCompatActivity {
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
    PayerSe payerSe;
    ArrayList<PayerSe> payerSeArrayList = new ArrayList<>();
    PostedAda regretAda;
    Button button;
    ListView listView, listing, list2;
    SearchView searchView, searchView3, search;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    SerSess custSess;
    UserModel userModel;
    BottomNavigationView botaz;
    DelMod delMod;
    ArrayList<DelMod> delModArrayList = new ArrayList<>();
    DeliAda deliAda;
    DelMod delMod2;
    ArrayList<DelMod> delModArrayList2 = new ArrayList<>();
    DeliAda deliAda2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_attach);
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
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.Ord);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Ord:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), ServeDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.att:
                    startActivity(new Intent(getApplicationContext(), PasstAttach.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), NoMoreDis.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            payerSe = (PayerSe) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Service Payment Details</u></b></font>"));
            builder.setMessage("CustomerID: " + payerSe.getCustid() + "\nName: " + payerSe.getName() + "\nPhone: " + payerSe.getPhone() + "\nLocation: " + payerSe.getLocation() + " - " + payerSe.getLandmark() + "\n\nFINANCE\nStatus: " + payerSe.getStatus() + "\n\nSERVICE\nCategory: " + payerSe.getCategory() + "\nType: " + payerSe.getType() + "\nDescription: " + payerSe.getDescription() + "\nAttachment: " + payerSe.getDisb() + "\nDate: " + payerSe.getReg_date());
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
            });
            if (payerSe.getDisb().equals("Pending")) {
                builder.setPositiveButton("Allocate", (dialogInterface, i) -> {
                });
            }
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
            if (payerSe.getDisb().equals("Pending")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getVd,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int success = jsonObject.getInt("trust");
                                    if (success == 1) {
                                        jsonArray = jsonObject.getJSONArray("victory");
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                            jsonObject = jsonArray.getJSONObject(j);
                                            delMod2 = new DelMod(jsonObject.getString("entry"), jsonObject.getString("fname"), jsonObject.getString("phone"));
                                            delModArrayList2.add(delMod2);
                                        }
                                        AlertDialog.Builder build = new AlertDialog.Builder(this);
                                        build.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Chose Videographer Below</u></b></font>"));
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        layer = layoutInflater.inflate(R.layout.warigia, null);
                                        layer.setMinimumWidth((int) (rect.width() * 1.0));
                                        layer.setMinimumHeight((int) (rect.height() * 0.01));
                                        listing = layer.findViewById(R.id.bester);
                                        listing.setTextFilterEnabled(true);
                                        search = layer.findViewById(R.id.anNi);
                                        deliAda2 = new DeliAda(NewAttach.this, R.layout.quee, delModArrayList2);
                                        listing.setAdapter(deliAda2);
                                        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String text) {
                                                return false;
                                            }

                                            @Override
                                            public boolean onQueryTextChange(String newText) {
                                                deliAda2.getFilter().filter(newText);
                                                return false;
                                            }
                                        });
                                        listing.setOnItemClickListener(((adapterView1, view2, i1, l1) -> {
                                            delMod2 = (DelMod) adapterView1.getItemAtPosition(i1);
                                            alert = new AlertDialog.Builder(this, R.style.Arap);
                                            alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>ADD Videographer</u></b></font>"));
                                            alert.setMessage("You selected:-\nName: " + delMod2.getName() + "\nPhone: " + delMod2.getPhone() + "\nas the Videographer to deliver\n" + payerSe.getName() + "\n" + payerSe.getCategory() + " ordered service.\nDo You want to proceed?");
                                            alert.setPositiveButton("Yes_Allocate", (tg, t) -> {
                                            });
                                            alert.setNegativeButton("No_Exit", (tg, t) -> {
                                            });
                                            dialog = alert.create();
                                            dialog.show();
                                            dialog.setCancelable(false);
                                            dialog.setCanceledOnTouchOutside(false);
                                            dialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                                                requestQueue = Volley.newRequestQueue(this);
                                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getDr,
                                                        respons -> {
                                                            try {
                                                                jsonObject = new JSONObject(respons);
                                                                int succs = jsonObject.getInt("trust");
                                                                if (succs == 1) {
                                                                    jsonArray = jsonObject.getJSONArray("victory");
                                                                    for (int jj = 0; jj < jsonArray.length(); jj++) {
                                                                        jsonObject = jsonArray.getJSONObject(jj);
                                                                        delMod = new DelMod(jsonObject.getString("entry"), jsonObject.getString("fname"), jsonObject.getString("phone"));
                                                                        delModArrayList.add(delMod);
                                                                    }
                                                                    AlertDialog.Builder buil = new AlertDialog.Builder(this);
                                                                    buil.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Chose Driver Below</u></b></font>"));
                                                                    rect = new Rect();
                                                                    window = this.getWindow();
                                                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                                    layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                                    layer = layoutInflater.inflate(R.layout.get_deli, null);
                                                                    layer.setMinimumWidth((int) (rect.width() * 1.0));
                                                                    layer.setMinimumHeight((int) (rect.height() * 0.01));
                                                                    list2 = layer.findViewById(R.id.myList);
                                                                    list2.setTextFilterEnabled(true);
                                                                    searchView3 = layer.findViewById(R.id.mySearch);
                                                                    deliAda = new DeliAda(NewAttach.this, R.layout.quee, delModArrayList);
                                                                    list2.setAdapter(deliAda);
                                                                    searchView3.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                                        @Override
                                                                        public boolean onQueryTextSubmit(String text) {
                                                                            return false;
                                                                        }

                                                                        @Override
                                                                        public boolean onQueryTextChange(String newText) {
                                                                            deliAda.getFilter().filter(newText);
                                                                            return false;
                                                                        }
                                                                    });
                                                                    list2.setOnItemClickListener(((adapterView11, view22, i11, l11) -> {
                                                                        delMod = (DelMod) adapterView11.getItemAtPosition(i11);
                                                                        AlertDialog.Builder bebwa = new AlertDialog.Builder(this, R.style.Arap);
                                                                        bebwa.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>ADD Driver</u></b></font>"));
                                                                        bebwa.setMessage("You selected:-\nName: " + delMod.getName() + "\nPhone: " + delMod.getPhone() + "\nas the driver to deliver\n" + payerSe.getName() + "\n" + payerSe.getCategory() + "  ordered service.\nDo You want to proceed?");
                                                                        bebwa.setPositiveButton("Yes_Allocate", (tg, t) -> {
                                                                        });
                                                                        bebwa.setNegativeButton("No_Exit", (tg, t) -> {
                                                                        });
                                                                        AlertDialog sibebwi = bebwa.create();
                                                                        sibebwi.show();
                                                                        sibebwi.setCancelable(false);
                                                                        sibebwi.setCanceledOnTouchOutside(false);
                                                                        sibebwi.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                                        sibebwi.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                                        sibebwi.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                                                            AlertDialog.Builder murife = new AlertDialog.Builder(this);
                                                                            murife.setTitle("Check Before Submit");
                                                                            murife.setMessage(payerSe.getCategory() + " - " + payerSe.getType() + "\n\nCustomer\nname: " + payerSe.getName() + "\nLocation: " + payerSe.getLocation() + "-" + payerSe.getLandmark() + "\n\nVideographer\nname: " + delMod2.getName() + "\nphone: " + delMod2.getPhone() + "\n\nDriver\nname: " + delMod.getName() + "\nphone: " + delMod.getPhone() + "\n\nDo you wish to submit?");
                                                                            murife.setPositiveButton("Yes", (dialogInterface, i) -> {
                                                                            });
                                                                            murife.setNegativeButton("No", (dialogInterface, i) -> {
                                                                            });
                                                                            AlertDialog dontRun = murife.create();
                                                                            dontRun.show();
                                                                            dontRun.setCancelable(false);
                                                                            dontRun.setCanceledOnTouchOutside(false);
                                                                            dontRun.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                                            dontRun.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                                            dontRun.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.strongerR,
                                                                                        responses -> {
                                                                                            try {
                                                                                                jsonObject = new JSONObject(responses);
                                                                                                String msg = jsonObject.getString("message");
                                                                                                int status = jsonObject.getInt("success");
                                                                                                if (status == 1) {
                                                                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                                                    startActivity(new Intent(getApplicationContext(), NewAttach.class));
                                                                                                    finish();
                                                                                                } else if (status == 0) {
                                                                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            } catch (JSONException e) {
                                                                                                e.printStackTrace();
                                                                                                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        }, error -> {
                                                                                    Toast.makeText(this, "There was a connection error", Toast.LENGTH_SHORT).show();

                                                                                }) {
                                                                                    @Nullable
                                                                                    @Override
                                                                                    protected Map<String, String> getParams() {
                                                                                        Map<String, String> params = new HashMap<>();
                                                                                        params.put("driver_id", delMod.getEntry());
                                                                                        params.put("driver_name", delMod.getName());
                                                                                        params.put("driver_phone", delMod.getPhone());
                                                                                        params.put("video_id", delMod2.getEntry());
                                                                                        params.put("video_name", delMod2.getName());
                                                                                        params.put("video_phone", delMod2.getPhone());
                                                                                        params.put("disb", "1");
                                                                                        params.put("serid", payerSe.getSerid());
                                                                                        params.put("cust_id", payerSe.getCustid());
                                                                                        params.put("cust_name", payerSe.getName());
                                                                                        params.put("cust_phone", payerSe.getPhone());
                                                                                        params.put("location", payerSe.getLocation());
                                                                                        params.put("landmark", payerSe.getLandmark());
                                                                                        params.put("form", "9");
                                                                                        params.put("alert", "Videographer Allocated. " + delMod2.getName() + " phone " + delMod2.getPhone() + ". Driver Allocated. " + delMod.getName() + " phone " + delMod.getPhone());
                                                                                        params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                                                                                        return params;
                                                                                    }
                                                                                });
                                                                            });
                                                                            dontRun.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                                                                dontRun.cancel();
                                                                            });
                                                                        });
                                                                        sibebwi.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                                                            sibebwi.cancel();
                                                                        });
                                                                    }));
                                                                    frameLayout = new FrameLayout(this);
                                                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                                    layer.setLayoutParams(params);
                                                                    frameLayout.addView(layer);
                                                                    buil.setView(frameLayout);
                                                                    buil.setPositiveButton("Close", (tr, r) -> {
                                                                    });
                                                                    AlertDialog hon = buil.create();
                                                                    hon.show();
                                                                    hon.setCancelable(false);
                                                                    hon.setCanceledOnTouchOutside(false);
                                                                    hon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                                    hon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                                                        startActivity(new Intent(getApplicationContext(), NewAttach.class));
                                                                        finish();
                                                                    });
                                                                    hon.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                                } else {
                                                                    AlertDialog.Builder beber = new AlertDialog.Builder(this);
                                                                    beber.setMessage("No Verified Driver was Found");
                                                                    AlertDialog dodo = beber.create();
                                                                    dodo.show();
                                                                    dodo.getWindow().setGravity(Gravity.TOP);
                                                                }

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                                                            }

                                                        }, error -> {
                                                    Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                                                }));
                                            });
                                            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                                                dialog.cancel();
                                            });
                                        }));
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                        layer.setLayoutParams(params);
                                        frameLayout.addView(layer);
                                        build.setView(frameLayout);
                                        build.setPositiveButton("Close", (tr, r) -> {
                                        });
                                        AlertDialog bon = build.create();
                                        bon.show();
                                        bon.setCancelable(false);
                                        bon.setCanceledOnTouchOutside(false);
                                        bon.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        bon.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            startActivity(new Intent(getApplicationContext(), NewAttach.class));
                                            finish();
                                        });
                                        bon.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    } else {
                                        AlertDialog.Builder beber = new AlertDialog.Builder(this);
                                        beber.setMessage("No Verified Videographer was Found");
                                        AlertDialog dodo = beber.create();
                                        dodo.show();
                                        dodo.getWindow().setGravity(Gravity.TOP);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                                }

                            }, error -> {
                        Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                    }));
                });
            }
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
                            regretAda = new PostedAda(NewAttach.this, R.layout.marathon, payerSeArrayList);
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
                para.put("status", "1");
                return para;
            }
        });
    }
}