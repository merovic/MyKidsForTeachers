package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.StudentListActivity;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.List;

public class ExamsMonthAdapter extends RecyclerView.Adapter<ExamsMonthAdapter.ExamsViewHolder> {

    List<ExamsMonthItem> monthItems;

    public TinyDB tinydb;

    public int language;

    String state;

    Context context;

    public ExamsMonthAdapter(List<ExamsMonthItem> monthItems){

        this.monthItems = monthItems;
    }


    @Override
    public ExamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        tinydb = new TinyDB(parent.getContext());
        language = tinydb.getInt("language");
        state = tinydb.getString("state");

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month, parent, false);
        ExamsViewHolder eh = new ExamsViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(ExamsViewHolder holder, final int position) {
        holder.level.setText(monthItems.get(position).level);
        holder.classroom.setText(monthItems.get(position).classroom);
        holder.date.setText(monthItems.get(position).date);
        holder.subject.setText(monthItems.get(position).subject);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, StudentListActivity.class);
                context.startActivity(intent);

            }
        });

        /*YoYo.with(Techniques.BounceIn)
                .duration(1950)
                .playOn(holder.itemView);*/

    }

    @Override
    public int getItemCount() {
        return monthItems.size();
    }

    class ExamsViewHolder extends RecyclerView.ViewHolder {
        TextView level;
        TextView classroom;
        TextView date;
        TextView subject;
        Context context;

        ExamsViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            level = itemView.findViewById(R.id.level);
            date = itemView.findViewById(R.id.date);
            classroom = itemView.findViewById(R.id.classroom);
            subject = itemView.findViewById(R.id.subject);

            if(!state.equals("exams"))
            {
                date.setVisibility(View.GONE);
                subject.setVisibility(View.GONE);
            }

        }
    }
}
