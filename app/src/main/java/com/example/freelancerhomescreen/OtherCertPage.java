package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OtherCertPage extends AppCompatActivity implements CertificationRecyclerAdapterInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_cert_page);
        recyclerView = (RecyclerView) findViewById(R.id.currentCertifications);
        bindContactData();
        setAdapter();
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
        List<Certification> certificationList = db.getAllCertifications();
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
        Log.d("skills in recycler item", mCertifications.get(position).getSkills());
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
        Log.d("position", Integer.toString(position));
        String url = mCertifications.get(position).getLink();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}