package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        // just a function to initialise the widgets we are going to use so that we can refer to them neatly
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        // the Month and Year
    }

    private void setMonthView() {
        // set the Month and Year right now
        monthYearText.setText(monthYearFromDate(selectedDate));
        // passing in the current date
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        //setting the adapter here
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        // setting the UI of the recycler vieww
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
// get a year month object with just Year and Month
        int daysInMonth = yearMonth.lengthOfMonth();
// no of days obtained in a month
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        // set the selected date to the first of the month
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
// integer value on the day of the week
        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date) {
        // getting the date formatted as Month, Year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    // these 2 methods basically show from what is selected, you can either move forwards or backwards in months
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        Bundle getData = getIntent().getExtras();
        boolean startDateClicked;
        boolean endDateClicked;
        boolean fromAddExperience;
        boolean fromEditCertifications;
        boolean fromEditExperience;
        boolean fromAddCertification;
        boolean fromEditProject;
        if (!dayText.equals("")) {
            String selectedMonthYear = monthYearFromDate(selectedDate);
//            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if (getData != null) {
                startDateClicked = getData.getBoolean("startDateClicked");
                endDateClicked = getData.getBoolean("endDateClicked");
                fromEditCertifications = getData.getBoolean("editCertifications");
                fromEditExperience = getData.getBoolean("fromEditExperience");
                fromAddCertification = getData.getBoolean("fromAddCertification");
                fromEditProject = getData.getBoolean("fromEditProject");
                fromAddExperience = getData.getBoolean("fromAddExperience");

                if (startDateClicked) {
                    if (fromAddExperience) {
                        Intent i = new Intent(CalendarViewActivity.this, AddExperience.class);
                        Log.d("selectedMonthYear", selectedMonthYear);
                        i.putExtra("startDateChosen", selectedMonthYear);
                        i.putExtra("startDateClicked", startDateClicked);
                        i.putExtra("name", getData.getString("name"));
                        i.putExtra("link", getData.getString("link"));
                        i.putExtra("endDate", getData.getString("endDate"));
                        i.putExtra("description", getData.getString("description"));
                        i.putExtra("skills", getData.getString("skills"));
                        i.putExtra("fromCalendarViewActivity", true);

                        startActivity(i);
                    }

                    if (fromAddCertification) {
                        Intent i2 = new Intent(CalendarViewActivity.this, AddCertification.class);
                        i2.putExtra("startDateChosen", selectedMonthYear);
                        i2.putExtra("startDateClicked", startDateClicked);
                        i2.putExtra("name", getData.getString("name"));
                        i2.putExtra("link", getData.getString("link"));
                        i2.putExtra("description", getData.getString("description"));
                        //i2.putExtra("skills", getData.getString("skills"));
                        i2.putExtra("fromCalendarViewActivity", true);
                        startActivity(i2);
                    }
                    if (fromEditCertifications) {
                        Log.d("Calendar View Activity -->", "Intent has Come from EditCertifications (select date)");
                        Intent i2 = new Intent(CalendarViewActivity.this, EditCertificationActivity.class);
                        i2.putExtra("startDateChosen", selectedMonthYear);
                        i2.putExtra("startDateClicked", startDateClicked);
                        i2.putExtra("name", getData.getString("name"));
                        i2.putExtra("link", getData.getString("link"));
                        i2.putExtra("endDate", getData.getString("endDate"));
                        i2.putExtra("description", getData.getString("description"));
                        i2.putExtra("skills", getData.getString("skills"));
                        i2.putExtra("fromCalendarViewActivity", true);
//                        i.putExtra("name", name);
//                        i.putExtra("link", link);
//                        i.putExtra("endDate", endDate);
//                        i.putExtra("description", description);
//                        i.putExtra("skills", skillsArrayList);

                        startActivity(i2);

                    }

                    if (fromEditExperience) {
                        Intent i3 = new Intent(CalendarViewActivity.this, EditExperienceActivity.class);
                        Log.d("selectedMonthYear", selectedMonthYear);
                        i3.putExtra("startDateChosen", selectedMonthYear);
                        i3.putExtra("startDateClicked", startDateClicked);
                        startActivity(i3);
                    }
                    if (fromEditProject) {
                        Log.d("Calendar View Activity--", "From Edit Project");
                        Intent i3 = new Intent(CalendarViewActivity.this, EditProject.class);
                        i3.putExtra("pN", getData.getString("pN"));
                        i3.putExtra("pSD", getData.getString("pSD"));
                        i3.putExtra("pD", getData.getString("pD"));
                        i3.putExtra("pED", getData.getString("pED"));
                        i3.putExtra("pL", getData.getString("pL"));
                        startActivity(i3);

                    }

                }
                if (endDateClicked) {
//                    Intent i = new Intent(CalendarViewActivity.this, AddExperience.class);
//                    i.putExtra("endDateChosen", selectedMonthYear);
//                    i.putExtra("endDateClicked", endDateClicked);
//
//                    startActivity(i);
                    if (fromEditProject) {
                        Intent i = new Intent(CalendarViewActivity.this, AddCertification.class);
                        i.putExtra("pN", getData.getString("pN"));
                        i.putExtra("pSD", getData.getString("pSD"));
                        i.putExtra("pD", getData.getString("pD"));
                        i.putExtra("pED", getData.getString("pED"));
                        i.putExtra("pL", getData.getString("pL"));
                    }
                }
            }

        }
    }
}