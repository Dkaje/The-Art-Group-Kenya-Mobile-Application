package com.example.artgroup.vide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.PathUtils;

import com.example.artgroup.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiUpload extends AppCompatActivity {
    Button btnSelec, btnSub;
    ListView listView;
    List<Uri> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_upload);
        btnSelec = findViewById(R.id.Selector);
        btnSub = findViewById(R.id.submiter);
        listView = findViewById(R.id.listerStress);
        btnSelec.setOnClickListener(view -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1:
                    if (requestCode == RESULT_OK && data != null) {
                        Uri image = data.getData();
                       // String imagePath= PathUtils.getPath(this,image);
                        //String imagePath= FileUtils
                        Uri uri = data.getData();
                        File file = new File(uri.getPath());//create path from uri
                    }
            }
        }
    }
}