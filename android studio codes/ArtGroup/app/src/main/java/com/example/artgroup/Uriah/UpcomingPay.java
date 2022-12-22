package com.example.artgroup.Uriah;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
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
import com.bumptech.glide.Glide;
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.ReqAdaPa;
import com.example.artgroup.models.RequestedM;
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

public class UpcomingPay extends AppCompatActivity {
    ImageView imageView, Cartel;
    TextView textView, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, major, begger;
    BottomNavigationView botaz;
    LayoutInflater layoutInflater, inflater;
    RequestedM requestedM;
    ArrayList<RequestedM> requestedMArrayList = new ArrayList<>();
    ReqAdaPa requestedAda;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustSess custSess;
    UserModel userModel;
    RequestQueue requestQueue;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    RelativeLayout relativeLayout, relColor, relImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_pay);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), SendRequirement.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), SelfDeli.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CustDash.class));
            finish();
        });
        findViewById(R.id.btnPast).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PayHisto.class));
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
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            requestedM = (RequestedM) adapterView.getItemAtPosition(i);
            String stella = String.format("%.0f", (Float.parseFloat(requestedM.getQuantity()) * Float.parseFloat(requestedM.getPrice())));
            builder = new AlertDialog.Builder(this);
            builder.setTitle(requestedM.getCategory() + " " + requestedM.getType());
            builder.setMessage("CUSTOMER\nID: " + requestedM.getCustid() + "\nname: " + requestedM.getName() + "\nphone: " + requestedM.getPhone() + "\n\nPRODUCT REQUEST\ncategory: " + requestedM.getCategory() + "\ntype: " + requestedM.getType() + "\ndescription: " + requestedM.getDescription() + "\nsize: " + requestedM.getSize() + "\nQuantity: " + requestedM.getQuantity() + "\nPrice: KES" + requestedM.getPrice() + "\n\nTOTAL COST\n" + requestedM.getQuantity() + " x KES" + requestedM.getPrice() + " = KES" + stella + "\n\nSTATUS\nstatus: " + requestedM.getStatus() + "\nrequestDate: " + requestedM.getDate() + "\nORDERStatus: " + requestedM.getPay());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            begger = layoutInflater.inflate(R.layout.comardos, null);
            begger.setMinimumWidth((int) (rect.width() * 1.0));
            begger.setMinimumHeight((int) (rect.height() * 0.01));
            relColor = begger.findViewById(R.id.relledColor);
            relImage = begger.findViewById(R.id.rellledImg);
            major = begger.findViewById(R.id.velvet);
            Cartel = begger.findViewById(R.id.imgDesc);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            begger.setLayoutParams(params);
            frameLayout.addView(begger);
            if (Float.parseFloat(requestedM.getDsc()) == 8) {
                Glide.with(this).load(requestedM.getImage()).into(Cartel);
                relImage.setVisibility(View.VISIBLE);
                builder.setView(frameLayout);
            }
            if (Float.parseFloat(requestedM.getMotive()) == 1) {
                int red = Integer.parseInt(requestedM.getRed()), green = Integer.parseInt(requestedM.getGreen()), blue = Integer.parseInt(requestedM.getBlue());
                major.setBackgroundColor(Color.rgb(red, green, blue));
                relColor.setVisibility(View.VISIBLE);
                builder.setView(frameLayout);
            }
            builder.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            builder.setPositiveButton("Make Payment", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder bilo = new AlertDialog.Builder(this);
                bilo.setMessage("Choose Option Below");
                bilo.setPositiveButton("+ SHIPMENT", (dialoga, o1) -> {
                });
                bilo.setNeutralButton("no SHIPMENT", (dialoga, o1) -> {
                });
                bilo.setNegativeButton("Close", (dialoga, o1) -> {
                });
                AlertDialog alert = bilo.create();
                alert.setCanceledOnTouchOutside(false);
                alert.setCancelable(false);
                alert.getWindow().setGravity(Gravity.CENTER);
                alert.show();
                alert.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                    alert.cancel();
                });
                alert.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                    AlertDialog.Builder pen = new AlertDialog.Builder(this);
                    pen.setMessage("Shipping Cost KES 310\nClick Continue to Proceed");
                    pen.setPositiveButton("Continue", (dialoga, o1) -> {
                    });
                    pen.setNegativeButton("Close", (dialoga, o1) -> {
                    });
                    AlertDialog book = pen.create();
                    book.setCanceledOnTouchOutside(false);
                    book.setCancelable(false);
                    book.getWindow().setGravity(Gravity.CENTER);
                    book.show();
                    book.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                    book.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    book.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view0 -> {
                        book.cancel();
                    });
                    book.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view0 -> {
                        AlertDialog.Builder brathe = new AlertDialog.Builder(this);
                        brathe.setTitle("Location");
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
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Current, android.R.layout.simple_spinner_item);
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
                            if (spin.equals("Current Location")) {
                                textView.setText("Current Location Required");
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
                                String alibaba = String.format("%.0f", (Float.parseFloat(stella)) + 310);
                                AlertDialog.Builder ranyard = new AlertDialog.Builder(this);
                                ranyard.setMessage("Amount KES" + stella + "\nShipping KES 310\nTotal: KES" + alibaba + "\nMPESA PAYBILL 456123 \nAccountNO:" + userModel.getUser_id() + "\nEnter MPESA CODE below");
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
                                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.paySel,
                                                respons -> {
                                                    try {
                                                        jsonObject = new JSONObject(respons);
                                                        int stat = jsonObject.getInt("success");
                                                        String msg = jsonObject.getString("message");
                                                        textView.setText(msg);
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.show();
                                                        if (stat == 1) {
                                                            startActivity(new Intent(getApplicationContext(), UpcomingPay.class));
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
                                                params.put("pay", "Ordered");
                                                params.put("slf", requestedM.getSlf());
                                                params.put("mpesa", myQty);
                                                params.put("amount", alibaba);
                                                params.put("orders", stella);
                                                params.put("ship", "310");
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
                alert.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view5 -> {
                    AlertDialog.Builder pen = new AlertDialog.Builder(this);
                    pen.setMessage("Amount KES" + stella + "\nMPESA PAYBILL 456123 \nAccountNO:" + userModel.getUser_id() + "\nEnter MPESA CODE below");
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
                    pen.setView(frameLayout);
                    pen.setPositiveButton("Submit", (dialoga, o1) -> {
                    });
                    pen.setNegativeButton("Close", (dialoga, o1) -> {
                    });
                    AlertDialog book = pen.create();
                    book.setCanceledOnTouchOutside(false);
                    book.setCancelable(false);
                    book.getWindow().setGravity(Gravity.CENTER);
                    book.show();
                    book.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                    book.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    book.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view0 -> {
                        book.cancel();
                    });
                    book.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view0 -> {
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
                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.paySel,
                                    respons -> {
                                        try {
                                            jsonObject = new JSONObject(respons);
                                            int stat = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            textView.setText(msg);
                                            toast.setDuration(Toast.LENGTH_SHORT);
                                            toast.show();
                                            if (stat == 1) {
                                                startActivity(new Intent(getApplicationContext(), UpcomingPay.class));
                                                finish();
                                            } else if (stat == 6) {
                                                description.setError(msg);
                                                description.requestFocus();
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
                                    params.put("pay", "Ordered");
                                    params.put("slf", requestedM.getSlf());
                                    params.put("mpesa", myQty);
                                    params.put("amount", stella);
                                    params.put("orders", stella);
                                    params.put("ship", "0");
                                    params.put("custid", userModel.getUser_id());
                                    params.put("name", userModel.getFname() + " " + userModel.getLname());
                                    params.put("phone", userModel.getPhone());
                                    params.put("location", "");
                                    params.put("landmark", "");
                                    params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                    return params;
                                }
                            });
                        }
                    });
                });
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.whoAim,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                requestedM = new RequestedM(jsonObject.getString("slf"), jsonObject.getString("dsc"), jsonObject.getString("custid"),
                                        jsonObject.getString("name"), jsonObject.getString("phone"), jsonObject.getString("category"),
                                        jsonObject.getString("type"), jsonObject.getString("description"), ModelUrl.img + jsonObject.getString("image"),
                                        jsonObject.getString("size"),jsonObject.getString("rgb"),jsonObject.getString("hexa"),jsonObject.getString("red"),
                                        jsonObject.getString("green"),jsonObject.getString("blue"),jsonObject.getString("motive"),
                                        jsonObject.getString("quantity"), jsonObject.getString("price"),
                                        jsonObject.getString("status"), jsonObject.getString("pay"),
                                        jsonObject.getString("fina"), ModelUrl.img + jsonObject.getString("image_desgn"), jsonObject.getString("design"), jsonObject.getString("date"));
                                requestedMArrayList.add(requestedM);
                            }
                            requestedAda = new ReqAdaPa(UpcomingPay.this, R.layout.okay, requestedMArrayList);
                            listView.setAdapter(requestedAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    requestedAda.getFilter().filter(newText);
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
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("custid", userModel.getUser_id());
                para.put("status", "Approved");
                para.put("pay", "Pending");
                return para;
            }
        });
    }
}