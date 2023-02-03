package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class EditCertificationActivity extends AppCompatActivity {

    //    private EditText editTextUpdateName;
    private TextView startDatePickerUpd;
    public static final String FORM_DATA = "FormData";
    SharedPreferences formData;
    TextView endDatePickerUpd;
    EditText descriptionFieldUpd;
    boolean startDateClicked = false;
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
        DatabaseHandler db = new DatabaseHandler(this);
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

            name = getData.getString("name");
            link = getData.getString("link");
            endDate = getData.getString("endDate");
            description = getData.getString("description");
            skills = getData.getString("skills");
            skillsArrayList = getStringArrayList(skills);

        }
        fromCalendarViewActivity = getData.getBoolean("fromCalendarViewActivity");
        if (getData.getBoolean("fromCalendarViewActivity")) {

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


        editTextUpdateName.setText(name);
        endDatePickerUpd.setText(endDate);
        descriptionFieldUpd.setText(description);
        editLink.setText(link);

        setAdapter(skillsArrayList);

        endDatePickerUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                i.putExtra("skills", skills);

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



                if (certName != null && !certName.isEmpty() && completionDate != null && !completionDate.isEmpty() && endDate != null && !endDate.isEmpty() && description != null && !description.isEmpty()) {

                    Intent i = new Intent(EditCertificationActivity.this, ExperienceMainActivity.class);

                } else {

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
                skillsArrayList.add(insertIndex,addedSkill);

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



                if (certName != null && !certName.isEmpty() && completionDate != null && !completionDate.isEmpty() && description != null && !description.isEmpty() && editedLink != null && !editedLink.isEmpty() && textListSkills != null && !textListSkills.isEmpty()) {

                    Certification c = new Certification(1, certName, editedLink, completionDate, textListSkills, description);
                    db.updateCertification(c);
                    Intent i = new Intent(EditCertificationActivity.this, CertificationPage.class);

                } else {

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
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);

        BottomNavigationView bv =findViewById(R.id.bottomNavigationView);
        int userRole = Integer.parseInt(prefs.getString("RoleID","-1"));

        bv.setSelectedItemId(R.id.profileNav);

        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        if(userRole==1){
                            i = new Intent(EditCertificationActivity.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(EditCertificationActivity.this, FreelancerOwnProfile.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(EditCertificationActivity.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(EditCertificationActivity.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(EditCertificationActivity.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    public void updateSD() {
        SharedPreferences sdsp = getSharedPreferences(START_DATE, MODE_PRIVATE);
        String text = sdsp.getString("ChosenStartDate", "");
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

    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();

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

    }
}