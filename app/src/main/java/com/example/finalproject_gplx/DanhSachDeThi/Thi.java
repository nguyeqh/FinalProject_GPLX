package com.example.finalproject_gplx.DanhSachDeThi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.MainActivity;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Exam;
import com.example.finalproject_gplx.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Thi extends AppCompatActivity {
    private BD_Helper databaseHelper;
    private TextView txtTime,txtDe_thi,txtTien_do;
    private Button btn_submit;
    private List<Question> questionList;
    private Exam exam;
    private RecyclerView rvQuestion;
    private Handler handler;
    ProgressBar pbTienDo;
    int id_exam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseHelper = new BD_Helper(Thi.this);
        try {
            databaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTime = findViewById(R.id.txtTime);
        txtDe_thi = findViewById(R.id.txtDe_thi);
        txtTien_do = findViewById(R.id.txtTien_do);
        btn_submit = findViewById(R.id.btn_submit);
        pbTienDo = findViewById(R.id.seekBar);
        rvQuestion = findViewById(R.id.rvExam);

        List<Exam> examList = databaseHelper.getAllExam();
        id_exam = Integer.parseInt(getIntent().getStringExtra("de_thi"));


        for (Exam exam:examList){
            if (exam.getId()==id_exam){
                this.exam = exam;
            }
        }

        String question = exam.getList_ques();
        String[] list_question = question.split(",");

        List<Question> lst = new ArrayList<Question>();
        List<Question> lst2 = new ArrayList<Question>();
        List<Question> test = databaseHelper.getAllQuestion();
        for (int i = 0; i < list_question.length; i++){
            int quesID = Integer.parseInt(list_question[i]);
            Question q1 = databaseHelper.getQuestionById(quesID);
            lst2.add(q1);
        }

        for(int i = 0; i < lst2.size(); i++){
            Question question1 = lst2.get(i);
            List<Answer> answerList = databaseHelper.getAnswersByQuestionId(question1.getId());
            question1.setAnswer(answerList);

            lst.add(question1);
        }


        txtDe_thi.setText("Đề thi số: " + exam.getId());

        QuesAdapter examAdapter = new QuesAdapter(this,lst);
        String str = "Độ dài: " + examAdapter.getItemCount();
        txtTien_do.setText(str);
        rvQuestion.setAdapter(examAdapter);
        this.rvQuestion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        startTimer();

    }

    // count down timer for 30 minutes
    private void startTimer() {
        new CountDownTimer(1800000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText( millisUntilFinished / 60000 + ":" + (millisUntilFinished % 60000) / 1000);
            }

            @Override
            public void onFinish() {
                scrollToBottom();
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogResult();
                    }
                }, 1000);
                databaseHelper.updateExam(exam);
            }
        }.start();
    }

    //create dialog to show result
    private void dialogResult(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_result);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Button result = dialog.findViewById(R.id.btn_result);
        Button home = dialog.findViewById(R.id.btn_home);
        result.setOnClickListener(v -> {
            Intent intent = new Intent(Thi.this, KetQua.class);
            intent.putExtra("de_thi",exam.getId()+"");
            startActivity(intent);
            dialog.dismiss();
        });
        home.setOnClickListener(v -> {
            Intent intent = new Intent(Thi.this, MainActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });
    }


    //scroll to bottom
    private void scrollToBottom() {
        rvQuestion.post(new Runnable() {
            @Override
            public void run() {
                rvQuestion.smoothScrollToPosition(questionList.size()-1);
            }
        });
    }
}
