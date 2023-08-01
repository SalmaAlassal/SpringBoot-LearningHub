package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity // Indicates that the class should be mapped to a table in the database.
public class Student {
    @Id // indicates that the field is a primary key.
    private int sid;
    private String sname;
    private String stech;
    public void setSid(int id) {
        this.sid = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSname(String name){
        this.sname = name;
    }

    public String getSname() {
        return sname;
    }

    public void setStech(String tech) {
        this.stech = tech;
    }

    public String getStech() {
        return stech;
    }

    @Override
    public String toString() {
        return "Student [sid=" + sid + ", sname=" + sname + ", stech=" + stech + "]";
    }
}