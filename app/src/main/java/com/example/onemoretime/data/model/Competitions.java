package com.example.onemoretime.data.model;

//object class used to hold data for one row in the comp table and to hold the keys for that table
//This is where the table is created
public class Competitions {

    public static final String TABLE = "Competitions";

    //KEYs are basically just column names
    public static final String KEY_CompId = "CompId";
    public static final String KEY_CompName= "CompName";
    public static final String KEY_CompStartDate = "CompDate";

    //all the values that are held in the comp table
    private String compId;
    private String compName;
    private String compDate;

    //getters and setters for each of the values
    public String getCompId() { return compId;}

    public void setCompId(String s) { compId = s;}

    public String getCompName() { return compName; }

    public void setCompName(String s) { compName = s;}

    public String getCompDate() { return compDate; }

    public void setCompDate(String s) { compDate = s;}
}
