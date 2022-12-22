package com.example.artgroup.midfield;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.Sup.Communicate;
import com.example.artgroup.Sup.History;
import com.example.artgroup.Sup.JustMile;
import com.example.artgroup.Sup.PayHistory;
import com.example.artgroup.models.OneAda;
import com.example.artgroup.models.OneModel;
import com.example.artgroup.models.SuppSess;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SupDash extends AppCompatActivity {
    SuppSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    AlertDialog.Builder builder;
    Dialog dialog;
    View layer;
    LayoutInflater inflater;
    RequestQueue requestQueue;
    OneModel oneModel;
    ArrayList<OneModel> oneModelArrayList = new ArrayList<>();
    OneAda oneAda;
    ListView listView;
    SearchView searchView;
    TextView textView, texter;
    Toast toast;
    JSONObject jsonObject;
    JSONArray jsonArray;
    BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new SuppSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_sup_dash);
        bottomNavigationItemView = findViewById(R.id.topper);
        bottomNavigationItemView.setSelectedItemId(R.id.home);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), PayHistory.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), History.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Communicate.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    return true;
            }
            return false;
        });
        imageView = findViewById(R.id.arrowBack);
        texter = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
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
        listView.setOnItemClickListener((parent, view, position, id) -> {
            oneModel = (OneModel) parent.getItemAtPosition(position);
            startActivity(new Intent(getApplicationContext(), JustMile.class).putExtra("classification", oneModel.getClassification()));
        });
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Location</b>: " + userModel.getCounty() + "<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
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
        texter.setText("Welcome " + userModel.getFname());

    }


    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.rainy,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String classification = jsonObject.getString("classification");
                                oneModel = new OneModel(classification);
                                oneModelArrayList.add(oneModel);
                            }
                            oneAda = new OneAda(SupDash.this, R.layout.locale, oneModelArrayList);
                            listView.setAdapter(oneAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    oneAda.getFilter().filter(newText);
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

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        });
    }
}