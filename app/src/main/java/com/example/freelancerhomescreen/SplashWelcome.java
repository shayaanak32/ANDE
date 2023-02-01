package com.example.freelancerhomescreen;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.core.splashscreen.SplashScreen;

public class SplashWelcome extends AppCompatActivity {


    int runTimes = 0;
    CreateTables ct = new CreateTables(this);


    public static final String APPSTART_TAG = "AppStarts";
    public static final String FREELANCERDATA_TAG = "FreelancerData";
    public static final String fId = "freelancerId";
    public static final String fName = "freelancerName";
    public static final String fEmail = "freelancerEmail";
    public static String fDescription = "freelancerDescription";
    public static String fListOfSkills = "freelancerListOfSkills";
    public static final String Upw = "upwKey";

//    SharedPreferences freelancerPrefs = getSharedPreferences(FREELANCERDATA_TAG, Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = freelancerPrefs.edit();
//    int id = freelancerPrefs.getInt("ID", 0);
//    Freelancer f = ct.getFreelancers(id);
//    int freelancerID = f.getId();
//    String name = f.getName();
//    String email = f.getEmail();
//    String description = f.getDescription();
//    String listOfSkills = f.getListOfSkills();
//    editor.putString(fName,name);
//    editor.putString(fEmail,email);
//    editor.putString(fDescription,description);
//    editor.putString(fListOfSkills,listOfSkills);

    //    SharedPreferences prefs = getSharedPreferences(APPSTART_TAG, Context.MODE_PRIVATE);
//
//    SharedPreferences.Editor editor = prefs.edit();
//    int startTimes = prefs.getInt(APPSTART_TAG, 0);
    void insertCertData() {
        String endDate1 = "2021-12-21 10:20:05.123";
        String endDate2 = "2022-12-25 10:20:05.123";
        String endDate3 = "2019-07-08 10:20:05.123";
        String endDate4 = "2022-08-01 10:20:05.123";

        Certification c1 = new Certification("OCP 11 Java Programmer", "https://www.aws.training/certification", endDate1, "Java,JavaScript", "Description1");
        Certification c2 = new Certification("IBM Professional Data Science Certification", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate2, "Python,Java", "Description2");
        Certification c3 = new Certification("DataCamp Certified Data Professional", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate3, "Java", "Description3");
        Certification c4 = new Certification("Google Cloud Certified Data Scientist", "https://www.coursera.org/professional-certificates/ibm-data-science", endDate4, "Java", "Description4");
        ct.addCertifications(c1);
        ct.addCertifications(c2);
        ct.addCertifications(c3);
        ct.addCertifications(c4);
    }

    void insertExperienceData() {
//        String startDate1 = "2020-09-21 10:20:05.123";
//        String startDate2 = "2020-12-25 10:20:05.123";
//        String startDate3 = "2019-01-01 10:20:05.123";
//
//        String endDate1 = "2021-12-21 10:20:05.123";
//        String endDate2 = "2022-12-25 10:20:05.123";
//        String endDate3 = "2019-07-08 10:20:05.123";
        String startDate1 = "September 2020";
        String endDate1 = "December 2021";
        String startDate2 = "December 2020";
        String endDate2 = "December 2022";
        String startDate3 = "January 2019";
        String endDate3 = "July 2019";

        Experience e1 = new Experience("Java GUI Support Helper", startDate1, endDate1, "My experience at GOogle included" +
                "me helping out in the Java Support role, which helped teach me a lot of things!!", "Google",1);

        Experience e2 = new Experience("Microsoft DevOps Assistant", startDate2, endDate2, "My experience at Microsoft included" +
                "me helping out in the DevOps Support role, which helped teach me a lot of things with regards to testing, maintenance, etc.", "Microsoft",2);

        Experience e3 = new Experience("Apple Mobile Developer", startDate3, endDate3, "My experience at Apple included" +
                "me helping out in the Swift Support role, which helped teach me a lot on Mobile Apps, and how to optimise performance", "Apple",3);

        ct.addExperience(e1);
        ct.addExperience(e2);
        ct.addExperience(e3);
    }

    void insertEmployerData() {
        Employer e1 = new Employer("Prince Kharal Tech", "Singapore", "Description1");
        Employer e2 = new Employer("NCS", "Singapore", "Description2");
        Employer e3 = new Employer("A*STAR", "Singapore", "Description3");
        ct.addEmployers(e1);
        ct.addEmployers(e2);
        ct.addEmployers(e3);
    }

    void insertFreelancerData() {
        Freelancer f1 = new Freelancer("Rai Shayaan Kharal", "rskharal@icloud.com", "pgfjfisen21423", "I am a driven individual who specialises in object" +
                "oriented languages, with a knack for finishing work to the highest quality", "Java, JavaScript, C#");
        Freelancer f2 = new Freelancer("Jared Jaimon", "jumbobeatz@gmail.com", "gtfbgdfxvf", "I am a driven individual who is willing to put in effort with regards to" +
                "anything i am assigned. I am great with all languages but my best ones are JavaScript, and Java", "Java, JavaScript,Python");
        Freelancer f3 = new Freelancer("Mohd Syauqi", "deadingeneral@gmail.com", "gtfrefbgdfxvf", "I am a driven individual who is willing to put in effort with regards to" +
                "anything i am assigned. I am great with web security and JavaScript, and Java", "Scala, C#, Python");
        ct.addFreelancers(f1);
        ct.addFreelancers(f2);
        ct.addFreelancers(f3);
    }

    void insertProjectData() {
        String startDate1 = "2020-09-21 10:20:05.123";
        String startDate2 = "2020-12-25 10:20:05.123";
        String startDate3 = "2019-01-01 10:20:05.123";

        String endDate1 = "2021-12-21 10:20:05.123";
        String endDate2 = "2022-12-25 10:20:05.123";
        String endDate3 = "2019-07-08 10:20:05.123";
//                Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1","1",1);
//                Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1","2",2);
//                Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3",3);

        Projects p1 = new Projects("Student Management System", startDate1, endDate1, "https://github.com/shayaanak32/JADProjectRepo", "Java,J2EE", "Description1", 1);
        Projects p2 = new Projects("IceBreaker!", startDate2, endDate2, "https://github.com/VielenKaat/ADES-Repo", "Java,J2EE", "Description1", 2);
        Projects p3 = new Projects("Software Engineering Practice", startDate3, endDate3, "https://github.com/GGsendhelpGG/SEP_CA2", "Node.js,JavaScript", "Description3", 3);

        ct.addProjects(p1);
        ct.addProjects(p2);
        ct.addProjects(p3);
    }

    void insertSkillsData() {
        Skills s1 = new Skills("Java");
        Skills s2 = new Skills("JavaScript");
        Skills s3 = new Skills("Go");
        Skills s4 = new Skills("Python");
        Skills s5 = new Skills("C#");
        Skills s6 = new Skills("C++");
        Skills s7 = new Skills("Node.js");
        Skills s8 = new Skills("Flask");
        Skills s9 = new Skills("Scala");
        ct.addSkills(s1);
        ct.addSkills(s2);
        ct.addSkills(s3);
        ct.addSkills(s4);
        ct.addSkills(s5);
        ct.addSkills(s6);
        ct.addSkills(s7);
        ct.addSkills(s8);
        ct.addSkills(s9);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*handle the splash screen transition just before calling super.onCreate()*/
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (startTimes < 1) {
        Log.d("Inserting Data","inside onCreate now");
        insertCertData();
        insertEmployerData();
        insertExperienceData();
        insertFreelancerData();
        insertCertData();
        insertSkillsData();
        insertProjectData();
        runTimes++;
        //editor.putString(APPSTART_TAG, Integer.toString(runTimes));
//        }


        /*to keep the splash screen*/
    }
}