package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Register extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle registerInfo = getIntent().getExtras();

        if(registerInfo != null){
            String status = registerInfo.getString("status");
            if(status.equals("freelancer")){
                // is freelancer
                String email = getIntent().getExtras().getString("email");
                String name = getIntent().getExtras().getString("name");
                String bio = getIntent().getExtras().getString("bio");

                Log.d("FREELANCER Input",
                        "Email: " + email +
                            "\nName: " + name +
                            "\nBio: " + bio);

            }else{
                // is employer
                String email = getIntent().getExtras().getString("email");
                String name = getIntent().getExtras().getString("name");
                String uen = getIntent().getExtras().getString("uen");
                String role = getIntent().getExtras().getString("role");
                String info = getIntent().getExtras().getString("info");

                Log.d("EMPLOYER Input",
                        "Email: " + email +
                            "\nName: " + name +
                            "\nUEN: " + uen +
                            "\nRole: " + role +
                            "\nInfo: " + info);

            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.freelancerBtn:
                i = new Intent(this, RegisterFreelancer.class);
                startActivity(i);
                break;
            case R.id.employerBtn:
                i = new Intent(this, RegisterEmployer.class);
                startActivity(i);
                break;
        }
    }
}