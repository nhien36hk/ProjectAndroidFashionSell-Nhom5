package com.example.sellproject.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.sellproject.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_CORE = 22;
    Button btnCamera;
    ImageView imgView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgVCam;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btnCamera = findViewById(R.id.btnCamera);
        imgView = findViewById(R.id.imgView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CORE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // RESULT_OK là hằng số = 01, đinh nghĩa hoàn thành
        if(requestCode == REQUEST_CORE && resultCode == RESULT_OK){
//            Lấy hình ảnh trả về và ép kiểu Bitmap(dùng để lưu hình ảnh)
            Bitmap photo =(Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(photo);
        }else{
            Toast.makeText(this, "Cancelled ", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
