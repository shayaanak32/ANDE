package com.example.freelancerhomescreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ProjectsMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);


//        final ProjectsCustomAdapter adapter = new ProjectsCustomAdapter(this,
//                 listItem);
//        listView.setAdapter(adapter);


    }



//    private void bindContactData() {
//        List<String> skillsList = ct.get();
//        int index = 0;
//        for (Experience e : experienceList) {
//            String name = e.getName();
//            String startDate = e.getStartDate();
//            String endDate = e.getEndDate();
//            String description = e.getDescription();
//            String companyName = e.getCompany();
//            Log.d("Name ", name);
//            Log.d("Start Date ", startDate);
//            Log.d("End Date ", endDate);
//            Log.d("Description ", description);
//            mExperiences.add(new ExperienceRecyclerItem(name, startDate, endDate, description,companyName));
//            index++;
//        }
////// id for add button: addExperienceBtn
//        MaterialButton addExperienceBtn = (MaterialButton) findViewById(R.id.addExperienceBtn);
//        addExperienceBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ProjectsMainActivity.this, AddExperience.class);
//                startActivity(i);
//
//            }
//
//        });
//
//    }

//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(ProjectsMainActivity.this, EditExperienceActivity.class);
//// on hold
//    }
}
