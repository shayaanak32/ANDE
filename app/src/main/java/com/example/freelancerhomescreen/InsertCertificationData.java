//package com.example.freelancerhomescreen;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//import android.database.sqlite.SQLiteDatabase;
//
//
//public class InsertCertificationData extends SQLiteOpenHelper {
//    private static final String KEY_NAME = "name";
//    private static final String KEY_LINK = "link";
//    private static final String KEY_DATE = "end_date";
//    private static final String KEY_SKILLS = "skills";
//    private static final String KEY_DESCRIPTION = "description";
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "FirehireDB";
//    private static final String TABLE_CERTIFICATIONS = "Certifications";
//    private static final String KEY_CERTID = "certification_id";
//    private static final String TABLE_FREELANCERS = "Freelancers";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_PASSWORD = "password";
//
//    public InsertCertificationData(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//
//    String CREATE_CERTIFICATIONS_TABLE = "CREATE TABLE " + TABLE_CERTIFICATIONS + "("
//            + KEY_CERTID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
//            + KEY_NAME + " TEXT NOT NULL,"
//            + KEY_LINK + " TEXT NOT NULL,"
//            + KEY_DATE + " TEXT NOT NULL,"
//            + KEY_SKILLS + " TEXT NOT NULL,"
//            + KEY_DESCRIPTION + " TEXT NOT NULL"
//            + ");";
//
//    void addCertifications(Certification certification) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, certification.getName()); // Contact Name
//        values.put(KEY_DESCRIPTION, certification.getDescription()); // Contact Phone
//        values.put(KEY_LINK, certification.getLink());
//        values.put(KEY_SKILLS, certification.getSkills());
//        values.put(KEY_DATE, certification.getEndDate());
//
//        // Contact Phone
//        // Inserting Row
//        db.insert(TABLE_CERTIFICATIONS, null, values);
//        Log.d("Certifications Added", "Created.");
//
//        //2nd argument is String containing nullColumnHack
//        db.close(); // Closing database connection
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        CreateTables db = new CreateTables(this);
//////        try {
////            String endDate1 = "2021-12-21 10:20:05.123";
////            String endDate2 = "2022-12-25 10:20:05.123";
////            String endDate3 = "2019-07-08 10:20:05.123";
////            String endDate4 = "2022-08-01 10:20:05.123";
//////            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//////            Date d1 = format.parse(endDate1);
//////            Date d2 = format.parse(endDate2);
//////            Date d3 = format.parse(endDate3);
//////            Date d4 = format.parse(endDate4);
////
//////            Certification c1 = new Certification("-NLl4BQkHkhZmfTI5Kuz", "OCP 11 Java Programmer", "https://www.aws.training/certification", endDate1, "Java,JavaScript", "Description1");
//////            Certification c2 = new Certification("-NLl4BQkHkhZmfTI5Kuz", "Singapore", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate2, "Python,Java", "Description2");
//////            Certification c3 = new Certification("-NLl4BR2gq9EwVWUHixE", "Singapore", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate3, "Java", "Description3");
//////            Certification c4 = new Certification("-NLl4BR2gq9EwVWUHixF", "IBM Professional Data Science Certification", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate4, "Java", "Description4");
////            Certification c1 = new Certification("OCP 11 Java Programmer","https://www.aws.training/certification",endDate1,"Java,JavaScript", "Description1");
////            Certification c2 = new Certification("IBM Professional Data Science Certification","https://www.coursera.org/professional-certificates/ibm-data-science",endDate2,"Python,Java", "Description2");
////            Certification c3 = new Certification("DataCamp Certified Data Professional","https://www.coursera.org/professional-certificates/ibm-data-science",endDate3,"Java", "Description3");
////            Certification c4 = new Certification("Google Cloud Certified Data Scientist","https://www.coursera.org/professional-certificates/ibm-data-science",endDate4,"Java", "Description4");
////            db.addCertifications(c1);
////            db.addCertifications(c2);
////            db.addCertifications(c3);
////            db.addCertifications(c4);
////        Log.d("hi: ", "certifications adding");
////
////        List<Certification> certifications = db.getAllCertifications();
//////
////        for (Certification cn : certifications) {
////            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Link: " +
////                    cn.getLink();
////            // Writing Contacts to log
////            Log.d("Name: ", log);
////        }
////
////
//////        } catch (ParseException e) {
//////            e.printStackTrace();
//////        }
////    }
////    void createTable() {
////        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
////
////        sqLiteDatabase.execSQL(CREATE_CERTIFICATIONS_TABLE);
////
////
////    }
//    void showTableData() {
//        Log.d("Trying to Show Data","Inside this function right now");
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT * FROM " + TABLE_CERTIFICATIONS;
//        Cursor c = db.rawQuery(query, null);
//
//    }
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_CERTIFICATIONS_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICATIONS);
//    }
//}
