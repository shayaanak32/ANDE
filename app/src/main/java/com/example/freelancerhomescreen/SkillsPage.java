package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class SkillsPage extends AppCompatActivity {

    //public List<RecyclerSkillsItem> skillsList;
    public RecyclerView recyclerView;
    //ArrayList<RecyclerSkillsItem> items = new ArrayList<RecyclerSkillsItem>();
    SkillsAdapter adapter;
    DatabaseHandler db = new DatabaseHandler(this);
    public ArrayList<RecyclerSkillsItem> mSkills = new ArrayList<RecyclerSkillsItem>();
    // in this page get the ID from the shared preferences!!
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_page);
        MaterialButton addSkill = (MaterialButton) findViewById(R.id.addSkillBtn);
        EditText addSkillField = (EditText) findViewById(R.id.editTextSkill);

        addSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedSkill = addSkillField.getText().toString();
                addSkillField.setText("");
                RecyclerSkillsItem s = new RecyclerSkillsItem(addedSkill);
                int insertIndex = mSkills.size() - 1;
                mSkills.add(insertIndex, s);
                adapter.notifyItemInserted(insertIndex);
                String textListSkills = "";
                int counter = 0;
                for (RecyclerSkillsItem rsi : mSkills) {
                    if (counter != mSkills.size()) {
                        String nameOfSkill = rsi.getName();
                        if (counter == mSkills.size() - 1) {
                            textListSkills += nameOfSkill;
                        } else {
                            textListSkills += nameOfSkill + ",";
                            counter++;

                        }
                    }
                }
                db.updateSkills(textListSkills, 1);


            }
        });
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);

        BottomNavigationView bv =findViewById(R.id.bottomNavigationView);
        int userRole = Integer.parseInt(prefs.getString("RoleID","-1"));


        bv.setSelectedItemId(R.id.profileNav);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        if(userRole==1){
                            i = new Intent(SkillsPage.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(SkillsPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                        }else{
                            i = new Intent(SkillsPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(SkillsPage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(SkillsPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });


        // store recycler view in a variable
        recyclerView = (RecyclerView) findViewById(R.id.skillsRecyclerView);
        bindContactData();
        setAdapter();
    }

    private void setAdapter() {

        adapter = new SkillsAdapter(mSkills);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
        Freelancer f = db.getFreelancers(profileID);
        boolean isNull = (f == null);
        String[] skillsList = f.getIndividualSkill(f.getSkills());
        if(skillsList.length>1){
            for (int i =0;i<skillsList.length;i++) {
                String name = skillsList[i];
                mSkills.add(new RecyclerSkillsItem(name));
            }
        }

    }
}