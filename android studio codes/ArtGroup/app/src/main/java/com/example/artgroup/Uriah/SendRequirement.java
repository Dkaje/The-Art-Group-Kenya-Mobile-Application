package com.example.artgroup.Uriah;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.artgroup.R;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendRequirement extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageView imageView, Cartel;
    TextView textView, texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    BottomNavigationView botaz;
    RequestQueue requestQueue;
    Rect rect;
    Window window;
    EditText describe, quantity, price;
    TextInputLayout textInputLayout;
    LayoutInflater layoutInflater, inflater;
    Spinner categor, typed, sized;
    String myTpe;
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
    CustSess custSess;
    UserModel userModel;
    String imagery;
    Bitmap bitmap;
    View vColor, major;
    TextView tvCode, tvValue, cSom, vSom;
    SeekBar sbRed, sbGreen, sbBlue;
    int red = 0, green = 0, blue = 0;
    CheckBox checkBox;
    RelativeLayout relativeLayout, relColor, relImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_requirement);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), UpcomingPay.class));
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
        findViewById(R.id.btnMade).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Designed.class));
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
        findViewById(R.id.addNEw).setOnClickListener(v -> {
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Send Requirement</u></b></font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.simon, null);
            layer.setMinimumWidth((int) (rect.width() * 1.0));
            layer.setMinimumHeight((int) (rect.height() * 0.01));
            categor = layer.findViewById(R.id.SpinCate);
            typed = layer.findViewById(R.id.SpinType);
            describe = layer.findViewById(R.id.edtDesc);
            sized = layer.findViewById(R.id.spinSize);
            quantity = layer.findViewById(R.id.edtAmount);
            textInputLayout = layer.findViewById(R.id.ME);
            price = layer.findViewById(R.id.Cost);
            ArrayAdapter<CharSequence> ada = ArrayAdapter.createFromResource(this, R.array.Size, android.R.layout.simple_spinner_item);
            ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sized.setAdapter(ada);
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
            vColor = layer.findViewById(R.id.v_color);
            tvCode = layer.findViewById(R.id.tv_code);
            tvValue = layer.findViewById(R.id.tv_value);
            tvValue.setVisibility(View.GONE);
            tvCode.setVisibility(View.GONE);
            sbGreen = layer.findViewById(R.id.sb_green);
            sbBlue = layer.findViewById(R.id.sb_blue);
            sbRed = layer.findViewById(R.id.sb_red);
            checkBox = layer.findViewById(R.id.checker);
            relativeLayout = layer.findViewById(R.id.linear);
            sbRed.setOnSeekBarChangeListener(this);
            sbGreen.setOnSeekBarChangeListener(this);
            sbBlue.setOnSeekBarChangeListener(this);
            checkBox.setOnCheckedChangeListener((check, d) -> {
                if (d) {
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    vColor.setBackgroundColor(Color.TRANSPARENT);
                    tvCode.setText("");
                    tvValue.setText("");
                    sbRed.setProgress(0);
                    sbGreen.setProgress(0);
                    sbBlue.setProgress(0);
                }
            });
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
            typed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String mME = typed.getSelectedItem().toString().trim();
                    if (mME.equals("Cushions")) {
                        price.setText("4500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Scarf")) {
                        price.setText("3000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Tshirt")) {
                        price.setText("3000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Sash")) {
                        price.setText("1200");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Caps")) {
                        price.setText("900");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Summer Landscape")) {
                        price.setText("4000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Flamingo")) {
                        price.setText("3500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Bull")) {
                        price.setText("9000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Tree of Wishes")) {
                        price.setText("8500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Tropical Rainforest")) {
                        price.setText("1000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Calendar")) {
                        price.setText("1500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Just a Note")) {
                        price.setText("2000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Magazines")) {
                        price.setText("1000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Lots of Note")) {
                        price.setText("2100");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Long Handle Bag")) {
                        price.setText("5500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Handle Cotton Shopper")) {
                        price.setText("12000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Lanyard")) {
                        price.setText("1500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wristband")) {
                        price.setText("1900");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Cloud Chasing")) {
                        price.setText("14000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Butterfly Kisses")) {
                        price.setText("7000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Regal Dawn")) {
                        price.setText("10000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Amanda Brooks")) {
                        price.setText("7000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Outdoor Wooden Signs")) {
                        price.setText("3000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wood Prints")) {
                        price.setText("7200");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wooden Brooks")) {
                        price.setText("5000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Wooden Signs")) {
                        price.setText("6000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Aggie")) {
                        price.setText("7500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Green Heron")) {
                        price.setText("4500");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Julia Pankhurst")) {
                        price.setText("7000");
                        textInputLayout.setVisibility(View.VISIBLE);
                    } else if (mME.equals("Love Love")) {
                        price.setText("6000");
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
                final String mSize = sized.getSelectedItem().toString().trim();
                final String myQty = quantity.getText().toString().trim();
                final String mDesc = describe.getText().toString().trim();
                final String mPrice = price.getText().toString().trim();
                if (myCate.equals("Product Collection")) {
                    textView.setText("Please select Product Collection");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    myTpe = typed.getSelectedItem().toString().trim();
                    if (myTpe.equals("Product Type")) {
                        textView.setText("Please select Product Type");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (mDesc.isEmpty()) {
                        describe.requestFocus();
                        textView.setText("Some Description");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (mSize.equals("Select Size")) {
                        textView.setText("You need to Select Size");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (myQty.isEmpty()) {
                        quantity.requestFocus();
                        textView.setText("Quantity Required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myQty) < 1) {
                        quantity.requestFocus();
                        textView.setText("Low Quantity");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (checkBox.isChecked()) {
                            String Codes = tvCode.getText().toString().trim();
                            String Values = tvValue.getText().toString().trim();
                            if (Codes.isEmpty()) {
                                textView.setText("If you choose to add color please add one");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            } else if (Values.isEmpty()) {
                                textView.setText("If you choose to add color please add one");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                alert.setTitle("More Description");
                                alert.setMessage("Hey " + userModel.getFname() + "\nDo you have some image description??\nProvide below.");
                                alert.setPositiveButton("No_Need", (dialogInterface, i) -> {
                                });
                                alert.setNegativeButton("Close", (dialogInterface, i) -> {
                                });
                                alert.setNeutralButton("Add_Image", (dialogInterface, i) -> {
                                });
                                AlertDialog mouse = alert.create();
                                mouse.show();
                                mouse.setCancelable(false);
                                mouse.setCanceledOnTouchOutside(false);
                                mouse.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                mouse.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.selfPolymer,
                                            response -> {
                                                try {
                                                    jsonObject = new JSONObject(response);
                                                    String mess = jsonObject.getString("message");
                                                    int Status = jsonObject.getInt("success");
                                                    textView.setText(mess);
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                    if (Status == 1) {
                                                        startActivity(new Intent(getApplicationContext(), SendRequirement.class));
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
                                            params.put("category", myCate);
                                            params.put("quantity", myQty);
                                            params.put("description", mDesc);
                                            params.put("price", mPrice);
                                            params.put("size", mSize);
                                            params.put("blue", String.valueOf(blue));
                                            params.put("green", String.valueOf(green));
                                            params.put("red", String.valueOf(red));
                                            params.put("hexa", Codes);
                                            params.put("rgb", Values);
                                            params.put("motive", "1");
                                            params.put("type", myTpe);
                                            params.put("name", userModel.getFname() + " " + userModel.getLname());
                                            params.put("phone", userModel.getPhone());
                                            params.put("custid", userModel.getUser_id());
                                            params.put("image", "No");
                                            params.put("dsc", "7");
                                            params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                            return params;
                                        }
                                    });
                                });
                                mouse.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                    mouse.cancel();
                                });
                                mouse.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                    AlertDialog.Builder child = new AlertDialog.Builder(this, R.style.Arap);
                                    child.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + myCate + " " + myTpe + "</u></b></font>"));
                                    child.setMessage("Last Step!\nAdd some Image");
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    begger = layoutInflater.inflate(R.layout.add_image, null);
                                    begger.setMinimumWidth((int) (rect.width() * 1.0));
                                    begger.setMinimumHeight((int) (rect.height() * 0.01));
                                    Cartel = begger.findViewById(R.id.imgDesc);
                                    frameLayout = new FrameLayout(this);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                    begger.setLayoutParams(params);
                                    frameLayout.addView(begger);
                                    child.setView(frameLayout);
                                    child.setPositiveButton("Next", (dd, d) -> {
                                    });
                                    child.setNegativeButton("Close", (dd, d) -> {
                                    });
                                    child.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Image</font>"), (dd, d) -> {
                                    });
                                    AlertDialog toto = child.create();
                                    toto.setCancelable(false);
                                    toto.setCanceledOnTouchOutside(false);
                                    toto.show();
                                    toto.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    toto.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                    toto.getWindow().setGravity(Gravity.CENTER);
                                    toto.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewo -> {
                                        toto.cancel();
                                    });
                                    toto.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewo -> {
                                        Drawable drawable = Cartel.getDrawable();
                                        if (drawable == null) {
                                            textView.setText("CLick Image");
                                            toast.setDuration(Toast.LENGTH_SHORT);
                                            toast.show();
                                        } else {
                                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.selfPolymer,
                                                    response -> {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(response);
                                                            int Status = jsonObject.getInt("success");
                                                            String mess = jsonObject.getString("message");
                                                            if (Status == 1) {
                                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                                startActivity(new Intent(getApplicationContext(), SendRequirement.class));
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
                                                    params.put("category", myCate);
                                                    params.put("quantity", myQty);
                                                    params.put("price", mPrice);
                                                    params.put("description", mDesc);
                                                    params.put("size", mSize);
                                                    params.put("blue", String.valueOf(blue));
                                                    params.put("green", String.valueOf(green));
                                                    params.put("red", String.valueOf(red));
                                                    params.put("hexa", Codes);
                                                    params.put("rgb", Values);
                                                    params.put("motive", "1");
                                                    params.put("type", myTpe);
                                                    params.put("name", userModel.getFname() + " " + userModel.getLname());
                                                    params.put("phone", userModel.getPhone());
                                                    params.put("custid", userModel.getUser_id());
                                                    params.put("image", imagery);
                                                    params.put("dsc", "8");
                                                    params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                                    return params;
                                                }
                                            });
                                        }
                                    });
                                    toto.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(viewo -> {
                                        ActivityCompat.requestPermissions(
                                                this,
                                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                22
                                        );
                                    });
                                });
                            }
                        } else {
                            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                            alert.setTitle("More Description");
                            alert.setMessage("Hey " + userModel.getFname() + "\nDo you have some image description??\nProvide below.");
                            alert.setPositiveButton("No_Need", (dialogInterface, i) -> {
                            });
                            alert.setNegativeButton("Close", (dialogInterface, i) -> {
                            });
                            alert.setNeutralButton("Add_Image", (dialogInterface, i) -> {
                            });
                            AlertDialog mouse = alert.create();
                            mouse.show();
                            mouse.setCancelable(false);
                            mouse.setCanceledOnTouchOutside(false);
                            mouse.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                            mouse.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.selfPolymer,
                                        response -> {
                                            try {
                                                jsonObject = new JSONObject(response);
                                                String mess = jsonObject.getString("message");
                                                int Status = jsonObject.getInt("success");
                                                textView.setText(mess);
                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                toast.show();
                                                if (Status == 1) {
                                                    startActivity(new Intent(getApplicationContext(), SendRequirement.class));
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
                                        params.put("category", myCate);
                                        params.put("quantity", myQty);
                                        params.put("price", mPrice);
                                        params.put("description", mDesc);
                                        params.put("size", mSize);
                                        params.put("blue", "0");
                                        params.put("green", "0");
                                        params.put("red", "0");
                                        params.put("hexa", "0");
                                        params.put("rgb", "0");
                                        params.put("motive", "0");
                                        params.put("type", myTpe);
                                        params.put("name", userModel.getFname() + " " + userModel.getLname());
                                        params.put("phone", userModel.getPhone());
                                        params.put("custid", userModel.getUser_id());
                                        params.put("image", "No");
                                        params.put("dsc", "7");
                                        params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                        return params;
                                    }
                                });
                            });
                            mouse.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                mouse.cancel();
                            });
                            mouse.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                AlertDialog.Builder child = new AlertDialog.Builder(this, R.style.Arap);
                                child.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + myCate + " " + myTpe + "</u></b></font>"));
                                child.setMessage("Last Step!\nAdd some Image");
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                begger = layoutInflater.inflate(R.layout.add_image, null);
                                begger.setMinimumWidth((int) (rect.width() * 1.0));
                                begger.setMinimumHeight((int) (rect.height() * 0.01));
                                Cartel = begger.findViewById(R.id.imgDesc);
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                begger.setLayoutParams(params);
                                frameLayout.addView(begger);
                                child.setView(frameLayout);
                                child.setPositiveButton("Next", (dd, d) -> {
                                });
                                child.setNegativeButton("Close", (dd, d) -> {
                                });
                                child.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Image</font>"), (dd, d) -> {
                                });
                                AlertDialog toto = child.create();
                                toto.setCancelable(false);
                                toto.setCanceledOnTouchOutside(false);
                                toto.show();
                                toto.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                toto.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                toto.getWindow().setGravity(Gravity.CENTER);
                                toto.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewo -> {
                                    toto.cancel();
                                });
                                toto.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewo -> {
                                    Drawable drawable = Cartel.getDrawable();
                                    if (drawable == null) {
                                        textView.setText("CLick Image");
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                    } else {
                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.selfPolymer,
                                                response -> {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        int Status = jsonObject.getInt("success");
                                                        String mess = jsonObject.getString("message");
                                                        if (Status == 1) {
                                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(getApplicationContext(), SendRequirement.class));
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
                                                params.put("category", myCate);
                                                params.put("quantity", myQty);
                                                params.put("price", mPrice);
                                                params.put("description", mDesc);
                                                params.put("size", mSize);
                                                params.put("blue", "0");
                                                params.put("green", "0");
                                                params.put("red", "0");
                                                params.put("hexa", "0");
                                                params.put("rgb", "0");
                                                params.put("motive", "0");
                                                params.put("type", myTpe);
                                                params.put("name", userModel.getFname() + " " + userModel.getLname());
                                                params.put("phone", userModel.getPhone());
                                                params.put("custid", userModel.getUser_id());
                                                params.put("image", imagery);
                                                params.put("dsc", "8");
                                                params.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                                return params;
                                            }
                                        });
                                    }
                                });
                                toto.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(viewo -> {
                                    ActivityCompat.requestPermissions(
                                            this,
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            22
                                    );
                                });
                            });
                        }
                    }
                }
            });
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            requestedM = (RequestedM) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle(requestedM.getCategory() + " " + requestedM.getType());
            builder.setMessage("CUSTOMER\nID: " + requestedM.getCustid() + "\nname: " + requestedM.getName() + "\nphone: " + requestedM.getPhone() + "\n\nPRODUCT REQUEST\ncategory: " + requestedM.getCategory() + "\ntype: " + requestedM.getType() + "\ndescription: " + requestedM.getDescription() + "\nsize: " + requestedM.getSize() + "\nQuantity: " + requestedM.getQuantity() + "\n\nSTATUS\nstatus: " + requestedM.getStatus() + "\nrequestDate: " + requestedM.getDate());
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
            builder.setPositiveButton("Close", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 22) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 22);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 22 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                Cartel.setImageBitmap(bitmap);
                Cartel.setVisibility(View.VISIBLE);
                encodedBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodedBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        imagery = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void getRecords() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getRq,
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
                            requestedAda = new RequestedAda(SendRequirement.this, R.layout.okay, requestedMArrayList);
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
                return para;
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_red:
                red = i;
                break;
            case R.id.sb_green:
                green = i;
                break;
            case R.id.sb_blue:
                blue = i;
                break;
        }
        vColor.setBackgroundColor(Color.rgb(red, green, blue));
        String code = HexCode(red, green, blue);
        tvCode.setText(code.toUpperCase());
        tvValue.setText(String.format("RGB(%d,%d,%d)", red, green, blue));
    }

    private String HexCode(int red, int green, int blue) {
        String code;
        code = "#";
        code += Integer.toHexString(red);
        code += Integer.toHexString(green);
        code += Integer.toHexString(blue);
        return code;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}