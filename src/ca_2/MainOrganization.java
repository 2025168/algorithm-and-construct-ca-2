/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2;

/**
 *
 * @author tamir
 */

import java.util.*;

public class MainOrganization {
    private Scanner scanner;
    
    // Basic menu enum
    enum MenuOption {
        SORT, SEARCH, ADD_RECORDS, CREATE_BINARY_TREE, DISPLAY_ALL, EXIT
    }
    
    public MainOrganization() {
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\n=== Bank Organization System Menu ===");
        int optionNumber = 1;
        for (MenuOption option : MenuOption.values()) {
            System.out.println(optionNumber + ". " + formatMenuOption(option));
            optionNumber++;
        }
        System.out.print("Choose an option: ");
    }
    
    private String formatMenuOption(MenuOption option) {
        switch (option) {
            case SORT: return "Sort Employee List";
            case SEARCH: return "Search Employee";
            case ADD_RECORDS: return "Add New Employee";
            case CREATE_BINARY_TREE: return "Create Employee Hierarchy Tree";
            case DISPLAY_ALL: return "Display All Employees";
            case EXIT: return "Exit System";
            default: return option.toString();
        }
    }
    
    public void run() {
        System.out.println("=== Bank Organization Management System ===");
        
        while (true) {
            try {
                displayMenu();
                String input = scanner.nextLine();
                
                if (input.isEmpty()) {
                    System.out.println("Please enter a valid option number.");
                    continue;
                }
                
                int choice = Integer.parseInt(input);
                MenuOption[] options = MenuOption.values();
                
                if (choice < 1 || choice > options.length) {
                    System.out.println("Invalid option! Please choose between 1 and " + options.length);
                    continue;
                }
                
                MenuOption selectedOption = options[choice - 1];
                
                switch (selectedOption) {
                    case EXIT:
                        System.out.println("Thank you for using Bank Organization System! Goodbye!");
                        return;
                    default:
                        System.out.println("Feature not implemented yet - selected: " + selectedOption);
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    public static void main(String[] args) {
        MainOrganization system = new MainOrganization();
        system.run();
    }
}
