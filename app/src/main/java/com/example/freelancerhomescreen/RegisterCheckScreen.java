package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterCheckScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_check_screen);
        DatabaseHandler db = new DatabaseHandler(this);

        Button freelancerBtn = (Button)findViewById(R.id.freelancerBtn);
        Button employerBtn = (Button)findViewById(R.id.employerBtn);

        freelancerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterCheckScreen.this, RegisterFreelancer.class));
            }
        });


        employerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterCheckScreen.this, RegisterEmployer.class));
            }
        });

    }
}