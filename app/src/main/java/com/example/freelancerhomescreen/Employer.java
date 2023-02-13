package com.example.freelancerhomescreen;

import java.util.ArrayList;

public class Employer {
    private int employerID;
    private String companyName;
    private String priorities;
private String description;
private String empEmail;
private String empPassword;
    private String uen;
private String profileImg;


    public Employer(String companyName, String description, String priorities) {
        this.companyName = companyName;
        this.description = description;
        this.priorities = priorities;
    }

    public Employer(String companyName, String priorities, String description,int employerID) {
        this.companyName = companyName;
        this.priorities = priorities;
        this.description = description;
        this.employerID = employerID;
    }

    public Employer(String companyName, String password, String description,String empEmail) {
        this.companyName = companyName;
        this.empPassword = password;
        this.description = description;
        this.empEmail = empEmail;
    }

    public Employer(String companyName, String priorities , String description, String password ,String empEmail, String UEN) {
        this.companyName = companyName;
        this.priorities = priorities;
        this.empPassword = password;
        this.description = description;
        this.empEmail = empEmail;
        this.uen = UEN;
    }


    public Employer(String companyName, String priorities, String description,int employerID, String empEmail) {
        this.companyName = companyName;
        this.priorities = priorities;
        this.description = description;
        this.employerID = employerID;
        this.empEmail = empEmail;
    }

    public Employer(String companyName, String priorities, String description,int employerID, String empEmail, String empPassword, String profileImg) {
        this.companyName = companyName;
        this.priorities = priorities;
        this.description = description;
        this.employerID = employerID;
        this.empEmail = empEmail;
        this.empPassword = empPassword;
        this.profileImg = profileImg;
    }

    public Employer(String companyName, String description, String priorities, String pfp,int employerID, String empEmail) {
        this.companyName = companyName;
        this.description = description;
        this.priorities = priorities;
        this.employerID = employerID;
        this.empEmail = empEmail;
        this.profileImg = pfp;
    }

    public Employer() {

    }
    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPriorities() {
        return this.priorities;
    }

    public void setPriorities(String priorities) {
        this.priorities = priorities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String[] getBrokenPriorities(String p){
        String[] indPrio = p.split(",");
        return  indPrio;
    }

    public String getUen() {
        return uen;
    }

    public void setUen(String uen) {
        this.uen = uen;
    }

}
