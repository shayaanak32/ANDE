package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewProject extends AppCompatActivity {
    TextView pName, pStart, pEnd, pLink, pDesc;
    ListView skillsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);
        // get extras from the previous page
        Intent intent = getIntent();
        int project_id = intent.getIntExtra("project_id", 0);
        DatabaseHandler db = new DatabaseHandler(this);
        Projects p1 = db.get1Project(project_id);
        pName = findViewById(R.id.viewProjName);
        pStart = findViewById(R.id.viewStartDatePicker);
        pEnd = findViewById(R.id.viewEndDatePicker);
        pLink = findViewById(R.id.viewpasteLink);
        skillsList = findViewById(R.id.viewProjSkills);
        pDesc = findViewById(R.id.viewPDesc);
        ArrayList<String> skills = new ArrayList<>(Arrays.asList(p1.getSkills().split(",")));
        pName.setText(p1.getNameOfProject());
        pDesc.setText(p1.getDescription());
        pStart.setText(p1.getStartDate());
        pEnd.setText(p1.getEndDate());
        pLink.setText(p1.getLink());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, skills);
        skillsList.setAdapter(adapter);
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
                            i = new Intent(ViewProject.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(ViewProject.this, FreelancerOwnProfile.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(ViewProject.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(ViewProject.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(ViewProject.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}