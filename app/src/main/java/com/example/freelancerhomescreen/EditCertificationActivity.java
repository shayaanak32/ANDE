package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class EditCertificationActivity extends AppCompatActivity {

    //    private EditText editTextUpdateName;
    private TextView startDatePickerUpd;
    public static final String FORM_DATA = "FormData";
    SharedPreferences formData;
    TextView endDatePickerUpd;
    EditText descriptionFieldUpd;
    boolean startDateClicked;
    boolean endDateClicked = false;
    private String startDateChosen;
    private String endDateChosen;
    ArrayList<String> skillsArrayList;
    MaterialButton updateCertificationButton;
    //private MaterialButton editBtn;
    private EditText editTextCompany;
    private EditText editLink;
    SharedPreferences sdPref;
    SharedPreferences edPref;
    EditCertSkillAdapter adapter;
    String[] skillsArray;
    public static final String START_DATE = "StartDateCert";
    public static final String END_DATE = "EndDate";
    RecyclerView recyclerView;
    String name;
    String link;
    String endDate;
    String description;
    String skills;
    EditText editTextUpdateName;
    boolean fromCalendarViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_certification);
        Bundle getData = getIntent().getExtras();
        editTextUpdateName = (EditText) findViewById(R.id.editName);
        endDatePickerUpd = (TextView) findViewById(R.id.completionDatePickerExp);
        descriptionFieldUpd = (EditText) findViewById(R.id.editdescriptionField);
        MaterialButton editBtn = (MaterialButton) findViewById(R.id.updateCertificationButton);
        editLink = (EditText) findViewById(R.id.editLink);
        recyclerView = (RecyclerView) findViewById(R.id.skillsRecyclerView);
        MaterialButton addCertSkillBtn = (MaterialButton) findViewById(R.id.addCertSkillBtn);
        EditText editTextSkillField = (EditText) findViewById(R.id.editTextSkillField);
        updateCertificationButton = (MaterialButton) findViewById(R.id.updateCertificationButton);

        if (getData.getBoolean("fromCertificationPage")) {
            Log.d("From Where??", "From the Certifications Main Activity Page!!!");
            name = getData.getString("name");
            link = getData.getString("link");
            endDate = getData.getString("endDate");
            description = getData.getString("description");
            skills = getData.getString("skills");
            skillsArrayList = getStringArrayList(skills);

        }
        fromCalendarViewActivity = getData.getBoolean("fromCalendarViewActivity");
        Log.d("Coming from CalendarViewActivity", Boolean.toString(getData.getBoolean("fromCalendarViewActivity")));
        if (getData.getBoolean("fromCalendarViewActivity")) {
            Log.d("About to set the data", "Just came back from Calendar View Activity!");

            name = getData.getString("name");
            link = getData.getString("link");
            endDate = getData.getString("completionDateSelected");

            description = getData.getString("description");
            skills = getData.getString("skills");
            skillsArrayList = getStringArrayList(skills);
            editTextUpdateName.setText(name);
            endDatePickerUpd.setText(endDate);
            descriptionFieldUpd.setText(description);
            editLink.setText(link);
            setAdapter(skillsArrayList);

        }


//        updateExperienceButton = (MaterialButton) findViewById(R.id.updateExperienceButton);
//        editTextCompany = (EditText) findViewById(R.id.editTextCompany);
        editTextUpdateName.setText(name);
        endDatePickerUpd.setText(endDate);
        descriptionFieldUpd.setText(description);
        editLink.setText(link);

        Log.d("Setting Adapter", "About to set adapter......");
        Log.d("ArrayList for the Skills", Boolean.toString(skillsArrayList == null));

        setAdapter(skillsArrayList);
        CreateTables ct = new CreateTables(this);

        endDatePickerUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("End Date Picker Clicked", "Clicked!");
                name = editTextUpdateName.getText().toString();
                endDate = endDatePickerUpd.getText().toString();
                description = descriptionFieldUpd.getText().toString();
                link = editLink.getText().toString();

                Intent i = new Intent(EditCertificationActivity.this, CalendarViewActivity.class);
                startDateClicked = true;
                i.putExtra("startDateClicked", startDateClicked);
                i.putExtra("editCertifications", true);
                i.putExtra("name", name);
                i.putExtra("link", link);
                i.putExtra("endDate", endDate);
                i.putExtra("description", description);
                String skillsString = skillsStringify();

                Log.d("skillsString", skillsString);

                startActivity(i);
            }

        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                editTextUpdateName.setText(name);
//                endDatePickerUpd.setText(endDate);
//                descriptionFieldUpd.setText(description);
//                editLink.setText(link);
                String certName = editTextUpdateName.getText().toString();
                String completionDate = endDatePickerUpd.getText().toString();
                String description = descriptionFieldUpd.getText().toString();
                String link = editLink.getText().toString();


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


                if (certName != null && !certName.isEmpty() && completionDate != null && !completionDate.isEmpty() && endDate != null && !endDate.isEmpty() && description != null && !description.isEmpty()) {
                    Log.d("Form Status", "Fields Complete");
//                    Experience e = new Experience(experienceName, startDate, endDate, companyName, description,1);
                    //ct.updateExperience(e);
                    Intent i = new Intent(EditCertificationActivity.this, ExperienceMainActivity.class);

                } else {
                    Log.d("Form Status", "Missing Fields");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EditCertificationActivity.this);
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

        addCertSkillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedSkill = editTextSkillField.getText().toString();

                RecyclerSkillsItem s = new RecyclerSkillsItem(addedSkill);
                int insertIndex = 0;
                skillsArrayList.add(insertIndex, addedSkill);
                Log.d("Is Adapter Null?", Boolean.toString(adapter == null));
                adapter.notifyItemInserted(insertIndex);


            }
        });

        updateCertificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedSkill = editTextSkillField.getText().toString();
                String textListSkills = "";
                String certName = editTextUpdateName.getText().toString();
                String completionDate = endDatePickerUpd.getText().toString();
                //String endDate = endDatePickerUpd.getText().toString();
                String description = descriptionFieldUpd.getText().toString();
                String editedLink = editLink.getText().toString();
                int counter = 0;
                textListSkills = skillsStringify();

                if (certName != null && !certName.isEmpty() && completionDate != null && !completionDate.isEmpty() && description != null && !description.isEmpty() && editedLink != null && !editedLink.isEmpty() && textListSkills != null && !textListSkills.isEmpty()) {
                    Log.d("Form Status", "Fields Complete");
                    Certification c = new Certification(1, certName, editedLink, completionDate, textListSkills, description);
                    ct.updateCertification(c);
                    Intent i = new Intent(EditCertificationActivity.this, CertificationPage.class);
                    startActivity(i);
                    finish();

                } else {
                    Log.d("Form Status", "Missing Fields");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EditCertificationActivity.this);
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
                endDatePickerUpd.setText(startDateChosen);
            } else {
                updateSD();
            }

            if (endDateClicked) {
                endDateChosen = getData.getString("endDateChosen");
                editor2.putString("ChosenEndDate", endDateChosen);
                editor2.apply();
                endDatePickerUpd.setText(endDateChosen);
            } else {
                //updateED();
            }
        }

    }

    public String skillsStringify() {
        String textListSkills = "";
        int counter = 0;
        for (String s : skillsArrayList) {
            if (counter != skillsArrayList.size()) {
                String nameOfSkill = s;
                if (counter == skillsArrayList.size() - 1) {
                    textListSkills += nameOfSkill;
                } else {
                    textListSkills += nameOfSkill + ",";
                    counter++;

                }
            }
        }
        return textListSkills;
    }

    public ArrayList<String> skillsUnstringify(String skills) {
        ArrayList<String> tempSkillsList = new ArrayList<String>();
        String[]tempSkillsArray = skills.split(",");
        for(int z = 0; z < tempSkillsArray.length;z++){
            tempSkillsList.add(tempSkillsArray[z]);
        }
        return tempSkillsList;
    }

    public void updateSD() {
        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
        String text = sdsp.getString("ChosenStartDate", "");
        Log.d("Inside UpdateSD Checking Text", text);
        endDatePickerUpd.setText(text);
    }

    private void setAdapter(ArrayList<String> skillsArrayList) {

        adapter = new EditCertSkillAdapter(skillsArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EditCertificationActivity.this, RecyclerView.HORIZONTAL, false);
        //Set Layout Manager to RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart msg", "The onStart() event");
    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume msg", "The onResume() event");
//        if (fromCalendarViewActivity) {
////            formData = getSharedPreferences(FORM_DATA, MODE_PRIVATE);
////            String name = formData.getString("Name", "");
////            String completionDate = formData.getString("CompletionDate", "");
////            String link = formData.getString("Link", "");
////            String description = formData.getString("Description", "");
////            String skills = formData.getString("Skills", "");
////            skillsArrayList = getStringArrayList(skills);
////            Log.d("ArrayList for Skills..", Boolean.toString(skillsArrayList == null));
//            editTextUpdateName.setText(name);
//            endDatePickerUpd.setText(endDate);
//            descriptionFieldUpd.setText(description);
//            editLink.setText(link);
//        }
    }

    public ArrayList<String> getStringArrayList(String skills) {
        skillsArray = skills.split(",");
        skillsArrayList = new ArrayList<String>();

        for (int i = 0; i < skillsArray.length; i++) {
            skillsArrayList.add(skillsArray[i]);
        }
        return skillsArrayList;
    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause msg", "The onPause() event");
    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
//        formData = getSharedPreferences(FORM_DATA, MODE_PRIVATE);
//        SharedPreferences.Editor editor = formData.edit();
//        editor.putString("Name", name);
//        editor.putString("CompletionDate", endDate);
//        editor.putString("Link", link);
//        editor.putString("Description", description);
//        editor.putString("Skills", skills);


    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy msg", "The onDestroy() event");
    }
}