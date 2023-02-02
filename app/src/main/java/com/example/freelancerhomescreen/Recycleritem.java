package com.example.freelancerhomescreen;

public class Recycleritem {
    private int id;
    private String name;
    private String about;
    private String[] prios;
    private String pfp;

    public Recycleritem(int id,String name, String[] prios, String about, String pfp)
    {
        this.id = id;
        this.name = name;
        this.about = about;
        this.prios = prios;
        this.pfp = pfp;
    }

    public String getName()
    {
        return name;
    }
    public int getId(){return id;}
    public String getAbout()
    {
        return about;
    }
    public String[] getPrios()
    {
        return prios;
    }
    public String getPfp(){return pfp;}
}
