package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PrivPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priv_policy);
        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setSelectedItemId(R.id.settingsNav);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        i = new Intent(PrivPolicy.this, ProfilePage.class);
                        startActivity(i);
//                        finish();
                        return true;
                    case R.id.feedNav:
                        i = new Intent(PrivPolicy.this, FeedPage.class);
                        startActivity(i);
//                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(PrivPolicy.this, SettingsPage.class);
                        startActivity(i);
//                        finish();
                        return true;
                }
                return false;
            }
        });
    }


}