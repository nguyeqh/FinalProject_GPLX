package com.example.finalproject_gplx.HocLyThuyet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject_gplx.R;

import java.util.Objects;

public class ActivityQuestion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_cau_hoi);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
