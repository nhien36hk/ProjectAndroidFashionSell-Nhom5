package com.example.sellproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sellproject.R;

public class ProfileActivity extends AppCompatActivity {
    private static final int REQUEST_CORE = 22;
    Button btnCamera;
    ImageView imgView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgVCam;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        imgView = findViewById(R.id.imageView2);
        btnCamera = findViewById(R.id.btn_camera);
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