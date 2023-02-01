package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegisterFreelancer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_freelancer);
    }

    EditText nameInput = (EditText) findViewById(R.id.name_input_fl);
    EditText emailInput = (EditText) findViewById(R.id.email_input_fl);
    EditText passwordInput = (EditText) findViewById(R.id.password_input_fl);
//    EditText descInput = (EditText) ;
}