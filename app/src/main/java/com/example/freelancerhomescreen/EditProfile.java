package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    ListView listView;
    TextView textView;
    private static final String TAG = "EditProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        DatabaseHandler db = new DatabaseHandler(this);
        CircleImageView pfp = findViewById(R.id.profile_image);
        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog();
            }
        });
        SharedPreferences prefs;
        prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        int identity_id = Integer.parseInt(prefs.getString("Identity ID","-1"));
        Employer emp = db.getContact(identity_id);
        TextView cName = (TextView) findViewById(R.id.comapnyNameEdit);
        TextView cAbout = (TextView) findViewById(R.id.companyAboutEdit);
        Button addSkills = findViewById(R.id.addBtn);
        EditText skillsEdit = findViewById(R.id.company1PrioEdit);
        listView = (ListView) findViewById(R.id.companyPrioList);
        cName.setText(emp.getCompanyName());
        cAbout.setText(emp.getDescription());


        int imageResource = getResources().getIdentifier(emp.getProfileImg(), "drawable", this.getPackageName());
        Drawable d= getResources().getDrawable(imageResource);
        pfp.setImageDrawable(d);
        ArrayList<String> listItem = new ArrayList<String>(Arrays.asList(emp.getPriorities().split(",")));
        final CustomAdapter adapter = new CustomAdapter(EditProfile.this, listItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the item at the specified position
                listItem.remove(position);

                // Notify the adapter of the change
                adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
            }
        });

        addSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItem.add(skillsEdit.getText().toString());
                adapter.notifyDataSetChanged();
                skillsEdit.setText("");
            }
        });

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
                            i = new Intent(EditProfile.this, ProfilePage.class);
                            startActivity(i);
                            finish();
                        }else if(userRole==2){
                            i = new Intent(EditProfile.this, FreelancerOwnProfile.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(EditProfile.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }

                        return true;
                    case R.id.feedNav:
                        i = new Intent(EditProfile.this, FeedPage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(EditProfile.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });


        Button updateBtn = findViewById(R.id.saveUpdateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prioStr = "";
                EditText compName = findViewById(R.id.comapnyNameEdit);
                String cN = compName.getText().toString();
                EditText compDesc = findViewById(R.id.companyAboutEdit);
                String cD = compDesc.getText().toString();
                if (adapter.getCount() > 0) {
                    for (int i = 0; i < adapter.getCount() - 1; i++) {
                        prioStr += adapter.getPrio(i) + ",";
                    }
                    prioStr += adapter.getPrio(adapter.getCount() - 1);
                }

                DatabaseHandler db = new DatabaseHandler(EditProfile.this);
                db.updateEmployer(new Employer(cN, prioStr, cD, 1));

                Intent i = new Intent(EditProfile.this, ProfilePage.class);
                startActivity(i);
                finish();
            }
        });


    }

    void showCustomDialog() {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setCancelable(true);
        d.setContentView(R.layout.activity_upload);
        d.show();
        ImageView camera = (ImageView) findViewById(R.id.editPhotoClickable);

    }

}