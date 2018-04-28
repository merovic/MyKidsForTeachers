package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.ExamSubmissionActivity;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.List;

public class ExamsResultsAdapter extends RecyclerView.Adapter<ExamsResultsAdapter.ExamsResultsViewHolder> {


    List<ExamsMonthItem> examsItemList;

    TinyDB tinyDB;

    int language;

    Context context;

    public ExamsResultsAdapter(List<ExamsMonthItem> examsItemList) {
        this.examsItemList = examsItemList;
    }

    @Override
    public ExamsResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        ExamsResultsViewHolder eh = new ExamsResultsViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(ExamsResultsViewHolder holder, int position) {
        holder.examDate.setText(examsItemList.get(position).date);
        holder.examDay.setText("٢٦");
        holder.subjectName.setText(examsItemList.get(position).subject);
        holder.editResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ExamSubmissionActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return examsItemList.size();
    }

    public class ExamsResultsViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName,examDate,examDay;
        Button editResult;

        public ExamsResultsViewHolder(View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.subjectname);
            examDate = itemView.findViewById(R.id.date);
            examDay = itemView.findViewById(R.id.date2);
            editResult = itemView.findViewById(R.id.buttonedit);

            tinyDB = new TinyDB(context);

            language = tinyDB.getInt("language");

            if(language==1)
            {
                editResult.setText("اضافة النتيجة");
            }else
                {
                    editResult.setText("Add Result");
                }
        }
    }
}
