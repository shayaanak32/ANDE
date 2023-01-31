package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        TextView privBtn = findViewById(R.id.privBtn);
        privBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsPage.this, PrivPolicy.class);
                startActivity(i);
            }
        });
        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);

        bv.setSelectedItemId(R.id.settingsNav);


        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        i = new Intent(SettingsPage.this, ProfilePage.class);
                        startActivity(i);
                        return true;
                    case R.id.feedNav:
                        i = new Intent(SettingsPage.this, FeedPage.class);
                        startActivity(i);
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(SettingsPage.this, SettingsPage.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }
}