package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.StudentsReportsItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.CircleDisplay;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class StudentsReportsAdapter extends RecyclerView.Adapter<StudentsReportsAdapter.StudentsReportsViewHolder> {

    private List<StudentsReportsItem> studentsReportsItems;

    TinyDB tinyDB;

    int language;

    Context context;

    public StudentsReportsAdapter(List<StudentsReportsItem> studentsReportsItems) {
        this.studentsReportsItems = studentsReportsItems;
    }

    @Override
    public StudentsReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        tinyDB = new TinyDB(context);



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new StudentsReportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsReportsViewHolder holder, int position) {
        holder.studentName.setText(studentsReportsItems.get(position).getStudentName());
        holder.input1.setText(studentsReportsItems.get(position).getInput1());
        holder.input2.setText(studentsReportsItems.get(position).getInput2());
        int result3 = 90;
        holder.percentage.showValue(Float.parseFloat(String.valueOf(result3)), 100f, true);

        if((position % 2) == 0)
        {
            holder.layout.setBackgroundColor(Color.LTGRAY);
        }else
            {
                holder.layout.setBackgroundColor(Color.WHITE);
            }

    }

    @Override
    public int getItemCount() {
        return studentsReportsItems.size();
    }

    class StudentsReportsViewHolder extends RecyclerView.ViewHolder {
        TextView studentName,input1,input2;
        CircleDisplay percentage;
        LinearLayout layout;

        StudentsReportsViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentname);
            input1 = itemView.findViewById(R.id.input1);
            input2 = itemView.findViewById(R.id.input2);
            percentage = itemView.findViewById(R.id.percentage);
            layout = itemView.findViewById(R.id.layout);

            language = tinyDB.getInt("language");

            if(language==1)
            {
                layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }else
                {
                    layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }

            percentage.setAnimDuration(3000);
            percentage.setValueWidthPercent(30f);
            percentage.setTextSize(10f);
            percentage.setColor(Color.parseColor("#009284"));
            percentage.setDrawText(true);
            percentage.setInnerCircleColor(Color.parseColor("#2C5768"));
            percentage.setTextColor(Color.WHITE);
            percentage.setDrawInnerCircle(true);
            percentage.setFormatDigits(0);
            percentage.setTouchEnabled(false);
            percentage.setUnit("%");
            percentage.setStepSize(0.5f);

        }
    }

    public void filterList(ArrayList<StudentsReportsItem> filterdNames) {
        this.studentsReportsItems = filterdNames;
        notifyDataSetChanged();
    }

}
