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
    }
}