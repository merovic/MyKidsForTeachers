package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.TeachersMessagingItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.List;

public class TeachersMessagingAdapter extends RecyclerView.Adapter<TeachersMessagingAdapter.TeachersMessagingViewHolder> {

    List<TeachersMessagingItem> teachersMessagingItems;

    TextView to;

    TinyDB tinyDB;

    Context context;

    int language;

    public TeachersMessagingAdapter(List<TeachersMessagingItem> teachersMessagingItems, TextView to) {
        this.teachersMessagingItems = teachersMessagingItems;
        this.to = to;
    }

    @Override
    public TeachersMessagingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        tinyDB = new TinyDB(context);

        language = tinyDB.getInt("language");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teachersmessaging, parent, false);
        TeachersMessagingViewHolder eh = new TeachersMessagingViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(final TeachersMessagingViewHolder holder, int position) {
        holder.teacherName.setText(teachersMessagingItems.get(position).teacherName);
        holder.teacherJob.setText(teachersMessagingItems.get(position).teacherJob);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(language==1)
                {
                    to.setText("الى: "+holder.teacherName.getText().toString());
                }else
                    {
                        to.setText("To: "+holder.teacherName.getText().toString());
                    }

            }
        });
    }

    @Override
    public int getItemCount() {
        return teachersMessagingItems.size();
    }

    public class TeachersMessagingViewHolder extends RecyclerView.ViewHolder {

        TextView teacherName,teacherJob;
        LinearLayout container;

        public TeachersMessagingViewHolder(View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.teacherName);
            teacherJob = itemView.findViewById(R.id.teacherJob);
            container = itemView.findViewById(R.id.container);

            if(language==1)
            {
                container.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                teacherName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                teacherJob.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            }else
                {
                    container.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                    teacherName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    teacherJob.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                }


        }
    }
}
