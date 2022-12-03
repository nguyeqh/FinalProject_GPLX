package com.example.finalproject_gplx.DanhSachDeThi;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Question;
import com.example.finalproject_gplx.model.Sign;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class KetQua extends AppCompatActivity {

        BD_Helper databaseHelper;
        TextView tvKetQua, tvDiem;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ketqua);
            Objects.requireNonNull(getSupportActionBar()).hide();
            tvKetQua = findViewById(R.id.textView2);
            tvDiem = findViewById(R.id.txtDiem);
            databaseHelper = new BD_Helper(KetQua.this);
            try {
                databaseHelper.createDatabase();

            } catch (IOException e) {
                throw new Error("Unable to create database");
            }

            databaseHelper.openDatabase();

            List<Question> listQuestion =  databaseHelper.getAllQuestion();
            List<Sign> listSign =  databaseHelper.getAllSign();
            //Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();

        }
}
