package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterBioDetails extends AppCompatActivity implements View.OnClickListener {

    EditText bioInput, pwInput;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bio_details);

        bioInput = findViewById(R.id.bioInput);
        pwInput = findViewById(R.id.userPwInput);
        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.nextBtn:
                if(String.valueOf(bioInput.getText()).isEmpty()||String.valueOf(pwInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }else{
                    String bio = String.valueOf(bioInput.getText());
                    String pw =String.valueOf(pwInput.getText());
                    DatabaseHandler db = new DatabaseHandler(this);
                    db.addFreelancer(name,email, pw, bio, "","");
                    int id = db.getFreelancerUserIdByEmail(email);
                    db.addUser(name,2,email,pw,id);
                    Toast.makeText(this, "Successful Registration", Toast.LENGTH_SHORT).show();
                    i = new Intent(this, LoginScreen.class);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }
}