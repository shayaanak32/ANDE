package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FreelancerOwnProfile extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    TextView freelancerName, freelancerDescription, editBtn;
    CircleImageView pfp;
    private final String IdentityID = "IdentityID";
    private final String UserID = "UserID";
    private final String RoleID = "RoleID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_own_profile);
        SharedPreferences prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int userId = Integer.parseInt(prefs.getString("Identity ID","-1"));
        Log.d("contents own profile", userId+"");
        //todo: get from sharedPrefs

        DatabaseHandler db = new DatabaseHandler(this);
        Freelancer fl = db.getFreelancers(userId);
        pfp = findViewById(R.id.profile_image);
        Log.d("konichiwa888",fl.getProfileImg());
        int imageResource = getResources().getIdentifier(fl.getProfileImg(), "drawable", this.getPackageName());
        Drawable d= getResources().getDrawable(imageResource);
        pfp.setImageDrawable(d);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        editBtn = findViewById(R.id.editFreelancer);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FreelancerEditProfile.class);
                startActivity(i);
                // comment as user should be able to go back if they want
                //finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int userId = Integer.parseInt(prefs.getString("Identity ID","-1"));

        DatabaseHandler db = new DatabaseHandler(this);
        Freelancer fl = db.getFreelancer(userId);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        editBtn = findViewById(R.id.editFreelancer);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FreelancerEditProfile.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        SharedPreferences prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int userId = Integer.parseInt(prefs.getString("Identity ID","-1"));
        Intent i = new Intent(getApplicationContext(), FreelancerOwnProfile.class);
        switch(view.getId()){
            case R.id.certificationsCard:
                i = new Intent(getApplicationContext(),CertificationPage.class);
                i.putExtra("profileid", userId);
                break;
            case R.id.skillsCard:
                i = new Intent(getApplicationContext(),SkillsPage.class);
//                startActivity(i);
                i.putExtra("profileid", userId);
                break;
            case R.id.experienceCard:
                i = new Intent(getApplicationContext(),ExperienceMainActivity.class);
//                startActivity(i);
                i.putExtra("profileid", userId);
                break;
            case R.id.projectsCard:
                i = new Intent(getApplicationContext(),ProjectsPage.class);
//                startActivity(i);
                i.putExtra("profileid", userId);
                break;
        }
        startActivity(i);

    }


}