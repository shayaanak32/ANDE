package com.example.freelancerhomescreen;

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

import com.google.android.material.button.MaterialButton;

public class EditExperienceActivity extends AppCompatActivity {
    private EditText editTextUpdateName;
    private TextView startDatePickerUpd;
    private TextView endDatePickerUpd;
    private EditText descriptionFieldUpd;
    boolean startDateClicked = false;
    boolean endDateClicked = false;
    boolean fromEditExperience;
    private String startDateChosen;
    private String endDateChosen;
    private MaterialButton updateExperienceButton;
    private EditText editTextCompany;
    private EditText editLink;
    SharedPreferences sdPref;
    SharedPreferences edPref;
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);
        Bundle getData = getIntent().getExtras();

        String name = getData.getString("name");
        String startDate = getData.getString("startDate");
        String endDate = getData.getString("endDate");
        String description = getData.getString("description");
        String companyName = getData.getString("companyName");
        editTextUpdateName = (EditText) findViewById(R.id.editTextUpdateName);
        startDatePickerUpd = (TextView) findViewById(R.id.startDatePickerUpd);
        endDatePickerUpd = (TextView) findViewById(R.id.endDatePickerUpd);
        descriptionFieldUpd = (EditText) findViewById(R.id.descriptionFieldUpd);
        updateExperienceButton = (MaterialButton) findViewById(R.id.updateExperienceButton);
        editTextCompany = (EditText) findViewById(R.id.editTextCompany);

        editTextUpdateName.setText(name);
        startDatePickerUpd.setText(startDate);
        endDatePickerUpd.setText(endDate);
        descriptionFieldUpd.setText(description);
        editTextCompany.setText(companyName);

        CreateTables ct = new CreateTables(this);

        startDatePickerUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditExperienceActivity.this, CalendarViewActivity.class);
                startDateClicked = true;
                fromEditExperience = true;
                i.putExtra("startDateClicked", startDateClicked);
                i.putExtra("fromEditExperience", fromEditExperience);
                startActivity(i);
            }

        });
        endDatePickerUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EditExperienceActivity.this, CalendarViewActivity.class);
                endDateClicked = true;
                i.putExtra("endDateClicked", endDateClicked);
                startActivity(i);

            }

        });

        updateExperienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String experienceName = editTextUpdateName.getText().toString();
                String startDate = startDatePickerUpd.getText().toString();
                String endDate = endDatePickerUpd.getText().toString();
                String description = descriptionFieldUpd.getText().toString();


                if (experienceName != null && !experienceName.isEmpty() && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty() && companyName != null && !companyName.isEmpty() && description != null && !description.isEmpty()) {
                    Log.d("Form Status", "Fields Complete");
                    Experience e = new Experience(experienceName, startDate, endDate, companyName, description, 1);
                    ct.updateExperience(e);
                    Intent i = new Intent(EditExperienceActivity.this, ExperienceMainActivity.class);

                } else {
                    Log.d("Form Status", "Missing Fields");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EditExperienceActivity.this);
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
            edPref = getSharedPreferences(END_DATE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sdPref.edit();
            SharedPreferences.Editor editor2 = edPref.edit();
            startDateClicked = getData.getBoolean("startDateClicked");
            endDateClicked = getData.getBoolean("endDateClicked");

            if (startDateClicked) {
                startDateChosen = getData.getString("startDateChosen");
                editor.putString("ChosenStartDate", startDateChosen);
                editor.apply();
                startDatePickerUpd.setText(startDateChosen);
            } else {
                updateSD();
            }

            if (endDateClicked) {
                endDateChosen = getData.getString("endDateChosen");
                editor2.putString("ChosenEndDate", endDateChosen);
                editor2.apply();
                endDatePickerUpd.setText(endDateChosen);
            } else {
                updateED();
            }
        }
    }

    public void updateSD() {
        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
        String text = sdsp.getString("ChosenStartDate", "");
        Log.d("Inside UpdateSD Checking Text", text);
        startDatePickerUpd.setText(text);
    }

    public void updateED() {
        Log.d("Inside UpdateED", "endDate clicked is false");
        SharedPreferences edsp = getSharedPreferences(END_DATE, MODE_PRIVATE);
        String text2 = edsp.getString("ChosenEndDate", "");
        Log.d("Inside UpdateED Checking Text", text2);

        endDatePickerUpd.setText(text2);
    }
}