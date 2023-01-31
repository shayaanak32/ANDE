package com.example.freelancerhomescreen;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectRecyclerItemArrayAdapter extends RecyclerView.Adapter<ProjectRecyclerItemArrayAdapter.MyViewHolder> {

    private ArrayList<Recycleritem> userArrayList;
    private MyRecyclerViewItemClickListener mItemClickListener;

    private ImageView freelancerPfp;
    private TextView freelancerName;
    private TextView freelancerAbout;
    private TextView skill1;
    private TextView skill2;
    private TextView skill3;
    private Drawable pfp;
    private View y;

    public ProjectRecyclerItemArrayAdapter(ArrayList<Recycleritem> userArrayList, MyRecyclerViewItemClickListener itemClickListener)
    {
        this.userArrayList = userArrayList;
        this.mItemClickListener = itemClickListener;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycleritem, parent, false);

        //Create View Holder
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mItemClickListener.onItemClicked(userArrayList.get(myViewHolder.getLayoutPosition()));
            }
        });
        y = view;

//        freelancerPfp = view.findViewById(R.id.freelancerPfp);
        freelancerName = view.findViewById(R.id.freelancerName);
        freelancerAbout = view.findViewById(R.id.freelancerAbout);
        skill1 = view.findViewById(R.id.freelancerPrio1);
        skill2 = view.findViewById(R.id.freelancerPrio2);
        skill3 = view.findViewById(R.id.freelancerPrio3);
//        pfp = ContextCompat.getDrawable(ImageView.ge,R.drawable.profile_pic);



        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        //Set Freelancer PFP (ask cher)
//        holder.freelancerPfp.set
        int pfpDrawable = R.drawable.profile_pic;
        //Set Freelancer Name
        freelancerName.setText(userArrayList.get(position).getName());
        //Set Freelancer About
        freelancerAbout.setText(userArrayList.get(position).getAbout());
        //Set Freelancer Name
        String[] skillList= userArrayList.get(position).getPrios();
        skill1.setText(skillList[0]);
        //Set Freelancer Name
        skill2.setText(skillList[1]);
        //Set Freelancer Name
        skill3.setText(skillList[2]);
        freelancerPfp.setImageResource(R.drawable.profile_pic);

    }

    @Override
    public int getItemCount()
    {
        return userArrayList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //RecyclerView View Holder
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            freelancerPfp = itemView.findViewById(R.id.freelancerPfp);
        }
    }

    //RecyclerView Click Listener
    public interface MyRecyclerViewItemClickListener
    {
        void onItemClicked(Recycleritem flncrList);
    }}

