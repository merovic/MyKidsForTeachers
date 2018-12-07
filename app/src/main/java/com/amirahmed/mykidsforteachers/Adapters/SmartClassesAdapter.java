package com.amirahmed.mykidsforteachers.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirahmed.mykidsforteachers.Models.SmartClassItem;
import com.amirahmed.mykidsforteachers.R;

import java.util.List;

public class SmartClassesAdapter extends RecyclerView.Adapter<SmartClassesAdapter.SmartClassesViewHolder> {

    private List<SmartClassItem> smartClassItems;

    public SmartClassesAdapter(List<SmartClassItem> smartClassItems) {
        this.smartClassItems = smartClassItems;
    }

    @NonNull
    @Override
    public SmartClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smart_classes, parent, false);

        return new SmartClassesViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull SmartClassesViewHolder holder, int position) {

        holder.classname.setText(smartClassItems.get(position).getClassName());
        holder.classroom.setText(smartClassItems.get(position).getClassClass());
    }

    @Override
    public int getItemCount() {
        return smartClassItems.size();
    }

    class SmartClassesViewHolder extends RecyclerView.ViewHolder {

        TextView classname,classroom;

        SmartClassesViewHolder(View itemView) {
            super(itemView);
            classname = itemView.findViewById(R.id.classname);
            classroom = itemView.findViewById(R.id.classroom);
        }
    }
}
