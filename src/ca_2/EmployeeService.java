package ca_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles employee sorting   and searching operations
 */
public class EmployeeService {

    /**
     * Sorts employees by name using recursive merge sort
     */
    public List<Employee> mergeSort(List<Employee> employees) {
        if (employees.size() <= 1) {
            return employees; // Base case: already sorted
        }

        // Splitting into two halves and sort recursively
        int mid = employees.size() / 2;
        List<Employee> left = mergeSort(new ArrayList<>(employees.subList(0, mid)));
        List<Employee> right = mergeSort(new ArrayList<>(employees.subList(mid, employees.size())));

        return merge(left, right); // Merge sorted halves
    }

    /**
     * Merges two sorted lists innto one
     */
    private List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Compare and add smaller name first
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        // Add any remaining elements
        while (i < left.size())
            merged.add(left.get(i++));
        while (j < right.size())
            merged.add(right.get(j++));

        return merged;
    }

    /**
     * Finds employee by name using binary search
     */
    public Employee binarySearch(List<Employee> sortedEmployees, String name) {
        int left = 0;
        int right = sortedEmployees.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Employee midEmployee = sortedEmployees.get(mid);
            int comparison = midEmployee.getName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return midEmployee; // Found match
            } else if (comparison < 0) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return null; // Not found
    }
}