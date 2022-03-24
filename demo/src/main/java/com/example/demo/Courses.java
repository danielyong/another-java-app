package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long ID;
    private String courseName;

    public Courses(){}
    public Courses(String courseName){ this.courseName = courseName; }

    public long getID(){ return this.ID; }
    public void setID(long ID){ this.ID = ID; }

    public String getCourseName(){ return this.courseName; }
    public void setCourseName(String courseName){ this.courseName = courseName; }
}
