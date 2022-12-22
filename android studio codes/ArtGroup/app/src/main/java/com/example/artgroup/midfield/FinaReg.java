package com.example.artgroup.midfield;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FinaReg extends AppCompatActivity {
    EditText username, password, confirm, fname, lname, email, phone;
    ImageView viewer, backer, vieconf;
    Button btn;
    RequestQueue requestQueue;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fina_reg);
        fname = findViewById(R.id.edtFname);
        lname = findViewById(R.id.edtLname);
        email = findViewById(R.id.edtMail);
        phone = findViewById(R.id.edtMobile);
        spinner = findViewById(R.id.spinCount);
        username = findViewById(R.id.edtUser);
        password = findViewById(R.id.edtPass);
        viewer = findViewById(R.id.viewPa);
        vieconf = findViewById(R.id.viewConfirm);
        btn = findViewById(R.id.btnLog);
        confirm = findViewById(R.id.edtconfirm);
        backer = findViewById(R.id.arrowBack);
        backer.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Finalog.class));
            finish();
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Finance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        viewer.setOnClickListener(view -> {
            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                viewer.setImageResource(R.drawable.hidden);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                viewer.setImageResource(R.drawable.visible);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        vieconf.setOnClickListener(view -> {
            if (confirm.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                vieconf.setImageResource(R.drawable.hidden);
                confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                vieconf.setImageResource(R.drawable.visible);
                confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        btn.setOnClickListener(v -> {
            final String mFname = fname.getText().toString().trim();
            final String mLname = lname.getText().toString().trim();
            final String mEmail = email.getText().toString().trim();
            final String mPhone = phone.getText().toString().trim();
            final String mSpin = spinner.getSelectedItem().toString().trim();
            final String mUser = username.getText().toString().trim();
            final String mpass = password.getText().toString().trim();
            final String mConf = confirm.getText().toString().trim();
            if (mFname.isEmpty()) {
                Toast.makeText(this, "Firstname?", Toast.LENGTH_SHORT).show();
            } else if (mLname.isEmpty()) {
                Toast.makeText(this, "Lastname?", Toast.LENGTH_SHORT).show();
            } else if (mEmail.isEmpty()) {
                Toast.makeText(this, "Email?", Toast.LENGTH_SHORT).show();
            } else if (!mEmail.matches(getString(R.string.valid_email)) & !mEmail.matches(getString(R.string.valid_email2))) {
                Toast.makeText(this, "Email format Error!!", Toast.LENGTH_SHORT).show();
            } else if (mPhone.isEmpty()) {
                Toast.makeText(this, "Phone?", Toast.LENGTH_SHORT).show();
            } else if (mPhone.length() < 10) {
                Toast.makeText(this, "Phone Error!!", Toast.LENGTH_SHORT).show();
            } else if (!mPhone.matches(getString(R.string.phoney)) & !mPhone.matches(getString(R.string.phone7))) {
                Toast.makeText(this, "Phone format Error!!", Toast.LENGTH_SHORT).show();
            }else if (mUser.isEmpty()) {
                Toast.makeText(this, "Username?", Toast.LENGTH_SHORT).show();
            } else if (mpass.isEmpty()) {
                Toast.makeText(this, "password?", Toast.LENGTH_SHORT).show();
            } else if (mpass.length() < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
            } else if (mConf.isEmpty()) {
                Toast.makeText(this, "Please retype password", Toast.LENGTH_SHORT).show();
            } else if (!mpass.equals(mConf)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.reg,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), Finalog.class));
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("username", mUser);
                        para.put("password", mpass);
                        para.put("fname", mFname);
                        para.put("lname", mLname);
                        para.put("email", mEmail);
                        para.put("county", mSpin);
                        para.put("phone", mPhone);
                        para.put("user", "s");
                        return para;
                    }
                });
            }
        });
    }
}