package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class EditProject extends AppCompatActivity {
    EditText pName, pLink, skillEdit, pDesc, pStart, pEnd;
    TextView indiSkill;
    ListView skillsList;

    private String startDateChosen;
    private String endDateChosen;
    boolean startDateClicked, endDateClicked, fromEditProject;
    Button addSkills, submitBtn;
    Intent intent;
    SharedPreferences sdPref;
    SharedPreferences edPref;
    SharedPreferences prefs;
    public static final String START_DATE = "ProjectStartDate";
    public static final String END_DATE = "ProjectEndDate";
    int project_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        // get extras from the previous page
        intent = getIntent();
         project_id = intent.getIntExtra("project_id", 0);
        Log.d("Project ID The First Intent", Integer.toString(project_id));
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Project ID the Second Intent", Integer.toString(project_id));
        Projects p1 = db.get1Project(project_id);
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
        ArrayList<String> skills = new ArrayList<>(Arrays.asList(p1.getSkills().split(",")));
        pName.setText(p1.getNameOfProject());
        pDesc.setText(p1.getDescription());
        Log.d("error handling", p1.getStartDate()+"  "+p1.getEndDate());
        pStart.setText(p1.getStartDate()+"");
        pEnd.setText(p1.getEndDate()+"");
        pLink.setText(p1.getLink());
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
                String skillsP = "", pN, pSD, pED, pL, pD;
                pN = pName.getText().toString();
                pSD = pStart.getText().toString();
                pD = pDesc.getText().toString();
                pED = pEnd.getText().toString();
                pL = pLink.getText().toString();
                if (adapter.getCount() > 0) {
                    for (int i = 0; i < adapter.getCount() - 1; i++) {
                        skillsP += adapter.getItem(i) + ",";
                    }
                    skillsP += adapter.getItem(adapter.getCount() - 1);
                }
                prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
                Projects p2 = new Projects(p1.getProjectID(), pN, pSD, pED, pL, skillsP, pD, identity_id);
                db.updateProjects(p2);
                Intent i = new Intent(EditProject.this, ProjectsPage.class);
                startActivity(i);
                finish();
            }
        });

    }

}