package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.freelancerhomescreen.CreateTables;
import com.example.freelancerhomescreen.DatabaseHandler;
import com.example.freelancerhomescreen.Employer;
import com.example.freelancerhomescreen.Freelancer;
import com.example.freelancerhomescreen.ProfilePage;
import com.example.freelancerhomescreen.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateTables cr = new CreateTables(this);
        DatabaseHandler db = new DatabaseHandler(this);
        Button b = findViewById(R.id.startBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProfilePage.class);
                startActivity(i);
            }
        });
        Log.d("Insert data", "Inserting");
        db.addContact(new Employer("test","test","test","test@test.com"));
        db.addFreelancers(new Freelancer("test","test@test.com","test","com","NA,NA,NA"));
        ArrayList<Employer> emp = db.getAllEmployers();
        for(Employer e : emp){
            Log.d("Name:", e.getCompanyName()+"000000000000000000000000000000");
        }
    }
}