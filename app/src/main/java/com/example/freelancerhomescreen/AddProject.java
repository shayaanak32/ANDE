package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddProject extends AppCompatActivity {
    EditText pName, pStart, pEnd, pLink, skillEdit, pDesc;
    TextView indiSkill;
    ListView skillsList;
    Button addSkills, submitBtn;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        DatabaseHandler db = new DatabaseHandler(this);
        Projects p1 = db.get1Project(1);
        pName = findViewById(R.id.editProjName);
        pStart = findViewById(R.id.startDatePicker);
        pEnd = findViewById(R.id.endDatePicker);
        skillEdit = findViewById(R.id.company1PrioEdit);
        submitBtn = findViewById(R.id.AddUpdate);
        pLink = findViewById(R.id.pasteLink);
        addSkills = findViewById(R.id.addBtn);
        skillsList = findViewById(R.id.projSkills);
        indiSkill = findViewById(R.id.indiSkills);
        pDesc = findViewById(R.id.pDesc);
        ArrayList<String> skills = new ArrayList<String>(0);
//        pName.setText(p1.getNameOfProject());
//        pDesc.setText(p1.getDescription());
//        pStart.setText(p1.getStartDate());
//        pEnd.setText(p1.getEndDate());
//        pLink.setText(p1.getLink());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, skills);
        skillsList.setAdapter(adapter);

        skillsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the item at the specified position
                skills.remove(position);

                // Notify the adapter of the change
                adapter.notifyDataSetChanged();

            }
        });

        addSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skills.add(skillEdit.getText().toString());
                adapter.notifyDataSetChanged();
                skillEdit.setText("");
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skillsP = "",pN, pSD, pED, pL, pD;
                pN = pName.getText().toString();
                pSD = pStart.getText().toString();
                pD = pDesc.getText().toString();
                pED = pEnd.getText().toString();
                pL = pLink.getText().toString();
                if(adapter.getCount()>0){
                    for(int i = 0; i < adapter.getCount()-1; i++){
                        skillsP+=adapter.getItem(i)+",";
                    }
                    skillsP+=adapter.getItem(adapter.getCount()-1);
                }
                prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
                Projects p2 = new Projects(p1.getProjectID(),pN, pSD, pED,pL, skillsP, pD, identity_id);
                db.addProjects(p2);
                Intent i = new Intent(AddProject.this, ProjectsPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}