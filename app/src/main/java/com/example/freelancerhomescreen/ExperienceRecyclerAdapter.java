package com.example.freelancerhomescreen;

import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceRecyclerAdapter.MyViewHolder> {
    private ArrayList<ExperienceRecyclerItem> experience_list;
    private final ExperienceReyclerViewInterface ervi;

    public ExperienceRecyclerAdapter(ArrayList<ExperienceRecyclerItem> experience_list,
                                     ExperienceReyclerViewInterface ervi) {
        this.experience_list = experience_list;
        this.ervi = ervi;
    }

    @NonNull
    @Override
    public ExperienceRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvexperience_card, parent, false);
        //Create View Holder
        final MyViewHolder myViewHolder = new MyViewHolder(itemView,ervi);
        Log.d("STATUS", "View Holder created");

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceRecyclerAdapter.MyViewHolder holder, int position) {
        ExperienceRecyclerItem e = experience_list.get(position);
        String name = experience_list.get(position).getName();
        String startDate = experience_list.get(position).getStartDate();
        String endDate = experience_list.get(position).getEndDate();
        String description = experience_list.get(position).getDescription();
//        Boolean isVisible = e.getDescVisibility();
//        Boolean isSeeMoreVisibility = e.getSeeMoreVisiblity();
//        holder.constraintLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
//        holder.seeMoreTxt.setVisibility(isSeeMoreVisibility ? View.VISIBLE : View.GONE);
        Log.d("Name: ", name);
        Log.d("Start Date: ", startDate);
        Log.d("End Date: ", endDate);
        Log.d("Description: ", description);

        holder.jobTitle.setText(name);
        holder.startDate.setText(startDate);
        holder.endDate.setText(endDate);
        holder.description.setText(description);

    }

    @Override
    public int getItemCount() {
        return experience_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView startDate;
        private TextView endDate;
        private TextView jobTitle;
        private TextView description;
        ConstraintLayout constraintLayout;
        private ImageView seeMore;
        private TextView seeMoreTxt;
        private MaterialButton editBtn;

        public MyViewHolder(@NonNull View itemView, ExperienceReyclerViewInterface ervi) {

            super(itemView);
            jobTitle = itemView.findViewById(R.id.jobName);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            description = itemView.findViewById(R.id.description);
            editBtn = itemView.findViewById(R.id.editBtn);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ervi != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            ervi.onItemClick(position);
                        }
                    }

                }

            });
        }
    }
}
