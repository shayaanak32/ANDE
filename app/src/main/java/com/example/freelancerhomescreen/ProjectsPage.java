package com.example.freelancerhomescreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProjectsPage extends AppCompatActivity {
    ListView listView;
    TextView projName;
    ArrayList<Projects> listItem;
    private final String TAG = "ProjectsPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        DatabaseHandler db = new DatabaseHandler(this);
        listView = (ListView) findViewById(R.id.ProjectListView);
//        projName = (TextView) findViewById(R.id.projName);
        String startDate1 = "2020-09-21 10:20:05.123";
        String startDate2 = "2020-12-25 10:20:05.123";
        String startDate3 = "2019-01-01 10:20:05.123";

        String endDate1 = "2021-12-21 10:20:05.123";
        String endDate2 = "2022-12-25 10:20:05.123";
        String endDate3 = "2019-07-08 10:20:05.123";
//                Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1","1",1);
//                Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1","2",2);
//                Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3",3);

        Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1", 1);
        Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1", 1);
        Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3", 3);
        db.addProjects(p1);
        db.addProjects(p2);
        db.addProjects(p3);
        listItem = db.getAllProjects(1);
        Log.d(TAG, listItem.get(4).getProjectID()+"");
        final ProjectsCustomAdapter adapter = new ProjectsCustomAdapter(this,
                 listItem);

        listView.setAdapter(adapter);
        //todo: replace the user id with the one in sharedPref
    }
}
