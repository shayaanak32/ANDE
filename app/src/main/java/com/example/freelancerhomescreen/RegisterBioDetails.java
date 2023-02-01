package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterBioDetails extends AppCompatActivity implements View.OnClickListener {

    EditText bioInput;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bio_details);

        bioInput = findViewById(R.id.bioInput);

        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.nextBtn:
                if(String.valueOf(bioInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }else{
                    i = new Intent(this, Register.class);
                    String bio = String.valueOf(bioInput.getText());
                    i.putExtra("status", "freelancer");
                    i.putExtra("email", email);
                    i.putExtra("name", name);
                    i.putExtra("bio", bio);
                    startActivity(i);
                }
                break;
        }
    }
}