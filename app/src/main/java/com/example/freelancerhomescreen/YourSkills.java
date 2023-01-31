package com.example.freelancerhomescreen;

public class YourSkills {

    private int userID;
    private int skillsID;
    private String skillsName;

    public YourSkills(int userID, int skillsID, String skillsName) {
        this.userID = userID;
        this.skillsID = skillsID;
        this.skillsName = skillsName;
    }
    public YourSkills(){

    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSkillsID() {
        return skillsID;
    }

    public void setSkillsID(int skillsID) {
        this.skillsID = skillsID;
    }

    public String getSkillsName() {
        return skillsName;
    }

    public void setSkillsName(String skillsName) {
        this.skillsName = skillsName;
    }
}
