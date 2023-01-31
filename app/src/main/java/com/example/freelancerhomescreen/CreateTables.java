package com.example.freelancerhomescreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CreateTables extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_CERTIFICATIONS = "Certifications";
//    private static final String TABLE_FREELANCERS = "Freelancers";
    private static final String TABLE_EMPLOYERS = "Employers";
//
    private static final String TABLE_PROJECTS = "Projects";
//    private static final String TABLE_SKILLS = "Skills";
//    private static final String TABLE_EXPERIENCE = "Experience";
//    private static final String TABLE_YOURSKILLS = "YourSkills";

    private static final String DATABASE_NAME = "Firehire.db";
    private static final String KEY_USERID = "userid";
    private static final String KEY_NAME = "name";
    private static final String KEY_LINK = "link";
    private static final String KEY_startDATE = "start_date";
    private static final String KEY_CERTID = "cert_id";
    private static final String KEY_EXPERIENCEID = "experienceID";
    private static final String KEY_COMPANYNAME = "company_name";

    private static final String KEY_DATE = "end_date";
    private static final String KEY_SKILLS = "skills";
    private static final String KEY_PRIORITIES = "priorities";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_KEY_SKILLS = "skills";
    private static final String TAG = "DatabaseHandler";

    private static final String KEY_DESCRIPTION = "description";
    SQLiteDatabase db;

    public CreateTables(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // use these strings to create table when doing onCreate
    private String CREATE_CERTIFICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CERTIFICATIONS + "("
            + KEY_CERTID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_LINK + " TEXT NOT NULL,"
            + KEY_DATE + " TEXT NOT NULL,"
            + KEY_SKILLS + " TEXT NOT NULL,"
            + KEY_DESCRIPTION + " TEXT NOT NULL"
            + ");";

    private String CREATE_FREELANCERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FREELANCERS + "("
            + "freelancerId INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_EMAIL + " TEXT NOT NULL,"
            + KEY_PASSWORD + " TEXT NOT NULL,"
            + KEY_DESCRIPTION + " TEXT NOT NULL,"
            + KEY_SKILLS + " TEXT NOT NULL"
            + ");";

    private String CREATE_EMPLOYERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_EMPLOYERS + "("
            + "employer_id INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_DESCRIPTION + " TEXT NOT NULL," +
            "priorities TEXT NOT NULL"
            + ");";

    private String CREATE_PROJECTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PROJECTS + "("
            + "projectID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_startDATE + " TEXT NOT NULL,"
            + KEY_DATE + " TEXT NOT NULL,"
            + KEY_LINK + " TEXT NOT NULL,"
            + KEY_SKILLS + " TEXT NOT NULL,"
            + KEY_DESCRIPTION + " TEXT NOT NULL,"
            + "freelancerID INTEGER NOT NULL,"
            + "FOREIGN KEY(freelancerID) REFERENCES Freelancers(freelancerId)"
            + ");";

    private String CREATE_SKILLS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SKILLS + "("
            + "skillsID INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NAME + " TEXT NOT NULL"
            + ");";

    private String CREATE_YOURSKILLS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_YOURSKILLS + "("
            + "skillsID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERID + "INTEGER NOT NULL,"
            + "skillsName TEXT NOT NULL "
            + ");";

    private String CREATE_EXPERIENCE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_EXPERIENCE + "("
            + KEY_EXPERIENCEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL, "
            + KEY_startDATE + " TEXT NOT NULL, "
            + KEY_DATE + " TEXT NOT NULL, "
            + KEY_DESCRIPTION + " TEXT NOT NULL, "
            + KEY_COMPANYNAME + " TEXT NOT NULL, "
            + "freelancerID INTEGER NOT NULL,"
            + "FOREIGN KEY(freelancerID) REFERENCES Freelancers(freelancerId)"
            + ");";


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Log.d("CreateTables onCreate", "Inside Now");
        Log.d("onCreate Experience", CREATE_EXPERIENCE_TABLE);
        db.execSQL(CREATE_CERTIFICATIONS_TABLE);
        db.execSQL(CREATE_FREELANCERS_TABLE);
        db.execSQL(CREATE_EMPLOYERS_TABLE);
        db.execSQL(CREATE_EXPERIENCE_TABLE);
        db.execSQL(CREATE_PROJECTS_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        //db.execSQL(CREATE_YOURSKILLS_TABLE);
        Log.d("Tables Created", "Created.");
        //insertCertificationData();
    }


    void openCertifications() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CERTIFICATIONS;
        Cursor c = db.rawQuery(query, null);

    }

    void openFreelancers() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FREELANCERS;
        Cursor c = db.rawQuery(query, null);

    }

    void openEmployers() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_EMPLOYERS;
        Cursor c = db.rawQuery(query, null);

    }

    void openExperience() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_EXPERIENCE;
        Cursor c = db.rawQuery(query, null);

    }

    void openProjects() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PROJECTS;
        Cursor c = db.rawQuery(query, null);
    }

    void openSkills() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SKILLS;
        Cursor c = db.rawQuery(query, null);
    }

    void openYourSkills() {
        Log.d(TAG, "openDatabase: called ");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_YOURSKILLS;
        Cursor c = db.rawQuery(query, null);
    }


    void addCertifications(Certification certification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, certification.getName()); // Contact Name
        values.put(KEY_DESCRIPTION, certification.getDescription()); // Contact Phone
        values.put(KEY_LINK, certification.getLink());
        values.put(KEY_SKILLS, certification.getSkills());
        values.put(KEY_DATE, certification.getEndDate());

        // Contact Phone
        // Inserting Row
        db.insert(TABLE_CERTIFICATIONS, null, values);
        String selectQuery = "SELECT * FROM Certifications";
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.d("Certifications Added", "Created.");

        //2nd argument is String containing nullColumnHack
        // Closing database connection
    }

    Certification getCertification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CERTIFICATIONS, new String[]{KEY_CERTID,
                        KEY_NAME, KEY_LINK, KEY_DATE, KEY_SKILLS, KEY_DESCRIPTION}, KEY_USERID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Certification c = new Certification(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                (cursor.getString(4)), cursor.getString(5));
        // return contact
        return c;
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

    //    KEY_CERTID,
//    KEY_NAME, KEY_LINK,KEY_DATE,KEY_SKILLS,KEY_DESCRIPTION
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

    public void deleteCertification(Certification contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CERTIFICATIONS, KEY_CERTID + " = ?",
                new String[]{String.valueOf(contact.getID())});

    }

    public int getCertificationCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CERTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }


    void addFreelancers(Freelancer freelancer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, freelancer.getName()); // Contact Name
        values.put(KEY_DESCRIPTION, freelancer.getDescription()); // Contact Phone
        values.put(KEY_EMAIL, freelancer.getEmail());
        values.put(KEY_PASSWORD, freelancer.getHashPassword());
        values.put(KEY_SKILLS, freelancer.getListOfSkills());

        // Contact Phone

        // Inserting Row
        db.insert(TABLE_FREELANCERS, null, values);
        //2nd argument is String containing nullColumnHack

        // Closing database connection
    }
//    "freelancerId INTEGER PRIMARY KEY AUTOINCREMENT ,"
//            + KEY_NAME + " TEXT NOT NULL,"
//            + KEY_EMAIL + " TEXT NOT NULL,"
//            + KEY_PASSWORD + " TEXT NOT NULL,"
//            + KEY_DESCRIPTION

    Freelancer getFreelancers(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CERTIFICATIONS, new String[]{"freelancerId",
                        KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_DESCRIPTION, KEY_SKILLS}, "freelancerId=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Freelancer f = new Freelancer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                (cursor.getString(4)), cursor.getString(5));
        // return contact
        return f;
    }

    public List<Freelancer> getAllFreelancer() {
        List<Freelancer> freelancerList = new ArrayList<Freelancer>();
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
                contact.setHashPassword(cursor.getString(2));
                contact.setDescription(cursor.getString(3));
                contact.setListOfSkills(cursor.getString(4));
                // Adding contact to list
                freelancerList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return freelancerList;
    }

    public int updateFreelancers(Freelancer f) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, f.getName());
        values.put(KEY_EMAIL, f.getEmail());
        values.put(KEY_PASSWORD, f.getHashPassword());
        values.put(KEY_DESCRIPTION, f.getDescription());
        values.put(KEY_SKILLS, f.getListOfSkills());
        // updating row
        return db.update(TABLE_FREELANCERS, values, "freelancerID" + " = ?",
                new String[]{String.valueOf(f.getId())});
    }

    // Deleting single contact
    public void deleteFreelancers(Freelancer f) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FREELANCERS, "freelancerID = ?",
                new String[]{String.valueOf(f.getId())});

    }

    public int getFreelancersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FREELANCERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    void addEmployers(Employer employer) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employer.getCompanyName());

        values.put(KEY_DESCRIPTION, employer.getDescription()); // Contact Phone
        values.put("priorities", employer.getPriorities());// Contact Name
        // Inserting Row
        db.insert(TABLE_EMPLOYERS, null, values);
        //2nd argument is String containing nullColumnHack

        // Closing database connection
    }

    Employer getEmployer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYERS, new String[]{"employer_id",
                        KEY_NAME, KEY_DESCRIPTION, "priorities"}, "employer_id" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employer e = new Employer(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)));

        // return contact
        return e;
    }

    public List<Employer> getAllEmployer() {
        List<Employer> contactList = new ArrayList<Employer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employer e = new Employer();
                e.setCompanyName(cursor.getString(0));
                e.setPriorities(cursor.getString(1));
                e.setDescription(cursor.getString(2));
                e.setEmployerID(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                contactList.add(e);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public int updateEmployer(Employer e) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("employer_id", e.getEmployerID());
        values.put(KEY_NAME, e.getCompanyName());
        values.put("priorities", e.getPriorities());
        values.put(KEY_DESCRIPTION, e.getDescription());


        // updating row
        return db.update(TABLE_EMPLOYERS, values, "employer_id = ?",
                new String[]{String.valueOf(e.getEmployerID())});
    }

    // Deleting single contact
    public void deleteEmployer(Employer e) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYERS, "employer_id = ?",
                new String[]{String.valueOf(e.getEmployerID())});

    }

    public int getEmployerCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
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

    //    CREATE TABLE " + TABLE_PROJECTS + "("
//            + KEY_NAME + " TEXT NOT NULL,"
//            + "based_in TEXT NOT NULL,"
//            + KEY_startDATE + " TEXT NOT NULL,"
//            + KEY_DATE + "TEXT NOT NULL,"
//            + KEY_LINK + "TEXT NOT NULL,"
//            + KEY_SKILLS + "TEXT NOT NULL,"
//            + KEY_DESCRIPTION + " TEXT NOT NULL,"
//            + "freelancerID INTEGER NOT NULL,"
    Projects getProjects(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYERS, new String[]{
                        "projectID", KEY_NAME, "based_in", KEY_startDATE, KEY_DATE, KEY_LINK, KEY_SKILLS, KEY_DESCRIPTION}, "employer_id" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Projects p = new Projects(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7))
        );

        // return contact
        return p;
    }

    public List<Projects> getAllProjects() {
        List<Projects> projectList = new ArrayList<Projects>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Projects p = new Projects();
                p.setProjectID(Integer.parseInt(cursor.getString(0)));
                p.setNameOfProject(cursor.getString(1));
                p.setStartDate(cursor.getString(2));
                p.setEndDate(cursor.getString(3));
                p.setLink(cursor.getString(4));
                p.setSkills(cursor.getString(5));
                p.setDescription(cursor.getString(6));
                p.setUserId(Integer.parseInt(cursor.getString(7)));
                // Adding contact to list
                projectList.add(p);
            } while (cursor.moveToNext());
        }

        // return contact list
        return projectList;
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

    // Deleting single contact
    public void deleteProjects(Projects p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECTS, "projectID = ?",
                new String[]{String.valueOf(p.getProjectID())});

    }

    public int getProjectsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PROJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    void addSkills(Skills skills) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, skills.getName()); // Contact Name
        db.insert(TABLE_SKILLS, null, values);

        //2nd argument is String containing nullColumnHack
        // Closing database connection
    }
// "skillsID INTEGER PRIMARY KEY AUTOINCREMENT ,"
//         + KEY_NAME + " TEXT NOT NULL"

    // code to get the single contact
    ArrayList<Skills> getSkills(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Skills> arrayListOfSkills = new ArrayList<Skills>();
        Cursor cursor = db.query(TABLE_FREELANCERS, new String[]{"skills"
                }, "freelancerId" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            Log.d("isNull", "False");
            cursor.moveToFirst();
        }

        String listOfSkills = cursor.getString(0);
        Log.d("Cursor", listOfSkills);
        String[] individualSkills = listOfSkills.split(",");
        for (int i = 0; i < individualSkills.length; i++) {
            String skills = individualSkills[i];
            Skills s = new Skills(skills);
            arrayListOfSkills.add(s);
        }
        // return contact
        return arrayListOfSkills;
    }

    // code to get all contacts in a list view
    public List<Skills> getAllSkills() {
        List<Skills> skillList = new ArrayList<Skills>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SKILLS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Skills s = new Skills();
                s.setName(cursor.getString(1));
                // Adding contact to list
                skillList.add(s);
            } while (cursor.moveToNext());
        }

        // return contact list
        return skillList;
    }

    // code to update the single contact
    public int updateSkills(String skills, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("skills", skills);

        // updating row
        return db.update(TABLE_FREELANCERS, values, "freelancerId = ?",
                new String[]{String.valueOf(id)});
    }

    // Deleting single contact
    public void deleteSkills(Skills skills) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SKILLS, "skills_id = ?",
                new String[]{String.valueOf(skills.getSkillsID())});

    }

    // Getting contacts Count
    public int getSkillsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SKILLS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


//"CREATE TABLE " + TABLE_YOURSKILLS + "("
//            + "skillsID INTEGER NOT NULL,"
//            + KEY_USERID + "INTEGER NOT NULL,"
//            + "skillsName TEXT NOT NULL "
//            + ");";

    void addYourSkills(YourSkills yourskills) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, yourskills.getSkillsName()); // Contact Name
        db.insert(TABLE_YOURSKILLS, null, values);
        db.execSQL("SELECT * FROM " + TABLE_YOURSKILLS);

        // Closing database connection
    }

    YourSkills getYourSkills(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_YOURSKILLS, new String[]{"skillsID",
                        KEY_USERID, "skillsName"}, "skillsID = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        YourSkills ys = new YourSkills(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return contact
        return ys;
    }

    // code to get all contacts in a list view
    public List<YourSkills> getAllYourSkills() {
        List<YourSkills> contactList = new ArrayList<YourSkills>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_YOURSKILLS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                YourSkills contact = new YourSkills();
                contact.setSkillsID(Integer.parseInt(cursor.getString(0)));
                contact.setUserID(Integer.parseInt(cursor.getString(1)));
                contact.setSkillsName(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int updateYourSkills(YourSkills contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, contact.getUserID());
        values.put("skills_id", contact.getSkillsID());
        values.put("skillsName", contact.getSkillsName());


        // updating row
        return db.update(TABLE_YOURSKILLS, values, "skills_id = ?",
                new String[]{String.valueOf(contact.getSkillsID())});
    }

    // Deleting single contact
    public void deleteYourSkills(YourSkills ys) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_YOURSKILLS, "skills_id = ?",
                new String[]{String.valueOf(ys.getSkillsID())});

    }

    // Getting contacts Count
    public int getYourSkillsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_YOURSKILLS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    void addExperience(Experience experience) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, experience.getName());
        values.put(KEY_startDATE, experience.getStartDate());
        values.put(KEY_DATE, experience.getEndDate());
        values.put(KEY_DESCRIPTION, experience.getDescription());
        values.put(KEY_COMPANYNAME, experience.getCompany());
        values.put("freelancerID",experience.getFreelancerID());
        // Contact Name
        db.insert(TABLE_EXPERIENCE, null, values);
        //2nd argument is String containing nullColumnHack

        // Closing database connection
    }


    public List<Experience> getAllExperience() {
        List<Experience> contactList = new ArrayList<Experience>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPERIENCE;

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

    public List<Experience> getExperienceById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Experience> contactList = new ArrayList<Experience>();
        Cursor cursor = db.query(TABLE_EXPERIENCE, new String[]{"experienceID",
                        KEY_NAME, KEY_startDATE, KEY_DATE, KEY_DESCRIPTION, "company_name"}, "freelancerID" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

       Experience e = new Experience(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
               Integer.parseInt(cursor.getString(6)));
        contactList.add(e);
        // return contact list
        return contactList;
    }

    Experience getExperience(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXPERIENCE, new String[]{"experienceID",
                        KEY_NAME, KEY_startDATE, KEY_DATE, KEY_DESCRIPTION, "company_name"}, "experience_id" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Experience e = new Experience(
                cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)));
        // return contact
        return e;
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

    // Deleting single contact
    public void deleteExperience(Experience e) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXPERIENCE, "experienceID = ?",
                new String[]{String.valueOf(e.getExperienceID())});

    }

    public int getExperienceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EXPERIENCE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICATIONS);
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FREELANCERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPERIENCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YOURSKILLS);
        Log.d("DB Status", "Database Created");

        // Create tables again
        onCreate(sqLiteDatabase);

    }
}
