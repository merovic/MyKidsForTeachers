package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.EvaluationInteractionActivity;
import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.List;

public class StudentsListAdapter2 extends RecyclerView.Adapter<StudentsListAdapter2.StudentsListViewHolder> {

    List<StudentItem> studentItems;
    Context context;

    public StudentsListAdapter2(List<StudentItem> studentItems) {
        this.studentItems = studentItems;
    }

    @Override
    public StudentsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentsListViewHolder eh = new StudentsListViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(final StudentsListViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EvaluationInteractionActivity.class);
                intent.putExtra("studentName",holder.studentname.getText().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }

    public class StudentsListViewHolder extends RecyclerView.ViewHolder {

        ImageView studentpic;
        TextView studentname;
        Button absent,absentwith,notabsent;

        public StudentsListViewHolder(View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.studentname);
            studentpic = itemView.findViewById(R.id.studentpic);
            absent = itemView.findViewById(R.id.absentbutton);
            absentwith = itemView.findViewById(R.id.absencewithbutton);
            notabsent = itemView.findViewById(R.id.notabsencebutton);

            absent.setVisibility(View.GONE);
            absentwith.setVisibility(View.GONE);
            notabsent.setVisibility(View.GONE);
        }
    }
}
