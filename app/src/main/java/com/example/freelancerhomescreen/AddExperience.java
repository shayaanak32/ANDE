package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class AddExperience extends AppCompatActivity {
    private String startDateChosen;
    private String endDateChosen;
    boolean startDateClicked = false;
    boolean endDateClicked = false;
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    SharedPreferences sdPref;
    SharedPreferences edPref;
    EditText startDateField;
    EditText endDateField;
    EditText editTextExpName;
    EditText editTextCompany;
    EditText descriptionField;
    MaterialButton addExperienceBtn;
    boolean fromAddExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);
        DatabaseHandler db = new DatabaseHandler(this);
        startDateField = (EditText) findViewById(R.id.startDatePickerExp);
        endDateField = (EditText) findViewById(R.id.endDatePicker);
        editTextExpName = (EditText) findViewById(R.id.editTextExpName);
        editTextCompany = (EditText) findViewById(R.id.editTextCompany);
        descriptionField = (EditText) findViewById(R.id.descriptionField);
        addExperienceBtn = (MaterialButton) findViewById(R.id.addExperienceButton);


//        startDateField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(AddExperience.this, CalendarViewActivity.class);
//                startDateClicked = true;
//
//                i.putExtra("startDateClicked", startDateClicked);
//                i.putExtra("fromAddExperience",true);
//                startActivity(i);
//
//            }
//
//        });
//        endDateField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(AddExperience.this, CalendarViewActivity.class);
//                endDateClicked = true;
//                i.putExtra("endDateClicked", endDateClicked);
//                startActivity(i);
//
//            }
//
//        });
        addExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String experienceName = editTextExpName.getText().toString();
                String startDate = startDateField.getText().toString();
                String endDate = endDateField.getText().toString();
                String companyName = editTextCompany.getText().toString();
                Log.d("Company Name", companyName);
                String description = descriptionField.getText().toString();
//                Log.d("experienceName", experienceName);
//                Log.d("Name Check", Boolean.toString(experienceName != null && !experienceName.isEmpty()));
//                Log.d("startDate", startDate);
//                Log.d("Start Date Check", Boolean.toString(startDate != null && !startDate.isEmpty()));
//                Log.d("endDate", endDate);
//                Log.d("End Date Check", Boolean.toString(endDate != null && !endDate.isEmpty()));
//                Log.d("companyName", companyName);
//                Log.d("Company Name Check", Boolean.toString(companyName != null && !companyName.isEmpty()));
//                Log.d("description", description);
//                Log.d("Description Check", Boolean.toString(description != null && !description.isEmpty()));


                if (experienceName != null && !experienceName.isEmpty() && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty() && companyName != null && !companyName.isEmpty() && description != null && !description.isEmpty()) {
                    Experience e = new Experience(experienceName, startDate, endDate, companyName, description,1);
                    db.addExperience(e);
                    Intent i = new Intent(AddExperience.this, ExperienceMainActivity.class);
                    startActivity(i);
                } else {
                    Log.d("Form Status", "Missing Fields");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddExperience.this);
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

//        Bundle getData = getIntent().getExtras();
//        if (getData != null) {
//            sdPref = getSharedPreferences(START_DATE, MODE_PRIVATE);
//            edPref = getSharedPreferences(END_DATE, MODE_PRIVATE);
//            SharedPreferences.Editor editor = sdPref.edit();
//            SharedPreferences.Editor editor2 = edPref.edit();
//            startDateClicked = getData.getBoolean("startDateClicked");
//            endDateClicked = getData.getBoolean("endDateClicked");
//
//            if (startDateClicked) {
//                startDateChosen = getData.getString("startDateChosen");
//                editor.putString("ChosenStartDate", startDateChosen);
//                editor.apply();
//                startDateField.setText(startDateChosen);
//            } else {
//                updateSD();
//            }
//
//            if (endDateClicked) {
//                endDateChosen = getData.getString("endDateChosen");
//                editor2.putString("ChosenEndDate", endDateChosen);
//                editor2.apply();
//                endDateField.setText(endDateChosen);
//            } else {
//                updateED();
//            }
//        } else {
//            //Toast.makeText(getApplicationContext(),"Failed to get Data",Toast.LENGTH_LONG).show();
//        }

    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        String experienceName = savedInstanceState.getString("experience_name", "");
//        String startDate = savedInstanceState.getString("start_name", "");
//        String endDate = savedInstanceState.getString("end_name", "");
//        String companyName = savedInstanceState.getString("company_name", "");
//        String description = savedInstanceState.getString("description", "");
//        editTextExpName.setText(experienceName);
//        startDateField.setText(startDate);
//        endDateField.setText(endDate);
//        editTextCompany.setText(companyName);
//        descriptionField.setText(description);
//    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.d("Inside onSaveInstanceState ", "About to Save Instance!!!");
//        String experienceName = editTextExpName.getText().toString();
//        String startDate = startDateField.getText().toString();
//        String endDate = endDateField.getText().toString();
//        String companyName = editTextCompany.getText().toString();
//        String description = descriptionField.getText().toString();
//        Log.d("experienceName", experienceName);
//        Log.d("startDate", startDate);
//        Log.d("endDate", endDate);
//        Log.d("companyName", companyName);
//        Log.d("description", description);
//
//        outState.putString("experience_name", experienceName);
//        outState.putString("start_name", startDate);
//        outState.putString("end_name", endDate);
//        outState.putString("company_name", companyName);
//        outState.putString("description", description);
//    }
//
//
//    public void updateSD() {
//        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
//        String text = sdsp.getString("ChosenStartDate", "");
//        Log.d("Inside UpdateSD Checking Text", text);
//        startDateField.setText(text);
//    }
//
//    public void updateED() {
//        Log.d("Inside UpdateED", "endDate clicked is false");
//        SharedPreferences edsp = getSharedPreferences(END_DATE, MODE_PRIVATE);
//        String text2 = edsp.getString("ChosenEndDate", "");
//        Log.d("Inside UpdateED Checking Text", text2);
//
//        endDateField.setText(text2);
//    }


}