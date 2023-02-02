package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity extends AppCompatActivity {
    private final String IdentityID = "IdentityID";
    private final String UserID = "UserID";
    private final String RoleID = "RoleID";
    SharedPreferences userDetailsPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DatabaseHandler db = new DatabaseHandler(this);
        CardView experienceCard = (CardView) findViewById(R.id.experienceCard);
        CardView projectsCard = (CardView) findViewById(R.id.projectsCard);
        CardView skillsCard = (CardView) findViewById(R.id.skillsCard);
        CardView certificationsCard = (CardView) findViewById(R.id.certificationsCard);


        userDetailsPref = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int a = userDetailsPref.getInt(IdentityID, 0);
        int b = userDetailsPref.getInt(UserID, 0);
        int c = userDetailsPref.getInt(RoleID, 0);
        Log.d("IdentityID", Integer.toString(a));
        Log.d("UserID", Integer.toString(b));
        Log.d("RoleID", Integer.toString(c));
//        experienceCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(TestActivity.this, ExperienceMainActivity.class);
//                startActivity(i);
//
//            }
//
//        });
//
//        projectsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                String endDate1 = "2021-12-21 10:20:05.123";
////                String endDate2 = "2022-12-25 10:20:05.123";
////                String endDate3 = "2019-07-08 10:20:05.123";
////                String endDate4 = "2022-08-01 10:20:05.123";
////                Log.d("About to Create!!", "Not Created");
////
//////            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//////            Date d1 = format.parse(endDate1);
//////            Date d2 = format.parse(endDate2);
//////            Date d3 = format.parse(endDate3);
//////            Date d4 = format.parse(endDate4);
////                Certification c1 = new Certification("OCP 11 Java Programmer", "https://www.aws.training/certification", endDate1, "Java,JavaScript", "Description1");
////                Certification c2 = new Certification("IBM Professional Data Science Certification", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate2, "Python,Java", "Description2");
////                Certification c3 = new Certification("DataCamp Certified Data Professional", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate3, "Java", "Description3");
////                Certification c4 = new Certification("Google Cloud Certified Data Scientist", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate4, "Java", "Description4");
//
//                Log.d("Project Card Clicked", "Clicked");
//
////                ct.openProjects();
//                Intent i = new Intent(TestActivity.this, ProjectsPage.class);
//                startActivity(i);
//
//            }
//        });
//        skillsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Log.d("Insert Expeirence", "About to Insert Expeirence");
////                String startDate1 = "2020-09-21 10:20:05.123";
////                String startDate2 = "2020-12-25 10:20:05.123";
////                String startDate3 = "2019-01-01 10:20:05.123";
////
////                String endDate1 = "2021-12-21 10:20:05.123";
////                String endDate2 = "2022-12-25 10:20:05.123";
////                String endDate3 = "2019-07-08 10:20:05.123";
////
////                Experience e1 = new Experience("Java GUI Support Helper", startDate1, endDate1, "My experience at GOogle included" +
////                        "me helping out in the Java Support role, which helped teach me a lot of things!!", "Google");
////
////                Experience e2 = new Experience("Microsoft DevOps Assistant", startDate2, endDate2, "My experience at Microsoft included" +
////                        "me helping out in the DevOps Support role, which helped teach me a lot of things with regards to testing, maintenance, etc.", "Microsoft");
////
////                Experience e3 = new Experience("Apple Mobile Developer", startDate3, endDate3, "My experience at Apple included" +
////                        "me helping out in the Swift Support role, which helped teach me a lot on Mobile Apps, and how to optimise performance", "Apple");
////
////                ct.addExperience(e1);
////                ct.addExperience(e2);
////                ct.addExperience(e3);
//                ct.openSkills();
//                Log.d("Skills Card Clicked", "Clicked");
//
//                Intent i = new Intent(TestActivity.this, SkillsPage.class);
//                startActivity(i);
//                Log.d("Intent", "intent is already created");
//            }
//
//        });
//        certificationsCard.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                ct.openCertifications();
//                Log.d("Certification Card Clicked", "Clicked");
//                Intent i = new Intent(TestActivity.this, CertificationPage.class);
//                startActivity(i);
//            }
//        });

    }
}