package com.example.finalproject_gplx.DanhSachDeThi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.HocLyThuyet.QuestionAdapter;
import com.example.finalproject_gplx.OnLyThuyet;
import com.example.finalproject_gplx.OnTap.StudyTrafficSignAdapter;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Question;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ThiTracNghiem extends AppCompatActivity {

    TextView tvQuestion;
    ImageView imageQuestion;
    RecyclerView recyclerViewAnswer;
    TextView tvAnsDung;
    BD_Helper databaseHelper;
    Button btnNext, btnPre;
    int cauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_trac_nghiem);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = this.getIntent();
        cauhoi = intent.getIntExtra("cauhoi", 1);

        tvQuestion = findViewById(R.id.tvQuestion);
        imageQuestion = findViewById(R.id.imageViewQuestion);
        recyclerViewAnswer = findViewById(R.id.recyclerViewAnswer);
        tvAnsDung = findViewById(R.id.textView2);
        tvAnsDung.setVisibility(View.INVISIBLE);
        databaseHelper = new BD_Helper(ThiTracNghiem.this);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnBack);
        try {
            databaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }


        setCauHoi(cauhoi);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cauhoi < 600) {
                    cauhoi = cauhoi + 1;
                    setCauHoi(cauhoi);
                }
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cauhoi > 2){
                    cauhoi = cauhoi - 1;
                    setCauHoi(cauhoi);
                }
            }
        });







    }

    private void setCauHoi(int cauhoi){
        Question ques = databaseHelper.getQuestionById(cauhoi);

        List<Answer> ansList = databaseHelper.getAnswersByQuestionId(cauhoi);


        tvQuestion.setText(ques.getContent());
        String mDrawableName = ques.getImage();
        if (mDrawableName != null){
            mDrawableName = mDrawableName.substring(0, mDrawableName.length() - 5);
            String uri = "@drawable/" + mDrawableName;  // where myresource (without the extension) is the file
            int imageResource = getApplicationContext().getResources().getIdentifier(uri, null, getApplicationContext().getPackageName());
            imageQuestion.setImageResource(imageResource);
        } else{

            ViewGroup.LayoutParams param = imageQuestion.getLayoutParams();
            param.height = 0;
            imageQuestion.setLayoutParams(param);
            //imageQuestion.setVisibility(View.INVISIBLE);
        }
        AnswerAdapter answerAdapter = new AnswerAdapter(this, ansList);
        this.recyclerViewAnswer.setAdapter(answerAdapter);
        this.recyclerViewAnswer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}