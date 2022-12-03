package com.example.finalproject_gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;



import com.example.finalproject_gplx.DanhSachDeThi.ExamActivity;

import com.example.finalproject_gplx.OnTap.HocBienBao;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView btnHocBienBao,btnThiHachSach,btnOnLyThuyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btnHocBienBao = findViewById(R.id.btnHocBienBao);
        btnThiHachSach = findViewById(R.id.btnThiHachSach);
        btnOnLyThuyet = findViewById(R.id.btnOnLyThuyet);

        btnHocBienBao.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HocBienBao.class);
            startActivity(intent);
        });
        btnThiHachSach.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExamActivity.class);
            startActivity(intent);
        });
        btnOnLyThuyet.setOnClickListener(v -> {
            Intent intent = new Intent( MainActivity.this, OnLyThuyet.class);
            startActivity(intent);
        });


    }

}