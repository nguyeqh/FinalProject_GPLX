package com.example.finalproject_gplx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.model.Sign;
import com.example.finalproject_gplx.model.StudyTrafficSignAdapter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class HocBienBao extends AppCompatActivity {
    RecyclerView rvBienBaoCam, rvBienBaoNguyHiem, rvBienBaoChiDan;
    BD_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_bien_bao);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rvBienBaoCam = findViewById(R.id.recyclerViewBienBaoCam);
        rvBienBaoChiDan = findViewById(R.id.recyclerViewBienBaoHieuLenh);
        rvBienBaoNguyHiem = findViewById(R.id.recyclerViewBienBaoNguyHiem);

        databaseHelper = new BD_Helper(HocBienBao.this);
        List<Sign> listBienBaoCam =  databaseHelper.getAllSign();

        StudyTrafficSignAdapter arrayAdapterBBCam = new StudyTrafficSignAdapter(this, listBienBaoCam);
        this.rvBienBaoCam.setAdapter(arrayAdapterBBCam);

        initListViewData();
    }

    public static final String TAG = "ListViewExample";

    private void initListViewData()  {

    }



}