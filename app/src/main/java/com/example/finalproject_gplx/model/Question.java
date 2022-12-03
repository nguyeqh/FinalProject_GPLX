package com.example.finalproject_gplx.model;

import java.util.List;

public class Question {
    private int id;
    private String content;
    private String image;
    private List<Answer> answers;

    public Question() {
    }

    public Question( String content, String image) {
        this.content = content;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Answer> getAnswer() {
        return answers;
    }

    public void setAnswer(List<Answer> answer) {
        this.answers = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}