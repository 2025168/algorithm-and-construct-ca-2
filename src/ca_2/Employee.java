package ca_2;

import java.util.*;

public class Employee {
    private String id;
    private String name;
    private Department department;
    private ManagerType managerType;
    private Date joinDate;

    public Employee(String name, Department department, ManagerType managerType) {
        this.id = "BANK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.department = department;
        this.managerType = managerType;
        this.joinDate = new Date();
    }

    public String getDetails() {
        return String.format("ID: %s | Name: %-20s | Dept: %-15s | Role: %-20s",
                id, name, department.getFullName(), managerType);
    }

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

}
