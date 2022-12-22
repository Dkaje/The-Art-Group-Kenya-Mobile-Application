package com.example.artgroup.midfield;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
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
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.design.DesignHistory;
import com.example.artgroup.design.DesignMess;
import com.example.artgroup.models.ProjeSess;
import com.example.artgroup.models.RequestedAda;
import com.example.artgroup.models.RequestedM;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DesignDash extends AppCompatActivity {
    ImageView imageView, Cartel, kartex, profile;
    TextView textView, texter, amount;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger, major;
    BottomNavigationView botaz;
    RequestQueue requestQueue;
    Rect rect;
    Window window;
    EditText describe, quantity;
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
    ProjeSess custSess;
    UserModel userModel;
    String imagery;
    Bitmap bitmap;
    RelativeLayout relativeLayout, relColor, relImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new ProjeSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_design_dash);
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.cart);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), DesignHistory.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), DesignMess.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
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
            builder.setPositiveButton("Finalize", (dialogInterface, i1) -> {
            });
            builder.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle("An image for final the product");
                rect = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                samoa = layoutInflater.inflate(R.layout.add_image, null);
                samoa.setMinimumWidth((int) (rect.width() * 1.0));
                samoa.setMinimumHeight((int) (rect.height() * 0.01));
                kartex = samoa.findViewById(R.id.imgDesc);
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                samoa.setLayoutParams(params);
                frameLayout.addView(samoa);
                alert.setView(frameLayout);
                alert.setPositiveButton("Submit", (dialogInterface, i1) -> {
                });
                alert.setNeutralButton("Image", (dialogInterface, i1) -> {
                });
                alert.setNegativeButton("Close", (dialogInterface, i1) -> {
                });
                AlertDialog mbao = alert.create();
                mbao.show();
                mbao.setCanceledOnTouchOutside(false);
                mbao.setCancelable(false);
                mbao.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                mbao.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view11 -> {
                    mbao.cancel();
                });
                mbao.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view11 -> {
                    Drawable drawable = kartex.getDrawable();
                    if (drawable == null) {
                        textView.setText("CLick Image");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.notEvery,
                                response -> {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int Status = jsonObject.getInt("success");
                                        String mess = jsonObject.getString("message");
                                        if (Status == 1) {
                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), DesignDash.class));
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
                                params.put("design", "Ready");
                                params.put("image_desgn", imagery);
                                return params;
                            }
                        });
                    }
                });
                mbao.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view11 -> {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            22
                    );
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
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
                kartex.setImageBitmap(bitmap);
                kartex.setVisibility(View.VISIBLE);
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
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getto,
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
                            requestedAda = new RequestedAda(DesignDash.this, R.layout.okay, requestedMArrayList);
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
                para.put("fina", "Approved");
                para.put("design", "Pending");
                return para;
            }
        });
    }
}