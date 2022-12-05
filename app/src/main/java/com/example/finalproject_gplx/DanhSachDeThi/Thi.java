package com.example.finalproject_gplx.DanhSachDeThi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.MainActivity;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Exam;
import com.example.finalproject_gplx.model.Question;

import java.util.List;

public class Thi extends AppCompatActivity {
    private BD_Helper databaseHelper;
    private TextView txtTime,txtDe_thi,txtTien_do;
    private Button btn_submit;
    private List<Question> questionList;
    private Exam exam;
    private RecyclerView rvQuestion;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
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

        List<Exam> examList = databaseHelper.getAllExam();
        int id_exam = Integer.parseInt(getIntent().getStringExtra("de_thi"));


        for (Exam exam:examList){
            if (exam.getId()==id_exam){
                this.exam = exam;
            }
        }
        String question = exam.getList_ques();
        String[] list_question = question.split(",");
        List<Question> lst = databaseHelper.getAllQuestion();
        List<Answer> lst_answer = databaseHelper.getAllAnswer();
        for(Question question1:lst){
            List<Answer> answerList = databaseHelper.getAnswersByQuestionId(question1.getId());
            question1.setAnswer(answerList);
        }


        txtDe_thi.setText("Đề thi số: "+exam.getId());
//        exam=databaseHelper.getExamDetail(exam);
//        questionList=exam.getQuestions();
        //ExamAdapter examAdapter = new ExamAdapter(this,questionList);
        //rvQuestion.setAdapter(examAdapter);
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
