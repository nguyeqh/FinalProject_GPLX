package com.example.finalproject_gplx.HocLyThuyet;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Question;

import java.io.File;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private Context context;
    private List<Question> questionList;
    private LayoutInflater mInflater;


    public QuestionAdapter( Context context, List<Question> questionList) {

        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.question.setText(question.getContent());
        List<Answer> lst=question.getAnswer();
        for (Answer answer:lst){
            if (answer.isCheck()){
                holder.answer.setText(answer.getContent());
            }
        }

    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView question,answer;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}


