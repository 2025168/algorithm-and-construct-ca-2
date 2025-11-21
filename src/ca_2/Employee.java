package ca_2;

public class Employee {
    private String id;
    private String name;
    private Department department;
    private ManagerType managerType;

    public Employee(String name, Department department, ManagerType managerType) {
        this.id = "EMP" + System.currentTimeMillis(); // Simple ID for now
        this.name = name;
        this.department = department;
        this.managerType = managerType;
    }

    public String getDetails() {
        return String.format("ID: %s | Name: %s | Dept: %s | Role: %s",
                id, name, department, managerType);
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
