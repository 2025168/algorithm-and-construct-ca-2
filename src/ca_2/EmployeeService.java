package ca_2;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public List<Employee> mergeSort(List<Employee> employees) {
        if (employees.size() <= 1) {
            return employees;
        }

        int mid = employees.size() / 2;
        List<Employee> left = mergeSort(new ArrayList<>(employees.subList(0, mid)));
        List<Employee> right = mergeSort(new ArrayList<>(employees.subList(mid, employees.size())));

        return merge(left, right);
    }

    private List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size())
            merged.add(left.get(i++));
        while (j < right.size())
            merged.add(right.get(j++));

        return merged;
    }

    public Employee binarySearch(List<Employee> sortedEmployees, String name) {
        int left = 0;
        int right = sortedEmployees.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Employee midEmployee = sortedEmployees.get(mid);
            int comparison = midEmployee.getName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return midEmployee;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
