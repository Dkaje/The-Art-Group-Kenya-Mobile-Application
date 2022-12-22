package com.example.artgroup.midfield;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.models.InveSess;
import com.example.artgroup.models.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InveLog extends AppCompatActivity {
    EditText username, password;
    ImageView viewer, mimi;
    TextView txtForgot, txtRegist;
    Button btn;
    RequestQueue requestQueue;
    InveSess custSess;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inve_log);
        username = findViewById(R.id.edtUser);
        password = findViewById(R.id.edtPass);
        viewer = findViewById(R.id.viewPa);
        mimi = findViewById(R.id.arrowBack);
        btn = findViewById(R.id.btnLog);
        txtForgot = findViewById(R.id.forgot);
        txtRegist = findViewById(R.id.register);
        custSess=new InveSess(getApplicationContext());
        userModel=custSess.getUserDetails();
        mimi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        txtRegist.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InveReg.class));
            finish();
        });
        txtForgot.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InveReset.class));
            finish();
        });
        viewer.setOnClickListener(view -> {
            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                viewer.setImageResource(R.drawable.hidden);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                viewer.setImageResource(R.drawable.visible);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        btn.setOnClickListener(v -> {
            final String mUSer = username.getText().toString().trim();
            final String mPas = password.getText().toString().trim();
            if (mUSer.isEmpty()) {
                Toast.makeText(this, "Username required", Toast.LENGTH_SHORT).show();
            } else if (mPas.isEmpty()) {
                Toast.makeText(this, "Password required", Toast.LENGTH_SHORT).show();
            } else {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.log,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
                                        custSess.loginUser(user_id, fname, lname, username, email, phone, county, reg_date);
                                        startActivity(new Intent(getApplicationContext(), InveDash.class));
                                        finish();
                                    }
                                } else if (st == 2) {
                                    JSONArray jsonArray = jsonObject.getJSONArray("log");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        String reg = object.getString("comment");
                                        Toast.makeText(this, reg, Toast.LENGTH_SHORT).show();
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
                        para.put("username", mUSer);
                        para.put("password", mPas);
                        return para;
                    }
                });
            }
        });
    }
}