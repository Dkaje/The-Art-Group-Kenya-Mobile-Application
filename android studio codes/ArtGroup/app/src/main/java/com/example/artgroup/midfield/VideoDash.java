package com.example.artgroup.midfield;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.util.Base64;
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
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.models.PayerSe;
import com.example.artgroup.models.ReviMode;
import com.example.artgroup.models.Seeker;
import com.example.artgroup.models.UserModel;
import com.example.artgroup.models.VideoSess;
import com.example.artgroup.vide.HistVide;
import com.example.artgroup.vide.VideMEss;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class VideoDash extends AppCompatActivity {
    BottomNavigationView botaz;
    ImageView imageView, profile;
    TextView textView, head;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog, otis;
    RequestQueue requestQueue;
    View layer, begger;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    LayoutInflater layoutInflater, inflater;
    Spinner categor, typed;
    String myTpe;
    Button button;
    ListView listView;
    SearchView searchView;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    VideoSess custSess;
    UserModel userModel;
    Seeker seeker;
    ArrayList<ReviMode> bidModelArrayList = new ArrayList<>();
    ReviMode reviMode;
    String mdater, md, mdt;
    PayerSe payerSe;
    ArrayList<PayerSe> payerSeArrayList = new ArrayList<>();
    String imagery, form;
    Bitmap bitmap;
    ImageView Cartel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dash);
        custSess = new VideoSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        profile.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this, R.style.Profile);
            builder.setTitle("My Profile");
            builder.setMessage(Html.fromHtml("<font><b>Firstname</b>: " + userModel.getFname() + "<br><b>Lastname</b>: " + userModel.getLname() + "<br><b>Username</b>: " + userModel.getUsername() + "<br><b>Phone</b>: " + userModel.getPhone() + "<br><b>Email</b>: " + userModel.getEmail() + "<br><b>Role</b>: " + userModel.getCounty() + " Manager<br><b>RegDate</b>: " + userModel.getReg_date() + "</font>"));
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
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.Ord);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Ord:
                    return true;
                case R.id.att:
                    startActivity(new Intent(getApplicationContext(), HistVide.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), VideMEss.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
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
        button = findViewById(R.id.addNEw);
        head = findViewById(R.id.myTxt);
        button.setOnClickListener(view -> {
            head.setText("The Art Group Nairobi\n0116 284 3691, 0706 287510");
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            printManager.print(getString(R.string.app_name), new PDFGenerator(this, findViewById(R.id.rela)), null);
        });
        listView.setOnItemClickListener(((adapterView, view, i, l) -> {
            reviMode = (ReviMode) adapterView.getItemAtPosition(i);
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.infor,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int ii = 0; ii < jsonArray.length(); ii++) {
                                    jsonObject = jsonArray.getJSONObject(ii);
                                    payerSe = new PayerSe(jsonObject.getString("serid"), jsonObject.getString("mpesa"), jsonObject.getString("amount"),
                                            jsonObject.getString("category"), jsonObject.getString("type"), jsonObject.getString("description"),
                                            jsonObject.getString("serv"), jsonObject.getString("custid"), jsonObject.getString("name"),
                                            jsonObject.getString("phone"), jsonObject.getString("location"), jsonObject.getString("landmark"),
                                            jsonObject.getString("status"), jsonObject.getString("comment"), jsonObject.getString("disb"), jsonObject.getString("reg_date"));
                                    payerSeArrayList.add(payerSe);
                                }
                                builder = new AlertDialog.Builder(this, R.style.Arap);
                                builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Service Delivery</u></b></font>"));
                                if (reviMode.getVideo().equals("Pending")) {
                                    mdater = "";
                                } else {
                                    mdater = reviMode.getVideo_date();
                                }
                                if (reviMode.getDrive().equals("Pending")) {
                                    mdt = "";
                                } else {
                                    mdt = reviMode.getDrive_date();
                                }
                                if (reviMode.getCustom().equals("Pending")) {
                                    md = "";
                                } else {
                                    md = reviMode.getCustom_date();
                                }
                                builder.setMessage("ATTACHMENT_DATE\n" + reviMode.getReg_date() + "\n\nCUSTOMER\nname: " + reviMode.getCust_name() + "\nphone: " + reviMode.getCust_phone() + "\nEvent: " + reviMode.getLocation() + " - " + reviMode.getLandmark() + "\n\nDRIVER\nname: " + reviMode.getDriver_name() + "\nphone: " + reviMode.getDriver_phone() + "\n\nVIDEOGRAPHER\nname: " + reviMode.getVideo_name() + "\nphone: " + reviMode.getVideo_phone() + "\nDelivery: " + reviMode.getVideo() + "\nDate: " + mdater + "\n\nSERVICE:\n" + payerSe.getCategory() + "-" + payerSe.getType());
                                builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Confirm</font>"), (dd, d) -> {
                                });
                                builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'>Add_Image</font>"), (dd, d) -> {
                                });
                                builder.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Close</font>"), (dd, d) -> {
                                });
                                alertDialog = builder.create();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.show();
                                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                alertDialog.getWindow().setGravity(Gravity.CENTER);
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                    alertDialog.cancel();
                                });
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.checkI,
                                            kani -> {
                                                try {
                                                    jsonObject = new JSONObject(kani);
                                                    int status = jsonObject.getInt("trust");
                                                    String msg = jsonObject.getString("mine");
                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                    if (status == 1) {
                                                        alert = new AlertDialog.Builder(this);
                                                        alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Confirm</u></b></font>"));
                                                        alert.setMessage("Hi " + reviMode.getVideo_name() + ",\nBy Clicking CONFIRM\nYou agree that the delivery Successful\nDo you want to proceed?\nYou cannot undo this process");
                                                        alert.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Yes_Agreed</font>"), (dd, d) -> {
                                                        });
                                                        alert.setNegativeButton(Html.fromHtml("<font color='#ff0000'>Not_Yet</font>"), (dd, d) -> {
                                                        });
                                                        otis = alert.create();
                                                        otis.setCancelable(false);
                                                        otis.setCanceledOnTouchOutside(false);
                                                        otis.show();
                                                        otis.getWindow().setGravity(Gravity.CENTER);
                                                        otis.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1r -> {
                                                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.otis,
                                                                    respon -> {
                                                                        try {
                                                                            jsonObject = new JSONObject(respon);
                                                                            int sta = jsonObject.getInt("success");
                                                                            String mess = jsonObject.getString("message");
                                                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                                            if (sta == 1) {
                                                                                startActivity(new Intent(getApplicationContext(), VideoDash.class));
                                                                                finish();
                                                                            }
                                                                        } catch (JSONException e) {
                                                                            e.printStackTrace();
                                                                            Toast.makeText(this, "Server Error!!", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }, error -> {
                                                                Toast.makeText(this, "Network Connection!!", Toast.LENGTH_SHORT).show();
                                                            }) {
                                                                @Nullable
                                                                @Override
                                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                                    Map<String, String> para = new HashMap<>();
                                                                    para.put("id", reviMode.getId());
                                                                    para.put("video", "Confirmed");
                                                                    para.put("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
                                                                    return para;
                                                                }
                                                            });
                                                        });
                                                        otis.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1r -> {
                                                            otis.cancel();
                                                        });
                                                    } else {
                                                        AlertDialog.Builder comb = new AlertDialog.Builder(this);
                                                        comb.setTitle("Failed!!!");
                                                        comb.setMessage("Hi " + userModel.getFname() + ",\nFor you to confirm; you must have uploaded at least two evidence photos\nKindly Click add Image button.");
                                                        AlertDialog key = comb.create();
                                                        key.show();
                                                        key.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                                        key.getWindow().setGravity(Gravity.TOP);
                                                        key.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Toast.makeText(this, "Server Error!!", Toast.LENGTH_SHORT).show();
                                                }
                                            }, error -> {
                                        Toast.makeText(this, "Network Connection!!", Toast.LENGTH_SHORT).show();
                                    }) {
                                        @Nullable
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> para = new HashMap<>();
                                            para.put("refere", reviMode.getEntry());
                                            para.put("cust_id", reviMode.getCust_id());
                                            return para;
                                        }
                                    });
                                });
                                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                    AlertDialog.Builder child = new AlertDialog.Builder(this);
                                    child.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + payerSe.getCategory() + "-" + payerSe.getType() + "</u></b></font>"));
                                    child.setMessage("Add some Image");
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
                                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.above,
                                                    res -> {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(res);
                                                            int Status = jsonObject.getInt("success");
                                                            String messo = jsonObject.getString("message");
                                                            if (Status == 1) {
                                                                Toast.makeText(this, messo, Toast.LENGTH_SHORT).show();
                                                                startActivity(new Intent(getApplicationContext(), VideoDash.class));
                                                                finish();
                                                            } else if (Status == 0) {
                                                                Toast.makeText(this, messo, Toast.LENGTH_SHORT).show();
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
                                                    params.put("refere", reviMode.getEntry());
                                                    params.put("cust_id", reviMode.getCust_id());
                                                    params.put("image", imagery);
                                                    params.put("category", payerSe.getCategory());
                                                    params.put("type", payerSe.getType());
                                                    params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
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
                            } else if (success == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("serid", reviMode.getEntry());
                    return para;
                }
            });
        }));
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
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.darker,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                reviMode = new ReviMode(jsonObject.getString("id"), jsonObject.getString("form"), jsonObject.getString("entry"), jsonObject.getString("driver_id"), jsonObject.getString("driver_name"),
                                        jsonObject.getString("driver_phone"), jsonObject.getString("drive"), jsonObject.getString("drive_date"),
                                        jsonObject.getString("video_id"), jsonObject.getString("video_name"), jsonObject.getString("video_phone"), jsonObject.getString("video"), jsonObject.getString("video_date"),
                                        jsonObject.getString("cust_id"), jsonObject.getString("cust_name"), jsonObject.getString("cust_phone"), jsonObject.getString("location"),
                                        jsonObject.getString("landmark"), jsonObject.getString("custom"), jsonObject.getString("custom_date"), jsonObject.getString("reg_date"));
                                bidModelArrayList.add(reviMode);
                            }
                            seeker = new Seeker(VideoDash.this, R.layout.marathon, bidModelArrayList);
                            listView.setAdapter(seeker);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    seeker.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            button.setVisibility(View.VISIBLE);
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("form", "9");
                para.put("video_id", userModel.getUser_id());
                para.put("video", "Pending");
                return para;
            }
        });
    }
}