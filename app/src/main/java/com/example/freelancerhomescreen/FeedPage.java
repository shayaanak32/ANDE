package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class FeedPage extends AppCompatActivity {
    private RecyclerView fRecyclerView;
    private ArrayList<Recycleritem> freelancers = new ArrayList<>();
    SharedPreferences prefs;
    private final String TAG = "FeedPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.activity_feed_page);
        setUIRef();
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int userRole = Integer.parseInt(prefs.getString("RoleID","-1"));
        switch(userRole){
            case 1:
                ArrayList<Freelancer> fl = db.getAllFreelancer();
                for(Freelancer f : fl){
                    freelancers.add(new Recycleritem(f.getId(),f.getName(), f.getIndividualSkill(f.getSkills()), f.getDescription(), f.getProfileImg()));
                    Log.d("skill debug", f.getIndividualSkill(f.getSkills()).length+"");
                }
                break;
            case 2:
                ArrayList<Employer> emp = db.getAllEmployers();
                for(Employer f : emp){
                    freelancers.add(new Recycleritem(f.getEmployerID(), f.getCompanyName(), f.getBrokenPriorities(f.getPriorities()), f.getDescription(),f.getProfileImg()));
                }

                break;
        }

        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setSelectedItemId(R.id.feedNav);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        if(userRole==1){
                            i = new Intent(FeedPage.this, ProfilePage.class);
                            startActivity(i);
                        }else if(userRole==2){
                            i = new Intent(FeedPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                        }else{
                            i = new Intent(FeedPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;

                    case R.id.feedNav:
//                        i = new Intent(FeedPage.this, FeedPage.class);
//                        startActivity(i);
//                        finish();
                        fRecyclerView.getLayoutManager().scrollToPosition(0);
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(FeedPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }
    private void setUIRef()
    {
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int userRole = Integer.parseInt(prefs.getString("RoleID","-1"));
        //Reference of RecyclerView
        fRecyclerView = findViewById(R.id.freelancerList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FeedPage.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        fRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        RecyclerItemArrayAdapter myRecyclerViewAdapter = new RecyclerItemArrayAdapter(freelancers, new RecyclerItemArrayAdapter.MyRecyclerViewItemClickListener()
        {
            //Handling clicks
            @Override
            public void onItemClicked(Recycleritem country)
            {
                //todo: retrieve user role from shared preference
                Intent i;
                int profileID = country.getId();

                switch(userRole){
                    case 1:
                        i = new Intent(getApplicationContext(), FreelancerProfile.class);
                        i.putExtra("profileid", profileID);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), EmployerProfile.class);
                        i.putExtra("profileid", profileID);
                        startActivity(i);
                        break;

                }

            }
        });
        //Set adapter to RecyclerView
        fRecyclerView.setAdapter(myRecyclerViewAdapter);
    }



}