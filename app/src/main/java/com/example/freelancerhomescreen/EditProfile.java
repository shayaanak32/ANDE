package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CROP_IMAGE_REQUEST = 2;
//    String[] listItem;

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
        Employer emp  = db.getContact(1);
        // TODO: for the id, reference from sharedPreference instead;

        TextView cName = (TextView)findViewById(R.id.comapnyNameEdit);
        TextView cAbout = (TextView)findViewById(R.id.companyAboutEdit);
        Button addSkills = findViewById(R.id.addBtn);
        EditText skillsEdit = findViewById(R.id.company1PrioEdit);
        listView=(ListView)findViewById(R.id.companyPrioList);
        cName.setText(emp.getCompanyName());
        cAbout.setText(emp.getDescription());


        pfp.setImageResource(R.drawable.profile_pic);
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

        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        i = new Intent(EditProfile.this, ProfilePage.class);
                        startActivity(i);
                        finish();
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
                if(adapter.getCount()>0){
                    for(int i = 0; i < adapter.getCount()-1; i++){
                        prioStr+=adapter.getPrio(i)+",";
                    }
                    prioStr+=adapter.getPrio(adapter.getCount()-1);
                }

                DatabaseHandler db = new DatabaseHandler(EditProfile.this);
                db.updateEmployer(new Employer(cN, prioStr, cD, 1));

                Intent i = new Intent(EditProfile.this, ProfilePage.class);
                startActivity(i);
                finish();
            }
        });



    }
    void showCustomDialog(){
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setCancelable(true);
        d.setContentView(R.layout.activity_upload);
        d.show();
        ImageView camera = (ImageView) findViewById(R.id.editPhotoClickable);

    }
//    @Override
//    public void onClick(View view) {
//        Log.d("We in","hi");
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            startCropActivity(uri);
//        } else if (requestCode == CROP_IMAGE_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Uri croppedUri = data.getData();
//                // Use the croppedUri as needed
//            }
//        }
//    }
//
//    private void startCropActivity(Uri uri) {
//        try {
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            cropIntent.setDataAndType(uri, "image/*");
//            cropIntent.putExtra("crop", "true");
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
//            cropIntent.putExtra("return-data", true);
//            startActivityForResult(cropIntent, CROP_IMAGE_REQUEST);
//        } catch (ActivityNotFoundException anfe) {
//            // Handle the error
//        }
//    }

}