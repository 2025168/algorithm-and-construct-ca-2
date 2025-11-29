package ca_2;

/**
 * Node for employee binary tree
 */
public class EmployeeTreeNode {
    Employee employee;        // Employee data
    EmployeeTreeNode left;    // Left child
    EmployeeTreeNode right;   // Right child

    public EmployeeTreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}