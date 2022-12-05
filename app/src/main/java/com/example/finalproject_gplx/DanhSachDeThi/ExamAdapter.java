package com.example.finalproject_gplx.DanhSachDeThi;

import android.content.Context;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject_gplx.DB_helper.BD_Helper;
import com.example.finalproject_gplx.R;
import com.example.finalproject_gplx.model.Answer;
import com.example.finalproject_gplx.model.Exam;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private Context context;
    private List<Exam>  mData;
    private LayoutInflater mInflater;
    private ExamAdapter.ItemClickListener mClickListener;


    public ExamAdapter(Context context, List<Exam> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ExamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.exam_item_view, parent, false);
        return new ExamAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamAdapter.ViewHolder holder, int position) {
        Exam exam =  mData.get(position);
        holder.name.setText("Đề thi số: " + exam.getId());
        holder.score.setText("Điểm: "+ exam.getScore());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Thi.class);
                intent.putExtra("de_thi",String.valueOf(exam.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView score;
        Button btn;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            score = itemView.findViewById(R.id.tv_score);
            btn = itemView.findViewById(R.id.btn_thi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // convenience method for getting data at click position
    Exam getItem(int id) {
        return  mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ExamAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}

