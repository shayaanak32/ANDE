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

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CertificationAdapter extends RecyclerView.Adapter<CertificationAdapter.MyViewHolder> {

    private ArrayList<CertificationRecyclerItem> certificationsList;
    private final CertificationRecyclerAdapterInterface crai;

    public CertificationAdapter(ArrayList<CertificationRecyclerItem> certificationsList,
                                CertificationRecyclerAdapterInterface crai) {
        this.certificationsList = certificationsList;
        this.crai = crai;
    }



    @NonNull
    @Override
    public CertificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvcertifications_card, parent, false);
        //Create View Holder
        final CertificationAdapter.MyViewHolder myViewHolder = new CertificationAdapter.MyViewHolder(itemView,crai);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CertificationAdapter.MyViewHolder holder, int position) {

        CertificationRecyclerItem e = certificationsList.get(position);
        String name = certificationsList.get(position).getName();
        String link = certificationsList.get(position).getLink();
        String endDate = certificationsList.get(position).getEndDate();
        String description = certificationsList.get(position).getDescription();
        String skills = certificationsList.get(position).getSkills();
//        Boolean isVisible = e.getDescVisibility();
//        Boolean isSeeMoreVisibility = e.getSeeMoreVisiblity();
//        holder.constraintLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
//        holder.seeMoreTxt.setVisibility(isSeeMoreVisibility ? View.VISIBLE : View.GONE);


        holder.certificationTitle.setText(name);
        holder.endDate.setText(endDate);

    }




    @Override
    public int getItemCount() {
        return certificationsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView endDate;
        private TextView certificationTitle;
        private TextView description;
        private MaterialButton editBtn;
        private MaterialButton viewLinkBtn;

        public MyViewHolder(@NonNull View itemView, CertificationRecyclerAdapterInterface crai) {

            super(itemView);
            certificationTitle = itemView.findViewById(R.id.certificationTitle);
            endDate = itemView.findViewById(R.id.dateOfCompletion);
//            description = itemView.findViewById(R.id.description);
            editBtn = itemView.findViewById(R.id.certEditBtn);
            viewLinkBtn = itemView.findViewById(R.id.viewLinkBtn);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (crai != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            crai.onItemClick(position);
                        }
                    }
                }
            });

            viewLinkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        crai.goToLink(position);

                    }

                }
            });
        }
    }
}
