package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.ClassScheduleItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.List;

public class ClassScheduleAdapter extends RecyclerView.Adapter<ClassScheduleAdapter.ClassScheduleViewHolder> {

    List<ClassScheduleItem> classScheduleItems;

    TinyDB tinyDB;

    int language;

    Context context;

    public ClassScheduleAdapter(List<ClassScheduleItem> classScheduleItems) {
        this.classScheduleItems = classScheduleItems;
    }

    @Override
    public ClassScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        tinyDB = new TinyDB(context);

        language = tinyDB.getInt("language");

        View view;

        if(language==1)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classschedule2, parent, false);
        }else
            {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classschedule2en, parent, false);
            }

        ClassScheduleViewHolder eh = new ClassScheduleViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(ClassScheduleViewHolder holder, int position) {
        holder.className.setText(classScheduleItems.get(position).getClassName());
        holder.classRoom.setText(classScheduleItems.get(position).getClassRoom());
        holder.from.setText(classScheduleItems.get(position).getFrom());
        holder.to.setText(classScheduleItems.get(position).getTo());

        /*if((position % 2) == 0)
        {
            holder.container.setBackgroundResource(R.drawable.roundedschedule2);
        }else
            {
                holder.container.setBackgroundResource(R.drawable.roundedschedule);
            }*/

    }

    @Override
    public int getItemCount() {
        return classScheduleItems.size();
    }

    public class ClassScheduleViewHolder extends RecyclerView.ViewHolder {

        TextView className,classRoom,from,to;
        RelativeLayout container;

        public ClassScheduleViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            className = itemView.findViewById(R.id.subject);
            classRoom = itemView.findViewById(R.id.classroom);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);

        }
    }
}
