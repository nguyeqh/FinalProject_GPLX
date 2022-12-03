package com.example.finalproject_gplx.DanhSachDeThi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Exam;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ExamActivity extends AppCompatActivity {
    private List<Exam> examList;
    private Button btnCreate;
    private RecyclerView rvExam;
    private ExamAdapter examAdapter;
    private BD_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);
        Objects.requireNonNull(getSupportActionBar()).hide();
        rvExam = findViewById(R.id.list_cau_hoi);
        btnCreate = findViewById(R.id.btn_create_exam);
        //createDatabase();
        databaseHelper = new BD_Helper(this);

        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        examList = databaseHelper.getAllExam();
        if (examList.size() > 0) {
            examAdapter = new ExamAdapter(this, examList);
            rvExam.setAdapter(examAdapter);
            rvExam.setLayoutManager(new LinearLayoutManager(this));
        }else {
            Toast.makeText(this, "Không có đề thi nào", Toast.LENGTH_SHORT).show();
        }
        btnCreate.setOnClickListener(v -> {
            Exam exam = new Exam();
            exam=createExam();
            examList.add(exam);
            examAdapter.notifyDataSetChanged();
            Toast.makeText(this, exam.getList_ques(), Toast.LENGTH_SHORT).show();
        });

    }

    private Exam createExam() {
        Exam exam = new Exam();
        //random 25 number from 1 to 600
        Random random = new Random();
        int[] arr = new int[25];
        for (int i = 0; i < 25; i++) {
            arr[i] = random.nextInt(600) + 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--;
                    break;
                }
            }
        }
        String listQuestion = "";
        for (int i = 0; i < 25; i++) {
            listQuestion += arr[i] + ",";
        }
        //substring to remove last ","
        listQuestion = listQuestion.substring(0, listQuestion.length() - 1);
        exam.setList_ques(listQuestion);


        databaseHelper = new BD_Helper(this);
        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exam=databaseHelper.addExam(exam);
        return  exam;
    }

    private void createDatabase() {
        databaseHelper = new BD_Helper(this);

        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        examList = databaseHelper.getAllExam();
    }

}
