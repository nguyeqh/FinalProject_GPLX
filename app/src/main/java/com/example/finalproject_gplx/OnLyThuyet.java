package com.example.finalproject_gplx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.HocLyThuyet.QuestionAdapter;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnLyThuyet extends AppCompatActivity {

    RecyclerView rvLyThuyet;
    BD_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_trac_nghiem);
        Objects.requireNonNull(getSupportActionBar()).hide();
        rvLyThuyet = findViewById(R.id.list_cau_hoi);
        initListViewData();

    }

    private void initListViewData(){

        databaseHelper = new BD_Helper(OnLyThuyet.this);
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
        List<Question>  questionList = databaseHelper.getAllQuestion();
        List<Answer> answerList = databaseHelper.getAllAnswer();
        List<Answer> lst=new ArrayList<>();
        for (Question question : questionList){
            for (Answer answer : answerList){
                if (question.getId() == answer.getQues_id()||answer.isCheck()){
                    lst.add(answer);
                }
            }
            question.setAnswer(lst);
        }
        QuestionAdapter questionAdapter = new QuestionAdapter(this, questionList);
        rvLyThuyet.setAdapter(questionAdapter);
        rvLyThuyet.setLayoutManager(new LinearLayoutManager(this));
    }
}
