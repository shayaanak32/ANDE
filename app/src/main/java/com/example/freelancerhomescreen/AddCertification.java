package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AddCertification extends AppCompatActivity {
    private String startDateChosen;
    private String endDateChosen;
    boolean startDateClicked = false;
    boolean endDateClicked = false;
    ArrayList<String> skillsArrayList;
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    SharedPreferences sdPref;
    SharedPreferences edPref;
    TextView addCertificationCompletionDate;
    EditText addCertificationName;
    EditText addCertificationDescriptionField;
    EditText addCertificationEditLink;
    MaterialButton addExperienceBtn;
    String[] skillsArray;
    MaterialButton addButton;
boolean fromAddCertification;

    RecyclerView addCertSkillsRecyclerView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_certification);

        addCertificationName = (EditText) findViewById(R.id.addCertificationName);
        addCertificationCompletionDate = (TextView) findViewById(R.id.addCertificationCompletionDate);
        addCertificationDescriptionField = (EditText) findViewById(R.id.addCertificationDescriptionField);
        addCertificationEditLink = (EditText) findViewById(R.id.addExperienceEditLink);
         addButton = (MaterialButton) findViewById(R.id.addingCertificationsButton);
        Bundle getData = getIntent().getExtras();

        addCertSkillsRecyclerView = (RecyclerView) findViewById(R.id.addCertSkillsRecyclerView);
        MaterialButton addCertSkillBtn = (MaterialButton) findViewById(R.id.addCertSkillBtn);
        EditText addCertificationSkillField = (EditText) findViewById(R.id.addCertificationSkillField);
        if (getData.getBoolean("fromCalendarViewActivity")) {
            Log.d("About to set the data", "Just came back from Calendar View Activity!");
            addCertificationName.setText(getData.getString("name"));
            addCertificationCompletionDate.setText(getData.getString("completionDate"));
            addCertificationDescriptionField.setText(getData.getString("description"));
            addCertificationEditLink.setText(getData.getString("link"));
        }

        addCertificationCompletionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddCertification.this, CalendarViewActivity.class);
                fromAddCertification = true;
                i.putExtra("fromAddCertification", fromAddCertification);
                i.putExtra("name", addCertificationName.getText());
                i.putExtra("link", addCertificationEditLink.getText());
                i.putExtra("completionDate", addCertificationCompletionDate.getText());
                i.putExtra("description", addCertificationDescriptionField.getText());
                //i.putExtra("skills", textListSkills);
                startDateClicked = true;
                i.putExtra("startDateClicked", startDateClicked);
                startActivity(i);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addCertificationName.getText().toString();
                String completionDate = addCertificationCompletionDate.getText().toString();
                String description = addCertificationDescriptionField.getText().toString();
                String link = addCertificationEditLink.getText().toString();
                String textListSkills = "";
                for (int counter = 0; counter < skillsArray.length; counter++) {
                    if (counter != skillsArray.length) {
                        String nameOfSkill = skillsArray[counter];
                        if (counter == skillsArray.length) {
                            textListSkills += nameOfSkill;
                        } else {
                            textListSkills += nameOfSkill + ",";
                            counter++;

                        }
                    }
                }
                if (name != null && !name.isEmpty() && completionDate != null && !completionDate.isEmpty() && description != null && !description.isEmpty()) {
                    Log.d("Form Status", "Fields Complete");
                    // NOTE: Add the Identity_ID from shared Preferences Here!
                    Certification e = new Certification(name, link, completionDate, textListSkills, description, 1);
                    db.addCertifications(e);
                    finish();
                } else {
                    Log.d("Form Status", "Missing Fields");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCertification.this);
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

        if (getData != null) {
            sdPref = getSharedPreferences(START_DATE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sdPref.edit();
            startDateClicked = getData.getBoolean("startDateClicked");
            endDateClicked = getData.getBoolean("endDateClicked");

            if (startDateClicked) {
                startDateChosen = getData.getString("startDateChosen");
                editor.putString("ChosenStartDate", startDateChosen);
                editor.apply();
                addCertificationCompletionDate.setText(startDateChosen);
            } else {
                updateSD();
            }


        } else {
            //Toast.makeText(getApplicationContext(),"Failed to get Data",Toast.LENGTH_LONG).show();
        }

    }


    public void updateSD() {
        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
        String text = sdsp.getString("ChosenStartDate", "");
        Log.d("Inside UpdateSD Checking Text", text);
        addCertificationCompletionDate.setText(text);
    }

    public ArrayList<String> getStringArrayList(String skills) {
        skillsArray = skills.split(",");
        skillsArrayList = new ArrayList<String>();

        for (int i = 0; i < skillsArray.length; i++) {
            skillsArrayList.add(skillsArray[i]);
        }
        return skillsArrayList;
    }


}