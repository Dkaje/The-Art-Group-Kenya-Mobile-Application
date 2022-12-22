package com.example.artgroup.Sup;

import android.Manifest;
import android.app.Dialog;
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
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.artgroup.MainActivity;
import com.example.artgroup.R;
import com.example.artgroup.midfield.SupDash;
import com.example.artgroup.models.BidAda;
import com.example.artgroup.models.BidModel;
import com.example.artgroup.models.SuppSess;
import com.example.artgroup.models.UserModel;
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

public class JustMile extends AppCompatActivity {
    SuppSess custSess;
    UserModel userModel;
    ImageView imageView, profile, Cartel;
    AlertDialog.Builder builder;
    Dialog dialog;
    AlertDialog alertDialog;
    View layer, samoa, begger;
    Rect rect;
    Window window;
    EditText price, description;
    LayoutInflater layoutInflater, inflater;
    RequestQueue requestQueue;
    BidModel bidModel;
    ArrayList<BidModel> bidModelArrayList = new ArrayList<>();
    BidAda bidAda;
    ListView listView;
    SearchView searchView;
    TextView textView, texter;
    Toast toast;
    FrameLayout.LayoutParams params;
    FrameLayout frameLayout;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String imagery, form;
    Bitmap bitmap;
    BottomNavigationView bottomNavigationItemView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        custSess = new SuppSess(getApplicationContext());
        userModel = custSess.getUserDetails();
        setContentView(R.layout.activity_just_mile);
        if (getIntent() != null) {
            form = getIntent().getStringExtra("classification");
        }
        button = findViewById(R.id.addNEw);
        button.setText(form);
        bottomNavigationItemView = findViewById(R.id.topper);
        bottomNavigationItemView.setSelectedItemId(R.id.home);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(), PayHistory.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.noti:
                    startActivity(new Intent(getApplicationContext(), History.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.chat:
                    startActivity(new Intent(getApplicationContext(), Communicate.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), SupDash.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        imageView = findViewById(R.id.arrowBack);
        texter = findViewById(R.id.text);
        profile = findViewById(R.id.myProfile);
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
        texter.setText("Welcome " + userModel.getFname());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            bidModel = (BidModel) parent.getItemAtPosition(position);
            builder = new AlertDialog.Builder(this, R.style.Arap);
            builder.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>Upload Product</u></b></font>"));
            builder.setMessage(Html.fromHtml("<font><b color='#000000'>" + bidModel.getClassification() + "<br>" + bidModel.getCategory() + "</b><br>Type: " + bidModel.getType() + "<br>Quantity: " + bidModel.getQuantity() + "<font>"));
            builder.setPositiveButton("Upload", (dd, d) -> {
            });
            builder.setNegativeButton("Close", (dd, d) -> {
            });
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.metal);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.Arap);
                alert.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + bidModel.getClassification() + "-" + bidModel.getCategory() + "</u></b></font>"));
                alert.setMessage("Type: " + bidModel.getType() + "\nQuantity Required: " + bidModel.getQuantity());
                final EditText editText = new EditText(this);
                editText.setHint("Your supply Quantity");
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                editText.setLayoutParams(params);
                frameLayout.addView(editText);
                alert.setView(frameLayout);
                alert.setPositiveButton("Next", (dd, d) -> {
                });
                alert.setNegativeButton("Close", (dd, d) -> {
                });
                AlertDialog dail = alert.create();
                dail.setCancelable(false);
                dail.setCanceledOnTouchOutside(false);
                dail.show();
                dail.getWindow().setGravity(Gravity.CENTER);
                dail.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dail.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                dail.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view9 -> {
                    dail.cancel();
                });
                dail.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view9 -> {
                    final String myQty = editText.getText().toString().trim();
                    if (myQty.isEmpty()) {
                        editText.requestFocus();
                        textView.setText("Quantity Required");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myQty) < 1) {
                        editText.requestFocus();
                        textView.setText("Low Quantity");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Float.parseFloat(myQty) > Float.parseFloat(bidModel.getQuantity())) {
                        editText.requestFocus();
                        editText.setError("Only " + bidModel.getQuantity());
                        textView.setText("Quantity Beyond Requirement");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        AlertDialog.Builder man = new AlertDialog.Builder(this, R.style.Arap);
                        man.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + bidModel.getClassification() + " - " + bidModel.getCategory() + "</u></b></font>"));
                        man.setMessage(Html.fromHtml("<font color='#7107DA'><u>How much is your Price per quantity?</u></font>"));
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        samoa = layoutInflater.inflate(R.layout.internal, null);
                        samoa.setMinimumWidth((int) (rect.width() * 1.0));
                        samoa.setMinimumHeight((int) (rect.height() * 0.01));
                        price = samoa.findViewById(R.id.newQty);
                        description = samoa.findViewById(R.id.edtDesc);
                        frameLayout = new FrameLayout(this);
                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.padding);
                        samoa.setLayoutParams(params);
                        frameLayout.addView(samoa);
                        man.setView(frameLayout);
                        man.setPositiveButton("Next", (dd, d) -> {
                        });
                        man.setNegativeButton("Close", (dd, d) -> {
                        });
                        AlertDialog woman = man.create();
                        woman.setCancelable(false);
                        woman.setCanceledOnTouchOutside(false);
                        woman.show();
                        woman.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        woman.getWindow().setBackgroundDrawableResource(R.drawable.metal);
                        woman.getWindow().setGravity(Gravity.CENTER);
                        woman.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(views -> {
                            woman.cancel();
                        });
                        woman.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(views -> {
                            final String mPrice = price.getText().toString().trim();
                            final String mDesc = description.getText().toString().trim();
                            if (mPrice.isEmpty()) {
                                price.requestFocus();
                                textView.setText("Price Tag required");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            } else if (Float.parseFloat(mPrice) < 1) {
                                price.requestFocus();
                                textView.setText("Cannot be zero");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            } else if (mDesc.isEmpty()) {
                                description.requestFocus();
                                textView.setText("Describe the Product");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                AlertDialog.Builder child = new AlertDialog.Builder(this, R.style.Arap);
                                child.setTitle(Html.fromHtml("<font color='#7107DA'><b><u>" + bidModel.getCategory() + "</u></b></font>"));
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
                                        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.add_pr,
                                                response -> {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        int Status = jsonObject.getInt("success");
                                                        String mess = jsonObject.getString("message");
                                                        if (Status == 1) {
                                                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(getApplicationContext(), SupDash.class));
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
                                                params.put("quantity", myQty);
                                                params.put("qty", String.format("%.0f", (Float.parseFloat(bidModel.getQuantity()) - Float.parseFloat(myQty))));
                                                params.put("category", bidModel.getCategory());
                                                params.put("classification", form);
                                                params.put("type", bidModel.getType());
                                                params.put("bid", bidModel.getBid());
                                                params.put("price", mPrice);
                                                params.put("description", mDesc);
                                                params.put("image", imagery);
                                                params.put("supplier", userModel.getUser_id());
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
                            }
                        });
                    }
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
        requestQueue.add(new StringRequest(Request.Method.POST, ModelUrl.viewAva,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String bid = jsonObject.getString("bid");
                                String classification = jsonObject.getString("classification");
                                String category = jsonObject.getString("category");
                                String type = jsonObject.getString("type");
                                String qty = jsonObject.getString("qty");
                                String quantity = jsonObject.getString("quantity");
                                String entry_date = jsonObject.getString("entry_date");
                                String update_date = jsonObject.getString("update_date");
                                bidModel = new BidModel(bid, classification, category, type, qty, quantity, entry_date, update_date);
                                bidModelArrayList.add(bidModel);
                            }
                            bidAda = new BidAda(JustMile.this, R.layout.marathon, bidModelArrayList);
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

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("classification", form);
                return params;
            }
        });
    }
}