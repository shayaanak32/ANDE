package com.example.freelancerhomescreen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditCertSkillAdapter extends RecyclerView.Adapter<EditCertSkillAdapter.MyViewHolder> {

    private ArrayList<String> skill_list;

    public EditCertSkillAdapter(ArrayList<String> skill_list) {
        this.skill_list = skill_list;
    }

    @NonNull
    @Override
    public EditCertSkillAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_list, parent, false);
        //Create View Holder
        final EditCertSkillAdapter.MyViewHolder myViewHolder = new EditCertSkillAdapter.MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditCertSkillAdapter.MyViewHolder holder, int position) {
        String name = skill_list.get(position);

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
