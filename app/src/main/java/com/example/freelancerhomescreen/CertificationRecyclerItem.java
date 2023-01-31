package com.example.freelancerhomescreen;

public class CertificationRecyclerItem {
    private String name;
    private String link;
    private String endDate;
    private String skills;
    private String description;
    private int certID;

    public CertificationRecyclerItem(String name, String link, String endDate, String skills, String description) {

        this.name = name;
        this.link = link;
        this.endDate = endDate;
        this.skills = skills;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCertID() {
        return certID;
    }

    public void setCertID(int certID) {
        this.certID = certID;
    }
}
