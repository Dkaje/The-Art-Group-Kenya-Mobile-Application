package com.example.artgroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.example.artgroup.midfield.About;
import com.example.artgroup.midfield.CustDash;
import com.example.artgroup.midfield.CustLog;
import com.example.artgroup.midfield.DesignDash;
import com.example.artgroup.midfield.DesignLog;
import com.example.artgroup.midfield.DrivDash;
import com.example.artgroup.midfield.DrivLog;
import com.example.artgroup.midfield.FinaDash;
import com.example.artgroup.midfield.Finalog;
import com.example.artgroup.midfield.Help;
import com.example.artgroup.midfield.InveDash;
import com.example.artgroup.midfield.InveLog;
import com.example.artgroup.midfield.ServMode;
import com.example.artgroup.midfield.ServeDash;
import com.example.artgroup.midfield.SupDash;
import com.example.artgroup.midfield.SupLog;
import com.example.artgroup.midfield.VideoDash;
import com.example.artgroup.midfield.VideoGrapher;
import com.example.artgroup.models.CustSess;
import com.example.artgroup.models.DrivSess;
import com.example.artgroup.models.FinaSess;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.ProjeSess;
import com.example.artgroup.models.SerSess;
import com.example.artgroup.models.SuppSess;
import com.example.artgroup.models.UserModel;
import com.example.artgroup.models.VideoSess;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    /*ViewPager viewPager;
    AdaptTrust adaptTrust;
    List<Trust> trusts;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();*/
    BottomNavigationView botaz, topaz, upper, lower;
    CustSess custSess;
    DrivSess drivSess;
    FinaSess finaSess;
    InveSess inveSess;
    ProjeSess projeSess;
    SuppSess suppSess;
    VideoSess videoSess;
    SerSess serSess;
    UserModel userModel;
    //RelativeLayout relativeLayout;
    //Button ship, stock;
    TextView textView, texter;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    LayoutInflater inflater;
    View layer;
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        //getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.kell));
        custSess = new CustSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        drivSess = new DrivSess(getApplicationContext());
        userModel = drivSess.getUserDetails();
        finaSess = new FinaSess(getApplicationContext());
        userModel = finaSess.getUserDetails();
        inveSess = new InveSess(getApplicationContext());
        userModel = inveSess.getUserDetails();
        projeSess = new ProjeSess(getApplicationContext());
        userModel = projeSess.getUserDetails();
        suppSess = new SuppSess(getApplicationContext());
        userModel = suppSess.getUserDetails();
        videoSess = new VideoSess(getApplicationContext());
        userModel = videoSess.getUserDetails();
        serSess = new SerSess(getApplicationContext());
        userModel = serSess.getUserDetails();
        // relativeLayout = findViewById(R.id.users);
        inflater = getLayoutInflater();
        layer = inflater.inflate(R.layout.toaster, null);
        textView = layer.findViewById(R.id.inform);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layer);
        /*ship = findViewById(R.id.Ship);
        stock = findViewById(R.id.Stok);
        stock.setOnClickListener(view -> {
            relativeLayout.setVisibility(View.GONE);
            if (inveSess.logged()) {
                startActivity(new Intent(getApplicationContext(), InveDash.class));
            } else {
                startActivity(new Intent(getApplicationContext(), InveLog.class));
            }
        });
        ship.setOnClickListener(view -> {
            relativeLayout.setVisibility(View.GONE);
            builder = new AlertDialog.Builder(this, R.style.CustomDialog);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Shipment Only!!</u></b></font>"));
            final EditText editText = new EditText(this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            //editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setHint("Enter Username");
            editText.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(50)});
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            editText.setLayoutParams(params);
            frameLayout.addView(editText);
            builder.setView(frameLayout);
            builder.setPositiveButton("Proceed", (v1, ve) -> {
            });
            builder.setNegativeButton("Close", (v1, ve) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewd -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewd -> {
                final String myUser = editText.getText().toString().trim();
                if (myUser.isEmpty()) {
                    editText.setError("??");
                    editText.requestFocus();
                    textView.setText("Username Required");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    AlertDialog.Builder build = new AlertDialog.Builder(this, R.style.CustomDialog);
                    build.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Shipment Only!!</u></b></font>"));
                    final EditText pass = new EditText(this);
                    pass.setInputType(InputType.TYPE_CLASS_TEXT);
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pass.setHint("Enter Password");
                    pass.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(10)});
                    frameLayout = new FrameLayout(this);
                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                    pass.setLayoutParams(params);
                    frameLayout.addView(pass);
                    build.setView(frameLayout);
                    build.setPositiveButton("Login", (v1, ve) -> {
                    });
                    build.setNegativeButton("Close", (v1, ve) -> {
                    });
                    AlertDialog diag = build.create();
                    diag.setCancelable(false);
                    diag.setCanceledOnTouchOutside(false);
                    diag.show();
                    diag.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    diag.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                    diag.getWindow().setGravity(Gravity.CENTER);
                    diag.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewde -> {
                        diag.cancel();
                    });
                    diag.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(viewde -> {
                        final String myPAss = pass.getText().toString().trim();
                        if (myPAss.isEmpty()) {
                            pass.setError("??");
                            pass.requestFocus();
                            textView.setText("Password Required");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (myPAss.length() < 8) {
                            pass.setError("??");
                            pass.requestFocus();
                            textView.setText("Your Password!!!");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.log,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("status");
                                            String msg = jsonObject.getString("message");
                                            textView.setText(msg);
                                            toast.setDuration(Toast.LENGTH_SHORT);
                                            toast.show();
                                            if (st == 1) {
                                                JSONArray jsonArray = jsonObject.getJSONArray("log");
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String user_id = object.getString("user_id");
                                                    String fname = object.getString("fname");
                                                    String lname = object.getString("lname");
                                                    String username = object.getString("username");
                                                    String email = object.getString("email");
                                                    String phone = object.getString("phone");
                                                    String county = object.getString("county");
                                                    String reg_date = object.getString("reg_date");
                                                    startActivity(new Intent(getApplicationContext(), Shipment.class));
                                                    finish();
                                                }
                                            } else if (st == 2) {
                                                JSONArray jsonArray = jsonObject.getJSONArray("log");
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String reg = object.getString("comment");
                                                    textView.setText(reg);
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.show();
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "connection issues", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    ;
                                    Map<String, String> para = new HashMap<>();
                                    para.put("user", "i");
                                    para.put("username", myUser);
                                    para.put("password", myPAss);
                                    return para;
                                }
                            });
                        }
                    });
                }
            });
        });
        trusts = new ArrayList<>();
        trusts.add(new Trust(R.drawable.tell, getString(R.string.About)));
        trusts.add(new Trust(R.drawable.ask, getString(R.string.Help)));
        trusts.add(new Trust(R.drawable.finance, "Five people who will never government"));
        adaptTrust = new AdaptTrust(trusts, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adaptTrust);
        viewPager.setPadding(20, 10, 20, 10);
        Integer[] colors = {getResources().getColor(R.color.prayer), getResources().getColor(R.color.mfalme)};
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adaptTrust.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position] + 1));
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        botaz = findViewById(R.id.bottom);
        botaz.setSelectedItemId(R.id.dashboard);
        topaz = findViewById(R.id.topper);
        topaz.setSelectedItemId(R.id.homer);
        upper = findViewById(R.id.upperPart);
        upper.setSelectedItemId(R.id.inven);
        lower = findViewById(R.id.bottomUp);
        lower.setSelectedItemId(R.id.designer);
        topaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.quit:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Are you sure you want to quit app??");
                    builder.setPositiveButton("Yes", (oo, o) -> {
                        finishAffinity();
                    });
                    builder.setNegativeButton("no", (oo, o) -> oo.cancel());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
                    alertDialog.show();
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.cust:
                    if (custSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), CustDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), CustLog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.about:
                    startActivity(new Intent(getApplicationContext(), About.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.homer:
                    return true;
            }
            return false;
        });
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.help:
                    startActivity(new Intent(getApplicationContext(), Help.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.sup:
                    if (suppSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), SupDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), SupLog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.drive:
                    if (drivSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), DrivDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), DrivLog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.dashboard:
                    if (videoSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), VideoDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), VideoGrapher.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        upper.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.fina:
                    if (finaSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), FinaDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), Finalog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.inven:
                    if (inveSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), InveDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), InveLog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        lower.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.videoG:
                    if (serSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), ServeDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), ServMode.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.designer:
                    if (projeSess.logged()) {
                        startActivity(new Intent(getApplicationContext(), DesignDash.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), DesignLog.class));
                    }
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }
}