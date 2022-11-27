package com.example.finalproject_gplx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThiTracNghiem extends AppCompatActivity {

    TextView tvQuestion;
    Button btnBack, btnNext;
    ImageButton btnHome;
    RecyclerView rvAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_trac_nghiem);

        AssignAddress();
    }

    public void AssignAddress(){
        //Nối với view

        tvQuestion = findViewById(R.id.tvQuestion); //Câu hỏi
        btnBack = findViewById(R.id.btnBack); //Nút trở về câu trước
        btnHome = findViewById(R.id.imaButtonHome); //Nút trở về nhà
        btnNext = findViewById(R.id.btnNext); //Nút câu hỏi kế tiếp
        rvAnswers = findViewById(R.id.recyclerViewAnswer);// recyler view chứa câu trả lời
    }
}