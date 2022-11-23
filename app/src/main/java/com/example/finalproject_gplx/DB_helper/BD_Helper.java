package com.example.finalproject_gplx.DB_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Exam;
import com.example.finalproject_gplx.model.Question;
import com.example.finalproject_gplx.model.Sign;

import java.util.ArrayList;
import java.util.List;

public class BD_Helper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DB_Question";

    // Table name:
    private static final String TABLE_EXAM = "EXAM";
    private static final String TABLE_SIGN = "SIGN";
    private static final String TABLE_QUESTION = "QUESTION";
    private static final String TABLE_ANSWER = "ANSWER";

    // Common column names
    private static final String COLUMN_EXAM_ID = "EXAM_ID";
    private static final String COLUMN_EXAM_LIST_QUES = "EXAM_NAME";
    private static final String COLUMN_EXAM_LIST_ANS = "EXAM_IMAGE";
    private static final String COLUMN_EXAM_SCORE = "EXAM_SCORE";

    private static final String COLUMN_SIGN_ID = "SIGN_ID";
    private static final String COLUMN_SIGN_NAME = "SIGN_NAME";
    private static final String COLUMN_SIGN_IMAGE = "SIGN_IMAGE";
    private static final String COLUMN_SIGN_TYPE = "SIGN_TYPE";

    private static final String COLUMN_QUESTION_ID = "QUESTION_ID";
    private static final String COLUMN_QUESTION_CONTENT = "QUESTION_CONTENT";
    private static final String COLUMN_QUESTION_IMAGE = "QUESTION_IMAGE";

    private static final String COLUMN_ANSWER_ID = "ANSWER_ID";
    private static final String COLUMN_ANSWER_QUESTION_ID = "ANSWER_QUESTION_ID";
    private static final String COLUMN_ANSWER_CONTENT = "ANSWER_CONTENT";
    private static final String COLUMN_ANSWER_CHECK = "ANSWER_CHECK";


    public BD_Helper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Get All Question
    public List<Question> getAllQuestion(){
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION, null);
        if (cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(0));
                question.setContent(cursor.getString(1));
                question.setImage(cursor.getString(2));
                questionList.add(question);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    //Get All Exam
    public List<Exam> getAllExam(){
        List<Exam> examList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXAM, null);
        if (cursor.moveToFirst()){
            do {
                Exam exam = new Exam();
                exam.setId(cursor.getInt(0));
                exam.setList_ques(cursor.getString(1));
                exam.setList_ans(cursor.getString(2));
                exam.setScore(cursor.getInt(3));
                examList.add(exam);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return examList;
    }
    //Get Exam by ID
    public Exam getExamById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXAM + " WHERE " + COLUMN_EXAM_ID + " = " + id, null);
        if (cursor != null)
            cursor.moveToFirst();
        Exam exam = new Exam();
        exam.setId(cursor.getInt(0));
        exam.setList_ques(cursor.getString(1));
        exam.setList_ans(cursor.getString(2));
        exam.setScore(cursor.getInt(3));
        exam=getExamDetail(exam);
        cursor.close();
        return exam;
    }

    //Get Exam Detail
    public Exam getExamDetail(Exam exam){
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();
        String[] list_ques = exam.getList_ques().split(",");
        String[] list_ans = exam.getList_ans().split(",");
        for (int i = 0; i < list_ques.length; i++){
            Question question = getQuestionById(Integer.parseInt(list_ques[i]));
            questionList.add(question);
            Answer answer = getAnswerById(Integer.parseInt(list_ans[i]));
            answerList.add(answer);
        }
        exam.setQuestions(questionList);
        exam.setAnswers(answerList);
        return exam;
    }

    //Get Sign After Type
    public List<Sign> getSignAfterType(String type){
        List<Sign> signList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SIGN + " WHERE " + COLUMN_SIGN_TYPE + " = " + type, null);
        if (cursor.moveToFirst()){
            do {
                Sign sign = new Sign();
                sign.setId(cursor.getInt(0));
                sign.setName(cursor.getString(1));
                sign.setImage(cursor.getString(2));
                sign.setType(cursor.getString(3));
                signList.add(sign);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return signList;
    }

    //Get All Sign
    public List<Sign> getAllSign(){
        List<Sign> signList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SIGN, null);
        if (cursor.moveToFirst()){
            do {
                Sign sign = new Sign();
                sign.setId(cursor.getInt(0));
                sign.setName(cursor.getString(1));
                sign.setImage(cursor.getString(2));
                sign.setType(cursor.getString(3));
                signList.add(sign);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return signList;
    }
    //Get Sign by ID
    public Sign getSignById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SIGN + " WHERE " + COLUMN_SIGN_ID + " = " + id, null);
        if (cursor != null)
            cursor.moveToFirst();
        Sign sign = new Sign();
        sign.setId(cursor.getInt(0));
        sign.setName(cursor.getString(1));
        sign.setImage(cursor.getString(2));
        sign.setType(cursor.getString(3));
        cursor.close();
        return sign;
    }

    //Get Question After ID
    public Question getQuestionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION + " WHERE " + COLUMN_QUESTION_ID + " = " + id, null);
        if (cursor != null)
            cursor.moveToFirst();
        Question question = new Question();
        question.setId(cursor.getInt(0));
        question.setContent(cursor.getString(1));
        question.setImage(cursor.getString(2));
        return question;
    }

    //Get Answer After ID
    public Answer getAnswerById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANSWER + " WHERE " + COLUMN_ANSWER_ID + " = " + id, null);
        if (cursor != null)
            cursor.moveToFirst();
        Answer answer = new Answer();
        answer.setAns_id(cursor.getInt(0));
        answer.setContent(cursor.getString(1));
        //get check
        //answer.setCheck(cursor.getInt(2));
        return answer;
    }
    //Get Answer After Question ID
    public List<Answer> getAnswersByQuestionId(int id){
        List<Answer> answerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANSWER + " WHERE " + COLUMN_ANSWER_QUESTION_ID + " = " + id, null);
        if (cursor.moveToFirst()){
            do {
                Answer answer = new Answer();
                answer.setAns_id(cursor.getInt(0));
                answer.setContent(cursor.getString(1));
                //get check
                //answer.setCheck(cursor.getInt(2));
                answerList.add(answer);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return answerList;
    }

    //Random Question
    public List<Integer> getRandomQuestion(){
        int number=25;
        List<Integer> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION + " ORDER BY RANDOM() LIMIT " + number, null);
        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getInt(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    //Add Exam
    public void addExam(Exam exam){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXAM_LIST_QUES, exam.getList_ques());
        values.put(COLUMN_EXAM_LIST_ANS, exam.getList_ans());
        values.put(COLUMN_EXAM_SCORE, exam.getScore());
        db.insert(TABLE_EXAM, null, values);
        db.close();
    }

}
