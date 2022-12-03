package com.example.finalproject_gplx.model;

public class Answer {
    private int ans_id;
    private int ans_ques_id;
    private String content;
    private boolean check;


    public Answer() {
    }

    public Answer(int ans_id,  int ques_id, String content, boolean check) {
        this.ans_id = ans_id;
        this.ans_ques_id = ques_id;
        this.content = content;
        this.check = check;
    }

    public int getAns_id() {
        return ans_id;
    }

    public void setAns_id(int ans_id) {
        this.ans_id = ans_id;
    }

    public int getQues_id() {
        return ans_ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ans_ques_id = ques_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "ans_id=" + ans_id +
                ", ques_id=" + ans_ques_id +
                ", content='" + content + '\'' +
                ", check=" + check +
                '}';
    }
}
