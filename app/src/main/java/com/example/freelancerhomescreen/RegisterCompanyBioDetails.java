package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCompanyBioDetails extends AppCompatActivity implements View.OnClickListener {

    EditText infoInput, pwInput;
    String name, email, uen, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company_bio_details);

        infoInput = findViewById(R.id.infoInput);
        pwInput = findViewById(R.id.pwInput);
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
                if(String.valueOf(infoInput.getText()).isEmpty()||String.valueOf(pwInput.getText()).isEmpty()){
                    Toast.makeText(this, "Fill in all info", Toast.LENGTH_SHORT).show();
                }
                else {
                    String info = String.valueOf(infoInput.getText());
                    String pw = String.valueOf(pwInput.getText());
                    DatabaseHandler db = new DatabaseHandler(this);
                    db.addEmployer(name,"start,",info,email,pw,uen,"");
                    int id = db.getEmployerUserIdByEmail(email);
                    db.addUser(name,1, email, pw, id);
                    Toast.makeText(this, "Successful Registration", Toast.LENGTH_SHORT).show();
                    i = new Intent(this, LoginScreen.class);
                    startActivity(i);
                    finish();
                }

                break;
        }
    }
}