package com.amirahmed.mykidsforteachers.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.Models.ExamsSubmissionItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.List;

public class ExamesSubmissionAdapter extends RecyclerView.Adapter<ExamesSubmissionAdapter.ExamesSubmissionViewHolder> {

    List<ExamsSubmissionItem> examsSubmissionItemList;

    Context context;

    public ExamesSubmissionAdapter(List<ExamsSubmissionItem> examsSubmissionItemList) {

        this.examsSubmissionItemList = examsSubmissionItemList;
    }

    @Override
    public ExamesSubmissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_examssubmission, parent, false);
        ExamesSubmissionViewHolder eh = new ExamesSubmissionViewHolder(view);
        return eh;

    }

    @Override
    public void onBindViewHolder(final ExamesSubmissionViewHolder holder, int position) {
        holder.studentName.setText(examsSubmissionItemList.get(position).getStudentName());
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score = holder.studentScore.getText().toString();
                String comment = holder.studentComment.getText().toString();

                showMessage(score);
                showMessage(comment);
            }
        });

    }


    @Override
    public int getItemCount() {
        return examsSubmissionItemList.size();
    }

    private void showMessage(String _s) {
        Toast.makeText(context, _s, Toast.LENGTH_SHORT).show();
    }

    public class ExamesSubmissionViewHolder extends RecyclerView.ViewHolder {

        TextView studentName;
        EditText studentScore,studentComment;
        Button done;

        public ExamesSubmissionViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentname);
            studentScore = itemView.findViewById(R.id.studentscore);
            studentComment = itemView.findViewById(R.id.studentcomment);
            done = itemView.findViewById(R.id.donebutton);
        }
    }
}
