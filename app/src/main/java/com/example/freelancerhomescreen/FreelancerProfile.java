package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class FreelancerProfile extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "FreelancerProfile";
    TextView freelancerName, freelancerDescription;
    CardView skills, proj, exp, certs;
    CircleImageView pfp;
    int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        DatabaseHandler db = new DatabaseHandler(this);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);

        Freelancer fl = db.getFreelancers(profileID);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());
        pfp = findViewById(R.id.profile_image);
        imageResource = getResources().getIdentifier(fl.getProfileImg(), "drawable", this.getPackageName());
        Drawable d= getResources().getDrawable(imageResource);
        pfp.setImageDrawable(d);
        skills = findViewById(R.id.skillsCard);
        certs = findViewById(R.id.certificationsCard);
        proj = findViewById(R.id.projectsCard);
        exp = findViewById(R.id.experienceCard);
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OtherSkillsPage.class);
                i.putExtra("profileid", profileID);
                startActivity(i);
            }
        });
        certs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OtherCertPage.class);
                i.putExtra("profileid", profileID);
                startActivity(i);
            }
        });
        proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OtherProjPage.class);
                i.putExtra("profileid", profileID);
                startActivity(i);
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OtherExpPage.class);
                i.putExtra("profileid", profileID);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i= new Intent(this, FreelancerProfile.class);
        Intent intent = getIntent();
        int profileID = intent.getIntExtra("profileid",0);
        switch (view.getId()) {
            case R.id.certificationsCard:
                i.putExtra("profileid", profileID);
//                startActivity(i);
                break;
            case R.id.skillsCard:
                i = new Intent(this, OtherSkillsPage.class);
                i.putExtra("profileid", profileID);
//                startActivity(i);
                break;
            case R.id.experienceCard:
                i = new Intent(this, OtherExpPage.class);
                i.putExtra("profileid", profileID);
//                startActivity(i);
                break;
            case R.id.projectsCard:
                i = new Intent(this, OtherProjPage.class);
                i.putExtra("profileid", profileID);
//                startActivity(i);
                break;

        }
        startActivity(i);
//

    }

}