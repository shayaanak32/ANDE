package com.example.freelancerhomescreen;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InsertEmployerData extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Employer e1 = new Employer("Prince Kharal Tech", "Singapore", "Description1");
        Employer e2 = new Employer("NCS", "Singapore", "Description2");
        Employer e3 = new Employer("A*STAR", "Singapore", "Description3");


    }

}
