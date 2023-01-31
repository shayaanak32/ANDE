package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ProfilePage extends AppCompatActivity {
    ListView listView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        CircleImageView pfp = findViewById(R.id.profile_image);
        Button b = findViewById(R.id.editProfileBtn);
        BottomNavigationView bv =findViewById(R.id.bottomNavigationView);
        DatabaseHandler db = new DatabaseHandler(this);
        bv.setSelectedItemId(R.id.profileNav);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilePage.this, EditProfile.class);
                startActivity(i);
            }
        });
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                         i = new Intent(ProfilePage.this, ProfilePage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.feedNav:
                         i = new Intent(ProfilePage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                         i = new Intent(ProfilePage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });
        Employer emp = db.getContact(1);
        // to make the thing dynamic
        TextView cName = (TextView)findViewById(R.id.companyName);
        TextView cAbout = (TextView)findViewById(R.id.aboutOrgInput);
        cName.setText(emp.getCompanyName());
        cAbout.setText(emp.getDescription());
        pfp.setImageResource(R.drawable.profile_pic);
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

    @Override
    protected void onResume() {
        super.onResume();
        CircleImageView pfp = findViewById(R.id.profile_image);
        Button b = findViewById(R.id.editProfileBtn);
        BottomNavigationView bv =findViewById(R.id.bottomNavigationView);
        DatabaseHandler db = new DatabaseHandler(this);
        Employer emp = db.getContact(1);
        // to make the thing dynamic
        TextView cName = (TextView)findViewById(R.id.companyName);
        TextView cAbout = (TextView)findViewById(R.id.aboutOrgInput);
        cName.setText(emp.getCompanyName());
        cAbout.setText(emp.getDescription());
        pfp.setImageResource(R.drawable.profile_pic);
        listView=(ListView)findViewById(R.id.orgPriosInput);
        textView=(TextView)findViewById(R.id.textViewProg);
        String prioStr = emp.getPriorities();
        ArrayList<String> listItem = new ArrayList<String>(Arrays.asList(prioStr.split(",")));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
    }
}