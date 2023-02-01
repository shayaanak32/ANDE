package com.example.freelancerhomescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectsCustomAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Projects> ps;
    private int imge;

    public ProjectsCustomAdapter(Context context, ArrayList<Projects> projects) {
        mContext = context;
        ps = projects;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ps.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        row = inflater.inflate(R.layout.activity_projects_list_view, parent, false);
        TextView projName, skills1, skills2,skills3, skills4,description;
        projName = (TextView) row.findViewById(R.id.projName);
        skills1 = (TextView) row.findViewById(R.id.Skills1);
        skills2 = (TextView) row.findViewById(R.id.Skills2);
        skills3 = (TextView) row.findViewById(R.id.Skills3);
        skills4 = (TextView) row.findViewById(R.id.Skills4);
        description = (TextView) row.findViewById(R.id.briefDescription);
        projName.setText(ps.get(position).getNameOfProject());
        String[] skills = ps.get(position).getSkills().split(",");
        switch (skills.length){
            case 1:
                skills1.setText(skills[0]);
                break;
            case 2:
                skills1.setText(skills[0]);
                skills2.setText(skills[1]);
                break;
            case 3:
                skills1.setText(skills[0]);
                skills2.setText(skills[1]);
                skills3.setText(skills[2]);
                break;
            case 4:
                skills1.setText(skills[0]);
                skills2.setText(skills[1]);
                skills3.setText(skills[2]);
                skills4.setText(skills[3]);
                break;
        }

        description.setText(ps.get(position).getDescription());
        return (row);
    }

}
