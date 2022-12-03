package com.example.finalproject_gplx.OnTap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Sign;

import java.util.ArrayList;
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


        //List<Sign> listBienBaoCam =  databaseHelper.getAllSign();


//        StudyTrafficSignAdapter arrayAdapterBBCam = new StudyTrafficSignAdapter(this, listBienBaoCam);
//        this.rvBienBaoCam.setAdapter(arrayAdapterBBCam);
//
        initListViewData();
    }

    private void initListViewData() {
        databaseHelper = new BD_Helper(HocBienBao.this);
        try {
            databaseHelper.createDatabase();
        } catch (Exception e) {

        }
        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {

        }


        List<Sign> listSign =  databaseHelper.getAllSign();
        List<Sign> listBienBaoCam =  new ArrayList<>();
        List<Sign> listBienBaoNguyHiem =  new ArrayList<>();
        List<Sign> listBienHieuLenh =  new ArrayList<>();
        for (Sign sign : listSign) {
            if (sign.getType().contains("cấm")) {
                listBienBaoCam.add(sign);
            } else if (sign.getType().contains("nguy hiểm")) {
                listBienBaoNguyHiem.add(sign);
            } else if (sign.getType().contains("hiệu lệnh")) {
                listBienHieuLenh.add(sign);
            }
        }
        StudyTrafficSignAdapter arrayAdapterBBCam = new StudyTrafficSignAdapter(this, listBienBaoCam);
        this.rvBienBaoCam.setAdapter(arrayAdapterBBCam);
        this.rvBienBaoCam.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        StudyTrafficSignAdapter arrayAdapterBBNguyHiem = new StudyTrafficSignAdapter(this, listBienBaoNguyHiem);
        this.rvBienBaoNguyHiem.setAdapter(arrayAdapterBBNguyHiem);
        //this.rvBienBaoNguyHiem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        StudyTrafficSignAdapter arrayAdapterBBHieuLenh = new StudyTrafficSignAdapter(this, listBienHieuLenh);
        this.rvBienBaoChiDan.setAdapter(arrayAdapterBBHieuLenh);
        //this.rvBienBaoChiDan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public static final String TAG = "ListViewExample";




}