package com.example.freelancerhomescreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OtherExpPage extends AppCompatActivity implements ExperienceReyclerViewInterface {

    public List<Experience> contactsList;
    public RecyclerView recyclerView;
    DatabaseHandler db = new DatabaseHandler(this);
    public ArrayList<ExperienceRecyclerItem> mExperiences = new ArrayList<>();
    private final String TAG = "OtherExpPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hi");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_exp_page);
        // store recycler view in a variable
        recyclerView = (RecyclerView) findViewById(R.id.experiences);
        bindContactData();
        setAdapter();
    }

    private void setAdapter() {

        ExperienceRecyclerAdapter adapter = new ExperienceRecyclerAdapter(mExperiences, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
        List<Experience> experienceList = db.getAllExperience(profileID);
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

    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: ");
        Intent intent = new Intent(this, EditExperienceActivity.class);

        intent.putExtra("name", mExperiences.get(position).getName());
        intent.putExtra("startDate", mExperiences.get(position).getStartDate());
        intent.putExtra("endDate", mExperiences.get(position).getEndDate());
        intent.putExtra("description", mExperiences.get(position).getDescription());
        intent.putExtra("companyName",mExperiences.get(position).getCompanyName());
        startActivity(intent);


// on hold
    }
}
