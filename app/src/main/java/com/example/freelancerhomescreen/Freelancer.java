package com.example.freelancerhomescreen;

import android.util.Log;

public class Freelancer {

    private String name;
    private String email;
    private String hashPassword;
    private String description;
    private String listOfSkills;
    private int id;
    private String skills;
    private String[] individualSkill;
    private String profileImg;


    public Freelancer(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Freelancer(String name, String description,String skills, String profilePic){
        this.name = name;
        this.description = description;
        this.skills = skills;
        this.profileImg = profilePic;
    }
    public Freelancer(String name, String description){
        this.name=name;
        this.description = description;
    }
    public Freelancer(String name, String email, String hashPassword, String description,String listOfSkills) {
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
        this.description = description;
        this.listOfSkills = listOfSkills;
    }

    public Freelancer() {

    }

    public Freelancer(int id, String name, String email, String hashPassword, String description,String listOfSkills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
        this.description = description;
        this.listOfSkills = listOfSkills;
    }

    public Freelancer(int id, String name, String email, String hashPassword, String description,String listOfSkills, String pfp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
        this.description = description;
        this.listOfSkills = listOfSkills;
        this.profileImg = pfp;
    }

    public String getListOfSkills() {
        return listOfSkills;
    }

    public void setListOfSkills(String listOfSkills) {
        this.listOfSkills = listOfSkills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIndividualSkill(String skills) {
        individualSkill = skills.split(",");
        for(int i =0; i<individualSkill.length;i++){

        }
        return individualSkill;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSkills(){
        return skills;
    }
    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
