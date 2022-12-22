package com.example.artgroup.midfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help extends AppCompatActivity {
    BottomNavigationView botaz, topaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        botaz = findViewById(R.id.bottom);
        botaz.setSelectedItemId(R.id.help);
        topaz = findViewById(R.id.topper);
        topaz.setSelectedItemId(R.id.about);
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
                    startActivity(new Intent(getApplicationContext(), CustLog.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.about:
                    startActivity(new Intent(getApplicationContext(), About.class));
                    return true;
                case R.id.homer:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.help:
                    return true;
                case R.id.sup:
                    startActivity(new Intent(getApplicationContext(), SupLog.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.drive:
                    startActivity(new Intent(getApplicationContext(), DrivLog.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.dashboard:
                    startActivity(new Intent(getApplicationContext(), VideoGrapher.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }
}