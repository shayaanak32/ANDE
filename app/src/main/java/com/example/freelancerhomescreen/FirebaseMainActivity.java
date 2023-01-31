package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseMainActivity extends AppCompatActivity {
    //private static final String TAG = "MainActivity";
    CreateTables ct = new CreateTables(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //basicReadWrite();
        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Nav Item Selected", "Nav Item Selected!!!!!");

                switch (item.getItemId()) {
                    case R.id.profileNav:

                        i = new Intent(FirebaseMainActivity.this, FirebaseMainActivity.class);
                        startActivity(i);
                        finish();
                        return true;

                    case R.id.settingsNav:
                        i = new Intent(FirebaseMainActivity.this, DebugPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });


        CardView experienceCard = (CardView) findViewById(R.id.experienceCard);
        CardView projectsCard = (CardView) findViewById(R.id.projectsCard);
        CardView skillsCard = (CardView) findViewById(R.id.skillsCard);
        CardView certificationsCard = (CardView) findViewById(R.id.certificationsCard);
//        LinearLayout certificationsLinearLayout = (LinearLayout) findViewById(R.id.certificationsLinearLayout);
//        LinearLayout experienceLinearLayout = (LinearLayout) findViewById(R.id.experienceLinearLayout);
//        LinearLayout projectsLinearLayout = (LinearLayout) findViewById(R.id.projectsLinearLayout);
//        LinearLayout skillsLinearLayout = (LinearLayout) findViewById(R.id.settingsLinearLayout);
        Button skillsBtn = (Button) findViewById(R.id.skillsButton);
        experienceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ct.openExperience();
                Intent i = new Intent(FirebaseMainActivity.this, ExperienceMainActivity.class);
                startActivity(i);

            }

        });

        projectsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String endDate1 = "2021-12-21 10:20:05.123";
//                String endDate2 = "2022-12-25 10:20:05.123";
//                String endDate3 = "2019-07-08 10:20:05.123";
//                String endDate4 = "2022-08-01 10:20:05.123";
//                Log.d("About to Create!!", "Not Created");
//
////            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
////            Date d1 = format.parse(endDate1);
////            Date d2 = format.parse(endDate2);
////            Date d3 = format.parse(endDate3);
////            Date d4 = format.parse(endDate4);
//                Certification c1 = new Certification("OCP 11 Java Programmer", "https://www.aws.training/certification", endDate1, "Java,JavaScript", "Description1");
//                Certification c2 = new Certification("IBM Professional Data Science Certification", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate2, "Python,Java", "Description2");
//                Certification c3 = new Certification("DataCamp Certified Data Professional", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate3, "Java", "Description3");
//                Certification c4 = new Certification("Google Cloud Certified Data Scientist", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate4, "Java", "Description4");
//                ct.addCertifications(c1);
//                ct.addCertifications(c2);
//                ct.addCertifications(c3);
//                ct.addCertifications(c4);
                Log.d("Project Card Clicked", "Clicked");

                ct.openProjects();
                Intent i = new Intent(FirebaseMainActivity.this, ProjectsPage.class);
                startActivity(i);

            }
        });
        skillsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Insert Expeirence", "About to Insert Expeirence");
//                String startDate1 = "2020-09-21 10:20:05.123";
//                String startDate2 = "2020-12-25 10:20:05.123";
//                String startDate3 = "2019-01-01 10:20:05.123";
//
//                String endDate1 = "2021-12-21 10:20:05.123";
//                String endDate2 = "2022-12-25 10:20:05.123";
//                String endDate3 = "2019-07-08 10:20:05.123";
//
//                Experience e1 = new Experience("Java GUI Support Helper", startDate1, endDate1, "My experience at GOogle included" +
//                        "me helping out in the Java Support role, which helped teach me a lot of things!!", "Google");
//
//                Experience e2 = new Experience("Microsoft DevOps Assistant", startDate2, endDate2, "My experience at Microsoft included" +
//                        "me helping out in the DevOps Support role, which helped teach me a lot of things with regards to testing, maintenance, etc.", "Microsoft");
//
//                Experience e3 = new Experience("Apple Mobile Developer", startDate3, endDate3, "My experience at Apple included" +
//                        "me helping out in the Swift Support role, which helped teach me a lot on Mobile Apps, and how to optimise performance", "Apple");
//
//                ct.addExperience(e1);
//                ct.addExperience(e2);
//                ct.addExperience(e3);
                ct.openSkills();
                Log.d("Skills Card Clicked", "Clicked");

                Intent i = new Intent(FirebaseMainActivity.this, SkillsPage.class);
                startActivity(i);
                Log.d("Intent", "intent is already created");
            }

        });
        certificationsCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                String startDate1 = "2020-09-21 10:20:05.123";
//                String startDate2 = "2020-12-25 10:20:05.123";
//                String startDate3 = "2019-01-01 10:20:05.123";
//
//                String endDate1 = "2021-12-21 10:20:05.123";
//                String endDate2 = "2022-12-25 10:20:05.123";
//                String endDate3 = "2019-07-08 10:20:05.123";
////                Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1","1",1);
////                Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1","2",2);
////                Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3",3);
//
//                Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1", 1);
//                Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1", 2);
//                Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3", 3);
//
//                ct.addProjects(p1);
//                ct.addProjects(p2);
//                ct.addProjects(p3);
                ct.openCertifications();
                Log.d("Certification Card Clicked", "Clicked");
                Intent i = new Intent(FirebaseMainActivity.this, CertificationPage.class);
                startActivity(i);
            }
        });

        skillsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Skills Card Clicked", "Clicked");
                ct.openSkills();
                Intent i = new Intent(FirebaseMainActivity.this, SkillsPage.class);
                startActivity(i);
            }
        });
    }


//    public void basicReadWrite() {
//        // [START write_message]
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//        // [END write_message]
//
//        // [START read_message]
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//        // [END read_message]
//    }

    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.skillsButton:
//                //db.createTable();
//
//                Log.d("hi from onClick switch", "I am inside onClick switch and being clicked");
//
//
//        }
    }
}
