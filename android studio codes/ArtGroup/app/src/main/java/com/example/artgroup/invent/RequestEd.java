package com.example.artgroup.invent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
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
import com.example.artgroup.midfield.InveDash;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.ProdModel;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestEd extends AppCompatActivity {
    ImageView imageView, Cartel;
    TextView textView, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, major, begger;
    BottomNavigationView botaz;
    InveSess custSess;
    UserModel userModel;
    RequestedM requestedM;
    ArrayList<RequestedM> requestedMArrayList = new ArrayList<>();
    RequestedAda requestedAda;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RequestQueue requestQueue;
    Rect rect;
    Window window;
    EditText describe, quantity;
    LayoutInflater layoutInflater, inflater;
    RelativeLayout relativeLayout, relColor, relImage;
    ProdModel bidModel;
    ArrayList<ProdModel> bidModelArrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ed);
        custSess = new InveSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.summer);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.summer:
                    return true;
                case R.id.pst:
                    startActivity(new Intent(getApplicationContext(), PastReq.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), InveDash.class));
            finish();
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
            builder = new AlertDialog.Builder(this);
            builder.setTitle(requestedM.getCategory() + " " + requestedM.getType());
            builder.setMessage("CUSTOMER\nID: " + requestedM.getCustid() + "\nname: " + requestedM.getName() + "\nphone: " + requestedM.getPhone() + "\n\nPRODUCT REQUEST\ncategory: " + requestedM.getCategory() + "\ntype: " + requestedM.getType() + "\ndescription: " + requestedM.getDescription() + "\nsize: " + requestedM.getSize() + "\nQuantity: " + requestedM.getQuantity() + "\nPrice: KES" + requestedM.getPrice() + "\n\nSTATUS\nstatus: " + requestedM.getStatus() + "\nrequestDate: " + requestedM.getDate());
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
            builder.setPositiveButton("Approve", (dialogInterface, i1) -> {
            });
            builder.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            builder.setNeutralButton("Reject", (dialogInterface, i1) -> {
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
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewPos,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int io = 0; io < jsonArray.length(); io++) {
                                        jsonObject = jsonArray.getJSONObject(io);
                                        String id = jsonObject.getString("id");
                                        String category = jsonObject.getString("category");
                                        String type = jsonObject.getString("type");
                                        String description = jsonObject.getString("description");
                                        String image = jsonObject.getString("image");
                                        String imagery = ModelUrl.img + image;
                                        String qty = jsonObject.getString("qty");
                                        String quantity = jsonObject.getString("quantity");
                                        String price = jsonObject.getString("price");
                                        String reg_date = jsonObject.getString("reg_date");
                                        bidModel = new ProdModel(id, category, type, description, imagery, qty, quantity, price, reg_date);
                                        bidModelArrayList.add(bidModel);
                                    }
                                    String fitiMan;
                                    if (Float.parseFloat(bidModel.getQuantity()) > Float.parseFloat(requestedM.getQuantity())) {
                                        fitiMan = String.format("%.0f", (Float.parseFloat(bidModel.getQuantity()) - Float.parseFloat(requestedM.getQuantity())));
                                        AlertDialog.Builder thama = new AlertDialog.Builder(this);
                                        thama.setTitle(Html.fromHtml("<font color='#ff0000'><b>Raw Materials Present</b></font>"));
                                        thama.setMessage(Html.fromHtml("<font color='#000000'><ul><li>Successful!! Raw materials were Found.</li><li>Quoted:-<ul><li>Category: " + requestedM.getCategory() + "</li><li>Type: " + requestedM.getType() + "</li><li>availableQuantity: " + bidModel.getQuantity() + "</li><li>orderedQuantity: " + requestedM.getQuantity() + "</li><li>expectedRemainder: " + fitiMan + "<li></ul></li></ul><br>Click NEXT to continue</font>"));
                                        thama.setPositiveButton("NEXT", (dialogInterface, i1) -> {
                                        });
                                        thama.setNegativeButton("Close", (dialogInterface, i1) -> {
                                        });
                                        AlertDialog never = thama.create();
                                        never.show();
                                        never.setCancelable(false);
                                        never.setCanceledOnTouchOutside(false);
                                        never.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        never.getWindow().setGravity(Gravity.TOP);
                                        never.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            AlertDialog.Builder priced = new AlertDialog.Builder(this);
                                            priced.setTitle("Editable Unit Price");
                                            final EditText editText = new EditText(this);
                                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                                            editText.setText(requestedM.getPrice());
                                            editText.setHint("price per quantity unit");
                                            frameLayout = new FrameLayout(this);
                                            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                            editText.setLayoutParams(params);
                                            frameLayout.addView(editText);
                                            priced.setView(frameLayout);
                                            priced.setPositiveButton("Submit", (dialogInterface, i1) -> {
                                            });
                                            priced.setNegativeButton("Close", (dialogInterface, i1) -> {
                                            });
                                            AlertDialog neem = priced.create();
                                            neem.show();
                                            neem.setCancelable(false);
                                            neem.setCanceledOnTouchOutside(false);
                                            neem.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                            neem.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                                                final String myPr = editText.getText().toString().trim();
                                                if (myPr.isEmpty()) {
                                                    editText.requestFocus();
                                                    textView.setText("Price is required");
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                } else if (Float.parseFloat(myPr) < 20) {
                                                    editText.requestFocus();
                                                    textView.setText("Price is even low");
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                } else {
                                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.updateSel,
                                                            respons -> {
                                                                try {
                                                                    jsonObject = new JSONObject(respons);
                                                                    int Status = jsonObject.getInt("success");
                                                                    String mess = jsonObject.getString("message");
                                                                    if (Status == 1) {
                                                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                                        startActivity(new Intent(getApplicationContext(), RequestEd.class));
                                                                        finish();
                                                                    } else if (Status == 0) {
                                                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }, error -> {
                                                        Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                                                    }) {
                                                        @Nullable
                                                        @Override
                                                        protected Map<String, String> getParams() {
                                                            Map<String, String> params = new HashMap<>();
                                                            params.put("slf", requestedM.getSlf());
                                                            params.put("status", "Approved");
                                                            params.put("quantity", fitiMan);
                                                            params.put("id", bidModel.getId());
                                                            params.put("price", myPr);
                                                            return params;
                                                        }
                                                    });
                                                }
                                            });
                                            neem.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                                                neem.cancel();
                                            });
                                        });
                                        never.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            never.cancel();
                                        });
                                    } else {
                                        fitiMan = String.format("%.0f", (Float.parseFloat(requestedM.getQuantity()) - Float.parseFloat(bidModel.getQuantity())));
                                        AlertDialog.Builder oyaa = new AlertDialog.Builder(this);
                                        oyaa.setTitle(Html.fromHtml("<font color='#ff0000'><b>Faced a Deficiency!!</b></font>"));
                                        oyaa.setMessage(Html.fromHtml("<font color='#000000'><ul><li>availableQuantity: " + bidModel.getQuantity() + "</li><li>orderedQuantity: " + requestedM.getQuantity() + "</li><li><b><u><i>VARIANCE: " + fitiMan + "</i></u></b></li></ul><ul><li>Post bid(raw Material).</li><li>Quote:-<ul><li>Category: " + requestedM.getCategory() + "</li><li>Type: " + requestedM.getType() + "</li><li>Quantity: " + fitiMan + "(or more)</li></ul></li></ul></font>"));
                                        AlertDialog summer = oyaa.create();
                                        summer.show();
                                        summer.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                        summer.getWindow().setGravity(Gravity.TOP);
                                    }
                                } else if (success == 0) {
                                    AlertDialog.Builder thama = new AlertDialog.Builder(this);
                                    thama.setTitle(Html.fromHtml("<font color='#ff0000'><b>Materials Deficiency!!</b></font>"));
                                    thama.setMessage(Html.fromHtml("<font color='#000000'><ul><li>Post bid(raw Material).</li><li>Quote:-<ul><li>Category: " + requestedM.getCategory() + "</li><li>Type: " + requestedM.getType() + "</li><li>Quantity: " + requestedM.getQuantity() + "(or more)</li></ul></li></ul></font>"));
                                    AlertDialog ush = thama.create();
                                    ush.show();
                                    ush.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    ush.getWindow().setGravity(Gravity.TOP);
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
                        Map<String, String> comb = new HashMap<>();
                        comb.put("category", requestedM.getCategory());
                        comb.put("type", requestedM.getType());
                        return comb;
                    }
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.updateSel,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int Status = jsonObject.getInt("success");
                                String mess = jsonObject.getString("message");
                                if (Status == 1) {
                                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), RequestEd.class));
                                    finish();
                                } else if (Status == 0) {
                                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("slf", requestedM.getSlf());
                        params.put("status", "Rejected");
                        params.put("quantity", "0");
                        params.put("id", "0");
                        params.put("price", requestedM.getPrice());
                        return params;
                    }
                });
            });
        });
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.justBy,
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
                                        jsonObject.getString("size"), jsonObject.getString("rgb"), jsonObject.getString("hexa"), jsonObject.getString("red"),
                                        jsonObject.getString("green"), jsonObject.getString("blue"), jsonObject.getString("motive"),
                                        jsonObject.getString("quantity"), jsonObject.getString("price"),
                                        jsonObject.getString("status"), jsonObject.getString("pay"),
                                        jsonObject.getString("fina"), ModelUrl.img + jsonObject.getString("image_desgn"), jsonObject.getString("design"), jsonObject.getString("date"));
                                requestedMArrayList.add(requestedM);
                            }
                            requestedAda = new RequestedAda(RequestEd.this, R.layout.okay, requestedMArrayList);
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
                para.put("status", "Pending");
                return para;
            }
        });
    }
}