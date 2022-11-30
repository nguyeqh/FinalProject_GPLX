package com.example.finalproject_gplx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;

import java.util.Objects;

public class HocBienBao extends AppCompatActivity {
    RecyclerView rvBienBaoCam, rvBienBaoNguyHiem, rvBienBaoChiDan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_bien_bao);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rvBienBaoCam = findViewById(R.id.recyclerViewBienBaoCam);
        rvBienBaoChiDan = findViewById(R.id.recyclerViewBienBaoHieuLenh);
        rvBienBaoNguyHiem = findViewById(R.id.recyclerViewBienBaoNguyHiem);

        initListViewData();
    }

    public static final String TAG = "ListViewExample";

    private void initListViewData()  {

    }



}