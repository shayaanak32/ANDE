package com.example.freelancerhomescreen;

public class ExperienceRecyclerItem {
    private String name;
    private String startDate;
    private String endDate;
    private String description;
    private Boolean descVisibility;
    private Boolean seeMoreVisiblity;
    private String companyName;
    public ExperienceRecyclerItem(String name, String startDate, String endDate, String description, String companyName) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.companyName = companyName;
    }
    public boolean getDescVisibility() {
        return descVisibility;
    }

    public void setDescVisibility(boolean descVisibility) {
        this.descVisibility = false;
    }

    public Boolean getSeeMoreVisiblity() {
        return seeMoreVisiblity;
    }

    public void setSeeMoreVisiblity(Boolean seeMoreVisiblity) {
        this.seeMoreVisiblity = true;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
