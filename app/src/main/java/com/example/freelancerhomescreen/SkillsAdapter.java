package com.example.freelancerhomescreen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MyViewHolder> {

    private ArrayList<RecyclerSkillsItem> skill_list;

    public SkillsAdapter(ArrayList<RecyclerSkillsItem> skill_list) {
        this.skill_list = skill_list;
    }

    @NonNull
    @Override
    public SkillsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_list, parent, false);
        //Create View Holder
        final SkillsAdapter.MyViewHolder myViewHolder = new SkillsAdapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = skill_list.get(position).getName();

        holder.skillsName.setText(name);
    }


    @Override
    public int getItemCount() {
        return skill_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView skillsName;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            skillsName = itemView.findViewById(R.id.skillsNames);
        }
    }







}
