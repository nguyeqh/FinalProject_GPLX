package com.example.finalproject_gplx.DanhSachDeThi;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Exam;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ExamActivity extends AppCompatActivity {
    List<Exam> examList;
    Button btnCreate;
    RecyclerView rvExam;
    BD_Helper databaseHelper;
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);
        Objects.requireNonNull(getSupportActionBar()).hide();



        rvExam = findViewById(R.id.rv_list_de_thi);



        btnCreate = findViewById(R.id.btn_create_exam);
        //createDatabase();
        databaseHelper = new BD_Helper(this);

        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        examList = databaseHelper.getAllExam();

        ExamAdapter eadapter = new ExamAdapter(this, examList);
        this.rvExam.setAdapter(eadapter);
        this.rvExam.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnCreate.setOnClickListener(v -> {
            examList.clear();
            createExam();
            examList = databaseHelper.getAllExam();

        });

    }

    private void createExam() {
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
        databaseHelper.addExam(exam);

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
