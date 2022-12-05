package com.example.finalproject_gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.example.finalproject_gplx.DanhSachDeThi.ExamActivity;

import com.example.finalproject_gplx.DanhSachDeThi.Thi;
import com.example.finalproject_gplx.DanhSachDeThi.ThiTracNghiem;
import com.example.finalproject_gplx.HocLyThuyet.ActivityQuestion;
import com.example.finalproject_gplx.OnTap.HocBienBao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView btnHocBienBao,btnThiHachSach,btnOnLyThuyet;
    DatePicker datePicker;
    TextView tvDate, lbTimeRemain, lbTienDo;
    Button btnOKDate;
    ProgressBar pbOnTap, pbThiHachSach;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    int savedQuestion = 1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String endDateKey = "endDateKey";
    public static final String tiendoOnKey = "tiendoonKey";
    public static final int ACTIVITY_THI_TRAC_NGHIEM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String endDate = sharedpreferences.getString(endDateKey, null);
        savedQuestion =  sharedpreferences.getInt(tiendoOnKey, 1);
        try{
            pbOnTap.setProgress(savedQuestion);
            String socau = savedQuestion + "/600";
            lbTienDo.setText(socau);
        } catch (NullPointerException e){

        }

        AssignAddress();
        setTimeRemain(endDate);
        buttonClickManager();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==ACTIVITY_THI_TRAC_NGHIEM)
        {
            savedQuestion = data.getIntExtra("cauhoi", 1);
            pbOnTap.setProgress(savedQuestion);
            String socau = savedQuestion + "/600";
            lbTienDo.setText(socau);

            editor = sharedpreferences.edit();
            editor.putInt(tiendoOnKey, savedQuestion);
            editor.apply();
        }
    }






    private void AssignAddress(){
        btnHocBienBao = findViewById(R.id.btnHocBienBao);
        btnThiHachSach = findViewById(R.id.btnThiHachSach);
        btnOnLyThuyet = findViewById(R.id.btnOnLyThuyet);
        datePicker = findViewById(R.id.datePicker);
        tvDate = findViewById(R.id.tvDate);
        btnOKDate = findViewById(R.id.btnOkDate);
        lbTimeRemain = findViewById(R.id.labelTimeRemain2);
        pbOnTap = findViewById(R.id.progressBaiOnTap);
        pbThiHachSach = findViewById(R.id.progressBaiThi);
        pbOnTap.setMax(600);
        lbTienDo = findViewById(R.id.tvTientrinhOnThi);
    }

    private void setTimeRemain(String endDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH) +1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String currentDate = day + "-" + month + "-" + year;

        if (endDate != null){
            DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date date1, date2;
                String startDate = currentDate;

                date1 = simpleDateFormat.parse(startDate);
                date2 = simpleDateFormat.parse(endDate);

                long getDiff = date2.getTime() - date1.getTime();

                long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);

                String calTime =getDaysDiff + " ngày";
                lbTimeRemain.setText(calTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lbTimeRemain.setText("Bạn chưa set ngày");
        }






    }

    private void buttonClickManager(){
        btnHocBienBao.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HocBienBao.class);
            //Intent intent = new Intent(MainActivity.this, Thi.class);
            //intent.putExtra("de_thi","1");
            startActivity(intent);
        });

        btnThiHachSach.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExamActivity.class);
            startActivity(intent);
        });
        btnOnLyThuyet.setOnClickListener(v -> {
            Intent intent = new Intent( MainActivity.this, ThiTracNghiem.class);
            intent.putExtra("cauhoi", savedQuestion);
            startActivityForResult(intent,ACTIVITY_THI_TRAC_NGHIEM);
        });


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                btnOKDate.setVisibility(View.VISIBLE);
            }
        });

        lbTimeRemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                btnOKDate.setVisibility(View.VISIBLE);
            }
        });

        btnOKDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.INVISIBLE);
                btnOKDate.setVisibility(View.INVISIBLE);
                String current = getCurrentDate();
                tvDate.setText(current);
                setTimeRemain(current);

                editor = sharedpreferences.edit();
                editor.putString(endDateKey, current);
                editor.apply();
            }
        });


    }


    private String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(datePicker.getDayOfMonth()+"-");
        builder.append((datePicker.getMonth())+ +1 + "-");//month is 0 based
        builder.append(datePicker.getYear());
        return builder.toString();
    }

}