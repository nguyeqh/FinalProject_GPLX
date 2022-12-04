package com.example.finalproject_gplx.DanhSachDeThi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Question;

import java.util.List;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.QuesViewHolder> {
    private Context context;
    private List<Question> questionList;
    private LayoutInflater mInflater;

    public QuesAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public QuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.question_item_view, parent, false);
        return new QuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuesAdapter.QuesViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.txtQues.setText("Câu hỏi số: "+position+1);
        holder.txtPass.setText(question.getContent());
        if (question.getAnswer().size()==4){
            holder.btnA.setText(question.getAnswer().get(0).getContent());
            holder.btnB.setText(question.getAnswer().get(1).getContent());
            holder.btnC.setText(question.getAnswer().get(2).getContent());
            holder.btnD.setText(question.getAnswer().get(3).getContent());
        }else if (question.getAnswer().size()==3){
            holder.btnA.setText(question.getAnswer().get(0).getContent());
            holder.btnB.setText(question.getAnswer().get(1).getContent());
            holder.btnC.setText(question.getAnswer().get(2).getContent());
            holder.btnD.setVisibility(View.GONE);
        }else if (question.getAnswer().size()==2){
            holder.btnA.setText(question.getAnswer().get(0).getContent());
            holder.btnB.setText(question.getAnswer().get(1).getContent());
            holder.btnC.setVisibility(View.GONE);
            holder.btnD.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuesViewHolder extends RecyclerView.ViewHolder {
        TextView txtQues,txtPass;
        RadioGroup rdgAns;
        RadioButton btnA,btnB,btnC,btnD;
        ImageView imgQues;

        public QuesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQues = itemView.findViewById(R.id.txtQuestion);
            txtPass = itemView.findViewById(R.id.txtPass);
            rdgAns = itemView.findViewById(R.id.rdgQuestion);
            btnA = itemView.findViewById(R.id.btnA);
            btnB = itemView.findViewById(R.id.btnB);
            btnC = itemView.findViewById(R.id.btnC);
            btnD = itemView.findViewById(R.id.btnD);
            imgQues = itemView.findViewById(R.id.imgQuestion);
        }
    }
}

