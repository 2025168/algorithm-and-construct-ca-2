package ca_2;

import java.util.*;

/**
 * Employee class stores all information about each employee
 */
public class Employee {
    // Employee data fields
    private String id;
    private String name;
    private Department department;
    private ManagerType managerType;
    private Date joinDate;
    private String email;
    private String gender;
    private double salary;
    private String jobTitle;
    private String company;

    /**
     * Constructor for manually added employees
     */
    public Employee(String name, Department department, ManagerType managerType, String email, String gender,
            Double salary, String jobTitle, String company) {
        this.id = "BANK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.department = department;
        this.managerType = managerType;
        this.joinDate = new Date();
        this.email = email;
        this.gender = gender;
        this.salary = salary;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    /**
     * Basic employee info for lists
     */
    public String getDetails() {
        return String.format("ID: %s | Name: %-20s | Dept: %-15s | Role: %-20s | Email: %s",
                id, name, department.getFullName(), managerType, email);
    }

    /**
     * Full employee details for search results
     */
    public String getFullDetails() {
        return String.format(
                "ID: %s\nName: %s\nEmail: %s\nGender: %s\nDepartment: %s\nPosition: %s\nJob Title: %s\nSalary: $%.2f\nCompany: %s",
                id, name, email, gender, department.getFullName(), managerType, jobTitle, salary, company);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }
}