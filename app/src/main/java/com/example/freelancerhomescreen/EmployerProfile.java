package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployerProfile extends AppCompatActivity {
    ListView listView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);
        CircleImageView pfp = findViewById(R.id.profile_image);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
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
                            i = new Intent(EmployerProfile.this, ProfilePage.class);
                            startActivity(i);
                        }else if(userRole==2){
                            i = new Intent(EmployerProfile.this, FreelancerOwnProfile.class);
                            startActivity(i);
                        }else{
                            i = new Intent(EmployerProfile.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(EmployerProfile.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(EmployerProfile.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });

        Employer emp = db.getContact(profileID);
        // to make the thing dynamic
        TextView cName = (TextView)findViewById(R.id.companyName);
        TextView cAbout = (TextView)findViewById(R.id.aboutOrgInput);
        cName.setText(emp.getCompanyName());
        cAbout.setText(emp.getDescription());
        int imageResource = getResources().getIdentifier(emp.getProfileImg(), "drawable", this.getPackageName());
        Drawable d= getResources().getDrawable(imageResource);
        pfp.setImageDrawable(d);
        listView=(ListView)findViewById(R.id.orgPriosInput);
        textView=(TextView)findViewById(R.id.textViewProg);
        String prioStr = emp.getPriorities();
        ArrayList<String> listItem = new ArrayList<String>(Arrays.asList(prioStr.split(",")));
        if(listItem.size()>0&& listItem.get(0).trim()!=""){
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
            listView.setAdapter(adapter);
        }
        if (listView.getCount()==0) {
            listView.setVisibility(View.GONE);
            TextView textView = findViewById(R.id.noContentMsg);
            textView.setText("Nothing yet");
            textView.setTextSize(20);
        }
    }
}