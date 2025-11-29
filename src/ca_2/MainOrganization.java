/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2;

import java.util.*;
import java.io.*;

/**
 * Main organization system for bank employee management
 * This is the core class that runs the entire employee management system
 * 
 * @author tamir
 */
public class MainOrganization {
    // System components
    private Scanner scanner; // For reading user input
    private List<Employee> employees; // Stores all employees
    private EmployeeService employeeService; // Handles sorting and searching
    private List<Employee> newlyAddedEmployees; // Tracks recently added employees
    private EmployeeBinaryTree employeeTree; // Organizational hierarchy tree

    // Available menu options - using enum for type safety
    enum MenuOption {
        SORT, SEARCH, ADD_RECORDS, CREATE_BINARY_TREE, DISPLAY_ALL, EXIT
    }

    /**
     * Constructor - initializes all system components
     */
    public MainOrganization() {
        this.scanner = new Scanner(System.in);
        this.employees = new ArrayList<>();
        this.employeeService = new EmployeeService();
        this.employeeTree = new EmployeeBinaryTree();
        this.newlyAddedEmployees = new ArrayList<>();
        initializeSystem();
    }

    /**
     * Initialize system and load employee data from file
     * Keeps asking for filename until a valid file is found
     */
    private void initializeSystem() {
        System.out.println("=== Bank Organization Management System ===");
        while (true) {
            System.out.print("Please enter the filename to read (Applicants_Form.txt): ");
            String filename = scanner.nextLine().trim();

            // Use default filename if user just presses enter
            if (filename.isEmpty()) {
                filename = "Applicants_Form.txt";
            }

            // Try to load file, break loop if successful
            if (loadDataFromFile(filename)) {
                return;
            } else {
                System.out.println("\nFile '" + filename + "' not found! Try again.");
            }
        }
    }

    /**
     * Load employee data from CSV file
     * @return true if file was loaded successfully, false otherwise
     */
    private boolean loadDataFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return false; // File doesn't exist
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean firstLine = true; // Flag to skip header row

            // Read file line by line
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header row with column names
                    continue;
                }

                if (!line.trim().isEmpty()) {
                    String[] columns = line.split(",");
                    if (columns.length >= 9) {
                        // Extract data from CSV columns
                        String firstName = columns[0].trim();
                        String lastName = columns[1].trim();
                        String fullName = firstName + " " + lastName;
                        String gender = columns[2].trim();
                        String email = columns[3].trim();
                        double salary = Double.parseDouble(columns[4].trim());
                        String departmentStr = columns[5].trim();
                        String positionStr = columns[6].trim();
                        String jobTitle = columns[7].trim();
                        String company = columns[8].trim();

                        // Convert string values to enum types
                        Department department = findDepartmentByName(departmentStr);
                        ManagerType managerType = findManagerTypeByName(positionStr);

                        // Use random values if CSV data doesn't match our enums
                        if (department == null) {
                            department = Department.values()[new Random().nextInt(Department.values().length)];
                        }
                        if (managerType == null) {
                            managerType = ManagerType.values()[new Random().nextInt(ManagerType.values().length)];
                        }

                        // Create and add employee with all data from CSV
                        employees.add(new Employee(fullName, department, managerType, email, gender, salary, jobTitle,
                                company));
                    }
                }
            }
            reader.close();
            System.out.println("File '" + filename + "' read successfully! Loaded " + employees.size() + " employees.");
            return true;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to find department by name
     * @return Department enum if found, null otherwise
     */
    private Department findDepartmentByName(String name) {
        for (Department dept : Department.values()) {
            // Check both enum name and full name
            if (dept.getFullName().equalsIgnoreCase(name) || dept.name().equalsIgnoreCase(name)) {
                return dept;
            }
        }
        return null;
    }

    /**
     * Helper method to find manager type by name
     * @return ManagerType enum if found, null otherwise
     */
    private ManagerType findManagerTypeByName(String name) {
        for (ManagerType type : ManagerType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Sort employees using merge sort algorithm and display first 20 results
     * Uses recursive merge sort for guaranteed O(n log n) performance
     */
    public void sortAndDisplay() {
        System.out.println("\n=== Sorting Employees (Using Merge Sort) ===");

        if (employees.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }

        // Perform the sorting
        List<Employee> sortedEmployees = employeeService.mergeSort(employees);

        // Display first 20 sorted names
        System.out.println("\nFirst 20 sorted employee names:");
        System.out.println("================================");
        for (int i = 0; i < Math.min(20, sortedEmployees.size()); i++) {
            System.out.printf("%2d. %s\n", (i + 1), sortedEmployees.get(i).getName());
        }

        // Update the main list with sorted version
        employees = sortedEmployees;
    }

    /**
     * Search for employee by name using binary search algorithm
     * Requires sorted list for optimal O(log n) performance
     */
    public void searchEmployee() {
        System.out.println("\n=== Search Employee (Using Binary Search) ===");

        if (employees.isEmpty()) {
            System.out.println("No employees to search. Please load data first.");
            return;
        }

        // Ensure list is sorted for binary search to work correctly
        List<Employee> sortedEmployees = employeeService.mergeSort(employees);
        employees = sortedEmployees;

        System.out.print("Enter employee FULL name to search: ");
        String searchName = scanner.nextLine();

        // Perform the search
        Employee result = employeeService.binarySearch(employees, searchName);

        if (result != null) {
            System.out.println("\n Employee Found!");
            System.out.println("====================");
            System.out.println(result.getFullDetails()); // Show complete employee information
        } else {
            System.out.println("\n Employee '" + searchName + "' not found in the system.");
        }
    }

    /**
     * Add new employee to the system with interactive input validation
     * Only requires name, department and manager type - other fields get default
     * values
     */
    public void addNewEmployee() {
        System.out.println("\n=== Add New Bank Employee ===");

        // Get and validate employee name
        System.out.print("Enter employee full name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Invalid name! Please enter a valid name.");
            return;
        }

        // Display available manager types and get user selection
        System.out.println("\nAvailable Manager Types:");
        for (int i = 0; i < ManagerType.values().length; i++) {
            ManagerType type = ManagerType.values()[i];
            System.out.printf("%d. %s - %s (Level %d)\n",
                    i + 1, type, type.getDescription(), type.getHierarchyLevel());
        }
        System.out.print("Choose manager type (1-" + ManagerType.values().length + "): ");

        ManagerType managerType;
        try {
            int mgrChoice = Integer.parseInt(scanner.nextLine());
            managerType = ManagerType.values()[mgrChoice - 1];
        } catch (Exception e) {
            System.out.println("Invalid manager type selection!");
            return;
        }

        // Display available departments and get user selection
        System.out.println("\nAvailable Departments:");
        for (int i = 0; i < Department.values().length; i++) {
            Department dept = Department.values()[i];
            System.out.printf("%d. %s - %s\n", i + 1, dept.getFullName(), dept.getDescription());
        }
        System.out.print("Choose department (1-" + Department.values().length + "): ");

        Department department;
        try {
            int deptChoice = Integer.parseInt(scanner.nextLine());
            department = Department.values()[deptChoice - 1];
        } catch (Exception e) {
            System.out.println("Invalid department selection!");
            return;
        }

        // Create new employee with provided data and default values for other fields
        Employee newEmployee = new Employee(name, department, managerType, "default@company.com",
                "Not specified", 0.0, "Employee", "Bank Organization");
        employees.add(newEmployee);
        newlyAddedEmployees.add(newEmployee);

        System.out.println("\n Employee added successfully!");
        System.out.println(newEmployee.getDetails());
    }

    /**
     * Create binary tree hierarchy of employees using level-order insertion
     * Displays organizational structure and tree statistics
     */
    public void createBinaryTree() {
        System.out.println("\n=== Creating Employee Hierarchy Binary Tree ===");

        if (employees.isEmpty()) {
            System.out.println("No employees to add to tree.");
            return;
        }

        // Reset and build new tree
        employeeTree = new EmployeeBinaryTree();

        System.out.println("Inserting " + employees.size() + " employees using level-order insertion...");

        // Add all employees to the tree
        for (Employee employee : employees) {
            employeeTree.insertLevelOrder(employee);
        }

        // Display tree statistics
        System.out.println("\n Binary Tree Statistics:");
        System.out.println("Total nodes inserted: " + employeeTree.getNodeCount());
        System.out.println("Tree height: " + employeeTree.getHeight());

        // Show the complete organizational hierarchy
        employeeTree.displayLevelOrder();
    }

    /**
     * Display the main menu with all available options
     * Uses enum values to ensure all options are shown
     */
    public void displayMenu() {
        System.out.println("\n=== Bank Organization System Menu ===");
        int optionNumber = 1;
        for (MenuOption option : MenuOption.values()) {
            System.out.println(optionNumber + ". " + formatMenuOption(option));
            optionNumber++;
        }
        System.out.print("Choose an option: ");
    }

    /**
     * Convert enum values to user-friendly menu text
     * @return Formatted display text for the menu option
     */
    private String formatMenuOption(MenuOption option) {
        switch (option) {
            case SORT:
                return "Sort Employee List";
            case SEARCH:
                return "Search Employee";
            case ADD_RECORDS:
                return "Add New Employee";
            case CREATE_BINARY_TREE:
                return "Create Employee Hierarchy Tree";
            case DISPLAY_ALL:
                return "Display All Employees";
            case EXIT:
                return "Exit System";
            default:
                return option.toString();
        }
    }

    /**
     * Main program loop - handles user interaction and menu navigation
     * Runs continuously until user chooses to exit
     */
    public void run() {
        System.out.println("\n Bank Organization System Started!");
        System.out.println("Managing: " + employees.size() + " employees");

        while (true) {
            try {
                displayMenu();
                String input = scanner.nextLine();

                // Validate input is not empty
                if (input.isEmpty()) {
                    System.out.println("Please enter a valid option number.");
                    continue;
                }

                int choice = Integer.parseInt(input);
                MenuOption[] options = MenuOption.values();

                // Validate input range
                if (choice < 1 || choice > options.length) {
                    System.out.println("Invalid option! Please choose between 1 and " + options.length);
                    continue;
                }

                MenuOption selectedOption = options[choice - 1];

                // Execute the selected menu option
                switch (selectedOption) {
                    case SORT:
                        sortAndDisplay();
                        break;
                    case SEARCH:
                        searchEmployee();
                        break;
                    case ADD_RECORDS:
                        addNewEmployee();
                        break;
                    case CREATE_BINARY_TREE:
                        createBinaryTree();
                        break;
                    case DISPLAY_ALL:
                        displayAllEmployees();
                        break;
                    case EXIT:
                        System.out.println("\nThank you for using Bank Organization System!");
                        System.out.println("Bye! Have a nice day.");
                        return;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    /**
     * Display all employees grouped by department for better organization
     * Shows email and salary information for each employee
     */
    public void displayAllEmployees() {
        System.out.println("\n=== All Employees (" + employees.size() + ") ===");
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }

        // Group employees by department for organized display
        Map<Department, List<Employee>> employeesByDept = new HashMap<>();
        for (Employee emp : employees) {
            Department dept = emp.getDepartment();
            if (!employeesByDept.containsKey(dept)) {
                employeesByDept.put(dept, new ArrayList<>());
            }
            employeesByDept.get(dept).add(emp);
        }

        // Display employees department by department
        for (Department dept : employeesByDept.keySet()) {
            System.out.println("\n--- " + dept.getFullName() + " ---");
            for (Employee emp : employeesByDept.get(dept)) {
                // Show basic details plus email and salary
                System.out.printf("  %s | Email: %s | Salary: $%.2f\n",
                        emp.getDetails(), emp.getEmail(), emp.getSalary());
            }
        }
    }

    /**
     * Main method - program entry point
     * Creates and runs the organization system
     */
    public static void main(String[] args) {
        MainOrganization system = new MainOrganization();
        system.run();
    }
}