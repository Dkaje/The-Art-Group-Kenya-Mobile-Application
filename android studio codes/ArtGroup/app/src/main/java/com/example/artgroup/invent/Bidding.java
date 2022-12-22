package com.example.artgroup.invent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
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
import com.example.artgroup.midfield.InveDash;
import com.example.artgroup.models.BidAda;
import com.example.artgroup.models.BidModel;
import com.example.artgroup.models.InveSess;
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

public class Bidding extends AppCompatActivity {
    ImageView imageView, profile, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    InveSess custSess;
    UserModel userModel;
    RequestQueue requestQueue;
    Rect rect;
    Window window;
    EditText qty;
    LayoutInflater layoutInflater, inflater;
    Spinner categor, typed, clasic;
    String myTpe;
    BidModel bidModel;
    ArrayList<BidModel> bidModelArrayList = new ArrayList<>();
    BidAda bidAda;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);
        custSess = new InveSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Supply.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), RequestEd.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), InveDash.class));
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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        textView.setText("Welcome " + userModel.getFname());
        findViewById(R.id.addNEw).setOnClickListener(v -> {
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Purchases Order</u></b></font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.tend_upload, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            categor = layer.findViewById(R.id.SpinCate);
            clasic = layer.findViewById(R.id.spinAngela);
            typed = layer.findViewById(R.id.SpinType);
            qty = layer.findViewById(R.id.edtQty);
            ArrayAdapter<CharSequence> clas = ArrayAdapter.createFromResource(this, R.array.Class, android.R.layout.simple_spinner_item);
            clas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            clasic.setAdapter(clas);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Collection, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categor.setAdapter(adapter);
            ArrayAdapter<CharSequence> soft = ArrayAdapter.createFromResource(this, R.array.Soft, android.R.layout.simple_spinner_item);
            soft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> sign = ArrayAdapter.createFromResource(this, R.array.Signature, android.R.layout.simple_spinner_item);
            sign.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> cale = ArrayAdapter.createFromResource(this, R.array.Calendar, android.R.layout.simple_spinner_item);
            cale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> station = ArrayAdapter.createFromResource(this, R.array.Stationery, android.R.layout.simple_spinner_item);
            station.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> bag = ArrayAdapter.createFromResource(this, R.array.Bags, android.R.layout.simple_spinner_item);
            bag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> canvas = ArrayAdapter.createFromResource(this, R.array.Canvas, android.R.layout.simple_spinner_item);
            canvas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> wooden = ArrayAdapter.createFromResource(this, R.array.Wooden, android.R.layout.simple_spinner_item);
            wooden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> art = ArrayAdapter.createFromResource(this, R.array.Art, android.R.layout.simple_spinner_item);
            art.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            layer.setLayoutParams(params);
            frameLayout.addView(layer);
            builder.setView(frameLayout);
            builder.setNegativeButton("Close", (v1, ve) -> {
            });
            builder.setPositiveButton("Send", (v1, ve) -> {
            });
            alertDialog = builder.create();
            categor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mSpinner = categor.getSelectedItem().toString();
                    if (mSpinner.equals("Soft Furnishing & Clothing")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(soft);
                    } else if (mSpinner.equals("Signature Collection")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(sign);
                    } else if (mSpinner.equals("Calendar")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(cale);
                    } else if (mSpinner.equals("Stationery")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(station);
                    } else if (mSpinner.equals("Tote Bags & Fittings")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(bag);
                    } else if (mSpinner.equals("Canvas")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(canvas);
                    } else if (mSpinner.equals("Wooden Wall Art")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(wooden);
                    } else if (mSpinner.equals("Art Paints")) {
                        typed.setVisibility(View.VISIBLE);
                        typed.setAdapter(art);
                    } else {
                        typed.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

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
                final String mCla = clasic.getSelectedItem().toString().trim();
                final String myQty = qty.getText().toString().trim();
                if (mCla.equals("Select Classification")) {
                    textView.setText("Please Select Classification");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else if (myCate.equals("Product Collection")) {
                    textView.setText("Please select Product Collection");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    myTpe = typed.getSelectedItem().toString().trim();
                    if (myTpe.equals("Product Type")) {
                        textView.setText("Please select Product Type");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (myQty.isEmpty()) {
                        qty.requestFocus();
                        textView.setText("Quantity Required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myQty) < 1) {
                        qty.requestFocus();
                        textView.setText("Low Quantity");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.uploadT,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        String mess = jsonObject.getString("message");
                                        int Status = jsonObject.getInt("success");
                                        textView.setText(mess);
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                        if (Status == 1) {
                                            startActivity(new Intent(getApplicationContext(), Bidding.class));
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        textView.setText("Server Error");
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }, error -> {
                            textView.setText("Network Error");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("classification", mCla);
                                params.put("category", myCate);
                                params.put("quantity", myQty);
                                params.put("type", myTpe);
                                params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                return params;
                            }
                        });
                    }
                }
            });
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
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (BidModel) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Update Record</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getClassification() + "<br>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<font>"));
            final EditText editText = new EditText(this);
            editText.setText(bidModel.getQuantity());
            editText.setHint("Enter Quantity");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            editText.setLayoutParams(params);
            frameLayout.addView(editText);
            builder.setView(frameLayout);
            builder.setPositiveButton("Submit", (dd, d) -> {
            });
            builder.setNegativeButton("Close", (dd, d) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Drop</font>"), (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String myQty = editText.getText().toString().trim();
                if (myQty.isEmpty()) {
                    editText.requestFocus();
                    textView.setText("Quantity Required");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else if (Float.parseFloat(myQty) < 1) {
                    editText.requestFocus();
                    textView.setText("Low Quantity");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.modiB,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int status = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    textView.setText(msg);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (status == 1) {
                                        startActivity(new Intent(getApplicationContext(), Bidding.class));
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
                            para.put("bid", bidModel.getBid());
                            para.put("quantity", myQty);
                            para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                            return para;
                        }
                    });
                }

            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                if (bidModel.getQty().equals(bidModel.getQuantity())) {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.killB,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int status = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    textView.setText(msg);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (status == 1) {
                                        startActivity(new Intent(getApplicationContext(), Bidding.class));
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
                            para.put("bid", bidModel.getBid());
                            return para;
                        }
                    });
                } else {
                    textView.setText("Operation Failed!!!\nCannot Delete This RECORD");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewUp,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String bid = jsonObject.getString("bid");
                                String classification = jsonObject.getString("classification");
                                String category = jsonObject.getString("category");
                                String type = jsonObject.getString("type");
                                String qty = jsonObject.getString("qty");
                                String quantity = jsonObject.getString("quantity");
                                String entry_date = jsonObject.getString("entry_date");
                                String update_date = jsonObject.getString("update_date");
                                bidModel = new BidModel(bid, classification, category, type, qty, quantity, entry_date, update_date);
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new BidAda(Bidding.this, R.layout.marathon, bidModelArrayList);
                            listView.setAdapter(bidAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    bidAda.getFilter().filter(newText);
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