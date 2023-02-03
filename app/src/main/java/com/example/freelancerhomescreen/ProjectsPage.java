package com.example.freelancerhomescreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProjectsPage extends AppCompatActivity {
    ListView listView;
    TextView addNP;
    ArrayList<Projects> listItem;
    private final String TAG = "ProjectsPage";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        DatabaseHandler db = new DatabaseHandler(this);
        listView = (ListView) findViewById(R.id.ProjectListView);
        addNP = findViewById(R.id.addNewProject);
        addNP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddProject.class);
                startActivity(i);
            }
        });
        prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        listItem = db.getAllProjects(identity_id);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int project_id = listItem.get(i).getProjectID();

                // set extra, so when opening new page can get project id data
                Intent intent = new Intent(getApplicationContext(), EditProject.class);
                intent.putExtra("project_id", project_id);

                startActivity(intent);
            }
        });

        final ProjectsCustomAdapter adapter = new ProjectsCustomAdapter(this,
                 listItem);

        listView.setAdapter(adapter);
        //todo: replace the user id with the one in sharedPref
    }
}
