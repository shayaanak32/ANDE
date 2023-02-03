package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    SharedPreferences prefs;
    private final String APP_STARTS = "NumberOfAppStarts";
    public static Activity welcomeScreenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences(APP_STARTS, MODE_PRIVATE);
        welcomeScreenActivity = this;
        int appStarts = prefs.getInt("AppStarts", 0);
        Log.d("App starts are equal to ", Integer.toString(appStarts));

        if (appStarts == 0) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("AppStarts", 0);
            editor.commit();
        }

        /*handle the splash screen transition just before calling super.onCreate()*/
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button regBtn = (Button) findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//
//                db.close();
                startActivity(new Intent(WelcomeScreen.this, LoginScreen.class));

            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, RegisterCheckScreen.class));
            }
        });
    }
}