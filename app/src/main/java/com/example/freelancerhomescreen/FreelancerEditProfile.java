package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FreelancerEditProfile extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    EditText freelancerName, freelancerDescription;
    Button saveChanges;
    CircleImageView pfp;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_edit_profile);
        prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        //todo: get from sharedPrefs

        DatabaseHandler db = new DatabaseHandler(this);
        Freelancer fl = db.getFreelancers(identity_id);
        pfp = findViewById(R.id.profile_image);
        int imageResource = getResources().getIdentifier(fl.getProfileImg(), "drawable", this.getPackageName());
        Drawable d= getResources().getDrawable(imageResource);
        pfp.setImageDrawable(d);
        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog();
            }
        });
        freelancerName = findViewById(R.id.nameEdit);
        freelancerDescription = findViewById(R.id.descEdit);
        saveChanges = findViewById(R.id.saveChanges);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fN = freelancerName.getText().toString();
                String fD = freelancerDescription.getText().toString();
                Freelancer f = new Freelancer(identity_id, fN, fD);
                db.updateFreelancer(f);
                Intent i = new Intent(getApplicationContext(), FreelancerOwnProfile.class);
                startActivity(i);
                //finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
    void showCustomDialog() {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setCancelable(true);
        d.setContentView(R.layout.activity_upload);
        d.show();
        ImageView camera = (ImageView) findViewById(R.id.editPhotoClickable);

    }



}