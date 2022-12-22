package com.example.artgroup.midfield;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.Uriah.Booker;
import com.example.artgroup.Uriah.Cart;
import com.example.artgroup.Uriah.CategoryYoo;
import com.example.artgroup.Uriah.Delivery;
import com.example.artgroup.Uriah.Messeging;
import com.example.artgroup.Uriah.MyReports;
import com.example.artgroup.Uriah.Payments;
import com.example.artgroup.Uriah.SendRequirement;
import com.example.artgroup.models.BadAda;
import com.example.artgroup.models.BadModel;
import com.example.artgroup.models.CateAda;
import com.example.artgroup.models.CateMode;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustDash extends AppCompatActivity {
    AlertDialog.Builder builder, alert;
    Dialog dialog;
    Toast toast;
    View samoa;
    BottomNavigationView botaz;
    CustSess custSess;
    UserModel userModel;
    ImageView imageView, profile;
    TextView textView;
    LayoutInflater inflater;
    RequestQueue requestQueue;
    VideoView videoView;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 4000;
    AudioManager audioManager;
    CardView cardView, require, repor;
    CateMode bidModel;
    ArrayList<CateMode> bidModelArrayList = new ArrayList<>();
    CateAda bidAda;
    ListView listView, locklist;
    SearchView searchView, lockSearch;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CircleImageView circleImageView;
    AlertDialog alertDialog, dailed;
    View layer, begger;
    Rect rect;
    Window window;
    EditText price, description, land, house;
    Spinner spinner;
    LayoutInflater layoutInflater;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    BadModel badModel;
    ArrayList<BadModel> badModelArrayList = new ArrayList<>();
    BadAda badAda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_cust_dash);
        imageView = findViewById(R.id.arrowBack);
        textView = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
        videoView = findViewById(R.id.myVideo);
        cardView = findViewById(R.id.venezuera);
        repor = findViewById(R.id.Report);
        require = findViewById(R.id.custom);
        circleImageView = findViewById(R.id.appSawa);
        circleImageView.setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.chagagaJusper,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    badModel = new BadModel(jsonObject.getString("id"), jsonObject.getString("cust_id"),
                                            jsonObject.getString("alert"), jsonObject.getString("reg_date"));
                                    badModelArrayList.add(badModel);
                                }
                                alert = new AlertDialog.Builder(this);
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                layer = layoutInflater.inflate(R.layout.notices, null);
                                layer.setMinimumWidth((int) (rect.width() * 1.0));
                                layer.setMinimumHeight((int) (rect.height() * 0.01));
                                locklist = layer.findViewById(R.id.majorList);
                                locklist.setTextFilterEnabled(true);
                                lockSearch = layer.findViewById(R.id.majorSerch);
                                frameLayout = new FrameLayout(this);
                                badAda = new BadAda(CustDash.this, R.layout.marathon, badModelArrayList);
                                locklist.setAdapter(badAda);
                                lockSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String text) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        badAda.getFilter().filter(newText);
                                        return false;
                                    }
                                });
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                                layer.setLayoutParams(params);
                                frameLayout.addView(layer);
                                alert.setView(frameLayout);
                                alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Custom Alert</u></b></font>"));
                                alert.setNeutralButton("Close", (dd, d) -> {
                                });
                                dailed = alert.create();
                                dailed.show();
                                dailed.setCancelable(false);
                                dailed.setCanceledOnTouchOutside(false);
                                dailed.getWindow().setGravity(Gravity.CENTER);
                                dailed.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                dailed.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                                dailed.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                    startActivity(new Intent(getApplicationContext(), CustDash.class));
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
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("cust_id", userModel.getUser_id());
                    return para;
                }
            });

        });
        repor.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MyReports.class));
        });
        require.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SendRequirement.class));
        });
        cardView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Booker.class));
        });
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.home);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), Cart.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), Payments.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Messeging.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.deliv:
                    startActivity(new Intent(getApplicationContext(), Delivery.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.art_group;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        audioManager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
        videoView.start();
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
        textView.setText("Welcome " + userModel.getFname());
        listView = findViewById(R.id.availableGrid);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        getSupp();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (CateMode) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, CategoryYoo.class);
            intent.putExtra("category", bidModel.getCategory());
            startActivity(intent);
        });
    }

    private void getSupp() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.getCater,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String category = jsonObject.getString("category");
                                String image = jsonObject.getString("image");
                                String imagery = ModelUrl.img + image;
                                bidModel = new CateMode(category, imagery);
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new CateAda(CustDash.this, R.layout.marathon, bidModelArrayList);
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

        }));
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onResume() {
        handler.postDelayed(runnable = () -> {
            handler.postDelayed(runnable, delay);
            //audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
            videoView.start();
        }, delay);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}