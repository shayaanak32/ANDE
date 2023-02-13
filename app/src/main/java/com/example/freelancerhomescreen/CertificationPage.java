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
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CertificationPage extends AppCompatActivity implements CertificationRecyclerAdapterInterface {
    DatabaseHandler db = new DatabaseHandler(this);
    //MaterialButton addCertificationBtn;
    SharedPreferences prefs;
    private final String IdentityID = "Identity ID";
    private final String UserID = "UserID";
    private final String RoleID = "RoleID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_page);
        recyclerView = (RecyclerView) findViewById(R.id.currentCertifications);
        Button addCertificationBtn = (Button) findViewById(R.id.goToAddExp);
        addCertificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CertificationPage.this, AddCertification.class);
                i.putExtra("fromCalendarViewActivity",true);
                startActivity(i);

            }
        });
        bindContactData();
        setAdapter();

         prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);

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
                            i = new Intent(CertificationPage.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(CertificationPage.this, FreelancerOwnProfile.class);
                            startActivity(i);
                        }else{
                            i = new Intent(CertificationPage.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(CertificationPage.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(CertificationPage.this, SettingsPage.class);
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
    public ArrayList<CertificationRecyclerItem> mCertifications = new ArrayList<>();



    private void setAdapter() {

        CertificationAdapter adapter = new CertificationAdapter(mCertifications, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CertificationPage.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void bindContactData() {
        DatabaseHandler db = new DatabaseHandler(this);
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        List<Certification> certificationList = db.getCertification(identity_id);
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
        Intent intent = new Intent(CertificationPage.this, EditCertificationActivity.class);
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