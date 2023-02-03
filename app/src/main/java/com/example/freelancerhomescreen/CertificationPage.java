package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

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
        prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        List<Certification> certificationList = db.getCertification(identity_id);
        int index = 0;
        for (Certification e : certificationList) {
            String name = e.getName();
            String link = e.getLink();
            String endDate = e.getEndDate();
            String skills = e.getSkills();
            String description = e.getDescription();
            Log.d("Skills ", skills);
            Log.d("End Date ", endDate);
            Log.d("Description ", description);
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
        Log.d("skills in recycler item", mCertifications.get(position).getSkills());
        startActivity(intent);

    }

    @Override
    public void goToLink(int position) {
        Log.d("position", Integer.toString(position));
        String url = mCertifications.get(position).getLink();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}