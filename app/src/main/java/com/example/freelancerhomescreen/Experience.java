package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Date;

public class Experience {

    private String name;
    private String startDate;
    private String endDate;
    private String description;
    private String company;
    private int experienceID;
    private int freelancerID;
    private boolean descVisibility;

    public Experience(String name, String startDate, String endDate, String description, String company,int freelancerID, int experienceID) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.company = company;
        this.freelancerID = freelancerID;
        this.experienceID = experienceID;

    }
    public Experience(String name, String startDate, String endDate, String description, String company,int freelancerID) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.company = company;
        this.freelancerID = freelancerID;

    }
    public Experience() {

    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    public String getName() {
        return name;
    }

    public int getExperienceID() {
        return experienceID;
    }

    public void setExperienceID(int experienceID) {
        this.experienceID = experienceID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}