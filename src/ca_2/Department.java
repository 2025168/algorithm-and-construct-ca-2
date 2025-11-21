package ca_2;

public enum Department {
     IT("IT Development", "Technical systems and support"),
    SALES("Sales", "Handles client relationships and product sales"),
    HR("HR", "Employee hiring, training, and relations"),
    FINANCE("Finance", "Company budgeting and financial planning");

    private final String fullName;
    private final String description;

    Department(String fullName, String description) {
        this.fullName = fullName;
        this.description = description;
    }

    public String getFullName() { return fullName; }
    public String getDescription() { return description; }
}
