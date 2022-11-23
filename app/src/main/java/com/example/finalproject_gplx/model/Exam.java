package com.example.finalproject_gplx.model;

import java.util.List;

public class Exam {
    private int id;
    private String List_ques;
    private String List_ans;
    private int score;
    private List<Question> questions;
    private List<Answer> answers;

    public Exam() {
    }

    public Exam( String list_ques, String list_ans, int score) {

        List_ques = list_ques;
        List_ans = list_ans;
        this.score = score;
    }
    public Exam(String list_ques, String list_ans, int score, List<Question> questions, List<Answer> answers) {
        List_ques = list_ques;
        List_ans = list_ans;
        this.score = score;
        this.questions = questions;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getList_ques() {
        return List_ques;
    }

    public void setList_ques(String list_ques) {
        List_ques = list_ques;
    }

    public String getList_ans() {
        return List_ans;
    }

    public void setList_ans(String list_ans) {
        List_ans = list_ans;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", List_ques='" + List_ques + '\'' +
                ", List_ans='" + List_ans + '\'' +
                ", score=" + score +
                '}';
    }
}
