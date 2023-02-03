package com.example.freelancerhomescreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
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
                            i = new Intent(ProjectsPage.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(ProjectsPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                        }else{
                            i = new Intent(ProjectsPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(ProjectsPage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(ProjectsPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });


        final ProjectsCustomAdapter adapter = new ProjectsCustomAdapter(this,
                 listItem);

        listView.setAdapter(adapter);
    }
}
