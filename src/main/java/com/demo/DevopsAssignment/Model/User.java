package com.demo.DevopsAssignment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid; // Changed from id to empid
    private String name;
    private String email;

    // Default constructor
    public User() {
    }

    // Constructor with fields
    public User(int empid, String name, String email) {
        this.empid = empid;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
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

    // toString method
    @Override
    public String toString() {
        return "User [empid=" + empid + ", name=" + name + ", email=" + email + "]";
    }
}
