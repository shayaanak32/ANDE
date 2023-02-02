package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OtherSkillsPage extends AppCompatActivity {
    private String TAG ="skillName";
    //public List<RecyclerSkillsItem> skillsList;
    public RecyclerView recyclerView;
    //ArrayList<RecyclerSkillsItem> items = new ArrayList<RecyclerSkillsItem>();
    SkillsAdapter adapter;
    CreateTables ct = new CreateTables(this);
    DatabaseHandler db = new DatabaseHandler(this);
    public ArrayList<RecyclerSkillsItem> mSkills = new ArrayList<RecyclerSkillsItem>();
    // in this page get the ID from the shared preferences!!
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_skills_page);

        // store recycler view in a variable
        recyclerView = (RecyclerView) findViewById(R.id.skillsRecyclerView);
        bindContactData();
        setAdapter();
    }

    private void setAdapter() {

        adapter = new SkillsAdapter(mSkills);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
        Freelancer f = db.getFreelancers(profileID);
        boolean isNull = (f == null);
        String[] skillsList = f.getIndividualSkill(f.getSkills());
        Log.d(TAG, "bindContactData: "+skillsList.length);
        if(skillsList.length>=1){
            for (int i =0;i<skillsList.length;i++) {

                String name = skillsList[i];
                Log.d("skillName",name);
                mSkills.add(new RecyclerSkillsItem(name));
            }
        }

    }
}