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
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ServAda;
import com.example.artgroup.models.ServModel;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Booker extends AppCompatActivity {
    ImageView viewer, mimi;
    ImageView imageView, profile;
    TextView textView, texter;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, logger;
    RequestQueue requestQueue;
    View layer;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    TextInputLayout textInputLayout;
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
        setContentView(R.layout.activity_booker);
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
        findViewById(R.id.addNEw).setOnClickListener(v -> {
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Photo/Video Services</u></b></font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.sure, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            textInputLayout = layer.findViewById(R.id.ME);
            price = layer.findViewById(R.id.Cost);
            categor = layer.findViewById(R.id.SpinCate);
            typed = layer.findViewById(R.id.SpinType);
            description = layer.findViewById(R.id.edtDesc);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categ, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categor.setAdapter(adapter);
            ArrayAdapter<CharSequence> soft = ArrayAdapter.createFromResource(this, R.array.Photo, android.R.layout.simple_spinner_item);
            soft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> sign = ArrayAdapter.createFromResource(this, R.array.Vide, android.R.layout.simple_spinner_item);
            sign.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> cale = ArrayAdapter.createFromResource(this, R.array.Edit, android.R.layout.simple_spinner_item);
            cale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            builder.setNegativeButton("Close", (v1, ve) -> {
            });
            builder.setPositiveButton("Next", (v1, ve) -> {
            });
            alertDialog = builder.create();
            categor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mSpinner = categor.getSelectedItem().toString();
                    if (mSpinner.equals("Photography")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(soft);
                    } else if (mSpinner.equals("Videography")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(sign);
                    } else if (mSpinner.equals("Editing & Design")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(cale);
                    } else {
                        typed.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            typed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String mME = typed.getSelectedItem().toString().trim();
                    if (mME.equals("Drones")) {
                        price.setText("12000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Medical Photography")) {
                        price.setText("14000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Corporate Photography")) {
                        price.setText("32000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Commercial Real Estate")) {
                        price.setText("52000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Industrial Photography")) {
                        price.setText("20000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Headshot Photography")) {
                        price.setText("2500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Real Estate Photography")) {
                        price.setText("60000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Kids Photography")) {
                        price.setText("15000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wedding Photography")) {
                        price.setText("44000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Food Photography")) {
                        price.setText("18000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Product Photography")) {
                        price.setText("45000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Fashion Photography")) {
                        price.setText("51000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Family Photography")) {
                        price.setText("7000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Engagement Photography")) {
                        price.setText("9000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Maternity Photography")) {
                        price.setText("11000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Funeral Videography")) {
                        price.setText("38000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Photo Shoot")) {
                        price.setText("36000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Product Videography")) {
                        price.setText("71000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Animation")) {
                        price.setText("14000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Documentary")) {
                        price.setText("91000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wedding Videography")) {
                        price.setText("70000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Corporate Video Production")) {
                        price.setText("67000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Live Stream Production Services")) {
                        price.setText("68000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Editing & Design")) {
                        price.setText("17000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else {
                        price.setText("");
                        textInputLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                final String myCate = categor.getSelectedItem().toString().trim();
                final String mDesc = description.getText().toString().trim();
                final String mPrice = price.getText().toString().trim();
                if (myCate.equals("Select Category")) {
                    textView.setText("Please select Select Category");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    myTpe = typed.getSelectedItem().toString().trim();
                    if (myTpe.equals("Select Type")) {
                        textView.setText("Please select Type");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (mDesc.isEmpty()) {
                        description.requestFocus();
                        textView.setText("Description Required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
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
                        ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(this, R.array.Ugitiri, android.R.layout.simple_spinner_item);
                        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapterr);
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
                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.sendS,
                                        respons -> {
                                            try {
                                                jsonObject = new JSONObject(respons);
                                                int stat = jsonObject.getInt("success");
                                                String msg = jsonObject.getString("message");
                                                textView.setText(msg);
                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                toast.show();
                                                if (stat == 1) {
                                                    startActivity(new Intent(getApplicationContext(), Booker.class));
                                                    finish();
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                textView.setText(e.toString());
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
                                        params.put("category", myCate);
                                        params.put("description", mDesc);
                                        params.put("charge", mPrice);
                                        params.put("type", myTpe);
                                        params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                        params.put("custid", userModel.getUser_id());
                                        params.put("name", userModel.getFname() + " " + userModel.getLname());
                                        params.put("phone", userModel.getPhone());
                                        params.put("location", spin);
                                        params.put("landmark", myLan + "-" + myHouse);
                                        return params;
                                    }
                                });
                            }
                        });
                    }
                }
            });
        });
        mimi = findViewById(R.id.arrowBack);
        mimi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
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
        listView.setOnItemClickListener((parent, view, position, id) -> {
            servModel = (ServModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(this, R.style.Arap);
            alert.setTitle("Ordered Service");
            alert.setMessage("CustomerID: " + servModel.getCustid() + "\nName: " + servModel.getName() + "\nPhone: " + servModel.getPhone() + "\nLocation: " + servModel.getLocation() + " - " + servModel.getLandmark() + "\n\nSERVICE\nCategory: " + servModel.getCategory() + "\nType: " + servModel.getType() + "\nDescription: " + servModel.getDescription() + "\n\nSTATUS\nCharge: KES" + servModel.getCharge() + "\ndateOrdered: " + servModel.getReg_date() + "\nStatus: " + servModel.getStatus());
            alert.setNegativeButton("Close", (dialogInterface, i1) -> {
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
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.milage,
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
                            servAda = new ServAda(Booker.this, R.layout.marathon, servModelArrayList);
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
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("custid", userModel.getUser_id());
                return params;
            }
        });
    }
}