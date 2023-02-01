package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {
    private final String APP_STARTS = "NumberOfAppStarts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        SharedPreferences prefs = getSharedPreferences(APP_STARTS, MODE_PRIVATE);
        int appStarts = prefs.getInt("AppStarts", 0);
        DatabaseHandler db = new DatabaseHandler(this);
        SharedPreferences.Editor editor = prefs.edit();

        if(appStarts == 0) {

            db.addUser("test", 1, "email@gmail.com", "password", "test");
            db.addUser("John Smith", 1, "johnsmith@gmail.com", "hashpassword1", "Description1");
            db.addUser("Jane Doe", 2, "janedoe@gmail.com", "hashpassword2", "Description2");
            db.addUser("Bob Johnson", 2, "bobjohnson@gmail.com", "hashpassword3", "Description3");
            db.addUser("Emily Davis", 1, "emilydavis@gmail.com", "hashpassword4", "Description4");
            db.addUser("Michael Brown", 2, "michaelbrown@gmail.com", "hashpassword5", "Description5");
            db.addUser("Ashley Taylor", 1, "ashleytaylor@gmail.com", "hashpassword6", "Description6");
//            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("AppStarts",1);
            editor.commit();
        }
        db.openUsers();
        EditText emailInput = (EditText) findViewById(R.id.email_input_login);
        EditText passwordInput = (EditText) findViewById(R.id.password_input_login);


        Button loginBtn = (Button) findViewById(R.id.loginBtn);


        DatabaseHandler dbHandler = new DatabaseHandler(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                Log.d("email", email);
                Log.d("password", password);
                Users u = dbHandler.checkUser(email, password);
                boolean check = !(u == null);
                if (check) {
//        prefs = getSharedPreferences(APP_STARTS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt("AppStarts",0);
                    int role = u.getRole();
                    int user_id = u.getUser_id();
                    Log.d("user role?", role + "");
                    editor.putInt("role", role);
                    editor.putInt("user_id",user_id);

                    if (role == 1) {
                        startActivity(new Intent(LoginScreen.this, ProfilePage.class));
                    } else if (role == 2) {
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));

                    }
                } else {
                    emailInput.setText("");
                    passwordInput.setText("");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginScreen.this);
                    builder1.setMessage("Please fill in all the fields!");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            }
        });
    }
}