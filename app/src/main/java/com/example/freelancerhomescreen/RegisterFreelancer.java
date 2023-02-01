package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFreelancer extends AppCompatActivity implements View.OnClickListener {

    EditText emailInput, nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_freelancer);

        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.nextBtn:
                if(String.valueOf(emailInput.getText()).isEmpty() || String.valueOf(nameInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }else{
                    i = new Intent(this, RegisterBioDetails.class);
                    String email = String.valueOf(emailInput.getText());
                    String name = String.valueOf(nameInput.getText());
                    i.putExtra("email", email);
                    i.putExtra("name", name);
                    startActivity(i);
                }
                break;
        }
    }
}