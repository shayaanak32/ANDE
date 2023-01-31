package com.example.freelancerhomescreen;

public class Recycleritem {
    private String name;
    private String about;
    private String[] prios;

    public Recycleritem(String name, String[] prios, String about)
    {
        this.name = name;
        this.about = about;
        this.prios = prios;

    }

    public String getName()
    {
        return name;
    }

    public String getAbout()
    {
        return about;
    }
    public String[] getPrios()
    {
        return prios;
    }

}
