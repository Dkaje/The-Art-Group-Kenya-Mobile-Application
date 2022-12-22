package com.example.artgroup.invent;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.InveDash;
import com.example.artgroup.models.FallacyWork;
import com.example.artgroup.models.GreatMes;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.TortureAda;
import com.example.artgroup.models.TortureMode;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages extends AppCompatActivity {
    ImageView imageView, profile, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    InveSess custSess;
    UserModel userModel;
    SearchView searchView;
    ListView listView;
    EditText editText;
    Spinner spinner;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RequestQueue requestQueue;
    ArrayList<TortureMode> tortureModeArrayList = new ArrayList<>();
    TortureMode tortureMode;
    TortureAda tortureAda;
    List<GreatMes> greatMesArrayList = new ArrayList<>();
    GreatMes greatMes;
    FallacyWork greatMidAda;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        custSess = new InveSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), InveDash.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Supply.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), RequestEd.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), Bidding.class));
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
            dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), InveDash.class));
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        listView = findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchHere);
        getto();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            tortureMode = (TortureMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle(tortureMode.getPhone());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            samoa = layoutInflater.inflate(R.layout.rada_bana, null);
            editText = samoa.findViewById(R.id.meesa);
            recyclerView = samoa.findViewById(R.id.recycle);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(layoutManager);
            getMess();
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            samoa.setLayoutParams(params);
            frameLayout.addView(samoa);
            builder.setView(frameLayout);
            alertDialog = builder.create();
            alertDialog.show();
            samoa.findViewById(R.id.btnExit).setOnClickListener(view1 -> {
                startActivity(new Intent(getApplicationContext(), Messages.class));
                finish();
            });
            samoa.findViewById(R.id.btnSend).setOnClickListener(view1 -> {
                final String mess = editText.getText().toString().trim();
                if (mess.isEmpty()) {
                    editText.setError("what are you sending");
                    editText.requestFocus();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.tumaSaa,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        editText.setText("");
                                        getMess();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("sen", tortureMode.getSen());
                            para.put("senid", tortureMode.getSenid());
                            para.put("message", mess);
                            para.put("rec", tortureMode.getRec());
                            para.put("name", tortureMode.getName());
                            para.put("phone", tortureMode.getPhone());
                            para.put("timing", new SimpleDateFormat("hh:mma").format(new Date()));
                            para.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                            para.put("dater", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                            para.put("meme", "M");
                            return para;
                        }
                    });
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        });
    }

    private void getMess() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.veveMess,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("success");
                        if (status == 1) {
                            greatMesArrayList.clear();
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                greatMes = new GreatMes(jsonObject.getString("sen"), jsonObject.getString("senid"), jsonObject.getString("phone"),
                                        jsonObject.getString("name"), jsonObject.getString("rec"), jsonObject.getString("message"),
                                        jsonObject.getString("timing"), jsonObject.getString("date"), jsonObject.getString("temple"),
                                        jsonObject.getString("temple2"), jsonObject.getString("meme"));
                                greatMesArrayList.add(greatMes);
                            }
                            greatMidAda = new FallacyWork(getApplicationContext(), greatMesArrayList);
                            recyclerView.setAdapter(greatMidAda);
                            recyclerView.scrollToPosition(greatMesArrayList.size() - 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<>();
                para.put("senid", tortureMode.getSenid());
                para.put("rec", tortureMode.getRec());
                return para;
            }
        });
    }

    private void getto() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.gettoVeve,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                tortureMode = new TortureMode(jsonObject.getString("senid"), jsonObject.getString("rec"), jsonObject.getString("count"),
                                        jsonObject.getString("phone"), jsonObject.getString("name"), jsonObject.getString("message"),
                                        jsonObject.getString("date"), jsonObject.getString("sen"), jsonObject.getString("timing"),
                                        jsonObject.getString("temple"));
                                tortureModeArrayList.add(tortureMode);
                            }
                            tortureAda = new TortureAda(Messages.this, R.layout.okay, tortureModeArrayList);
                            listView.setAdapter(tortureAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    tortureAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("rec", "Inventory");
                return para;
            }
        });
    }
}