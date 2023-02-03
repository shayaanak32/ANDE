package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class OtherCertPage extends AppCompatActivity implements CertificationRecyclerAdapterInterface {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_cert_page);
        recyclerView = (RecyclerView) findViewById(R.id.currentCertifications);
        bindContactData();
        setAdapter();
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
                            i = new Intent(OtherCertPage.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(OtherCertPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(OtherCertPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(OtherCertPage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(OtherCertPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    public List<Certification> contactsList;
    public RecyclerView recyclerView;
    DatabaseHandler db = new DatabaseHandler(this);
    public ArrayList<CertificationRecyclerItem> mCertifications = new ArrayList<>();


    private void setAdapter() {

        CertificationAdapter adapter = new CertificationAdapter(mCertifications, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int profile = Integer.parseInt(prefs.getString("Identity ID","-1"));
        List<Certification> certificationList = db.getCertification(1);
        int index = 0;
        for (Certification e : certificationList) {
            String name = e.getName();
            String link = e.getLink();
            String endDate = e.getEndDate();
            String skills = e.getSkills();
            String description = e.getDescription();

            mCertifications.add(new CertificationRecyclerItem(name, link, endDate, skills, description));
            index++;
        }


    }


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, EditCertificationActivity.class);
        boolean fromCertificationPage = true;
        intent.putExtra("name", mCertifications.get(position).getName());
        intent.putExtra("link", mCertifications.get(position).getLink());
        intent.putExtra("endDate", mCertifications.get(position).getEndDate());
        intent.putExtra("description", mCertifications.get(position).getDescription());
        intent.putExtra("skills", mCertifications.get(position).getSkills());
        intent.putExtra("fromCertificationPage",fromCertificationPage);
        startActivity(intent);

    }

    @Override
    public void goToLink(int position) {

        String url = mCertifications.get(position).getLink();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}