package com.example.freelancerhomescreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ExperienceMainActivity extends AppCompatActivity implements ExperienceReyclerViewInterface {

    public List<Experience> contactsList;
    public RecyclerView recyclerView;
    DatabaseHandler db = new DatabaseHandler(this);
    public ArrayList<ExperienceRecyclerItem> mExperiences = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        // store recycler view in a variable
        recyclerView = (RecyclerView) findViewById(R.id.experiences);
        bindContactData();
        setAdapter();

    }

    private void setAdapter() {

        ExperienceRecyclerAdapter adapter = new ExperienceRecyclerAdapter(mExperiences, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExperienceMainActivity.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        List<Experience> experienceList = db.getAllExperience(identity_id);
        int index = 0;
        for (Experience e : experienceList) {
            String name = e.getName();
            String startDate = e.getStartDate();
            String endDate = e.getEndDate();
            String description = e.getDescription();
            String companyName = e.getCompany();
            Log.d("Name ", name);
            Log.d("Start Date ", startDate);
            Log.d("End Date ", endDate);
            Log.d("Description ", description);
            mExperiences.add(new ExperienceRecyclerItem(name, startDate, endDate, description,companyName));
            index++;
        }
// id for add button: addExperienceBtn
        MaterialButton addExperienceBtn = (MaterialButton) findViewById(R.id.addExperienceBtn);

        addExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperienceMainActivity.this, AddExperience.class);
                startActivity(i);

            }

        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ExperienceMainActivity.this, EditExperienceActivity.class);

        intent.putExtra("name", mExperiences.get(position).getName());
        intent.putExtra("startDate", mExperiences.get(position).getStartDate());
        intent.putExtra("endDate", mExperiences.get(position).getEndDate());
        intent.putExtra("description", mExperiences.get(position).getDescription());
        intent.putExtra("companyName",mExperiences.get(position).getCompanyName());
        startActivity(intent);

// on hold
    }
}
