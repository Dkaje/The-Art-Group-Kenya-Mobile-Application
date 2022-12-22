package com.example.artgroup.fina;

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
import android.widget.Button;
import android.widget.EditText;
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
import com.example.artgroup.midfield.FinaDash;
import com.example.artgroup.models.CredAda;
import com.example.artgroup.models.CredMod;
import com.example.artgroup.models.FinaSess;
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

public class Creditors extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<CredMod> credModArrayList = new ArrayList<>();
    CredAda credAda;
    CredMod credMod;
    View layer;
    EditText MPESA;
    TextView textView, amount;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    FinaSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    BottomNavigationView botaz;
    Dialog dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditors);
        custSess = new FinaSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.credit);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.credit:
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.ready:
                    startActivity(new Intent(getApplicationContext(), FinaDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.hire:
                    startActivity(new Intent(getApplicationContext(), ServPe.class));
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
        textView.setText("Welcome " + userModel.getFname());

        btn = findViewById(R.id.btnPast);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PastCredit.class));
        });
        listView = findViewById(R.id.listed);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getCredit();
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            credMod = (CredMod) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Funds Disbursement");
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
            if (credMod.getAccredit().equals("Pending")) {
                amount.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510\n\nSupplierID: " + credMod.getCreditor() + "::" + credMod.getFname() + " " + credMod.getLname() + "\nPhone: " + credMod.getPhone() + "\nAmount: KSH" + credMod.getCredit() + "\nDisbursement: " + credMod.getAccredit());
                builder.setPositiveButton("Disburse", (dialog, idm) -> {
                });
            } else {
                amount.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510\n\nConfirmed. \nKES" + credMod.getCredit() + ",\nset to \nIdNO: " + credMod.getCreditor() + "::" + credMod.getFname() + " " + credMod.getLname() + "\nPhone: " + credMod.getPhone());
                builder.setPositiveButton("Print", (dial, idm) -> {
                });
            }
            builder.setNegativeButton("Close", (dialo, iof) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            if (credMod.getAccredit().equals("Pending")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("Payment By MPESA");
                    alert.setMessage("SupplierID: " + credMod.getCreditor() + "\nFullname: " + credMod.getFname() + " " + credMod.getLname() + "\nPhone: " + credMod.getPhone() + "\nAmount: KSH" + credMod.getCredit() + "\nDisbursement: " + credMod.getAccredit());
                    Rect retan = new Rect();
                    Window window8 = this.getWindow();
                    window8.getDecorView().getWindowVisibleDisplayFrame(retan);
                    LayoutInflater layoutInflater1 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    layer = layoutInflater1.inflate(R.layout.mpesa, null);
                    layer.setMinimumWidth((int) (retan.width() * 0.9f));
                    layer.setMinimumHeight((int) (retan.height() * 0.01f));
                    MPESA = layer.findViewById(R.id.mpesa);
                    frameLayout = new FrameLayout(this);
                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                    layer.setLayoutParams(params);
                    frameLayout.addView(layer);
                    alert.setView(frameLayout);
                    alert.setPositiveButton("Submit", (dialo, iof) -> {
                    });
                    alert.setNegativeButton("Close", (dialo, iof) -> {
                    });
                    dialog = alert.create();
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view12 -> {
                        final String muCode = MPESA.getText().toString().trim();
                        int len = muCode.length();
                        if (muCode.isEmpty()) {
                            Toast.makeText(this, "Mpesa needed", Toast.LENGTH_SHORT).show();
                        } else if (len < 10) {
                            Toast.makeText(this, "Oops!! Your MPESA CODE is wrong", Toast.LENGTH_SHORT).show();
                        } else {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.surmount,
                                    respon -> {
                                        try {
                                            jsonObject = new JSONObject(respon);
                                            String msg = jsonObject.getString("message");
                                            int statuses = jsonObject.getInt("success");
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            if (statuses == 1) {
                                                startActivity(new Intent(getApplicationContext(), Creditors.class));
                                                finish();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                                        }

                                    }, error -> {
                                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("supplier", credMod.getCreditor());
                                    params.put("mpesa", muCode);
                                    params.put("amount", credMod.getCredit());
                                    params.put("fullname", credMod.getFname() + " " + credMod.getLname());
                                    params.put("phone", credMod.getPhone());
                                    params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                                    return params;
                                }
                            });
                        }
                    });
                    dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view12 -> {
                        dialog.cancel();
                    });
                });
            } else {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    genPDF();
                });
            }
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                startActivity(new Intent(getApplicationContext(), Creditors.class));
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
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getSu,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                credMod = new CredMod(jsonObject.getString("creditor"), jsonObject.getString("credit"), jsonObject.getString("accredit"), jsonObject.getString("fname"),
                                        jsonObject.getString("lname"), jsonObject.getString("phone"));
                                credModArrayList.add(credMod);
                            }
                            credAda = new CredAda(Creditors.this, R.layout.render, credModArrayList);
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

        }));

    }
}