package com.example.freelancerhomescreen;

import static android.database.DatabaseUtils.dumpCursor;
import static android.database.DatabaseUtils.dumpCursorToString;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Firehire.db";
    private static final String KEY_USERID = "userid";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRIORITIES = "priorities";
    private static final String KEY_EMAIL = "email";
    private static final String TABLE_PROJECTS = "Projects";
    private static final String KEY_PASSWORD = "password";
    private static final String TABLE_EMPLOYERS = "Employers";
    private static final int DATABASE_VERSION = 2;
    private static final String KEY_DESCRIPTION = "description";
    private static final String TABLE_FREELANCERS = "Freelancers";
    private static final String KEY_YOUR_SKILLS = "freelancerSkills";
    private static final String KEY_UEN = "uen";
    private static final String KEY_PROJECTID = "projId";
    private static final String TABLE_EXPERIENCE = "Experience";
    private static final String KEY_LINK = "link";
    private static final String KEY_startDATE = "start_date";
    private static final String KEY_DATE = "end_date";
    private static final String KEY_SKILLS = "skills";
    private static final String TABLE_CERTIFICATIONS = "Certifications";
    private static final String KEY_COMPANYNAME = "company_name";
    private static final String KEY_CERTID = "cert_id";
    private static final String KEY_EXPERIENCEID = "experienceID";

    private static final String TABLE_USERS = "Users";
    private static final String KEY_ROLE = "role";
    private static final String KEY_IDENTITY = "identity_id";
    private static final String KEY_PFP = "profile_pic";

    private static final String TAG = "DatabaseHandler";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + TABLE_FREELANCERS);
        String CREATE_EMPLOYERS_TABLE = "CREATE TABLE " + TABLE_EMPLOYERS + "("
                + KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_EMAIL + " TEXT NOT NULL ,"
                + KEY_PASSWORD + " TEXT NOT NULL ,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_DESCRIPTION + " TEXT NOT NULL,"
                + KEY_PRIORITIES + " TEXT DEFAULT \"start,\" NOT NULL, "
                + KEY_UEN + " TEXT NOT NULL, "
                + KEY_PFP + " TEXT"
                + ");";
        String CREATE_FREELANCERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FREELANCERS + "("
                + "freelancerId INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_EMAIL + " TEXT NOT NULL,"
                + KEY_PASSWORD + " TEXT NOT NULL,"
                + KEY_DESCRIPTION + " TEXT NOT NULL, "
                + KEY_YOUR_SKILLS + " TEXT DEFAULT \"start,\" NOT NULL, "
                + KEY_PFP + " TEXT"
                + ");";
        String CREATE_PROJECTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PROJECTS + "("
                + KEY_PROJECTID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_startDATE + " TEXT NOT NULL,"
                + KEY_DATE + " TEXT NOT NULL,"
                + KEY_LINK + " TEXT NOT NULL,"
                + KEY_SKILLS + " TEXT NOT NULL,"
                + KEY_DESCRIPTION + " TEXT NOT NULL,"
                + "freelancerID INTEGER NOT NULL,"
                + "FOREIGN KEY(freelancerID) REFERENCES Freelancers(freelancerId)"
                + ");";
        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
                + KEY_USERID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ROLE + " INT, "
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_IDENTITY + " INT" + ")";
        String CREATE_CERTIFICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CERTIFICATIONS + "("
                + KEY_CERTID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_LINK + " TEXT NOT NULL,"
                + KEY_DATE + " TEXT NOT NULL,"
                + KEY_SKILLS + " TEXT NOT NULL,"
                + KEY_DESCRIPTION + " TEXT NOT NULL,"
                + "freelancerID INTEGER NOT NULL,"
                + "FOREIGN KEY(freelancerID) REFERENCES Freelancers(freelancerId)"
                + ");";

        String CREATE_EXPERIENCE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_EXPERIENCE + "("
                + KEY_EXPERIENCEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT NOT NULL, "
                + KEY_startDATE + " TEXT NOT NULL, "
                + KEY_DATE + " TEXT NOT NULL, "
                + KEY_DESCRIPTION + " TEXT NOT NULL, "
                + KEY_COMPANYNAME + " TEXT NOT NULL, "
                + "freelancerID INTEGER NOT NULL,"
                + "FOREIGN KEY(freelancerID) REFERENCES Freelancers(freelancerId)"
                + ");";


        db.execSQL(CREATE_EMPLOYERS_TABLE);
        db.execSQL(CREATE_FREELANCERS_TABLE);
        db.execSQL(CREATE_PROJECTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CERTIFICATIONS_TABLE);
        db.execSQL(CREATE_EXPERIENCE_TABLE);

    }

    void openUsers() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor c = db.rawQuery(query, null);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FREELANCERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Create tables again
        onCreate(db);
    }

    void addCertifications(Certification certification) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "addCertifications: RUNNING");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, certification.getName()); // Contact Name
        values.put(KEY_DESCRIPTION, certification.getDescription()); // Contact Phone
        values.put(KEY_LINK, certification.getLink());
        values.put(KEY_SKILLS, certification.getSkills());
        values.put(KEY_DATE, certification.getEndDate());
        Log.d(TAG, certification.getFreelancerID() + "");
        values.put("freelancerID", certification.getFreelancerID());
        // Contact Phone
        // Inserting Row
        db.insert(TABLE_CERTIFICATIONS, null, values);
        String selectQuery = "SELECT * FROM Certifications";
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.d("Certifications Added", "Created.");

        //2nd argument is String containing nullColumnHack
        // Closing database connection
    }

    void addExperience(Experience experience) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, experience.getName());
        values.put(KEY_startDATE, experience.getStartDate());
        values.put(KEY_DATE, experience.getEndDate());
        values.put(KEY_DESCRIPTION, experience.getDescription());
        values.put(KEY_COMPANYNAME, experience.getCompany());
        values.put("freelancerID", experience.getFreelancerID());
        // Contact Name
        db.insert(TABLE_EXPERIENCE, null, values);
        //2nd argument is String containing nullColumnHack

        // Closing database connection
    }
    public int updateExperience(Experience contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_startDATE, contact.getStartDate());
        values.put(KEY_DATE, contact.getEndDate());
        values.put(KEY_DESCRIPTION, contact.getDescription());
        values.put(KEY_COMPANYNAME, contact.getCompany());
        // updating row
        return db.update(TABLE_EXPERIENCE, values, "experienceID = ?",
                new String[]{String.valueOf(contact.getExperienceID())});
    }

    // code to add the new contact
    public void addContact(Employer contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, contact.getEmpEmail());
        values.put(KEY_PASSWORD, contact.getEmpPassword());// Contact Phone
        values.put(KEY_NAME, contact.getCompanyName()); // Contact Name
        values.put(KEY_DESCRIPTION, contact.getDescription()); // Contact Phone
        values.put(KEY_UEN, "start");


        // Inserting Row
        db.insert(TABLE_EMPLOYERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Employer getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("employerid", id+"");
        Cursor cursor = db.query(TABLE_EMPLOYERS, new String[]{KEY_NAME,
                        KEY_DESCRIPTION, KEY_PRIORITIES, KEY_PFP, KEY_USERID,KEY_EMAIL}, KEY_USERID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Log.d("konichiwa22", cursor.getString((3)));
        Employer contact = new Employer(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(Integer.parseInt(cursor.getString(4))), cursor.getString(5));
        // return contact
        return contact;
    }

    // code to get the single contact
    Freelancer getFreelancer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("contents DB", id+"");
        Cursor cursor = db.query(TABLE_FREELANCERS, new String[] { KEY_NAME,
                        KEY_DESCRIPTION },  "freelancerId =?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Freelancer contact = new Freelancer(cursor.getString(0), cursor.getString(1));
        return contact;
    }
    Freelancer getFreelancers(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FREELANCERS, new String[]{
                        KEY_NAME, KEY_DESCRIPTION, KEY_YOUR_SKILLS, KEY_PFP}, "freelancerId=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Freelancer f = new Freelancer(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        Log.d("konichiwa555",cursor.getString(3));
        // return contact
        return f;
    }

    public void addFreelancer(String name, String email, String password, String description, String skills, String profilePic) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_YOUR_SKILLS, skills);
        values.put(KEY_PFP, profilePic);
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        db.insert(TABLE_FREELANCERS, null, values);
        Log.i("Info", "User Added");
        db.close(); // Closing database connection
    }

    public ArrayList<Freelancer> getAllFreelancer() {
        ArrayList<Freelancer> freelancerList = new ArrayList<Freelancer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FREELANCERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Freelancer contact = new Freelancer();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setHashPassword(cursor.getString(3));
                contact.setDescription(cursor.getString(4));
                contact.setSkills(cursor.getString(5));
                // Adding contact to list
                freelancerList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return freelancerList;
    }

    public void addEmployer(String companyName, String priorities, String description, String empEmail, String empPassword, String uen, String profilePic) {
        ContentValues values = new ContentValues();
        Log.i("Info", "Setting values...");
        values.put(KEY_EMAIL, empEmail);
        values.put(KEY_PASSWORD, empPassword);
        values.put(KEY_NAME, companyName);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_PRIORITIES, priorities);
        values.put(KEY_UEN, uen);
        values.put(KEY_PFP, profilePic);
        Log.d("Inputting values:", empEmail + empPassword + companyName + description + priorities + uen);
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        db.insert(TABLE_EMPLOYERS, null, values);
        Log.i("Info", "User Added");
        db.close(); // Closing database connection
    }

    //    // code to get all contacts in a list view
    public ArrayList<Employer> getAllEmployers() {
        ArrayList<Employer> contactList = new ArrayList<Employer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String temp = dumpCursorToString(cursor);
        Log.d(TAG, temp);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employer contact = new Employer();
                contact.setEmployerID(cursor.getInt(0));
                contact.setEmpEmail(cursor.getString(1));
                contact.setCompanyName(cursor.getString(3));
                contact.setPriorities(cursor.getString(5));
                contact.setDescription(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateEmployer(Employer employer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employer.getCompanyName());
        values.put(KEY_DESCRIPTION, employer.getDescription());
        values.put(KEY_PRIORITIES, employer.getPriorities());
        Log.d("update debugging", employer.getEmployerID() + "");
        // updating row
        return db.update(TABLE_EMPLOYERS, values, KEY_USERID + " = ?",
                new String[]{String.valueOf(employer.getEmployerID())});
    }

    public int updateFreelancer(Freelancer freelancer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, freelancer.getName());
        values.put(KEY_DESCRIPTION, freelancer.getDescription());
        // updating row
        return db.update(TABLE_FREELANCERS, values, "freelancerId = ?",
                new String[]{String.valueOf(freelancer.getId())});
    }

    Projects get1Project(int projId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROJECTS, new String[]{KEY_NAME,
                        KEY_startDATE, KEY_DATE, KEY_LINK, KEY_SKILLS, KEY_DESCRIPTION, "freelancerID"}, KEY_PROJECTID + "=?",
                new String[]{String.valueOf(projId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Projects p1 = new Projects(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
        // return contact
        return p1;
    }

    public ArrayList<Projects> getAllProjects(int userid) {
        Log.d(TAG, "get all projects called");
        ArrayList<Projects> projectList = new ArrayList<Projects>();
        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS +" WHERE freelancerID = "+userid;
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS + " WHERE freelancerID = " + userid;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Projects proj = new Projects();
                proj.setProjectID(Integer.parseInt(cursor.getString(0)));
                proj.setNameOfProject(cursor.getString(1));
                proj.setStartDate(cursor.getString(2));
                proj.setEndDate(cursor.getString(3));
                proj.setLink(cursor.getString(4));
                proj.setSkills(cursor.getString(5));
                proj.setDescription(cursor.getString(6));
                // Adding contact to list
                projectList.add(proj);
            } while (cursor.moveToNext());
        }
        // return contact list
        return projectList;
    }
    public int getFreelancerUserIdByEmail(String email){
        String selectQuery = "SELECT freelancerId FROM " + TABLE_FREELANCERS + " WHERE " + KEY_EMAIL + " = '" + email +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int uid = Integer.parseInt(cursor.getString(0));

        cursor.close();
        db.close();
        return uid;
    }
    public int getEmployerUserIdByEmail(String email){
        String selectQuery = "SELECT userid FROM " + TABLE_EMPLOYERS + " WHERE " + KEY_EMAIL + " = '" + email +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int uid = Integer.parseInt(cursor.getString(0));

        cursor.close();
        db.close();
        return uid;
    }
    public Users checkUser(String email, String password) {
        Log.i("Info", "Trying to run query...");
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = '" + email + "' AND " + KEY_PASSWORD + " = '" + password + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean exists = cursor.moveToFirst();
        Users u;

        if (cursor.getCount() != 0) {
            Log.d("Record Exists", Boolean.toString(exists));

            Log.d("0", cursor.getString(0));
            Log.d("0", cursor.getString(1));
            Log.d("0", cursor.getString(2));
            Log.d("0", cursor.getString(3));
            Log.d("0", cursor.getString(4));
            Log.d("0", cursor.getString(5));

            u = new Users(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)));
        } else {
            u = null;
        }


        cursor.close();
        db.close();
        return u;
    }
    public int updateCertification(Certification certification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

//        values.put(KEY_CERTID, certification.getID());
        values.put(KEY_NAME, certification.getName());
        values.put(KEY_LINK, certification.getLink());
        values.put(KEY_DATE, certification.getEndDate());
        values.put(KEY_SKILLS, certification.getSkills());
        values.put(KEY_DESCRIPTION, certification.getDescription());

        // updating row
        return db.update(TABLE_CERTIFICATIONS, values, KEY_CERTID + " = ?",
                new String[]{String.valueOf(certification.getID())});
    }
    ArrayList<Certification> getCertification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Certification> certificationArrayList = new ArrayList<Certification>();
        Cursor cursor = db.query(TABLE_CERTIFICATIONS, new String[]{KEY_CERTID,
                        KEY_NAME, KEY_LINK, KEY_DATE, KEY_SKILLS, KEY_DESCRIPTION},  "freelancerID=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Certification contact = new Certification();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setLink(cursor.getString(2));
                contact.setEndDate(cursor.getString(3));
                contact.setSkills(cursor.getString(4));
                contact.setDescription(cursor.getString(5));
                // Adding contact to list
                certificationArrayList.add(contact);
            } while (cursor.moveToNext());
        }
        return certificationArrayList;

    }

    public void addUser(String uName, int uRole, String uEmail, String uPassword, int uIdentity) {
        ContentValues values = new ContentValues();
        Log.i("Info", "Setting values...");
        values.put(KEY_NAME, uName);
        values.put(KEY_ROLE, uRole);
        values.put(KEY_EMAIL, uEmail);
        values.put(KEY_PASSWORD, uPassword);
//        values.put(KEY_DESCRIPTION, uDescription);
        values.put(KEY_IDENTITY, uIdentity);
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        Log.i("Info", "User Added");
        db.close(); // Closing database connection
    }

    void addProjects(Projects projects) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(KEY_NAME, projects.getNameOfProject());
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, projects.getNameOfProject()); // Contact Name
        values.put(KEY_startDATE, projects.getStartDate()); // Contact Phone
        values.put(KEY_DATE, projects.getEndDate());
        values.put(KEY_LINK, projects.getLink());
        values.put(KEY_SKILLS, projects.getSkills());
        values.put(KEY_DESCRIPTION, projects.getDescription());
        values.put("freelancerID", projects.getUserId());
        db.insert(TABLE_PROJECTS, null, values);
        //db.execSQL("SELECT * FROM " + TABLE_PROJECTS);

        // Closing database connection
    }

    public int updateProjects(Projects p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getNameOfProject());
        values.put(KEY_startDATE, p.getStartDate());
        values.put(KEY_DATE, p.getEndDate());
        values.put(KEY_LINK, p.getLink());
        values.put(KEY_SKILLS, p.getSkills());
        values.put(KEY_DESCRIPTION, p.getDescription());
        values.put(KEY_USERID, p.getUserId());


        // updating row
        return db.update(TABLE_PROJECTS, values, "projectID = ?",
                new String[]{String.valueOf(p.getProjectID())});
    }

    public List<Certification> getAllCertifications() {
        List<Certification> contactList = new ArrayList<Certification>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CERTIFICATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Certification contact = new Certification();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setLink(cursor.getString(2));
                contact.setEndDate(cursor.getString(3));
                contact.setSkills(cursor.getString(4));
                contact.setDescription(cursor.getString(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public List<Experience> getAllExperience(int id) {
        List<Experience> contactList = new ArrayList<Experience>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPERIENCE+ " WHERE freelancerID = "+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Experience e = new Experience();
                e.setName((cursor.getString(1)));
                e.setStartDate(cursor.getString(2));
                e.setEndDate(cursor.getString(3));
                e.setDescription(cursor.getString(4));
                e.setCompany(cursor.getString(5));
                // Adding contact to list
                contactList.add(e);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    // code to update the single contact
    public int updateSkills(String skills, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("freelancerSkills", skills);

        // updating row
        return db.update(TABLE_FREELANCERS, values, "freelancerId = ?",
                new String[]{String.valueOf(id)});
    }
}
