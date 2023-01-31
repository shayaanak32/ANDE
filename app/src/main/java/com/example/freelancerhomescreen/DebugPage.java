package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DebugPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_page);
        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profileNav:

                        i = new Intent(DebugPage.this, FirebaseMainActivity.class);
                        startActivity(i);
                        finish();
                        return true;

                    case R.id.settingsNav:
                        i = new Intent(DebugPage.this, DebugPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });


    }

}
