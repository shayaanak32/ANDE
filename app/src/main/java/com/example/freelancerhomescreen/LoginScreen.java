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
    private final String USER_DETAILS = "UserDetails";
private final String IdentityID = "IdentityID";
private final String UserID = "UserID";
private final String RoleID = "RoleID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //SplashWelcome sw = new SplashWelcome();

        SharedPreferences prefs = getSharedPreferences(APP_STARTS, MODE_PRIVATE);
        int appStarts = prefs.getInt("AppStarts", 0);
        DatabaseHandler db = new DatabaseHandler(this);
        SharedPreferences.Editor editor = prefs.edit();
        //CreateTables ct = new CreateTables(this);
        if (appStarts == 0) {
            Log.d("About to Insert Data", "App Starts is 0");
            db.addUser("test", 1, "email@gmail.com", "password", "test", 1);
            db.addUser("John Smith", 1, "johnsmith@gmail.com", "hashpassword1", "Description1", 1);
            db.addUser("Jane Doe", 1, "janedoe@gmail.com", "hashpassword2", "Description2", 2);
            db.addUser("Bob Johnson", 1, "bobjohnson@gmail.com", "123", "Description3", 2);
            db.addUser("Emily Davis", 2, "emilydavis@gmail.com", "1234567890", "Description4", 1);
            db.addUser("Michael Brown", 2, "michaelbrown@gmail.com", "asdfghjkl", "Description5", 2);
            db.addUser("Ashley Taylor", 2, "ashleytaylor@gmail.com", "hashpassword6", "Description6", 3);

            db.addEmployers("Test Thing Company", "Programmers, Software Engineers", "A fun tech company", "testing@testCompany.gov.sg", "12345", "T123456789");
            db.addEmployers("Company Too", "Coders, Interns", "Another boring company", "com2@gmail.com", "asdfg", "T0001112");
            db.addEmployers("Company 3", "Coders, Internvvs", "Another bland company", "com3@gmail.com", "asdfrffg", "T3001112");

            db.addFreelancer("Emily Davis", "emilydavis@gmail.com", "123", "Computing and stuff", "Java, C++, NodeJS");
            db.addFreelancer("Michael Brown", "michaelbrown@gmail.com", "asdfghjkl", "More Computing and More Computing", "Java, NodeJS");
            db.addFreelancer("Ashley Taylor", "ashleytaylor@gmail.com", "hashpassword6", "I love tech", "Python");

            Projects p1 = new Projects("Student Management System", "January 2019", "Januray 2022", "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1", 1);
            Projects p2 = new Projects("IceBreaker!", "August 2020", "December 2022", "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1", 1);
            Projects p3 = new Projects("Software Engineering Practice", "June 2021", "June 2022", "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3", 3);
            db.addProjects(p1);
            db.addProjects(p2);
            db.addProjects(p3);


            Certification c1 = new Certification("OCP 11 Java Programmer", "https://www.aws.training/certification", "Januray 2020", "Java,JavaScript", "Description1");
            Certification c2 = new Certification("IBM Professional Data Science Certification", "https://www.coursera.org/professional-certificates/ibm-data-science", "August 2021", "Python,Java", "Description2");
            Certification c3 = new Certification("DataCamp Certified Data Professional", "https://www.coursera.org/professional-certificates/ibm-data-science", "January 2022", "Java", "Description3");
            Certification c4 = new Certification("Google Cloud Certified Data Scientist", "https://www.coursera.org/professional-certificates/ibm-data-science", "May 2022", "Java", "Description4");
            db.addCertifications(c1);
            db.addCertifications(c2);
            db.addCertifications(c3);
            db.addCertifications(c4);


            String startDate1 = "September 2020";
            String endDate1 = "December 2021";
            String startDate2 = "December 2020";
            String endDate2 = "December 2022";
            String startDate3 = "January 2019";
            String endDate3 = "July 2019";

            Experience e1 = new Experience("Java GUI Support Helper", startDate1, endDate1, "My experience at GOogle included" +
                    "me helping out in the Java Support role, which helped teach me a lot of things!!", "Google", 1);

            Experience e2 = new Experience("Microsoft DevOps Assistant", startDate2, endDate2, "My experience at Microsoft included" +
                    "me helping out in the DevOps Support role, which helped teach me a lot of things with regards to testing, maintenance, etc.", "Microsoft", 2);

            Experience e3 = new Experience("Apple Mobile Developer", startDate3, endDate3, "My experience at Apple included" +
                    "me helping out in the Swift Support role, which helped teach me a lot on Mobile Apps, and how to optimise performance", "Apple", 3);
            db.addExperience(e1);
            db.addExperience(e2);
            db.addExperience(e3);


//            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("AppStarts", 1);
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
                    int role = u.getRole();
                    int user_id = u.getUser_id();
                    int identityId;
                    Log.d("user role?", role + "");
                    editor.putInt("role", role);
                    editor.putInt("user_id", user_id);

                    if (role == 1) {
                        SharedPreferences userDetailsPref = getSharedPreferences("UserDetails", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = userDetailsPref.edit();
                        editor2.putString(IdentityID, Integer.toString(u.getIdentityID()));
                        editor2.putString(UserID, Integer.toString(u.getUser_id()));
                        editor2.putString(RoleID, Integer.toString(u.getRole()));
                        startActivity(new Intent(LoginScreen.this, ProfilePage.class));
                        finish();
                        WelcomeScreen.welcomeScreenActivity.finish();

                    } else if (role == 2) {
                        SharedPreferences userDetailsPref = getSharedPreferences("UserDetails", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = userDetailsPref.edit();
                        editor2.putString(IdentityID, Integer.toString(u.getIdentityID()));
                        editor2.putString(UserID, Integer.toString(u.getUser_id()));
                        editor2.putString(RoleID, Integer.toString(u.getRole()));
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                        WelcomeScreen.welcomeScreenActivity.finish();

                        finish();

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