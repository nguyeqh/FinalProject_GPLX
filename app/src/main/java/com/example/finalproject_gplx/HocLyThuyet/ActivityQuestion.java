package com.example.finalproject_gplx.HocLyThuyet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Question;
import com.example.finalproject_gplx.model.Sign;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityQuestion extends AppCompatActivity {
    RecyclerView recyclerView;
    BD_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_cau_hoi);
        Objects.requireNonNull(getSupportActionBar()).hide();
        recyclerView = findViewById(R.id.list_cau_hoi);
        initDatabase();
    }

    private void initDatabase() {
        db=new BD_Helper(ActivityQuestion.this);

        try {
            db.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            db.createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Question> listQuestion = db.getAllQuestion();

        List<Answer> as=db.getAnswersByQuestionId(1);
        for (Question question : listQuestion) {
            List<Answer> listAnswerByQuestionId = db.getAnswersByQuestionId(question.getId());
            question.setAnswer(listAnswerByQuestionId);
        }
        QuestionAdapter adapter = new QuestionAdapter(this, listQuestion);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


}
