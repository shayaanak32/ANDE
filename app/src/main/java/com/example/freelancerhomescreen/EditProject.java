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
    EditText pName, pLink, skillEdit, pDesc;
    TextView indiSkill;
    ListView skillsList;
    TextView pStart, pEnd;
    private String startDateChosen;
    private String endDateChosen;
    boolean startDateClicked, endDateClicked, fromEditProject;
    Button addSkills, submitBtn;
    Intent intent;
    SharedPreferences sdPref;
    SharedPreferences edPref;
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
        pStart.setText(p1.getStartDate());
        pEnd.setText(p1.getEndDate());
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

                Projects p2 = new Projects(p1.getProjectID(), pN, pSD, pED, pL, skillsP, pD, 1);
                // TODO: 1/2/2023 get user id from shared Pref
                db.updateProjects(p2);
                Intent i = new Intent(EditProject.this, ProjectsPage.class);
                startActivity(i);
                finish();
            }
        });

        pStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProject.this, CalendarViewActivity.class);
                startDateClicked = true;
                fromEditProject = true;
                i.putExtra("startDateClicked", startDateClicked);
                i.putExtra("fromEditProject", fromEditProject);
                i.putExtra("pN", pName.getText().toString());
                i.putExtra("pSD", pStart.getText().toString());
                i.putExtra("pD", pDesc.getText().toString());
                i.putExtra("pED", pEnd.getText().toString());
                i.putExtra("pL", pLink.getText().toString());
                i.putExtra("project_id",project_id);

                startActivity(i);
            }

        });
        pEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EditProject.this, CalendarViewActivity.class);
                endDateClicked = true;
                i.putExtra("fromEditProject", fromEditProject);
                i.putExtra("pN", pName.getText().toString());
                i.putExtra("pSD", pStart.getText().toString());
                i.putExtra("pD", pDesc.getText().toString());
                i.putExtra("pED", pEnd.getText().toString());
                i.putExtra("pL", pLink.getText().toString());
                i.putExtra("endDateClicked", endDateClicked);
                i.putExtra("project_id",project_id);

                startActivity(i);

            }

        });

        if (intent != null) {
            sdPref = getSharedPreferences(START_DATE, MODE_PRIVATE);
            edPref = getSharedPreferences(END_DATE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sdPref.edit();
            SharedPreferences.Editor editor2 = edPref.edit();
            startDateClicked = intent.getBooleanExtra("startDateClicked", false);
            endDateClicked = intent.getBooleanExtra("endDateClicked", false);

            if (startDateClicked) {
                Log.d("Start Date Clicked", "Received the Intent.");
//                i.putExtra("pN", pName.getText().toString());
//                i.putExtra("pSD", pStart.getText().toString());
//                i.putExtra("pD", pDesc.getText().toString());
//                i.putExtra("pED", pEnd.getText().toString());
//                i.putExtra("pL", pLink.getText().toString());
                String name = intent.getStringExtra("pN");
                String description = intent.getStringExtra("pSD");
                String endDate = intent.getStringExtra("pED");
                String link = intent.getStringExtra("pL");
                startDateChosen = intent.getStringExtra("startDateChosen");
                editor.putString("ChosenStartDate", startDateChosen);
                editor.apply();
                pStart.setText(startDateChosen);
                pName.setText(name);
                pDesc.setText(description);
                pEnd.setText(endDate);
                pLink.setText(link);
            } else {
                updateSD();
            }

            if (endDateClicked) {
                String name = intent.getStringExtra("pN");
                String description = intent.getStringExtra("pSD");
                String endDate = intent.getStringExtra("pED");
                String link = intent.getStringExtra("pL");
                startDateChosen = intent.getStringExtra("startDateChosen");
                endDateChosen = intent.getStringExtra("endDateChosen");
                editor2.putString("ChosenEndDate", endDateChosen);
                editor2.apply();
                pEnd.setText(endDateChosen);
                pStart.setText(startDateChosen);
                pName.setText(name);
                pDesc.setText(description);
                pEnd.setText(endDate);
                pLink.setText(link);
            } else {
                updateED();
            }
        }
    }


    public void updateSD() {
        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
        String text = sdsp.getString("ChosenStartDate", "");
        Log.d("Inside UpdateSD Checking Text", text);
        pStart.setText(text);
    }

    public void updateED() {
        Log.d("Inside UpdateED", "endDate clicked is false");
        SharedPreferences edsp = getSharedPreferences(END_DATE, MODE_PRIVATE);
        String text2 = edsp.getString("ChosenEndDate", "");
        Log.d("Inside UpdateED Checking Text", text2);

        pEnd.setText(text2);
    }

}