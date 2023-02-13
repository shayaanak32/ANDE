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
        SharedPreferences prefs =getSharedPreferences("UserDetails", MODE_PRIVATE);
        BottomNavigationView bv =findViewById(R.id.bottomNavigationView);
        int userRole = Integer.parseInt(prefs.getString("RoleID","-1"));


        bv.setSelectedItemId(R.id.feedNav);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        if(userRole==1){
                            i = new Intent(OtherProjPage.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(OtherProjPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(OtherProjPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(OtherProjPage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(OtherProjPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });

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
