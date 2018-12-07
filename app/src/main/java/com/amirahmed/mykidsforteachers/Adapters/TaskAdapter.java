package com.amirahmed.mykidsforteachers.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.TaskItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<TaskItem> taskItemList;

    Context context;

    public TaskAdapter(List<TaskItem> taskItemList) {
        this.taskItemList = taskItemList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position) {

        holder.taskname.setText(taskItemList.get(position).getTitle());
        holder.taskdate.setText(taskItemList.get(position).getDate());
        holder.tasktime.setText(taskItemList.get(position).getTime());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {

                    holder.taskname.setPaintFlags(holder.taskname.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    taskItemList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,taskItemList.size());

                }else
                    {
                        holder.taskname.setPaintFlags(holder.taskname.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

                    }

                    holder.checkBox.setChecked(false);
                    holder.taskname.setPaintFlags(holder.taskname.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        if(taskItemList.get(position).getType().equals("Work"))
        {
            holder.star.setImageResource(R.drawable.stargreen);
        }else
            {
                holder.star.setImageResource(R.drawable.starred);
            }
    }

    @Override
    public int getItemCount() {
        return taskItemList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskname,taskdate,tasktime;
        CheckBox checkBox;
        ImageView star;

        TaskViewHolder(View itemView) {
            super(itemView);

            taskname = itemView.findViewById(R.id.taskname);
            taskdate = itemView.findViewById(R.id.taskdate);
            tasktime = itemView.findViewById(R.id.tasktime);
            checkBox = itemView.findViewById(R.id.check);
            star = itemView.findViewById(R.id.star);

        }
    }
}
