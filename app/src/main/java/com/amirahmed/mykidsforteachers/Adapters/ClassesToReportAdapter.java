package com.amirahmed.mykidsforteachers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Activities.ClassesReportsActivity;
import com.amirahmed.mykidsforteachers.Models.ExamsMonthItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

import java.util.List;


public class ClassesToReportAdapter extends RecyclerView.Adapter<ClassesToReportAdapter.ClassesToReportViewHolder> {


    private List<ExamsMonthItem> monthItems;

    public TinyDB tinydb;

    public int language;

    Context context;

    public ClassesToReportAdapter(List<ExamsMonthItem> monthItems){

        this.monthItems = monthItems;
    }

    @Override
    public ClassesToReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        tinydb = new TinyDB(parent.getContext());
        language = tinydb.getInt("language");


        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month, parent, false);
        ClassesToReportViewHolder eh = new ClassesToReportViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(final ClassesToReportViewHolder holder, int position) {

        holder.level.setText(monthItems.get(position).level);
        holder.classroom.setText(monthItems.get(position).classroom);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ClassesReportsActivity.class);
                intent.putExtra("className",holder.classroom.getText().toString());
                context.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return monthItems.size();
    }

    public class ClassesToReportViewHolder extends RecyclerView.ViewHolder {
        TextView level;
        TextView classroom;
        TextView description;
        TextView button;
        Context context;
        public ClassesToReportViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            level = itemView.findViewById(R.id.level);
            classroom = itemView.findViewById(R.id.classroom);
            description = itemView.findViewById(R.id.disk);
            button = itemView.findViewById(R.id.button2);



            if(language==1)
            {
                description.setText("اطلع على تقارير الفصل");
                button.setText("زيارة الفصل");
            }else
            {
                description.setText("Check reports of the class");
                button.setText("Visit Class");
            }
        }
    }
}
