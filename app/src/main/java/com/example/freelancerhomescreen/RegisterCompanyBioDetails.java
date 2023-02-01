package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCompanyBioDetails extends AppCompatActivity implements View.OnClickListener {

    EditText infoInput;
    String name, email, uen, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company_bio_details);

        infoInput = findViewById(R.id.infoInput);

        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
        uen=getIntent().getExtras().getString("uen");
        role=getIntent().getExtras().getString("role");
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.nextBtn:
                if(String.valueOf(infoInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }
                else {
                    i = new Intent(this, Register.class);
                    String info = String.valueOf(infoInput.getText());
                    i.putExtra("status", "employer");
                    i.putExtra("email", email);
                    i.putExtra("name", name);
                    i.putExtra("uen", uen);
                    i.putExtra("role", role);
                    i.putExtra("info", info);
                    startActivity(i);
                }

                break;
        }
    }
}