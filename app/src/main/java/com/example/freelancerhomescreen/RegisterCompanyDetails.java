package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCompanyDetails extends AppCompatActivity implements View.OnClickListener {

    EditText uenInput, roleInput;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company_details);

        uenInput = findViewById(R.id.uenInput);
        roleInput = findViewById(R.id.roleInput);

        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.nextBtn:
                if(String.valueOf(uenInput.getText()).isEmpty() || String.valueOf(roleInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }else {
                    i = new Intent(this, RegisterCompanyBioDetails.class);
                    String uen = String.valueOf(uenInput.getText());
                    String role = String.valueOf(roleInput.getText());
                    i.putExtra("email", email);
                    i.putExtra("name", name);
                    i.putExtra("uen", uen);
                    i.putExtra("role", role);
                    startActivity(i);
                }
                break;
        }
    }
}