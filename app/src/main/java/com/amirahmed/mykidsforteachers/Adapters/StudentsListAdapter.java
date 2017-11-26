package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.ExamSubmissionActivity;
import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.List;

public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.StudentListViewHolder> {

    List<StudentItem> studentItems;

    public TinyDB tinydb;

    public int language;

    String state;

    Context context;

    public StudentsListAdapter(List<StudentItem> studentItems) {
        this.studentItems = studentItems;
    }

    @Override
    public StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        tinydb = new TinyDB(parent.getContext());
        language = tinydb.getInt("language");
        state = tinydb.getString("state");

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentListViewHolder eh = new StudentListViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(final StudentListViewHolder holder, final int position) {

        holder.studentname.setText(studentItems.get(position).name);
        Glide.with(holder.studentpic.getContext()).load(studentItems.get(position).getPhotoId()).into(holder.studentpic);
        /*if(state.equals("attendance"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(holder.absent.getVisibility()==View.VISIBLE)
                    {
                        holder.absent.setVisibility(View.GONE);
                    } else if(holder.absentwith.getVisibility()==View.VISIBLE)
                    {
                        holder.absentwith.setVisibility(View.GONE);
                    }else
                    {
                        holder.absent.setVisibility(View.VISIBLE);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    holder.absentwith.setVisibility(View.VISIBLE);
                    return true;
                }
            });
        }else if(state.equals("exams"))
            {
                Intent intent = new Intent(context, ExamSubmissionActivity.class);
                context.startActivity(intent);
            }else
                {

                }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state.equals("attendance"))
                {
                    if(holder.absent.getVisibility()==View.VISIBLE)
                    {
                        holder.absent.setVisibility(View.GONE);
                    } else if(holder.absentwith.getVisibility()==View.VISIBLE)
                    {
                        holder.absentwith.setVisibility(View.GONE);
                    }else
                    {
                        holder.absent.setVisibility(View.VISIBLE);
                    }
                }else if(state.equals("exams"))
                {
                    Intent intent = new Intent(context, ExamSubmissionActivity.class);
                    context.startActivity(intent);
                }else
                    {

                    }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.absentwith.setVisibility(View.VISIBLE);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }

    class StudentListViewHolder extends RecyclerView.ViewHolder {

        ImageView studentpic;
        TextView studentname;
        TextView absent;
        TextView absentwith;

        StudentListViewHolder(View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.studentname);
            studentpic = itemView.findViewById(R.id.studentpic);
            absent = itemView.findViewById(R.id.absent);
            absentwith = itemView.findViewById(R.id.absentwith);
        }
    }
}
