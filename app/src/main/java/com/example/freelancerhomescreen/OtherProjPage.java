package com.example.freelancerhomescreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OtherProjPage extends AppCompatActivity {
    ListView listView;
    TextView addNP;
    ArrayList<Projects> listItem;
    private final String TAG = "ProjectsPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_proj_page);
        DatabaseHandler db = new DatabaseHandler(this);
        listView = (ListView) findViewById(R.id.ProjectListView);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
        listItem = db.getAllProjects(profileID);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int project_id = listItem.get(i).getProjectID();
                Log.d(TAG, project_id+"");

                // set extra, so when opening new page can get project id data
                Intent intent = new Intent(getApplicationContext(), ViewProject.class);
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
