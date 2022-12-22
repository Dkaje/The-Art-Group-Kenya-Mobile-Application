package com.example.artgroup.vide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.VideoDash;
import com.example.artgroup.models.GreatMes;
import com.example.artgroup.models.GreatMidAda;
import com.example.artgroup.models.UserModel;
import com.example.artgroup.models.VideoSess;
import com.example.artgroup.models.WadaAda;
import com.example.artgroup.models.Wadawida;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideMEss extends AppCompatActivity {
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog, otis;
    View samoa;
    BottomNavigationView botaz;
    ImageView imageView, profile;
    TextView textView, head;
    VideoSess custSess;
    UserModel userModel;
    SearchView searchView;
    ListView listView;
    EditText editText;
    Spinner spinner;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RequestQueue requestQueue;
    ArrayList<Wadawida> wadawidaArrayList = new ArrayList<>();
    Wadawida wadawida;
    WadaAda wadaAda;
    List<GreatMes> greatMesArrayList = new ArrayList<>();
    GreatMes greatMes;
    GreatMidAda greatMidAda;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vide_mess);
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
        botaz = findViewById(R.id.topper);
        botaz.setSelectedItemId(R.id.chat);
        botaz.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.chat:
                    return true;
                case R.id.att:
                    startActivity(new Intent(getApplicationContext(), HistVide.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Ord:
                    startActivity(new Intent(getApplicationContext(), VideoDash.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        listView = findViewById(R.id.listing);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.Searcher);
        getto();
        textView.setText("Welcome " + userModel.getFname());
        findViewById(R.id.btnRoster).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Rate Us");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            samoa = layoutInflater.inflate(R.layout.compose, null);
            editText = samoa.findViewById(R.id.messo);
            spinner = samoa.findViewById(R.id.spinRec);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Vid, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            samoa.setLayoutParams(params);
            frameLayout.addView(samoa);
            builder.setView(frameLayout);
            alertDialog = builder.create();
            alertDialog.show();
            samoa.findViewById(R.id.btnExit).setOnClickListener(view1 -> {
                startActivity(new Intent(getApplicationContext(), VideMEss.class));
                finish();
            });
            samoa.findViewById(R.id.btnSend).setOnClickListener(view1 -> {
                final String mess = editText.getText().toString().trim();
                final String sele = spinner.getSelectedItem().toString().trim();
                if (sele.equals("Pick Receiver")) {
                    Toast.makeText(this, "no receiver", Toast.LENGTH_SHORT).show();
                } else if (mess.isEmpty()) {
                    editText.setError("what are you sending");
                    editText.requestFocus();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.tumaSaa,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), VideMEss.class));
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
                            para.put("sen", "Videographer");
                            para.put("senid", userModel.getUser_id());
                            para.put("message", mess);
                            para.put("rec", sele);
                            para.put("name", userModel.getFname());
                            para.put("phone", userModel.getPhone());
                            para.put("timing", new SimpleDateFormat("hh:mma").format(new Date()));
                            para.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                            para.put("dater", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                            para.put("meme", "K");
                            return para;
                        }
                    });
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            wadawida = (Wadawida) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle(wadawida.getPhone());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            samoa = layoutInflater.inflate(R.layout.rada_bana, null);
            editText = samoa.findViewById(R.id.meesa);
            recyclerView = samoa.findViewById(R.id.recycle);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(layoutManager);
            getMess();
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
            samoa.setLayoutParams(params);
            frameLayout.addView(samoa);
            builder.setView(frameLayout);
            alertDialog = builder.create();
            alertDialog.show();
            samoa.findViewById(R.id.btnExit).setOnClickListener(view1 -> {
                startActivity(new Intent(getApplicationContext(), VideMEss.class));
                finish();
            });
            samoa.findViewById(R.id.btnSend).setOnClickListener(view1 -> {
                final String mess = editText.getText().toString().trim();
                if (mess.isEmpty()) {
                    editText.setError("what are you sending");
                    editText.requestFocus();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.tumaSaa,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        editText.setText("");
                                        getMess();
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
                            para.put("sen", "Videographer");
                            para.put("senid", wadawida.getSenid());
                            para.put("message", mess);
                            para.put("rec", wadawida.getRec());
                            para.put("name", wadawida.getName());
                            para.put("phone", wadawida.getPhone());
                            para.put("timing", new SimpleDateFormat("hh:mma").format(new Date()));
                            para.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                            para.put("dater", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                            para.put("meme", "K");
                            return para;
                        }
                    });
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        });
    }

    private void getMess() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.veveMess,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("success");
                        if (status == 1) {
                            greatMesArrayList.clear();
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                greatMes = new GreatMes(jsonObject.getString("sen"), jsonObject.getString("senid"), jsonObject.getString("phone"),
                                        jsonObject.getString("name"), jsonObject.getString("rec"), jsonObject.getString("message"),
                                        jsonObject.getString("timing"), jsonObject.getString("date"), jsonObject.getString("temple"),
                                        jsonObject.getString("temple2"), jsonObject.getString("meme"));
                                greatMesArrayList.add(greatMes);
                            }
                            greatMidAda = new GreatMidAda(getApplicationContext(), greatMesArrayList);
                            recyclerView.setAdapter(greatMidAda);
                            recyclerView.scrollToPosition(greatMesArrayList.size() - 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<>();
                para.put("senid", wadawida.getSenid());
                para.put("rec", wadawida.getRec());
                return para;
            }
        });
    }

    private void getto() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.kasumba,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                wadawida = new Wadawida(jsonObject.getString("rec"), jsonObject.getString("senid"), jsonObject.getString("count"),
                                        jsonObject.getString("phone"), jsonObject.getString("name"), jsonObject.getString("message"),
                                        jsonObject.getString("date"), jsonObject.getString("timing"),
                                        jsonObject.getString("temple"));
                                wadawidaArrayList.add(wadawida);
                            }
                            wadaAda = new WadaAda(VideMEss.this, R.layout.okay, wadawidaArrayList);
                            listView.setAdapter(wadaAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    wadaAda.getFilter().filter(newText);
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
                para.put("senid", userModel.getUser_id());
                return para;
            }
        });
    }
}