package com.example.artgroup.Uriah;

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
import android.widget.FrameLayout;
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
import com.bumptech.glide.Glide;
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.FirstAda;
import com.example.artgroup.models.FirstTest;
import com.example.artgroup.models.SecondAda;
import com.example.artgroup.models.SecondTest;
import com.example.artgroup.models.ThirdAda;
import com.example.artgroup.models.ThirdTest;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyImages extends AppCompatActivity {
    ImageView mimi;
    ImageView imageView;
    TextView textView;
    RequestQueue requestQueue;
    View layer, begger, beg3;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater, inflater;
    ListView listView, list2, list3;
    SearchView searchView, search2;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustSess custSess;
    UserModel userModel;
    BottomNavigationView botaz;
    FirstAda firstAda;
    FirstTest firstTest;
    ArrayList<FirstTest> firstTestArrayList = new ArrayList<>();
    SecondAda secondAda;
    SecondTest secondTest;
    ArrayList<SecondTest> secondTestArrayList = new ArrayList<>();
    ThirdAda thirdAda;
    ThirdTest thirdTest;
    ArrayList<ThirdTest> thirdTestArrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_images);
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
        mimi = findViewById(R.id.arrowBack);
        mimi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.Imag);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Imag:
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), MyPastServ.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Booker.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), SuccessfulPlan.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            firstTest = (FirstTest) adapterView.getItemAtPosition(i);
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.sagaSe,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    secondTest = new SecondTest(jsonObject.getString("category"), jsonObject.getString("type"));
                                    secondTestArrayList.add(secondTest);
                                }
                                AlertDialog.Builder child = new AlertDialog.Builder(this);
                                child.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + firstTest.getCategory() + "</u></b></font>"));
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                begger = layoutInflater.inflate(R.layout.lister2, null);
                                begger.setMinimumWidth((int) (rect.width() * 1.0));
                                begger.setMinimumHeight((int) (rect.height() * 0.01));
                                list2 = begger.findViewById(R.id.revesred);
                                list2.setTextFilterEnabled(true);
                                search2 = begger.findViewById(R.id.sekoo);
                                secondAda = new SecondAda(MyImages.this, R.layout.first, secondTestArrayList);
                                list2.setAdapter(secondAda);
                                search2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String text) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        secondAda.getFilter().filter(newText);
                                        return false;
                                    }
                                });
                                list2.setOnItemClickListener((adapterView1, view1, i1, l1) -> {
                                    secondTest = (SecondTest) adapterView1.getItemAtPosition(i1);
                                    requestQueue = Volley.newRequestQueue(this);
                                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.sagaThre,
                                            respo -> {
                                                try {
                                                    jsonObject = new JSONObject(respo);
                                                    int comer = jsonObject.getInt("trust");
                                                    if (comer == 1) {
                                                        jsonArray = jsonObject.getJSONArray("victory");
                                                        for (int j = 0; j < jsonArray.length(); j++) {
                                                            jsonObject = jsonArray.getJSONObject(j);
                                                            thirdTest = new ThirdTest(jsonObject.getString("refere"), jsonObject.getString("category"),
                                                                    jsonObject.getString("type"), jsonObject.getString("cust_id"),
                                                                    ModelUrl.img + jsonObject.getString("image"), jsonObject.getString("reg_date"));
                                                            thirdTestArrayList.add(thirdTest);
                                                        }
                                                        AlertDialog.Builder mama = new AlertDialog.Builder(this);
                                                        mama.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + secondTest.getCategory() + " " + secondTest.getType() + "</u></b></font>"));
                                                        rect = new Rect();
                                                        window = this.getWindow();
                                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                        beg3 = layoutInflater.inflate(R.layout.lister3, null);
                                                        beg3.setMinimumWidth((int) (rect.width() * 1.0));
                                                        beg3.setMinimumHeight((int) (rect.height() * 0.01));
                                                        list3 = beg3.findViewById(R.id.combWeb);
                                                        list3.setTextFilterEnabled(true);
                                                        thirdAda = new ThirdAda(MyImages.this, R.layout.only_im, thirdTestArrayList);
                                                        list3.setAdapter(thirdAda);
                                                        list3.setOnItemClickListener((adapterView2, view2, i2, l2) -> {
                                                            thirdTest = (ThirdTest) adapterView2.getItemAtPosition(i2);
                                                            AlertDialog.Builder prog = new AlertDialog.Builder(this);
                                                            prog.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + secondTest.getCategory() + " " + secondTest.getType() + "</u></b></font>"));
                                                            prog.setMessage("Uploaded on: " + thirdTest.getReg_date());
                                                            rect = new Rect();
                                                            window = this.getWindow();
                                                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                            View Mku = layoutInflater.inflate(R.layout.kanungo, null);
                                                            Mku.setMinimumWidth((int) (rect.width() * 1.0));
                                                            Mku.setMinimumHeight((int) (rect.height() * 0.01));
                                                            imageView = Mku.findViewById(R.id.fert);
                                                            Glide.with(this).load(thirdTest.getImage()).into(imageView);
                                                            frameLayout = new FrameLayout(this);
                                                            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                            Mku.setLayoutParams(params);
                                                            frameLayout.addView(Mku);
                                                            prog.setView(frameLayout);
                                                            prog.setNeutralButton("Download", (dd, d) -> {
                                                            });
                                                            prog.setPositiveButton("Close", (dd, d) -> {
                                                            });
                                                            AlertDialog cook = prog.create();
                                                            cook.setCancelable(false);
                                                            cook.setCanceledOnTouchOutside(false);
                                                            cook.show();
                                                            cook.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                            cook.getWindow().setGravity(Gravity.CENTER);
                                                            cook.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                            cook.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewo -> {
                                                                cook.cancel();
                                                            });
                                                            cook.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(viewo -> {
                                                                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                                                printManager.print(getString(R.string.app_name), new PDFGenerator(this, Mku.findViewById(R.id.memeYoo)), null);
                                                            });
                                                        });
                                                        frameLayout = new FrameLayout(this);
                                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                                        beg3.setLayoutParams(params);
                                                        frameLayout.addView(beg3);
                                                        mama.setView(frameLayout);
                                                        mama.setPositiveButton("Close", (dd, d) -> {
                                                        });
                                                        AlertDialog sweeper = mama.create();
                                                        sweeper.setCancelable(false);
                                                        sweeper.setCanceledOnTouchOutside(false);
                                                        sweeper.show();
                                                        sweeper.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                        sweeper.getWindow().setGravity(Gravity.TOP);
                                                        sweeper.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        sweeper.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewo -> {
                                                            startActivity(new Intent(getApplicationContext(), MyImages.class));
                                                            finish();
                                                        });
                                                    } else if (comer == 0) {
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
                                            params.put("cust_id", userModel.getUser_id());
                                            params.put("category", secondTest.getCategory());
                                            params.put("type", secondTest.getType());
                                            return params;
                                        }
                                    });
                                });
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                begger.setLayoutParams(params);
                                frameLayout.addView(begger);
                                child.setView(frameLayout);
                                child.setPositiveButton("Close", (dd, d) -> {
                                });
                                AlertDialog toto = child.create();
                                toto.setCancelable(false);
                                toto.setCanceledOnTouchOutside(false);
                                toto.show();
                                toto.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                toto.getWindow().setGravity(Gravity.TOP);
                                toto.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                toto.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewo -> {
                                    startActivity(new Intent(getApplicationContext(), MyImages.class));
                                    finish();
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
                    params.put("cust_id", userModel.getUser_id());
                    params.put("category", firstTest.getCategory());
                    return params;
                }
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.sagaF,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                firstTest = new FirstTest(jsonObject.getString("category"));
                                firstTestArrayList.add(firstTest);
                            }
                            firstAda = new FirstAda(MyImages.this, R.layout.first, firstTestArrayList);
                            listView.setAdapter(firstAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    firstAda.getFilter().filter(newText);
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
                params.put("cust_id", userModel.getUser_id());
                return params;
            }
        });
    }
}