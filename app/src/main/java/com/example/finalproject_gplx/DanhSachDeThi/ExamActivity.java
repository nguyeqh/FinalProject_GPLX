package com.example.finalproject_gplx.DanhSachDeThi;

import android.os.Bundle;
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

public class ExamActivity extends AppCompatActivity {
    private List<Exam> examList;
    private RecyclerView rvExam;
    private ExamAdapter examAdapter;
    private BD_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);
        Objects.requireNonNull(getSupportActionBar()).hide();
      rvExam = findViewById(R.id.list_cau_hoi);
       createExamList();
        if (examList.size() > 0) {
            examAdapter = new ExamAdapter(this, examList);
            rvExam.setAdapter(examAdapter);
            rvExam.setLayoutManager(new LinearLayoutManager(this));
        }else {
            Toast.makeText(this, "Không có đề thi nào", Toast.LENGTH_SHORT).show();
        }
//        examAdapter = new ExamAdapter(this, examList);
//        rvExam.setAdapter(examAdapter);
//        rvExam.setLayoutManager(new LinearLayoutManager(this));
    }
//
    private void createExamList(){
        databaseHelper = new BD_Helper(this);
        try {
            databaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            databaseHelper.openDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        examList = databaseHelper.getAllExam();
    }

}
