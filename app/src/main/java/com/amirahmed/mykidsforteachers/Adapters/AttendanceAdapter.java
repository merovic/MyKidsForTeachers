package com.amirahmed.mykidsforteachers.Adapters;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.StudentItem;
import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.List;


public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    List<StudentItem> studentItems;

    TinyDB tinyDB;

    int language;

    Context context;


    public AttendanceAdapter(List<StudentItem> studentItems) {
        this.studentItems = studentItems;
    }

    @Override
    public AttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        tinyDB = new TinyDB(context);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        AttendanceViewHolder eh = new AttendanceViewHolder(view);
        return eh;
    }

    @Override
    public void onBindViewHolder(final AttendanceViewHolder holder, final int position) {

        holder.studentname.setText(studentItems.get(position).name);
        Glide.with(holder.studentpic.getContext()).load(studentItems.get(position).getPhotoId()).into(holder.studentpic);


        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.absent.setVisibility(View.GONE);
                holder.notabsent.setVisibility(View.VISIBLE);
            }
        });

        holder.notabsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.notabsent.setVisibility(View.GONE);
                holder.absent.setVisibility(View.VISIBLE);
            }
        });

        holder.notabsent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.notabsent.setVisibility(View.GONE);
                holder.absentwith.setVisibility(View.VISIBLE);
                return true;
            }
        });

        holder.absentwith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.absentwith.setVisibility(View.GONE);
                holder.notabsent.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class AttendanceViewHolder extends RecyclerView.ViewHolder {

        ImageView studentpic;
        TextView studentname;
        Button absent,absentwith,notabsent;

        AttendanceViewHolder(View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.studentname);
            studentpic = itemView.findViewById(R.id.studentpic);
            absent = itemView.findViewById(R.id.absentbutton);
            absentwith = itemView.findViewById(R.id.absencewithbutton);
            notabsent = itemView.findViewById(R.id.notabsencebutton);

            language = tinyDB.getInt("language");

            if(language==1)
            {
                absent.setText("غائب");
                absentwith.setText("متأخر");
                notabsent.setText("حاضر");
            }else
                {
                    absent.setText("Absent");
                    absentwith.setText("Late");
                    notabsent.setText("Present");
                }

        }
    }
}
