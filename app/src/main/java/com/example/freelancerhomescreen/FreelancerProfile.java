package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class FreelancerProfile extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "FreelancerProfile";
    TextView freelancerName, freelancerDescription;
    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        DatabaseHandler db = new DatabaseHandler(this);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);

        Freelancer fl = db.getFreelancer(profileID);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());
        grid = findViewById(R.id.grid);
        grid.getColumnCount();
        grid.getChildAt(0);
    }

    @Override
    public void onClick(View view) {
        Log.d("Status ", view.getId()+" !!!");
        Log.d("Status ", R.id.certificationsCard+" !!!");
        Log.d("Status ", R.id.skillsCard+" !!!");
        Log.d("Status ", R.id.experienceCard+" !!!");
        Log.d("Status ", R.id.projectsCard+" !!!");
        Intent i= new Intent(this, FreelancerProfile.class);
        Log.d(TAG, "something got clicked!");
        switch (view.getId()) {
            case R.id.certificationsCard:
                i = new Intent(this, OtherCertPage.class);
//                startActivity(i);
                Log.d(TAG, "onClick: certificationsCard");
                break;
            case R.id.skillsCard:
                i = new Intent(this, OtherSkillsPage.class);
//                startActivity(i);
                break;
            case R.id.experienceCard:
                i = new Intent(this, OtherExpPage.class);
//                startActivity(i);
                break;
            case R.id.projectsCard:
                i = new Intent(this, OtherProjPage.class);
//                startActivity(i);
                break;

        }
        startActivity(i);
//

    }

}