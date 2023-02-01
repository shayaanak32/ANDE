package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.RoundedCorner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class FreelancerProfile extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    TextView freelancerName, freelancerDescription;
    //InsertCertificationData db = new InsertCertificationData(this);
//    CreateTables ct = new CreateTables(this);
//    ShowData sd = new ShowData(this);
//    CreateTables ct = new CreateTables(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);

        DatabaseHandler db = new DatabaseHandler(this);
        Freelancer fl = db.getFreelancer(profileID);
        Log.d("OIOIOIIOIOIOIOIOIO", fl.getName());
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());

    }

    @Override
    public void onClick(View view) {

        Log.d(TAG, "onClick: putang ina mo");
//        switch (view.getId()) {
//            case R.id.showDataButton:
//                Log.d("Status ", "Inside onClick !!!");
//                //sd.displayDB();
//            case R.id.certificationsLinearLayout:
//                //db.createTable();
////        try {
//                String endDate1 = "2021-12-21 10:20:05.123";
//                String endDate2 = "2022-12-25 10:20:05.123";
//                String endDate3 = "2019-07-08 10:20:05.123";
//                String endDate4 = "2022-08-01 10:20:05.123";
//                Log.d("About to Create!!","Not Created");
//
////            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
////            Date d1 = format.parse(endDate1);
////            Date d2 = format.parse(endDate2);
////            Date d3 = format.parse(endDate3);
////            Date d4 = format.parse(endDate4);
//
//
//                Certification c1 = new Certification("OCP 11 Java Programmer","https://www.aws.training/certification",endDate1,"Java,JavaScript", "Description1");
//                Certification c2 = new Certification("IBM Professional Data Science Certification","https://www.coursera.org/professional-certificates/ibm-data-science",endDate2,"Python,Java", "Description2");
//                Certification c3 = new Certification("DataCamp Certified Data Professional","https://www.coursera.org/professional-certificates/ibm-data-science",endDate3,"Java", "Description3");
//                Certification c4 = new Certification("Google Cloud Certified Data Scientist","https://www.coursera.org/professional-certificates/ibm-data-science",endDate4,"Java", "Description4");
//                ct.addCertifications(c1);
//                ct.addCertifications(c2);
//                ct.addCertifications(c3);
//                ct.addCertifications(c4);
//                //ct.showTableData();
//                Intent i = new Intent(this, CertificationPage.class);
//                startActivity(i);
//
//        }

    }




}