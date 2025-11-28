package ca_2;

public enum Department {
    IT("IT Development", "Technical systems and support"),
    SALES("Sales", "Handles client relationships and product sales"),
    HR("HR", "Employee hiring, training, and relations"),
    FINANCE("Finance", "Company budgeting and financial planning"),
    MARKETING("Marketing", "Brand promotion and market strategy"),
    ACCOUNTING("Accounting", "Financial records, payroll, and invoices"),
    OPERATIONS("Operations", "Daily workflow and business processes"),
    TECH_SUPPORT("Technical Support", "User and system troubleshooting"),
    CUSTOMER_SERVICE("Customer Service", "Customer assistance and issue resolution");

    private final String fullName;
    private final String description;

    Department(String fullName, String description) {
        this.fullName = fullName;
        this.description = description;
    }

    public String getFullName() { return fullName; }
    public String getDescription() { return description; }
}
